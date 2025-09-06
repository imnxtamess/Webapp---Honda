package com.honda.webapp.backoffice.controller;

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

import com.honda.webapp.backoffice.model.Category;
import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.model.Variant;
import com.honda.webapp.backoffice.repository.CategoryRepository;
import com.honda.webapp.backoffice.repository.ColorVariantRepository;
import com.honda.webapp.backoffice.repository.MotoRepository;
import com.honda.webapp.backoffice.repository.VariantRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private MotoRepository motoRepository;

  @Autowired
  VariantRepository variantRepository;

  @Autowired
  ColorVariantRepository colorVariantRepository;

  @GetMapping
  public String index(Model model) {

    model.addAttribute("categories", categoryRepository.findAll());

    return "categories/index";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("category", new Category());

    return "categories/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("category") Category categoryForm, BindingResult bindingResult,
      Model model) {

    if (bindingResult.hasErrors()) {
      return "categories/create-or-edit";
    }

    categoryRepository.save(categoryForm);

    return "redirect:/categories";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Optional<Category> categoryAttempt = categoryRepository.findById(id);

    if (categoryAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("category", categoryAttempt.get());

    return "categories/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(Model model, @Valid @ModelAttribute("category") Category categoryForm,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "categories/create-or-edit";
    }

    categoryRepository.save(categoryForm);

    return "redirect:/categories";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Optional<Category> categoryAttempt = categoryRepository.findById(id);

    if (categoryAttempt.isEmpty()) {
      return "404";
    }

    Category category = categoryAttempt.get();

    for (Moto moto : category.getMotos()) {
      for (Variant variant : moto.getVariants()) {
        colorVariantRepository.deleteAll(variant.getColorVariants());
      }
      variantRepository.deleteAll(moto.getVariants());
      motoRepository.delete(moto);
    }

    categoryRepository.delete(category);

    return "redirect:/categories";

  }

}
