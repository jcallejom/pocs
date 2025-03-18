package com.poc.kafkapreformace.domain.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.poc.kafkapreformace.domain.Prototype;
import com.poc.kafkapreformace.infrastructure.out.db.jpa.entity.PrototypeEntity;

@Mapper(componentModel = "spring" , collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface PrototypeMapperDomain {
	@Mapping(target = "id", ignore = true)
	PrototypeEntity toDomain (Prototype prototype);
	
	@InheritInverseConfiguration
	Prototype toResponse(PrototypeEntity prototypeEntity);
	
	@Mapping(target = "target.id", ignore = true)
	void clone(PrototypeEntity source, @MappingTarget PrototypeEntity target);

	@Mapping(target = "target.id", ignore = true)
	PrototypeEntity clon(PrototypeEntity source, @MappingTarget PrototypeEntity target);
}
