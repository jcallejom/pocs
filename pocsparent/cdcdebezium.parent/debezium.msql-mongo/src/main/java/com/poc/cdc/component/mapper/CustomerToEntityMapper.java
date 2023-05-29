package com.poc.cdc.component.mapper;

import org.springframework.stereotype.Component;

import com.poc.cdc.model.dto.CustomerVo;
import com.poc.cdc.model.entity.CustomerEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerToEntityMapper {
  
  public CustomerEntity buildCustomer(CustomerVo customerVo) {
    return CustomerEntity
      .builder()
      ._id(customerVo.getId())
      .firstName(customerVo.getFirstName())
      .lastName(customerVo.getLastName())
      .email(customerVo.getEmail())
      .build();
  }

}