package com.poc.cdc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.cdc.model.entity.CustomerEntity;


public interface CustomerRepository extends CrudRepository<CustomerEntity, String>{}