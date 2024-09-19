package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Culture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nom;
  private double coefficient;
  private LocalDate dateSemis;

  public LocalDate getDateSemis() {
    return dateSemis;
  }

  public void setDateSemis(LocalDate dateSemis) {
    this.dateSemis = dateSemis;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public double getCoefficient() {
    return coefficient;
  }

  public void setCoefficient(double cultureCoefficient) {
    this.coefficient = cultureCoefficient;
  }
}
