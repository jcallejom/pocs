package com.poc.cdc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.cdc.model.entity.ProductEntity;


public interface ProductRepository extends MongoRepository<ProductEntity, String>{}