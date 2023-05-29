package com.poc.cdc.event.listener;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import com.poc.cdc.event.OrderCreatedEvent;
import com.poc.cdc.model.Outbox;
import com.poc.cdc.repository.OutboxRepository;

@Service
public class OrderLineUpdatedEventListener implements ApplicationListener<OrderCreatedEvent> {

	private OutboxRepository repository;

	public OrderLineUpdatedEventListener(OutboxRepository repository) {
		this.repository = repository;
	}

	@Override
	public void onApplicationEvent(OrderCreatedEvent event) {
		Outbox outbox = Outbox.builder().aggregateid(event.getAggregateId()).aggregatetype(event.getAggregateType())
				.payload(event.getPayload().toString()).type(event.getType()).id(UUID.randomUUID().toString())
				.timestamp(event.getTimestamp()).build();

		this.repository.save(outbox);

	}

}
