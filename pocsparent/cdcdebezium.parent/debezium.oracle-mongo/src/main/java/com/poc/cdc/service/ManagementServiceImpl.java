package com.poc.cdc.service;

import static com.poc.cdc.utils.Consts.UNDEFINED_OPERATION_ERROR_MESSAGE;

import org.springframework.stereotype.Service;

import com.poc.cdc.enums.SourceDatabaseOperation;
import com.poc.cdc.model.dto.CustomerVo;
import com.poc.cdc.model.dto.Product;
import com.poc.cdc.model.event.EventPayload;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class ManagementServiceImpl implements ManagementService {
  
  private final ProductService productService;
  private final CustomerService customerService;
  
  public ManagementServiceImpl(ProductService productService, CustomerService customerService){
    this.productService = productService;
	this.customerService = customerService;
  }
  
  @Override
  public void manageProduct(EventPayload payload){
    SourceDatabaseOperation operation = SourceDatabaseOperation.fromId(payload.getOp());
    switch (operation){
      case CREATE:
      case UPDATE:
        Product product = Product
          .builder()
          .id(payload.getAfter().get("id").toString())
          .name(payload.getAfter().get("name").toString())
          .description(payload.getAfter().get("description").toString())
          .weight(Double.parseDouble(payload.getAfter().get("weight").toString()))
          .build();
        productService.create(product);
        break;
      case DELETE:
        String productId = payload.getAfter().get("id").toString();
        productService.delete(productId);
        break;
      default:
        log.error(UNDEFINED_OPERATION_ERROR_MESSAGE);
    }
  }

@Override
public void manageCustomer(EventPayload payload) {
	SourceDatabaseOperation operation = SourceDatabaseOperation.fromId(payload.getOp());
    switch (operation){
      case CREATE:
      case UPDATE:
    	  CustomerVo customerVo = CustomerVo
          .builder()
          .id(payload.getAfter().get("id").toString())
          .firstName(payload.getAfter().get("firstName").toString())
          .lastName(payload.getAfter().get("lastName").toString())
          .email(payload.getAfter().get("email").toString())
          .build();
    	  customerService.create(customerVo);
        break;
      case DELETE:
        String customerId = payload.getAfter().get("id").toString();
        customerService.delete(customerId);
        break;
      default:
        log.error(UNDEFINED_OPERATION_ERROR_MESSAGE);
    }
	
}
  
}
