package com.honda.webapp.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.Variant;

public interface VariantRepository extends JpaRepository<Variant, Integer> {

  List<Variant> findByMotoId(Integer motoId);

}
