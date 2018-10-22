package com.krosf.pctr.s2;

/**
 * Punto
 */
public class Punto {

  public Punto(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double distancia(Punto p) {
    return Math.hypot(p.x - this.x , p.y - this.y);
  }

  public Punto traslacion(Punto p) {
    return new Punto(x + p.x, y + p.y);
  }

  public Punto traslacion(double c) {
    return new Punto(x + c, y + c);
  }

  public String cuadrante() {
    String rsp = "";
    if (x > 0 && y > 0) {
      rsp = "Primer Cuadrante";
    } else if (x < 0 && y > 0) {
      rsp = "Segundo Cuadrante";
    } else if (x < 0 && y < 0) {
      rsp = "Tercer Cuadrante";
    } else {
      rsp = "Cuarto Cuadrante";
    }
    return rsp;
  }

  @Override
  public String toString() {
    return "(" + getX() +", " + getY() +")";
  }

  private double x;
  private double y;
}