package com.poc.cdc.infratructure.consumers.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Invoice {
	
	@Id
	private String id;
	private int orderId;
	private String invoiceDate;
	private float invoiceValue;
}
