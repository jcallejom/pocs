package com.poc.cdc.infratructure.consumers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, String>{

}
