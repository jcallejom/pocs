package com.poc.cdc.model;

/**
 * https://debezium.io/documentation/reference/stable/transformations/outbox-event-router.html
 */

public interface EventModel {
	
	
	public  String id = "";
	
	public  String aggregatetype= "";
	
	public  String aggregateid= "";
	
	public  String type= "";
	
	public  String payload = "";
	
	public  Long timestamp =0L;
	
	
    
}
