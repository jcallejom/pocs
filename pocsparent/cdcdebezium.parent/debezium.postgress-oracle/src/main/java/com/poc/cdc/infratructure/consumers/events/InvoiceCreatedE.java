package com.poc.cdc.infratructure.consumers.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class InvoiceCreatedE {
	
	private String id;
	private int orderId;
	private String invoiceDate;
	private float invoiceValue;
}
