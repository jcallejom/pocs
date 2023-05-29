package com.poc.cdc.service;

import com.poc.cdc.model.event.EventPayload;

public interface ManagementService {
  
  void manageProduct(EventPayload payload);
  
  void manageCustomer(EventPayload payload);
  
}
