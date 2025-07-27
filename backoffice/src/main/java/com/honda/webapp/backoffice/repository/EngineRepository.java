package com.honda.webapp.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.Engine;

public interface EngineRepository extends JpaRepository<Engine, Integer> {

}
