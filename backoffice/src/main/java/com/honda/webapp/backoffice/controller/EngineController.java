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

import com.honda.webapp.backoffice.model.Engine;
import com.honda.webapp.backoffice.repository.EngineRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/engines")
public class EngineController {

  @Autowired
  private EngineRepository engineRepository;

  @GetMapping
  public String index(Model model) {

    List<Engine> engines = engineRepository.findAll();

    model.addAttribute("engines", engines);

    return "engines/index";
  }

  @GetMapping("/{id}")
  public String show(Model model, @PathVariable Integer id) {

    Optional<Engine> engineAttempt = engineRepository.findById(id);

    if (engineAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("engine", engineAttempt.get());

    return "engines/show";
  }

  @GetMapping("/create")
  public String create(Model model) {

    model.addAttribute("engine", new Engine());

    return "engines/create-or-edit";
  }

  @PostMapping("/create")
  public String store(Model model, @Valid @ModelAttribute("engine") Engine engineForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "engines/create-or-edit";
    }

    engineRepository.save(engineForm);

    return "redirect:/engines";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model) {

    Optional<Engine> engineAttempt = engineRepository.findById(id);

    if (engineAttempt.isEmpty()) {
      return "404";
    }

    model.addAttribute("engine", engineAttempt.get());

    return "engines/create-or-edit";
  }

  @PostMapping("/edit/{id}")
  public String update(Model model, @Valid @ModelAttribute("engine") Engine engineForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "engines/create-or-edit";
    }

    engineRepository.save(engineForm);

    return "redirect:/engines";
  }

  @PostMapping("/delete/{id}")
  public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {

    Optional<Engine> engineAttempt = engineRepository.findById(id);

    if (engineAttempt.isEmpty()) {
      return "404";
    }

    Engine engine = engineAttempt.get();

    if (!engine.getMotos().isEmpty()) {

      redirectAttributes.addFlashAttribute("error",
          "You can't delete an engine if it has motorcycles associated with it.");
      return "redirect:/engines";
    }
    engineRepository.delete(engine);

    return "redirect:/engines";

  }

}
