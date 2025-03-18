package com.poc.cdc.infratructure.consumers.model;

import static com.poc.cdc.infratructure.consumers.model.Consts.ENUM_NOT_FOUND_ERROR_MESSAGE;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum SourceEvent {
  
  ORDERCREATED("OrderCreated"),
  INVOICECREATED("InvoiceCreated");
  
  private String id;
  
  SourceEvent(final String id) {
    this.id = id;
  }
  
  public static SourceEvent fromId(String id){
    return Arrays.stream(SourceEvent.values())
      .filter(v -> v.id.equals(id))
      .findFirst()
      .orElseThrow( () -> new RuntimeException(ENUM_NOT_FOUND_ERROR_MESSAGE));
  }
  
}
