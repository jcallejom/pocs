package com.poc.cdc.controller.dto;

import java.math.BigDecimal;

import com.poc.cdc.model.OrderLineStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLineDto {

	private Long id;
	private String item;
	private int quantity;
	private BigDecimal totalPrice;
	private OrderLineStatus status;
}
