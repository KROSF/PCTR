package com.krosf.pctr.s2;

/**
 * Semaforo
 */
public class Semaforo {

  public Semaforo(int color) {
    if (color >= 0 && color <= 2) {
      this.color = color;
    } else {
      this.color = 0;
    }
  }

  public void setColor(int color) {
    this.color = color;
  }

  public int getColor() {
    return this.color;
  }

  @Override
  public String toString() {
    return color();
  }

  private String color() {
    String clr = "";
    if (color == 0) {
      clr = ANSI_RED + "ROJO" + ANSI_RESET;
    } else if (color == 1) {
      clr = ANSI_YELLOW + "AMBAR" + ANSI_RESET;
    } else if (color == 2) {
      clr = ANSI_GREEN + "VERDE" + ANSI_RESET;
    }
    return clr;
  }

  private int color;
  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
}