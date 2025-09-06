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
import com.honda.webapp.backoffice.model.Moto;
import com.honda.webapp.backoffice.model.Variant;
import com.honda.webapp.backoffice.repository.CategoryRepository;
import com.honda.webapp.backoffice.repository.ColorVariantRepository;
import com.honda.webapp.backoffice.repository.EngineRepository;
import com.honda.webapp.backoffice.repository.VariantRepository;
import com.honda.webapp.backoffice.service.MotoService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/motos")
public class MotoController {

  @Autowired
  private MotoService motoService;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private EngineRepository engineRepository;

  @Autowired
  private VariantRepository variantRepository;

  @Autowired
  private ColorVariantRepository colorVariantRepository;

  @GetMapping
  public String index(Model model) {

    List<Moto> motos = motoService.findAll();

    model.addAttribute("motos", motos);

    return "motos/index";
  }

  @GetMapping("/{id}")
  public String show(@PathVariable Integer id, Model model) {

    Optional<Moto> motoAttempt = motoService.findById(id);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("moto", motoAttempt.get());
    model.addAttribute("categories", categoryRepository.findAll());

    return "motos/show";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("moto", new Moto());
    model.addAttribute("categories", categoryRepository.findAll());
    model.addAttribute("engines", engineRepository.findAll());

    return "motos/create-or-edit";
  }

  @PostMapping("/create")
  public String store(@Valid @ModelAttribute("moto") Moto motoForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("categories", categoryRepository.findAll());
      return "motos/create-or-edit";
    }

    motoService.create(motoForm);

    return "redirect:/motos";
  }

  @GetMapping("/edit/{id}")
  public String edit(Model model, @PathVariable Integer id) {

    Optional<Moto> motoAttempt = motoService.findById(id);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("moto", motoAttempt.get());
    model.addAttribute("categories", categoryRepository.findAll());
    model.addAttribute("engines", engineRepository.findAll());
    return "motos/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(@Valid @ModelAttribute("moto") Moto motoForm, BindingResult bindingResult, Model model) {

    if (bindingResult.hasErrors()) {
      model.addAttribute("categories", categoryRepository.findAll());
      return "motos/create-or-edit";
    }

    motoService.update(motoForm);

    return "redirect:/motos";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id) {

    Optional<Moto> motoAttempt = motoService.findById(id);

    if (motoAttempt.isEmpty()) {
      return "404";
    }

    Moto moto = motoAttempt.get();

    for (Variant variant : moto.getVariants()) {
      colorVariantRepository.deleteAll(variant.getColorVariants());
    }

    variantRepository.deleteAll(moto.getVariants());

    motoService.deleteById(id);

    return "redirect:/motos";

  }

}
