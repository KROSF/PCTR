package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;

/**
 * matVectorConcurrente
 */
public class matVectorConcurrente implements Runnable {

  private static volatile Double[][] matriz;
  private static volatile Double[] vector;
  private static volatile Double[] resultado;
  private int fila;

  public matVectorConcurrente(int fila) {
    this.fila = fila;
  }

  public static void initMatrizVectorAleatorios(int filas, int columnas) {
    matriz = new Double[filas][columnas];
    vector = new Double[columnas];
    resultado = new Double[filas];
    Arrays.fill(resultado, 0.0);
    Arrays.parallelSetAll(vector, (x) -> {
      Random r2 = new Random();
      return r2.nextInt(22) + r2.nextDouble();
    });
    for (int i = 0; i < filas; ++i) {
      Arrays.parallelSetAll(matriz[i], (x) -> {
        Random r2 = new Random();
        return r2.nextInt(22) + r2.nextDouble();
      });
    }
  }

  public static void printResultado() {
    System.out.println(Arrays.toString(resultado));
  }

  @Override
  public void run() {
    for (int i = 0; i < matriz[0].length; ++i) {
      resultado[fila] += matriz[fila][i] + vector[i];
    }
  }

  public static void start(int filas, int columnas) {
    initMatrizVectorAleatorios(filas, columnas);
    Thread[] hilos = new Thread[filas];
    for (int i = 0; i < hilos.length; ++i) {
      hilos[i] = new Thread(new matVectorConcurrente(i));
      hilos[i].start();
    }
    try {
      for (Thread t : hilos) {
        t.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    int dimension = Integer.parseInt(args[0]);
    start(dimension, dimension);
  }

}