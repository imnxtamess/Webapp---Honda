package com.honda.webapp.backoffice.model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "motos")
public class Moto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 3, max = 55, message = "The name needs to be between 3 and 50 characters")
  @Column(nullable = false)
  @NotBlank(message = "Name is required")
  private String name;

  @Lob
  private String description;

  @Size(min = 3, max = 255, message = "The url needs to be between 3 and 255 characters long")
  private String imagePath;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "engine_id", nullable = false)
  private Engine engine;

  @OneToMany(mappedBy = "moto", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Variant> variants;

  @ManyToMany()
  @JsonIgnore
  @JoinTable(name = "category_moto", joinColumns = @JoinColumn(name = "moto_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Engine getEngine() {
    return this.engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public List<Variant> getVariants() {
    return this.variants;
  }

  public void setVariants(List<Variant> variants) {
    this.variants = variants;
  }

  public List<Category> getCategories() {
    return this.categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

}
