package com.poc.cdc.event.consumer;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ManagentEventListener {
  
//		  private final IntegrationService integrationService;
//		  
//		  public EventListener (IntegrationService integrationService){
//		    this.integrationService = integrationService;
//		  }

//		  @KafkaListener(
//		    topics = {"outbox.Customer"},
//		    groupId = "${spring.kafka.consumer.group-id}"
//		  )
//		  public void listenproductEvents(Event event){
//		    if (Objects.nonNull(event.getPayload().getSource())){
//		      log.info("New product event consumed successfully");
//		      integrationService.processKafkaEvent(event);
//		    }else{
//		      log.error("The event has been discarded for not having the expected information");
//		    }
//		  }
//  		  @KafkaListener(
//		    topics = {"${kafka.topic.customer}"},
//		    groupId = "${spring.kafka.consumer.group-id}"
//		  )
//		  public void listencustomerEvents(Event event){
//		    if (Objects.nonNull(event.getPayload().getSource())){
//		      log.info("New customer event consumed successfully");
//		      integrationService.processKafkaEvent(event);
//		    }else{
//		      log.error("The event has been discarded for not having the expected information");
//		    }
//		  }

}