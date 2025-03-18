package com.poc.cdc.infratructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.poc.cdc.infratructure.consumers.events.InvoiceCreatedE;
import com.poc.cdc.infratructure.consumers.model.Event;
import com.poc.cdc.infratructure.consumers.model.SourceEvent;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class ManagentEventListener implements IEventListener{
	
	@Autowired
	private IConsumerEventHandler handler;

//	@Override
//	@KafkaListener(topics="outbox.Customer",groupId = "${spring.kafka.consumer.group-id}")
//	public void listencustomerEvents(Event event, Map<String, Object> headers) {
//		
//		SourceEvent op= SourceEvent.fromId(headers.get("eventType").toString());
//		log.info("evento: {0}",headers.get("eventType").toString());
//		switch(op) {
//			case INVOICECREATED:
//				
//				handler.on(
//					InvoiceCreatedE.builder()
//					.id(headers.get("id").toString())
//					.orderId(Integer.parseInt(event.getEventPayload().getPayload().get("orderId").toString()))
//					.invoiceDate(event.getEventPayload().getPayload().get("invoiceDate").toString())
//					.invoiceValue(Float.parseFloat(event.getEventPayload().getPayload().get("invoiceValue").toString()))
//					.build()
//				);
//				break;
//				
//			default:
//				log.info("NOCASE_CUSTOMER");
//			break;
//				
//		}
//	}
//	@Override
////	@KafkaListener(topics="outbox.Order",groupId = "${spring.kafka.consumer.group-id}")
//	public void listenorderEvents(Event event, Map<String, Object> headers) {
//		SourceEvent op= SourceEvent.fromId(headers.get("eventType").toString());
//		log.info("evento: {0}",headers.get("eventType").toString());
//		switch(op) {
//			case ORDERCREATED:
//				break;
//		}
//	}
	@KafkaListener(topics="outbox.Customer",groupId = "${spring.kafka.consumer.group-id}")
	public void listencustomerEvents(Event event) {
		SourceEvent op= SourceEvent.fromId(event.getEventPayload().getHeaders().get("eventType").toString());
		log.info("evento: {0}",event.getEventPayload().getHeaders().get("eventType").toString());
		switch(op) {
			case INVOICECREATED:
				
				handler.on(
					InvoiceCreatedE.builder()
						.id(event.getEventPayload().getHeaders().get("id").toString())
						.orderId(Integer.parseInt(event.getEventPayload().getPayload().get("orderId").toString()))
						.invoiceDate(event.getEventPayload().getPayload().get("invoiceDate").toString())
						.invoiceValue(Float.parseFloat(event.getEventPayload().getPayload().get("invoiceValue").toString()))
						.build()
				);
				break;
				
			default:
				log.info("NOCASE_CUSTOMER");
			break;
				
		}
		
	}
//
//	@KafkaListener(topics="outbox.Order",groupId = "${spring.kafka.consumer.group-id}")
//	public void listenorderEvents(Event event) {
//		// TODO Auto-generated method stub
//		
//	}
  
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