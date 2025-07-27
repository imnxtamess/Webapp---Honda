package com.honda.webapp.backoffice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {

  List<Moto> findByCategories_Id(Integer categoryId);

}
