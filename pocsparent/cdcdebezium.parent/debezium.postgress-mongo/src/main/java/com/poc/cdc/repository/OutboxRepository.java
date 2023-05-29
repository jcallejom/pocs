package com.poc.cdc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.cdc.model.Outbox;

public interface OutboxRepository extends CrudRepository<Outbox, String>{

}
