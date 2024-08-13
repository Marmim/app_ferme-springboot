package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Culture {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String nom;
  private double coefficient;

  public void setId(int id) {
    this.id = id;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setCoefficient(double cultureCoefficient) {
    this.coefficient = cultureCoefficient;
  }
}
