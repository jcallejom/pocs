package com.poc.kafkapreformace.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.archetype.base.core.exception.FunctionalException;
import com.poc.kafkapreformace.domain.Prototype;

public interface IPrototypeService {
	
	public Prototype get(Long id);

	public List<Prototype> get();

	public Prototype save(Prototype data);
	
	public Prototype update(Long id,Prototype data) throws FunctionalException;
	
	public Page<Prototype>findAllPage(Pageable pageable);
}