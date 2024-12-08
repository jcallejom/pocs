package com.poc.cdc.event.listener;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.poc.cdc.event.InvoiceCreatedEvent;
import com.poc.cdc.model.Invoice;
import com.poc.cdc.repository.InvoiceRepository;

@Service
public class InvoiceCreatedEventListener implements ApplicationListener<InvoiceCreatedEvent> {

	private InvoiceRepository repository;
	
	public InvoiceCreatedEventListener(InvoiceRepository repository) {
		this.repository = repository;
	}

	@Override
	public void onApplicationEvent(InvoiceCreatedEvent event) {
		
		Invoice invoice=Invoice.builder()
				.orderId(Long.parseLong(event.getPayload().get("orderId").toString()))
				.invoiceDate (LocalDateTime.parse(event.getPayload().get("invoiceDate").toString()))
				.invoiceValue(Double.parseDouble(event.getPayload().get("invoiceValue").toString()))
				.build();
				
		this.repository.save(invoice);

	}

}
