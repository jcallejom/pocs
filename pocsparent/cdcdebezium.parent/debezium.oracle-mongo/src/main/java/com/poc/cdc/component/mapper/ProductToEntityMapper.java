package com.poc.cdc.component.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.poc.cdc.model.dto.Product;
import com.poc.cdc.model.entity.ProductEntity;

@Slf4j
@Component
public class ProductToEntityMapper {
  
  public ProductEntity buildProduct(Product product) {
    return ProductEntity
      .builder()
      ._id(product.getId())
      .name(product.getName())
      .description(product.getDescription())
      .weight(product.getWeight())
      .build();
  }

}