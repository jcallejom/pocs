package com.poc.cdc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * https://debezium.io/documentation/reference/stable/transformations/outbox-event-router.html
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Outbox implements EventModel{
	
	@Id
	private String id;
	
	private String aggregatetype;
	
	private String aggregateid;
	
	private String type;
	
	private String payload;
	
	private Long timestamp;
	
	
}
