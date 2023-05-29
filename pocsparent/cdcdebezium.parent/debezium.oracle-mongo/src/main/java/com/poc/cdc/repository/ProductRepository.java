package com.poc.cdc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.cdc.model.entity.ProductEntity;


public interface ProductRepository extends CrudRepository<ProductEntity, String>{}