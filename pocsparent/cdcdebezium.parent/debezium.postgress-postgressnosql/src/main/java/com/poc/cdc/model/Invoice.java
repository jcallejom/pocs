package com.poc.cdc.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_ids")
	@SequenceGenerator(name = "invoice_ids", sequenceName = "seq_invoice", allocationSize = 50)
	private Long id;
	 
	private Long orderId;

	private LocalDateTime invoiceDate;
	
	private Double invoiceValue;
}
