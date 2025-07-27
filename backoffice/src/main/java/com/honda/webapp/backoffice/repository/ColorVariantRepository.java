package com.honda.webapp.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.ColorVariant;

public interface ColorVariantRepository extends JpaRepository<ColorVariant, Integer> {

}
