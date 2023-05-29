package com.poc.cdc.service;

import org.springframework.stereotype.Service;

import com.poc.cdc.component.mapper.CustomerToEntityMapper;
import com.poc.cdc.model.dto.CustomerVo;
import com.poc.cdc.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
  
  private final CustomerRepository customerRepository;
  private final CustomerToEntityMapper mapper;
  
  public CustomerServiceImpl(CustomerRepository customerRepository, CustomerToEntityMapper mapper) {
	
	this.customerRepository = customerRepository;
	this.mapper = mapper;
  }

@Override
  public void create(CustomerVo customer){
    customerRepository.save(mapper.buildCustomer(customer));
    //Todo  improtarscrip create view desfichero
    //mediante jdbc template insert des fichero
    //o buscar solucion mas nueva
    
  }
  
  @Override
  public void delete(String customerId){
    customerRepository.deleteById(customerId);
  }
 
}