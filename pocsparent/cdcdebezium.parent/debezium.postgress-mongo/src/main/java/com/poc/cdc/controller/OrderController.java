package com.poc.cdc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.cdc.controller.dto.CreateOrderRequest;
import com.poc.cdc.controller.dto.OrderOperationResponse;
import com.poc.cdc.controller.dto.UpdateOrderLineRequest;
import com.poc.cdc.model.PurchaseOrder;
import com.poc.cdc.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	private OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@PostMapping(consumes = "application/json")
	public OrderOperationResponse addOrder(@RequestBody CreateOrderRequest createOrderRequest) {
		PurchaseOrder order = createOrderRequest.toOrder();
		order = orderService.addOrder(order);
		return OrderOperationResponse.from(order);
	}

	@PutMapping("/{orderId}/lines/{orderLineId}")
	public OrderOperationResponse updateOrderLine(@PathVariable("orderId") long orderId,
			@PathVariable("orderLineId") long orderLineId, @RequestBody UpdateOrderLineRequest request) {
		PurchaseOrder updated = orderService.updateOrderLine(orderId, orderLineId, request.getNewStatus());
		return OrderOperationResponse.from(updated);
	}
	
	@GetMapping("/{orderId}")
	public OrderOperationResponse getOrderLine(@PathVariable("orderId") long orderId) {
		PurchaseOrder order = orderService.getOrderLine(orderId);
		return OrderOperationResponse.from(order);
	}
	
}
