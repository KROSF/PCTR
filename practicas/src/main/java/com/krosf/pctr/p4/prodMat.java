package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * prodMat
 */
public class prodMat {

  public static Double[][] matrizAleatoria(int filas, int columnas) {
    Double[][] aleatoria = new Double[filas][columnas];
    Random r = new Random();
    for (int i = 0; i < filas; ++i) {
      for (int j = 0; j < columnas; ++j) {
        aleatoria[i][j] = r.nextInt(22) + r.nextDouble();
      }
    }
    return aleatoria;
  }

  public static Double[][] producto(Double[][] A, Double[][] B) {
    if (A[0].length != B.length) {
      throw new IndexOutOfBoundsException("Dimensiones incorrectas");
    }
    Double[][] C = new Double[A.length][B[0].length];
    for(Double[] c : C) {
      Arrays.fill(c, 0.0);
    }
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < B[0].length; ++j) {
        for (int k = 0; k < A[0].length; ++k) {
          C[i][j] += A[i][k] * B[k][j];
        }
      }
    }
    return C;
  }

  public static void printMatriz(Double[][] matriz) {
    System.out.println(Arrays.stream(matriz).map(Arrays::toString)
    .collect(Collectors.joining(System.lineSeparator())));
  }
  public static void main(String[] args) {
    int dimension = Integer.parseInt(args[0]);
    Double[][] A = matrizAleatoria(dimension, dimension);
    Double[][] B = matrizAleatoria(dimension, dimension);
    producto(A, B);
  }
}