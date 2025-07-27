package com.honda.webapp.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, Integer> {

}
