package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
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