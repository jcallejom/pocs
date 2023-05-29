package com.poc.cdc.service;

import com.poc.cdc.model.event.Event;

public interface IntegrationService {

  void processKafkaEvent(Event event);
  
}
