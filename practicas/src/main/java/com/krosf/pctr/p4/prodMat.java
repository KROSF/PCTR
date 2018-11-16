package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
* Clase para realizar el producto matricial secuencialmente.
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class prodMat {

  /**
   * Metodo para crear una matriz con números aleatorios.
   * @param filas de la matriz.
   * @param columnas de la matriz.
   * @return matriz[filas][columnas] de elementos aleatorios.
   */
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
   * Metodo para realizar el producto matricial.
   * @param A matriz de dimension m x n
   * @param B matriz de dimension n x r
   * @return matriz de dimension m x r
   */
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

  /**
   * Metodo para visualizar los datos de una matriz por la salida estandar.
   * @param matriz a visualizar.
   */
  public static void printMatriz(Double[][] matriz) {
    System.out.println(Arrays.stream(matriz).map(Arrays::toString)
    .collect(Collectors.joining(System.lineSeparator())));
  }
  public static void main(String[] args) {
    int[] dim = null;
    Double[][] A = null;
    Double[][] B = null;
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(opciones)) {
        case "1":
          dim = preguntarDimensiones();
          A = matrizUsuario(dim[0], dim[1]);
          B = matrizUsuario(dim[1], dim[2]);
          System.out.println("*** *** Matriz Resultado *** ***");
          printMatriz(producto(A, B));
        break;
        case "2":
          dim = preguntarDimensiones();
          A = matrizAleatoria(dim[0], dim[1]);
          B = matrizAleatoria(dim[1], dim[2]);
          System.out.println("*** *** Matriz Resultado *** ***");
          printMatriz(producto(A, B));
        break;
      }
    }
  }

  /**
   * Metodo para obtener las dimensiones de las matrices.
   * @return array con las dimensiones de las matrices.
   */
  private static int[] preguntarDimensiones() {
    int[] dimensiones = new int[3];
    System.out.print("Ingrese filas A: ");
    dimensiones[0] = scan.nextInt();
    System.out.print("Ingrese columnas A: ");
    dimensiones[1] = scan.nextInt();
    System.out.print("Ingrese columnas B: ");
    dimensiones[2] = scan.nextInt();
    scan.nextLine();
    return dimensiones;
  }

  /**
   * Metodo que genera un menu de usuario en la salida estandar.
   * @param opciones con las que se genera el menu.
   * @return opcion selecionada por el usuario.
   */
  private static String displayOpciones(String[] opciones) {
    for (String opcion : opciones) {
      System.out.println(opcion);
    }
    System.out.print("Seleccione una opcion: ");
    String rsp = scan.nextLine();
    scan.reset();
    return rsp;
  }

  private static Scanner scan = new Scanner(System.in);
  private static final String[] opciones = {
    "      Producto Matrices",
    "1. Insertar Matrices",
    "2. Matrices Aleatorias",
    "S. Salir"
  };
}