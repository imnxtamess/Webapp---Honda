package com.honda.webapp.backoffice.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "engines")
public class Engine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull(message = "Displacement is required")
  @Min(value = 50, message = "Engine displacement can't be lower than 50cc")
  @Column(nullable = false)
  private Integer engineDisplacement;

  @Size(max = 30, message = "Max power can't be longer than 30 characters")
  private String maxPower;

  @Size(max = 30, message = "Max power can't be longer than 30 characters")
  private String maxTorque;

  @NotBlank(message = "Engine Type is required")
  @Column(nullable = false)
  private String engineType;

  @NotBlank(message = "Fuel system is required")
  @Column(nullable = false)
  private String fuelSystem;

  @NotBlank(message = "Compression ratio is required")
  @Column(nullable = false)
  private String compressionRatio;

  @OneToMany(mappedBy = "engine", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Moto> motos;

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getEngineDisplacement() {
    return this.engineDisplacement;
  }

  public void setEngineDisplacement(Integer engineDisplacement) {
    this.engineDisplacement = engineDisplacement;
  }

  public String getMaxPower() {
    return this.maxPower;
  }

  public void setMaxPower(String maxPower) {
    this.maxPower = maxPower;
  }

  public String getMaxTorque() {
    return this.maxTorque;
  }

  public void setMaxTorque(String maxTorque) {
    this.maxTorque = maxTorque;
  }

  public String getEngineType() {
    return this.engineType;
  }

  public void setEngineType(String engineType) {
    this.engineType = engineType;
  }

  public String getFuelSystem() {
    return this.fuelSystem;
  }

  public void setFuelSystem(String fuelSystem) {
    this.fuelSystem = fuelSystem;
  }

  public String getCompressionRatio() {
    return this.compressionRatio;
  }

  public void setCompressionRatio(String compressionRatio) {
    this.compressionRatio = compressionRatio;
  }

  public List<Moto> getMotos() {
    return this.motos;
  }

  public void setMotos(List<Moto> motos) {
    this.motos = motos;
  }

  @Override
  public String toString() {
    return engineDisplacement + "cc " + engineType;
  }
}
