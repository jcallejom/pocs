package com.poc.cdc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.poc.cdc.event.InvoiceCreatedEvent;
import com.poc.cdc.event.OrderCreatedEvent;
import com.poc.cdc.event.OrderLineUpdatedEvent;
import com.poc.cdc.event.publisher.EventPublisher;
import com.poc.cdc.model.OrderLineStatus;
import com.poc.cdc.model.PurchaseOrder;
import com.poc.cdc.repository.PurchaseOrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	private PurchaseOrderRepository repository;
	
	private EventPublisher publisher;
    
	public OrderService(PurchaseOrderRepository repository, EventPublisher publisher) {
		this.repository = repository;
		this.publisher = publisher;
	}

	public PurchaseOrder addOrder(PurchaseOrder order) {
		PurchaseOrder newOrder = repository.save(order);
		
		
		diparar(order);
		return newOrder;
	}
	//Esta operacion se podira poner en raite ,
	//estep,
	//despues de presistir en base de datos,
	//en metod postsSTep despues del step 
	private void diparar(PurchaseOrder order) {
		publisher.fire(OrderCreatedEvent.of(this,order));
		publisher.fire(InvoiceCreatedEvent.of(this,order));
	}

	public PurchaseOrder updateOrderLine(long orderId, long orderLineId, OrderLineStatus newStatus) {
		Optional<PurchaseOrder> purcharseOrder = repository.findById(orderId);
		if (purcharseOrder.isPresent()) {
			PurchaseOrder purchaseOrder = purcharseOrder.get();
			OrderLineStatus oldStatus = purchaseOrder.updateOrderLine(orderLineId, newStatus);
			repository.save(purchaseOrder);
			publisher.fire(OrderLineUpdatedEvent.of(this,orderId, orderLineId, newStatus, oldStatus));
			
			return purchaseOrder;
		} else {
			throw new EntityNotFoundException("Order with id " + orderId + " could not be found");
		}
	}

	public PurchaseOrder getOrderLine(long orderId) {
		return repository.findById(orderId).orElseThrow();
	}

}
