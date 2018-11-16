package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
* Clase para multiplicar matrices en grano fino.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class prodMatConcurrente extends Thread {

  private int fila;
  private static Double[][] A;
  private static Double[][] B;
  private static volatile Double[][] resultado;
  private static Scanner scan = new Scanner(System.in);

  /**
   * Constructor que recibe la fila sobre la que debe actuar.
   * @param fila sobre la que se realizarán las operaciones del hilo.
   */
  public prodMatConcurrente(int fila) {
    this.fila = fila;
  }

  /**
   * Metodo para crear una matriz aleatoria de m x n.
   * @param filas con las que contara las matriz.
   * @param columnas con las que contara la matriz.
   * @return Una matriz {@code Double[filas][columnas]}.
   */
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

  /**
   * Metodo para crear una matriz de dimension m x n con datos instroducidos por la entrada estandar.
   * @param filas con las que contara las matriz.
   * @param columnas con las que contara la matriz.
   * @return Una matriz {@link Double}[filas][columnas].
   */
  public static Double[][] matrizUsuario(int filas, int columnas) {
    Double[][] matriz = new Double[filas][columnas];
    System.out.println("Ingrese los elementos de la matriz");
    for (int i = 0; i < filas; ++i) {
      for (int j = 0; j < columnas; ++j) {
        System.out.printf("Elemento (%d, %d) = ", i, j);
        matriz[i][j] = scan.nextDouble();
      }
    }
    scan.nextLine();
    return matriz;
  }

  /**
   * Metodo para inicializar las matrices aleatoriamente.
   * @param dim {@link Arrays} con las dimensiones de las matrices.
   */
  public static void initMatrizAleatoria(int[] dim) {
    A = matrizAleatoria(dim[0], dim[1]);
    B = matrizAleatoria(dim[1], dim[2]);
    resultado = new Double[dim[0]][dim[2]];
    for (Double[] c : resultado) {
      Arrays.fill(c, 0.0);
    }
  }

  /**
   * Metodo para inicializar las matrices con datos de la entrada estandar.
   * @param dim {@link Arrays} con las dimensiones de las matrices.
   */
  public static void initMatrizUsuario(int[] dim) {
    A = matrizUsuario(dim[0], dim[1]);
    B = matrizUsuario(dim[1], dim[2]);
    resultado = new Double[dim[0]][dim[2]];
    for (Double[] c : resultado) {
      Arrays.fill(c, 0.0);
    }
  }

  /**
   * Metodo para visualizar los datos de la matriz resultado por la salida estandar.
   */
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