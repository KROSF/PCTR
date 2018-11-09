package com.krosf.pctr.p3;

/**
 * Usa_Cajero
 */
public class Usa_Cajero {

  public static void main(String[] args) {
    Cajero.setIter((int) 1E3);
    System.out.format("%f\n", Cajero.saldo());
    Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];
    for (int i = 0; i < hilos.length; ++i) {
      hilos[i] = new Thread(new Cajero(i % 2 == 0));
      hilos[i].start();
    }
    try {
      for (Thread t : hilos) {
        t.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.format("%f\n", Cajero.saldo());
  }
}