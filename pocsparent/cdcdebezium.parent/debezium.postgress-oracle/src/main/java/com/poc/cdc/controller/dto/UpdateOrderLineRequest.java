package com.poc.cdc.controller.dto;

import com.poc.cdc.model.OrderLineStatus;

import lombok.Data;

@Data
public class UpdateOrderLineRequest {

	private OrderLineStatus newStatus;
}
