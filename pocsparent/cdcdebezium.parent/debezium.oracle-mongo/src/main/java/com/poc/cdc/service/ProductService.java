package com.poc.cdc.service;

import com.poc.cdc.model.dto.Product;

public interface ProductService {
  
  void create(Product product);
  
  void delete(String productId);
 
}