package com.poc.cdc.infratructure.consumers;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.poc.cdc.infratructure.consumers.model.Event;


public interface IEventListener {
	
//	 public void listencustomerEvents(@Payload Event event ,@Headers Map<String, Object> headers);
	 
//	 public void listenorderEvents(@Payload Event event,@Headers Map<String, Object> headers);
	 
	 public void listencustomerEvents(Event event );
//	 
//	 public void listenorderEvents(Event event);
//  
//		  private final IntegrationService integrationService;
//		  
//		  public IEventListener (IntegrationService integrationService){
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