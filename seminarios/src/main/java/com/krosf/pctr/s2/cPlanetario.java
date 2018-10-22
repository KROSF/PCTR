package com.krosf.pctr.s2;

public class cPlanetario {

  public cPlanetario(double densidad, double diametro, double distancia, double masa, int id, String nombre) {
    this.densidad = densidad;
    this.diametro = diametro;
    this.distancia = distancia;
    this.masa = masa;
    this.id = id;
    this.nombre = nombre;
  }

  public double getDensidad() {
      return this.densidad;
  }

  public double getDiametro() {
      return this.diametro;
  }

  public double getDistancia() {
      return this.distancia;
  }

  public double getMasa() {
      return this.masa;
  }

  public int getId() {
      return this.id;
  }

  public String getNombre() {
      return this.nombre;
  }

  @Override
  public String toString() {
      return "{" + " \"densidad\": " + getDensidad() + ",\n\"diametro\": " + getDiametro() + ",\n\"distancia\": "
              + getDistancia() + ",\n\"masa\": " + getMasa() + ",\n\"id\": " + getId() + ",\n\"nombre\": \""
              + getNombre() + "\"\n" + "},";
  }

  private double densidad;
  private double diametro;
  private double distancia;
  private double masa;
  private int id;
  private String nombre;
}