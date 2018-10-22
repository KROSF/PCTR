package com.krosf.pctr.s2;

/**
 * usaSemaforo
 */
public class usaSemaforo {

  public static void main(String[] args) {
    Semaforo s = new Semaforo(2);
    for (int i = 0; i < 10; ++i) {
      System.out.println(s);
      s.setColor(i % 3);
    }
  }
}