package com.poc.cdc.repository;

import org.springframework.data.repository.CrudRepository;

import com.poc.cdc.model.Invoice;

public interface InvoiceRepository extends CrudRepository<Invoice, Long>{

}
