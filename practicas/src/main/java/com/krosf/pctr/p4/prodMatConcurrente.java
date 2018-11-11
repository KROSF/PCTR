package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * prodMatConcurrente
 */
public class prodMatConcurrente extends Thread {

  private static volatile Double[][] A;
  private static volatile Double[][] B;
  private static volatile Double[][] resultado;
  private int fila;

  public prodMatConcurrente(int fila) {
    this.fila = fila;
  }

  public static Double[][] matrizAleatoria(int filas, int columnas) {
    Double[][] aleatoria = new Double[filas][columnas];
    for (Double[] f : aleatoria) {
      Arrays.parallelSetAll(f, x -> {
        Random r = new Random();
        return r.nextInt(22) + r.nextDouble();
      });
    }
    return aleatoria;
  }

  public static void initAllMatriz(int filas, int columnas) {
    A = matrizAleatoria(filas, columnas);
    B = matrizAleatoria(filas, columnas);
    resultado = new Double[filas][columnas];
    for (Double[] c : resultado) {
      Arrays.fill(c, 0.0);
    }
  }

  public static void printMatriz() {
    System.out.println(Arrays.stream(resultado).map(Arrays::toString)
    .collect(Collectors.joining(System.lineSeparator())));
  }

  @Override
  public void run() {
    for (int j = 0; j < B[0].length; ++j) {
      for (int k = 0; k < A[0].length; ++k) {
        resultado[fila][j] += A[fila][k] * B[k][j];
      }
    }
  }
}