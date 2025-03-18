package com.poc.kafkapreformace.infrastructure.out.db.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.kafkapreformace.infrastructure.out.db.jpa.entity.PrototypeEntity;

interface IPrototypeEntityRepository extends JpaRepository<PrototypeEntity, Long> {

}
