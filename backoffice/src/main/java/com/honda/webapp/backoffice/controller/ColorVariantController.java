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
import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.model.Variant;
import com.honda.webapp.backoffice.repository.ColorVariantRepository;
import com.honda.webapp.backoffice.repository.VariantRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/color-variants")
public class ColorVariantController {

  @Autowired
  private ColorVariantRepository colorVariantRepository;
  @Autowired
  private VariantRepository variantRepository;

  @GetMapping
  public String index(Model model) {

    List<ColorVariant> colorVariants = colorVariantRepository.findAll();

    model.addAttribute("colorVariants", colorVariants);

    return "color-variants/index";
  }

  @GetMapping("/variant/{variantId}")
  public String indexByVariant(@PathVariable Integer variantId, Model model) {
    List<ColorVariant> colorVariants = colorVariantRepository.findByVariantId(variantId);
    model.addAttribute("colorVariants", colorVariants);
    model.addAttribute("variantId", variantId);

    Optional<Variant> variantAttempt = variantRepository.findById(variantId);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("variant", variantAttempt.get());
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

  @GetMapping("/variant/{variantId}/create")
  public String create(@PathVariable Integer variantId, Model model) {

    ColorVariant colorVariant = new ColorVariant();

    Optional<Variant> variantAttempt = variantRepository.findById(variantId);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    Variant variant = variantAttempt.get();
    Moto moto = variant.getMoto();

    model.addAttribute("moto", moto);
    model.addAttribute("variant", variant);

    colorVariant.setVariant(variant);

    model.addAttribute("colorVariant", colorVariant);

    return "color-variants/create-or-edit";
  }

  @PostMapping("/variant/{variantId}/create")
  public String store(@PathVariable Integer variantId, Model model,
      @Valid @ModelAttribute("colorVariant") ColorVariant colorVariantForm,
      BindingResult bindingResult) {

    Optional<Variant> variantAttempt = variantRepository.findById(variantId);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    if (bindingResult.hasErrors()) {
      colorVariantForm.setVariant(variantAttempt.get());
      return "color-variants/create-or-edit";
    }

    colorVariantForm.setVariant(variantAttempt.get());

    colorVariantRepository.save(colorVariantForm);

    return "redirect:/color-variants/variant/" + variantId;
  }

  @GetMapping("/variant/{variantId}/edit/{id}")
  public String edit(@PathVariable Integer variantId, @PathVariable Integer id, Model model) {

    Optional<Variant> variantAttempt = variantRepository.findById(variantId);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    Variant variant = variantAttempt.get();
    Moto moto = variant.getMoto();

    model.addAttribute("moto", moto);
    model.addAttribute("variant", variant);

    Optional<ColorVariant> colorVariantAttempt = colorVariantRepository.findById(id);

    if (colorVariantAttempt.isEmpty()) {
      return "404";
    }

    ColorVariant colorVariant = colorVariantAttempt.get();

    colorVariant.setVariant(variant);

    model.addAttribute("colorVariant", colorVariant);

    return "color-variants/create-or-edit";
  }

  @PostMapping("/variant/{variantId}/edit/{id}")
  public String update(@PathVariable Integer variantId, Model model,
      @Valid @ModelAttribute("colorVariant") ColorVariant colorVariantForm,
      BindingResult bindingResult) {

    Optional<Variant> variantAttempt = variantRepository.findById(variantId);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    if (bindingResult.hasErrors()) {
      colorVariantForm.setVariant(variantAttempt.get());
      return "color-variants/create-or-edit";
    }

    colorVariantForm.setVariant(variantAttempt.get());

    colorVariantRepository.save(colorVariantForm);

    return "redirect:/color-variants/variant/" + variantId;
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Optional<ColorVariant> colorVariantAttempt = colorVariantRepository.findById(id);

    if (colorVariantAttempt.isEmpty()) {
      return "404";
    }

    Integer variantId = colorVariantAttempt.get().getVariant().getId();

    colorVariantRepository.delete(colorVariantAttempt.get());

    return "redirect:/color-variants/variant/" + variantId;

  }
}
