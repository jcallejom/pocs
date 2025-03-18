package com.poc.kafkapreformace.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.MicrometerProducerListener;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableScheduling//permite programar algo que se ejecute cada cierto tiempo
@Configuration
public class KafkaConfiguration {
//	@Autowired
//	private MeterRegistry meterRegistry;
	
	@Scheduled(fixedDelay = 100000000, initialDelay = 5000)//cada segundo se imprime el log
	public void print() {
		log.info("##metricas disponibles##");
		List<Meter> avoidMetrics=meterRegistry().getMeters();
		for(Meter meter:avoidMetrics) {
			 log.info("Metric {} ",meter.getId());
			 }	
	}
//	@Scheduled(fixedDelay = 2000, initialDelay = 500)
//	 public void messageCountMetric() {   
//		 double count = 
//		 meterRegistry().get("kafka.producer.record.send.total").functionCounter().count();
//		 log.info("Count {} ",count);
//	 }
	  /**Metricas prometheus en el produccer**/
	  @Bean
	  public MeterRegistry meterRegistry() {
		  PrometheusMeterRegistry prometheusMeterRegistry=  new PrometheusMeterRegistry (PrometheusConfig.DEFAULT);
		  return prometheusMeterRegistry;
	  }
	/*Producer*/
	  
	private Map<String, Object> producerProps() {
		 Map<String, Object> props=new HashMap<>();
		 props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
		 "localhost:9092");
		 props.put(ProducerConfig.RETRIES_CONFIG, 0);
		 props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		 //props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		 //props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		 props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		 props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);
		 return props;
		 }
	 @Bean
	 public KafkaTemplate<String, String> kafkaTemplate() {
		 Map<String, Object>senderProps= producerProps();
		 DefaultKafkaProducerFactory<String, String> producerFactory= new  DefaultKafkaProducerFactory<String, String>(senderProps);
		 /*Añadir listener de mertricas prometeus*/
		 producerFactory.addListener(new MicrometerProducerListener<String,String> (meterRegistry()));
		 KafkaTemplate<String, String> template=new KafkaTemplate<>(producerFactory);
		 return template;
	 }

/*Consumer*/
	 
	 private Map<String, Object> consumerProps() {
		 Map<String, Object>props=new HashMap<>();
		 props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
		 props.put(ConsumerConfig.GROUP_ID_CONFIG, "prototypeConsumer");
		 props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); 
		 props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		 props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		 props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		 props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		 
		 return props;
	  }

	  @Bean
	  public ConsumerFactory<String, String> consumerFactory() {

	    return new DefaultKafkaConsumerFactory<>(consumerProps());
		//  return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(Event.class));
	
	  }
	
	  @Bean
	  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
	    ConcurrentKafkaListenerContainerFactory<String, String> listenerContainerFactory = new ConcurrentKafkaListenerContainerFactory<>();
	    listenerContainerFactory.setConsumerFactory(consumerFactory());
	    listenerContainerFactory.setBatchListener(true);//añade batch al consumer
	    listenerContainerFactory.setConcurrency(5);//añade hilos
	//    factory.setCommonErrorHandler(new KafkaCommonErrorHandler());
	    /*NOTE ErrorHandler @deprecated in favor of {@link #setCommonErrorHandler(CommonErrorHandler) */
	//    factory.setErrorHandler(new KafkaErrorHandler());
	    
	    return listenerContainerFactory;
	  }

	
}