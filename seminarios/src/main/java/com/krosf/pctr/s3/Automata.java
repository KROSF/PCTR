package com.krosf.pctr.s3;

import java.util.Random;

/**
 * Automata
 */
public class Automata extends Thread {

  public Automata(int inicio, int fin) {
    this.inicio = inicio;
    this.fin = fin;
  }

  public static void rellenar() {
    Random r = new Random();
    for(int i = 0; i < celulas.length; ++i) {
      celulas[i] = r.nextInt(3);
    }
  }

  public static void setSize(int size) {
    celulas = new int[size];
  }

  public static void setGen(int n) {
    gen = n;
  }

  public static int transicion(int index) {
    if (index == 0) {
      return (celulas[celulas.length - 1] + celulas[index] + celulas[index + 1]) % 3;
    } else if (index == celulas.length - 1) {
      return (celulas[index - 1] + celulas[index] + celulas[0]) % 3;
    } else {
      return (celulas[index - 1] + celulas[index] + celulas[index + 1]) % 3;
    }
  }

  public static void main(String[] args) {
    //int n_hilos = Runtime.getRuntime().availableProcessors();
    //Automata[] hilos = new Automata[n_hilos];
  }

  @Override
  public void run() {
    for(int i = 0; i < gen; ++i) {
      for(int j = inicio; j < fin; ++j) {
        nextGen[i] = transicion(j);
      }
    }
    celulas = nextGen;
  }

  private static int[] celulas;
  private static int[] nextGen;
  private static int gen;
  private int inicio;
  private int fin;
}