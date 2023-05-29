package com.poc.cdc.service;

import lombok.extern.slf4j.Slf4j;

import static com.poc.cdc.utils.Consts.UNDEFINED_ENTITY_ERROR_MESSAGE;

import org.springframework.stereotype.Service;

import com.poc.cdc.enums.SourceDatabaseEntity;
import com.poc.cdc.model.event.Event;

@Slf4j
@Service
public class IntegrationServiceImpl implements IntegrationService {
  
  private final ManagementService managementService;
  
  public IntegrationServiceImpl(ManagementService managementService){
    this.managementService = managementService;
  }
  
  @Override
  public void processKafkaEvent(Event event){
      SourceDatabaseEntity entity = SourceDatabaseEntity.fromId(event.getPayload().getSource().getTable());
      switch (entity){
        case PRODUCT:
          managementService.manageProduct(event.getPayload());
          break;
        default:
          log.error(UNDEFINED_ENTITY_ERROR_MESSAGE);
      }
  }
  
}
