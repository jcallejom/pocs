package com.poc.kafkapreformace.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.archetype.base.core.exception.FunctionalException;
import com.poc.kafkapreformace.domain.Prototype;

public interface IPrototypeRepository {
	
	public Prototype save(Prototype data);

	public List<Prototype> findAll();

	public Prototype findById(Long id);
	
	public Prototype update(Long id, Prototype data) throws FunctionalException;
	
	public Page<Prototype> findAllPage(Pageable page);
}
