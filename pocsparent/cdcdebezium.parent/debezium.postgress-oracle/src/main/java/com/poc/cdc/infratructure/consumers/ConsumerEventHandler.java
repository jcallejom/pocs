package com.poc.cdc.infratructure.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.cdc.infratructure.consumers.events.InvoiceCreatedE;
import com.poc.cdc.infratructure.consumers.repository.Invoice;
import com.poc.cdc.infratructure.consumers.repository.InvoiceRepository;

	

/**
 * The Interface EventHandler.
 */
@Service
public class ConsumerEventHandler implements IConsumerEventHandler {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	/**
	 * On.
	 *
	 * @param event the event
	 */
	public void on(InvoiceCreatedE event) {
		invoiceRepository.save(
			Invoice.builder()
			.id(event.getId())
			.orderId(event.getOrderId())
			.invoiceDate(event.getInvoiceDate())
			.invoiceValue(event.getInvoiceValue())
			.build()
		);
	}

	
}
