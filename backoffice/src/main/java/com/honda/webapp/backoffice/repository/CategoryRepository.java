package com.honda.webapp.backoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.honda.webapp.backoffice.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
