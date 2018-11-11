package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;

/**
*
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class matVector {
  public static Double[] producto(Double[][] matriz, Double[] vector) {
    if (matriz[0].length != vector.length) {
      throw new IndexOutOfBoundsException("Dimensiones no apropiadas");
    }
    Double[] resultado = new Double[matriz.length];
    Arrays.fill(resultado, 0.0);
    for (int i = 0; i < matriz.length; ++i) {
      for (int j = 0; j < vector.length; ++j) {
        resultado[i] += matriz[i][j] * vector[j];
      }
    }
    return resultado;
  }

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

  public static Double[] vectorAleatorio(int tam) {
    Double[] aleatorio = new Double[tam];
    Random r = new Random();
    for (int i = 0; i < tam; ++i) {
      aleatorio[i] = r.nextInt(22) + r.nextDouble();
    }
    return aleatorio;
  }

  public static void printVector(Double[] vector) {
    System.out.println(Arrays.toString(vector));
  }

  public static void main(String[] args) {
    int dimension = Integer.parseInt(args[0]);
    Double[][] matriz = matrizAleatoria(dimension, dimension);
    Double[] vector = vectorAleatorio(dimension);
    producto(matriz, vector);
  }
}