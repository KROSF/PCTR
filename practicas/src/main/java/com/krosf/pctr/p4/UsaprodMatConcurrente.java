package com.krosf.pctr.p4;

/**
 * UsaprodMatConcurrente
 */
public class UsaprodMatConcurrente {

  public static void main(String[] args) {
    int dimension = Integer.parseInt(args[0]);
    prodMatConcurrente.initAllMatriz(dimension, dimension);
    prodMatConcurrente[] hilos = new prodMatConcurrente[dimension];
    for (int i = 0; i < hilos.length; i++) {
      hilos[i] = new prodMatConcurrente(i);
      hilos[i].start();
    }
    try {
      for (prodMatConcurrente h : hilos) {
        h.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}