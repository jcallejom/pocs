package com.poc.cdc.service;

import com.poc.cdc.model.dto.CustomerVo;

public interface CustomerService {
  
  void create(CustomerVo customerVo);
  
  void delete(String customerId);
 
}