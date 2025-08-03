package com.honda.webapp.backoffice.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "variants")
public class Variant {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Variant name is required")
  @Size(max = 100)
  @Column(nullable = false)
  private String name;

  @NotNull(message = "Price is rquired")
  @Column(precision = 8, scale = 2)
  private BigDecimal price;

  @NotNull
  @Column(nullable = false)
  private Double weight;

  @Max(value = 100, message = "Tank capacity can't be higher than 100l")
  private Integer tankCapacity;

  @Max(value = 10000, message = "Seat Height can't be higher than 10000mm")
  private Integer seatHeight;

  @Size(max = 100, message = "Text can't be longer than 100 char")
  private String frontSuspension;

  @Size(max = 100, message = "Text can't be longer than 100 char")
  private String rearSuspension;

  @Size(max = 100, message = "Text can't be longer than 100 char")
  private String headLights;

  @Size(max = 100, message = "Text can't be longer than 100 char")
  private String frameType;

  @Column(nullable = false)
  private boolean cruiseControl;

  @Column(nullable = false)
  private boolean quickShifter;

  @Column(nullable = false)
  private boolean manualTransmission;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "moto_id", nullable = false)
  private Moto moto;

  @OneToMany(mappedBy = "variant")
  private List<ColorVariant> colorVariants;

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

  public BigDecimal getPrice() {
    return this.price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Double getWeight() {
    return this.weight;
  }

  public void setWeight(Double weight) {
    this.weight = weight;
  }

  public Integer getTankCapacity() {
    return this.tankCapacity;
  }

  public void setTankCapacity(Integer tankCapacity) {
    this.tankCapacity = tankCapacity;
  }

  public Integer getSeatHeight() {
    return this.seatHeight;
  }

  public void setSeatHeight(Integer seatHeight) {
    this.seatHeight = seatHeight;
  }

  public String getFrontSuspension() {
    return this.frontSuspension;
  }

  public void setFrontSuspension(String frontSuspension) {
    this.frontSuspension = frontSuspension;
  }

  public String getRearSuspension() {
    return this.rearSuspension;
  }

  public void setRearSuspension(String rearSuspension) {
    this.rearSuspension = rearSuspension;
  }

  public String getHeadLights() {
    return this.headLights;
  }

  public void setHeadLights(String headLights) {
    this.headLights = headLights;
  }

  public String getFrameType() {
    return this.frameType;
  }

  public void setFrameType(String frameType) {
    this.frameType = frameType;
  }

  public boolean isCruiseControl() {
    return this.cruiseControl;
  }

  public void setCruiseControl(boolean cruiseControl) {
    this.cruiseControl = cruiseControl;
  }

  public boolean isQuickShifter() {
    return this.quickShifter;
  }

  public void setQuickShifter(boolean quickShifter) {
    this.quickShifter = quickShifter;
  }

  public boolean isManualTransmission() {
    return this.manualTransmission;
  }

  public void setManualTransmission(boolean manualTransmission) {
    this.manualTransmission = manualTransmission;
  }

  public Moto getMoto() {
    return this.moto;
  }

  public void setMoto(Moto moto) {
    this.moto = moto;
  }

  public List<ColorVariant> getColorVariants() {
    return this.colorVariants;
  }

  public void setSpecialColorVariants(List<ColorVariant> colorVariants) {
    this.colorVariants = colorVariants;
  }

  @Override
  public String toString() {
    return getName();
  }

}
