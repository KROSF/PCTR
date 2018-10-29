package com.krosf.pctr.extras;

/**
 * Coche
 */
public class Coche {

  public Coche(boolean type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return type ? "A" : "B";
  }

  private boolean type;
}