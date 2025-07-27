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

import com.honda.webapp.backoffice.model.ColorVariant;
import com.honda.webapp.backoffice.repository.ColorVariantRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/color-variants")
public class ColorVariantController {

  @Autowired
  private ColorVariantRepository colorVariantRepository;

  @GetMapping
  public String index(Model model) {

    List<ColorVariant> colorVariants = colorVariantRepository.findAll();

    model.addAttribute("colorVariants", colorVariants);

    return "color-variants/index";
  }

  @GetMapping("/{id}")
  public String show(Model model, @PathVariable Integer id) {

    Optional<ColorVariant> colorVariantAttempt = colorVariantRepository.findById(id);

    if (colorVariantAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("colorVariant", colorVariantAttempt.get());

    return "color-variants/show";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("colorVariant", new ColorVariant());

    return "color-variants/create-or-edit";
  }

  @PostMapping("/create")
  public String store(Model model, @Valid @ModelAttribute("colorVariant") ColorVariant colorVariantForm,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "color-variants/create-or-edit";
    }

    colorVariantRepository.save(colorVariantForm);

    return "redirect:/color-variants";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Optional<ColorVariant> colorVariantAttempt = colorVariantRepository.findById(id);

    if (colorVariantAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("colorVariant", colorVariantAttempt.get());

    return "color-variants/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(Model model, @Valid @ModelAttribute("colorVariant") ColorVariant colorVariantForm,
      BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "color-variants/create-or-edit";
    }

    colorVariantRepository.save(colorVariantForm);

    return "redirect:/color-variants";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Optional<ColorVariant> colorVariantAttempt = colorVariantRepository.findById(id);

    if (colorVariantAttempt.isEmpty()) {
      return "404";
    }

    colorVariantRepository.delete(colorVariantAttempt.get());

    return "redirect:/color-variants";

  }
}
