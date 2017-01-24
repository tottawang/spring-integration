package com.sample.resources;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("/api")
public class RestResource {

  @Autowired
  @Qualifier("primaryWorkerChannel")
  ExecutorChannel primaryWorkerChannel;

  /**
   * 100 will be transformed to a list with 5 incremental values starting from itself, filter out
   * odd numbers and get printed.
   * 
   * @return
   */
  @GET
  @Path("spring-integration")
  public boolean runSpringIntegration() {
    Integer payload = Integer.valueOf(100);
    Map<String, Object> headers = new HashMap<String, Object>();
    return primaryWorkerChannel.send(new GenericMessage<>(payload, headers));
  }
}
