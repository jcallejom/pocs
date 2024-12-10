package com.poc.cdc.event.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventPayload {
  
//  private EventSource source;
  
  private String eventType;
  private String aggregatetype;
  private String aggregateid;
  private HashMap<String,Object> headers;
  private HashMap<String,Object> payload;
  

  
}
