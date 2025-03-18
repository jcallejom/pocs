package com.poc.cdc.infratructure.consumers;

import com.poc.cdc.infratructure.consumers.events.InvoiceCreatedE;

// TODO: Auto-generated Javadoc
/**
 * The Interface EventHandler.
 */
public interface IConsumerEventHandler {
	
	/**
	 * On.
	 *
	 * @param event the event
	 */
	void on(InvoiceCreatedE event);

	
}
