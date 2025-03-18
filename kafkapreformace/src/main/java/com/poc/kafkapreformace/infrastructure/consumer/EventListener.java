package com.poc.kafkapreformace.infrastructure.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventListener {
	
//	  @KafkaListener(topics="${spring.kafka.template.default-topic}",groupId = "${spring.kafka.consumer.group-id}")
//	  public void listen(String message){
//		  if(Integer.parseInt(message)%100000==0)
//	       log.info("Received Messasge in group : {}",message); 
//	  }

//	  @KafkaListener(topics="${spring.kafka.template.default-topic}",
//			  groupId = "${spring.kafka.consumer.group-id}",
//			  containerFactory  ="kafkaListenerContainerFactory",
//			  properties = {"max.poll.interval.ms:10000",//4 sec
//		  		"max.poll.records:50"})//10 rgis 
//	  public void listenBatch(List<String> messages){
//		  log.info("Start Reading Messages"); 
//		  for(String message: messages) {
////			  if(Integer.parseInt(message)%100000==0)
//				  log.info("Recived Message in group : {}",message); 
//		  }
//		  log.info("Batch complete"); 
//	  }
	  
	  /**Listener que permite traza 
	   * 
	   */
	  @KafkaListener(topics="${spring.kafka.template.default-topic}",
			  groupId = "${spring.kafka.consumer.group-id}",
			  containerFactory  ="kafkaListenerContainerFactory",
			  properties = {"max.poll.interval.ms:10000",//4 sec
		  		"max.poll.records:50"})//10 rgis 
	  public void listenBatchRecord(List<ConsumerRecord<String,String>> messages){
		  log.info("Start Reading Messages"); 
		  for(ConsumerRecord<String,String> message: messages) {
//			  if(Integer.parseInt(message.value())%100000==0)
				log.info("Recived Message :  topic {}, offset {}, partition {}, key: {}, value: {}",
						message.topic(),
						message.offset(),
						message.partition(),
						message.key(),
						message.value()
						); 	

		  }
		  log.info("Batch complete"); 
	  }

}