package com.poc.cdc.component.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class KafkaCommonErrorHandler implements CommonErrorHandler {
  
  @Override
  public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer,
			MessageListenerContainer container, boolean batchListener) {
	  log.error("'handleOtherException' is not implemented by this handler",thrownException.getMessage());
  }
  
  @Override
  public void handleRecord(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer,
			MessageListenerContainer container) {
	  log.error("'handleRecord' is not implemented by this handler",thrownException.getMessage());
  }
  
  @Override
  public void handleRemaining(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer, MessageListenerContainer container) {
    log.error(thrownException.getMessage());
    
    String s = thrownException.getMessage().split("Error deserializing key/value for partition ")[1].split(". If needed, please seek past the record to continue consumption.")[0];
    String topics = s.substring(0, s.lastIndexOf('-'));
    int offset = Integer.parseInt(s.split("offset ")[1]);
    int partition = Integer.parseInt(s.substring(s.lastIndexOf('-') + 1).split(" at")[0]);
    
    TopicPartition topicPartition = new TopicPartition(topics, partition);
    
    log.error("Skipping " + topics + " - " + partition + " offset " + offset);
    // ignore event
    consumer.seek(topicPartition, offset + 1);
    
  }
  
}