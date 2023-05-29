package com.poc.cdc.enums;

import lombok.Getter;

import static com.poc.cdc.utils.Consts.ENUM_NOT_FOUND_ERROR_MESSAGE;

import java.util.Arrays;

import com.poc.cdc.exception.EnumNotFoundError;

@Getter
public enum SourceDatabaseEntity {
  
  PRODUCT("products"),
  CUSTOMER("customers");
  
  private String id;
  
  SourceDatabaseEntity(final String id) {
    this.id = id;
  }
  
  public static SourceDatabaseEntity fromId(String id){
    return Arrays.stream(SourceDatabaseEntity.values())
      .filter(v -> v.id.equals(id))
      .findFirst()
      .orElseThrow( () -> new EnumNotFoundError(ENUM_NOT_FOUND_ERROR_MESSAGE));
  }
  
}
