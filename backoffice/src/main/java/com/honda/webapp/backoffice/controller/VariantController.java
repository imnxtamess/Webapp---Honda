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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.model.Variant;
import com.honda.webapp.backoffice.repository.VariantRepository;
import com.honda.webapp.backoffice.service.MotoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/variants")
public class VariantController {

  @Autowired
  private VariantRepository variantRepository;

  @Autowired
  private MotoService motoService;

  @GetMapping
  public String index(Model model) {

    List<Variant> variants = variantRepository.findAll();

    model.addAttribute("variants", variants);

    return "variants/index";
  }

  @GetMapping("/moto/{motoId}")
  public String indexByMoto(@PathVariable Integer motoId, Model model) {
    List<Variant> variants = variantRepository.findByMotoId(motoId);
    model.addAttribute("variants", variants);
    model.addAttribute("motoId", motoId);

    Optional<Moto> motoAttempt = motoService.findById(motoId);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("moto", motoAttempt.get());
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

  @GetMapping("/moto/{motoId}/create")
  public String create(@PathVariable Integer motoId, Model model) {

    Variant variant = new Variant();

    Optional<Moto> motoAttempt = motoService.findById(motoId);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    Moto moto = motoAttempt.get();

    model.addAttribute("moto", moto);

    variant.setMoto(moto);

    model.addAttribute("variant", variant);
    System.out.println("Moto name: " + moto.getName());

    return "variants/create-or-edit";
  }

  @PostMapping("/moto/{motoId}/create")
  public String store(@PathVariable Integer motoId, Model model, @Valid @ModelAttribute("variant") Variant variantForm,
      BindingResult bindingResult) {

    Optional<Moto> motoAttempt = motoService.findById(motoId);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    Moto moto = motoAttempt.get();

    if (bindingResult.hasErrors()) {
      variantForm.setMoto(motoAttempt.get());
      model.addAttribute("variant", variantForm);
      model.addAttribute("moto", moto);
      return "variants/create-or-edit";
    }

    variantForm.setMoto(moto);

    variantRepository.save(variantForm);

    return "redirect:/variants/moto/" + motoId;
  }

  @GetMapping("moto/{motoId}/edit/{id}")
  public String edit(@PathVariable Integer motoId, @PathVariable Integer id, Model model) {

    Optional<Moto> motoAttempt = motoService.findById(motoId);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    Moto moto = motoAttempt.get();

    model.addAttribute("moto", moto);

    Optional<Variant> variantAttempt = variantRepository.findById(id);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    Variant variant = variantAttempt.get();

    variant.setMoto(moto);

    model.addAttribute("variant", variant);

    return "variants/create-or-edit";
  }

  @PostMapping("moto/{motoId}/edit/{id}")
  public String update(@PathVariable Integer motoId, Model model,
      @Valid @ModelAttribute("variant") Variant variantForm, BindingResult bindingResult) {

    Optional<Moto> motoAttempt = motoService.findById(motoId);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    Moto moto = motoAttempt.get();

    if (bindingResult.hasErrors()) {
      variantForm.setMoto(motoAttempt.get());
      model.addAttribute("variant", variantForm);
      model.addAttribute("moto", moto);
      return "variants/create-or-edit";
    }

    variantForm.setMoto(moto);

    variantRepository.save(variantForm);

    return "redirect:/variants/moto/" + motoId;
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

    Optional<Variant> variantAttempt = variantRepository.findById(id);

    if (variantAttempt.isEmpty()) {
      return "404";
    }

    Variant variant = variantAttempt.get();

    Integer motoId = variant.getMoto().getId();

    if (!variant.getColorVariants().isEmpty()) {
      redirectAttributes.addFlashAttribute("error",
          "You can't delete a variant that has a color-variant associated with it");
      return "redirect:/variants/moto/" + motoId;
    }

    variantRepository.delete(variant);

    return "redirect:/variants/moto/" + motoId;

  }
}
