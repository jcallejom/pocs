package com.poc.kafkapreformace.domain.mapper;

import java.util.List;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

import com.poc.kafkapreformace.domain.Prototype;
import com.poc.kafkapreformace.domain.dto.PrototypeRequest;
import com.poc.kafkapreformace.domain.dto.PrototypeResponse;

@Mapper(componentModel = "spring" , collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface PrototypeMapperVo {

	Prototype toDomain (PrototypeRequest example);
	
	PrototypeResponse toResponse(Prototype example);
	
	List<PrototypeResponse> toResponse(List<Prototype> example);

}
