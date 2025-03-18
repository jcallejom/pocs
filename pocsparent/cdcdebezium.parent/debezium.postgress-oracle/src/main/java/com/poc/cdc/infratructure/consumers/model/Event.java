package com.poc.cdc.infratructure.consumers.model;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {

  private EventPayload eventPayload;
//  private HashMap<String,Object> payload;
}
