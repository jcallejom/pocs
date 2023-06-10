package com.poc.cdc.event.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.poc.cdc.event.OrderLineUpdatedEvent;
import com.poc.cdc.repository.OrderLineRepository;

@Service
public class OrderLineUpdatedEventListener implements ApplicationListener<OrderLineUpdatedEvent> {

	
	private OrderLineRepository repository;

	public OrderLineUpdatedEventListener(OrderLineRepository repository) {
		this.repository = repository;
	}

	@Override
	public void onApplicationEvent(OrderLineUpdatedEvent event) {
		//TODO

	}

}
