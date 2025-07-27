package com.honda.webapp.backoffice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.honda.webapp.backoffice.model.Variant;
import com.honda.webapp.backoffice.repository.VariantRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/variants")
public class VariantController {

  @Autowired
  private VariantRepository variantRepository;

  @GetMapping
  public String index(Model model) {

    List<Variant> variants = variantRepository.findAll();

    model.addAttribute("variants", variants);

    return "variants/index";
  }

  @GetMapping("/{id}")
  public String show(Model model, @PathVariable Integer id) {

    Optional<Variant> variantAttempt = variantRepository.findById(id);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("variant", variantAttempt.get());

    return "variants/show";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("variant", new Variant());

    return "variants/create-or-edit";
  }

  @PostMapping("/create")
  public String store(Model model, @Valid @ModelAttribute("variant") Variant variantForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "variants/create-or-edit";
    }

    variantRepository.save(variantForm);

    return "redirect:/variants";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Optional<Variant> variantAttempt = variantRepository.findById(id);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("variant", variantAttempt.get());

    return "variants/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(Model model, @Valid @ModelAttribute("variant") Variant variantForm,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "variants/create-or-edit";
    }

    variantRepository.save(variantForm);

    return "redirect:/variants";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Optional<Variant> variantAttempt = variantRepository.findById(id);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    variantRepository.delete(variantAttempt.get());

    return "redirect:/variants";

  }
}
