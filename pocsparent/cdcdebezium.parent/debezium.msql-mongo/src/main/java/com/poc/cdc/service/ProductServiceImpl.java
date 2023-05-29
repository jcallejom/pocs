package com.poc.cdc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.poc.cdc.component.mapper.ProductToEntityMapper;
import com.poc.cdc.model.dto.Product;
import com.poc.cdc.repository.ProductRepository;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
  
  private final ProductRepository productRepository;
  private final ProductToEntityMapper mapper;
  
  public ProductServiceImpl(ProductRepository productRepository, ProductToEntityMapper mapper){
    this.productRepository = productRepository;
    this.mapper = mapper;
  }
  
  @Override
  public void create(Product product){
    productRepository.save(mapper.buildProduct(product));
    //Todo  improtarscrip create view desfichero
    //mediante jdbc template insert des fichero
    //o buscar solucion mas nueva
    
  }
  
  @Override
  public void delete(String productId){
    productRepository.deleteById(productId);
  }
 
}