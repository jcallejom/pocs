package com.poc.kafkapreformace.infrastructure.producer;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PerformanceProducer {
	/** The kafka template. */
	@Autowired
	private KafkaTemplate<String,String> kafkaTemplate;
	
	@Autowired
	private MeterRegistry metricRegistry;
	

	@Scheduled(fixedDelay = 2000000000, initialDelay = 5000)
	public void messageCountMetric() {
		log.info("##metricas disponibles producer##");
		List<Meter> avoidMetrics=metricRegistry.getMeters();
		for(Meter meter:avoidMetrics) {
			 log.info("Metric {} ",meter.getId());
			 }
//		 double count = 
//				 metricRegistry.get("kafka.producer.record.send.total").functionCounter().count();
//		 log.info("Count {} ",count);
	}
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param event the event
	 */
	
	public void produceASyn(String topic, String event) {
		kafkaTemplate.send(topic,event);
		
	}
	
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param key the key
	 * @param event the event
	 */
	
	public void produceASyn(String topic, String key, String event) {
		kafkaTemplate.send(topic,key,event);
	}
	public void produceASynCall(String topic, String event) {

		CompletableFuture <SendResult<String, String>>future=
				kafkaTemplate.send(topic,event);
					 
			   future.thenAccept(result -> {
					log.info("Message sent: time {}, topic {}, offset {}, partition {}, key: {}, data: {}"
							,result.getRecordMetadata().timestamp()
							,result.getRecordMetadata().topic()
							,result.getRecordMetadata().offset()
							,result.getRecordMetadata().partition()
							,result.getProducerRecord().key()
							,event);
				}).exceptionally(ex -> {
					log.error("Error sending message {}",ex.getMessage() );
						return null;
					});
				
	}		 
	public void produceASynCall(String topic, String key, String event) {

		CompletableFuture <SendResult<String, String>>future=
				kafkaTemplate.send(topic,key,event);
		   future.thenAccept(result -> {
				log.info("Message sent: time {}, topic {}, offset {}, partition {}, key: {}, data: {}"
						,result.getRecordMetadata().timestamp()
						,result.getRecordMetadata().topic()
						,result.getRecordMetadata().offset()
						,result.getRecordMetadata().partition()
						,result.getProducerRecord().key()
						,event);
			}).exceptionally(ex -> {
				log.error("Error sending message {}",ex.getMessage() );
					return null;
				});
		   /** forma equivalente con intefce**/
//			 future.whenCompleteAsync(new BiConsumer<SendResult<String,String>,Throwable>() {
//				@Override
//				public void accept(SendResult<String, String> result, Throwable e) {
//					if(e!=null)
//						log.error("Error sending message {}",e.getMessage() );
//					else
//					  if(Integer.parseInt(event)%100000==0)
//						log.info("Message sent: time {}, topic {}, offset {}, partition {}, key: {}, data: {}"
//								,result.getRecordMetadata().timestamp()
//								,result.getRecordMetadata().topic()
//								,result.getRecordMetadata().offset()
//								,result.getRecordMetadata().partition()
//								,result.getProducerRecord().key()
//								,event);
//					
//				}
//			 });
				 
	//	@SuppressWarnings("deprecation")		
	//deprecated since 6.0		 
//				ListenableFuture<SendResult<String, String>>future=
//						(ListenableFuture<SendResult<String, String>>) kafkaTemplate.send(topic,key,event);
////						kafkaTemplate.send("devs4j-topic","Sample message ");
////				 future.addCallback(new KafkaSendCallback<String, String>() {
//					 future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {	 
//						 		 
//						 public void onSuccess(SendResult<String, String>result) {
//							 log.info("Message sent event {}",event);
//						 }
//						
//						 public void onFailure(Throwable ex) {
//							 log.error("Error sending message ",ex);
//						 }
//						 public void onFailure(KafkaProducerException ex) {
//							 log.error("Error sending message ",ex);
//						 }
//						 
//					 });		
				
		
	}
	public void produceSyn(String topic, String event) {
		try {
			kafkaTemplate.send(topic,event).get();
			
		} catch (InterruptedException | ExecutionException e) {
			log.error("Error sending message {}",e.getMessage() );
		}
		
	}
	
	/**
	 * Produce.
	 *
	 * @param topic the topic
	 * @param key the key
	 * @param event the event
	 */
	
	public void produceSyn(String topic, String key, String event) {
		try {
			kafkaTemplate.send(topic,key,event).get(10,TimeUnit.SECONDS);
			
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			log.error("Error sending message {}",e.getMessage() );
		}
	}
	
	class BaseEvent {
		
	}
}
