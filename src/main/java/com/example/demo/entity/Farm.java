package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Farm {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  String nom;

  String region;
  String commune;
  String douar;

  private Double latitude;

  private Double longitude;

  @ManyToOne public User user;

  @ManyToMany
  @JoinTable(
      name = "farm_culture",
      joinColumns = @JoinColumn(name = "farm_id"),
      inverseJoinColumns = @JoinColumn(name = "culture_id"))
  Set<Culture> cultures = new HashSet<>();

  public Farm() {}

  public Farm(
      int id,
      String nom,
      String region,
      String commune,
      String douar,
      Double latitude,
      Double longitude,
      User user,
      Set<Culture> cultures) {
    this.id = id;
    this.nom = nom;
    this.region = region;
    this.commune = commune;
    this.douar = douar;
    this.latitude = latitude;
    this.longitude = longitude;
    this.user = user;
    this.cultures = cultures;
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

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getCommune() {
    return commune;
  }

  public void setCommune(String commune) {
    this.commune = commune;
  }

  public String getDouar() {
    return douar;
  }

  public void setDouar(String douar) {
    this.douar = douar;
  }

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Set<Culture> getCultures() {
    return cultures;
  }

  public void setCultures(Set<Culture> cultures) {
    this.cultures = cultures;
  }

  @Override
  public String toString() {
    return "Farm{"
        + "id="
        + id
        + ", nom='"
        + nom
        + '\''
        + ", region='"
        + region
        + '\''
        + ", commune='"
        + commune
        + '\''
        + ", douar='"
        + douar
        + '\''
        + ", latitude="
        + latitude
        + ", longitude="
        + longitude
        + ", user="
        + user
        + ", cultures="
        + cultures
        + '}';
  }
}
