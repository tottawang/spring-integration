package com.sample.conf;

import java.util.concurrent.Executor;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.util.CallerBlocksPolicy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.sample.service.Filter;
import com.sample.service.Handler;
import com.sample.service.Splitter;
import com.sample.service.Transformer;

@Configuration
public class ApplicationConfig extends ResourceConfig {

  @Autowired
  private Transformer transformer;

  @Autowired
  private Filter filter;

  @Autowired
  private Splitter splitter;

  public ApplicationConfig() {
    packages("com.sample.resources");
  }

  @Bean(name = "primaryWorkers")
  public Executor executors() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    int poolSize = 10;
    executor.setCorePoolSize(8);
    executor.setMaxPoolSize(poolSize);
    executor.setQueueCapacity(256);
    executor.setThreadNamePrefix("PrimaryWorkerThread-");
    executor.setRejectedExecutionHandler(new CallerBlocksPolicy(5000));
    executor.initialize();
    return executor;
  }

  @Bean
  @Qualifier("primaryWorkerChannel")
  public ExecutorChannel workerChannel() {
    return MessageChannels.executor(executors()).get();
  }

  @Bean
  @Qualifier("primaryHandler")
  public Handler primaryHandler() {
    return new Handler();
  }

  @Bean
  public IntegrationFlow primaryFlow() {
    return IntegrationFlows.from(workerChannel()).transform(transformer).split(splitter, null)
        .filter(filter).handle(m -> primaryHandler().getMessage(m)).get();
  }

}
