package com.poc.cdc.enums;

import lombok.Getter;

import static com.poc.cdc.utils.Consts.ENUM_NOT_FOUND_ERROR_MESSAGE;

import java.util.Arrays;

import com.poc.cdc.exception.EnumNotFoundError;

@Getter
public enum SourceDatabaseOperation {
  
  CREATE("c"),
  UPDATE("u"),
  DELETE("d");
  
  private final String id;
  
  SourceDatabaseOperation(final String id) {
    this.id = id;
  }
  
  public static SourceDatabaseOperation fromId(String id){
    return Arrays.stream(SourceDatabaseOperation.values())
      .filter(v -> v.id.equals(id))
      .findFirst()
      .orElseThrow( () -> new EnumNotFoundError(ENUM_NOT_FOUND_ERROR_MESSAGE));
  }
  
}
