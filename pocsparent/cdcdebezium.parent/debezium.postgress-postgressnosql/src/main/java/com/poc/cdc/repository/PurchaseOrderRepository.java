package com.poc.cdc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.cdc.model.PurchaseOrder;

public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {

}
