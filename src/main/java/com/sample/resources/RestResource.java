package com.sample.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.stereotype.Component;

import com.sample.domain.DomainObject;
import com.sample.service.Aggregator;
import com.sample.service.Cleaner;
import com.sample.service.Filter;
import com.sample.service.Handler;
import com.sample.service.Splitter;
import com.sample.service.Transformer;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class RestResource {

  @Autowired
  private Transformer transformer;

  @Autowired
  private Splitter splitter;

  @Autowired
  private Handler handler;

  @Autowired
  private Cleaner cleaner;

  @Autowired
  private Aggregator aggregator;

  @Autowired
  private Filter filter;

  @Autowired
  @Qualifier("primaryWorkerChannel")
  ExecutorChannel primaryWorkerChannel;

  /**
   * "test1" transformed to a list with values "test1", "test2" and "test3", and then filter out
   * "test2" and print "test1" and "test3" finally.
   * 
   * @return
   */
  @GET
  @Path("spring-integration")
  public boolean runSpringIntegration() {
    IntegrationFlows.from(primaryWorkerChannel).transform(transformer).split(splitter, null)
        .filter(filter).handle((m, h) -> {
          // http://stackoverflow.com/questions/34929476/spring-dsl-sending-error-message-to-jms-queue-get-an-error-one-way-messageha
          handler.getMessage((DomainObject) m);
          return m;
        }).aggregate(a -> a.processor(aggregator), null).handle(m -> cleaner.cleanup(m)).get();
    return false;
  }
}
