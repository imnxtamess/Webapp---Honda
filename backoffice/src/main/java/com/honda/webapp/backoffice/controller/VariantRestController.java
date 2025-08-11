package com.honda.webapp.backoffice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.honda.webapp.backoffice.model.Variant;
import com.honda.webapp.backoffice.repository.VariantRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/variants")
public class VariantRestController {

  @Autowired
  private VariantRepository variantRepository;

  @GetMapping("/{id}")
  public ResponseEntity<Variant> show(@Valid @PathVariable Integer id) {

    Optional<Variant> variantAttempt = variantRepository.findById(id);

    if (variantAttempt.isEmpty()) {
      return new ResponseEntity<Variant>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Variant>(variantAttempt.get(), HttpStatus.OK);
  }
}
