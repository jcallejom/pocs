package com.poc.cdc.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.poc.cdc.event.OrderCreatedEvent;
import com.poc.cdc.repository.OrderLineRepository;
import com.poc.cdc.repository.PurchaseOrderRepository;

@Service
public class OrderCreatedEventListener implements ApplicationListener<OrderCreatedEvent> {

	private OrderLineRepository repositoryL;
	private PurchaseOrderRepository repository;
	public OrderCreatedEventListener(PurchaseOrderRepository repository, OrderLineRepository repositoryL) {
		this.repository = repository;
		this.repositoryL = repositoryL;
	}

	@Override
	public void onApplicationEvent(OrderCreatedEvent event) {
		//TODO

	}
}
