package com.krosf.pctr.s2;

import java.util.Random;

/**
 * Automata
 */
public class Automata {
  /**
   * 
   * @param size numero de celulas.
   */
  public Automata(int size) {
    celulas = new int[size];
  }
  /**
   * 
   * @param index
   * @return 
   */
  public int transicion(int index) {
    if (index == 0) {
      return (celulas[celulas.length - 1] + celulas[index] + celulas[index + 1]) % 3;
    } else if (index == celulas.length - 1) {
      return (celulas[index - 1] + celulas[index] + celulas[0]) % 3;
    } else {
      return (celulas[index - 1] + celulas[index] + celulas[index + 1]) % 3;
    }
  }

  public void generaciones(int gen) {
    for(int i = 0; i < gen; ++i) {
      mostrar(celulas);
      celulas = nuevaGeneracion();
    }
  }

  public static void mostrar(int...args) {
    for(int i = 0; i < args.length; ++i) {
      System.out.print(args[i] + " ");
    }
    System.out.println();
  }

  public void rellenar() {
    Random r = new Random();
    for(int i = 0; i < celulas.length; ++i) {
      celulas[i] = r.nextInt(3);
    }
  }

  private int[] nuevaGeneracion() {
    int[] nextGen = new int[celulas.length];
    for(int i = 0; i < nextGen.length; ++i) {
      nextGen[i] = transicion(i);
    }
    return nextGen;
  }
  public static void main(String[] args) {
    Automata a = new Automata(60);
    a.rellenar();
    a.generaciones(1000);
  }

  private int[] celulas;
}