package com.honda.webapp.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.ColorVariant;

public interface ColorVariantRepository extends JpaRepository<ColorVariant, Integer> {

  List<ColorVariant> findByVariantId(Integer variantId);

}
