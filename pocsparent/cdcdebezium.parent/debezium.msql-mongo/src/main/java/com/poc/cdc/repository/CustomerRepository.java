package com.poc.cdc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.poc.cdc.model.entity.CustomerEntity;


public interface CustomerRepository extends MongoRepository<CustomerEntity, String>{}