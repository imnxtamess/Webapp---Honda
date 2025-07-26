package com.honda.webapp.backoffice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "color_variants")
public class ColorVariant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Color name is required")
  @Size(max = 50)
  private String name;

  @Size(max = 7, message = "Hex code must be max 7 characters long")
  private String hexCode;

  @Size(min = 3, max = 255, message = "The url needs to be between 3 and 255 characters long")
  private String imagePath;

  @ManyToOne
  @JoinColumn(name = "variant_id", nullable = false)
  private Variant variant;

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

  public String getHexCode() {
    return this.hexCode;
  }

  public void setHexCode(String hexCode) {
    this.hexCode = hexCode;
  }

  public String getImagePath() {
    return this.imagePath;
  }

  public void setImageUrl(String imagePath) {
    this.imagePath = imagePath;
  }

  public Variant getVariant() {
    return this.variant;
  }

  public void setVariant(Variant variant) {
    this.variant = variant;
  }

}