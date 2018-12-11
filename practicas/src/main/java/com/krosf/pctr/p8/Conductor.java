package com.krosf.pctr.p8;

import java.util.Objects;

/**
 *
 *
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class Conductor {

  public Conductor(String name, String dni, String validUntil, String points) {
    this.name = name;
    this.dni = dni;
    this.validUntil = validUntil;
    this.points = Integer.parseInt(points);
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDni() {
    return this.dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getValidUntil() {
    return this.validUntil;
  }

  public void setValidUntil(String validUntil) {
    this.validUntil = validUntil;
  }

  public int getPoints() {
    return this.points;
  }

  public void setPoints(String points) {
    this.points = Integer.parseInt(points);
  }

  @Override
  public String toString() {
    return String.format("{ name: %s, dni: %s, validUntil: %s, points: %d }", name, dni, validUntil, points);
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Conductor)) {
            return false;
        }
        Conductor conductor = (Conductor) o;
        return Objects.equals(name, conductor.name) && Objects.equals(dni, conductor.dni) && Objects.equals(validUntil, conductor.validUntil) && points == conductor.points;
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dni, validUntil, points);
  }

  private String name;
  private String dni;
  private String validUntil;
  private int points;
}