package com.honda.webapp.backoffice.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Category name is required")
  @Size(max = 50)
  @Column(nullable = false, unique = true)
  private String name;

  @Size(min = 3, max = 255, message = "The url needs to be between 3 and 255 characters long")
  private String imagePath;

  @OneToMany(mappedBy = "category")
  private List<Moto> motos;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public List<Moto> getMotos() {
    return this.motos;
  }

  public void setMotos(List<Moto> motos) {
    this.motos = motos;
  }

  @Override
  public String toString() {
    return getName();
  }
}
