package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Clase para multiplar una Matriz por un Vector.
 *
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class matVector {

  /**
   * Metodo para realizar el producto matriz vector.
   * @param matriz de dimension m x n
   * @param vector de dimension n
   * @return vector de dimension m
   */
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
   * Metodo para crear un vector de números aleatorios.
   * @param tam tamaño del vector.
   * @return vector[tam].
   */
  public static Double[] vectorAleatorio(int tam) {
    Double[] aleatorio = new Double[tam];
    Random r = new Random();
    for (int i = 0; i < tam; ++i) {
      aleatorio[i] = r.nextInt(22) + r.nextDouble();
    }
    return aleatorio;
  }

  /**
   * Metodo para visualizar un vector por la salida estandar.
   * @param vector a visualizar.
   */
  public static void printVector(Double[] vector) {
    System.out.println(Arrays.toString(vector));
  }

  public static void main(String[] args) {
    int[] dim = null;
    Double[][] matriz = null;
    Double[] vector = null;
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(opciones)) {
        case "1":
          dim = preguntarDimensiones();
          matriz = matrizUsuario(dim[0], dim[1]);
          vector = vectorUsuario(dim[1]);
          System.out.println("*** *** Vector Resultado *** ***");
          printVector(producto(matriz, vector));
          break;
        case "2":
          dim = preguntarDimensiones();
          matriz = matrizAleatoria(dim[0], dim[1]);
          vector = vectorAleatorio(dim[1]);
          System.out.println("*** *** Vector Resultado *** ***");
          printVector(producto(matriz, vector));
          break;
      }
    }
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

  /**
   * Metodo para obtener las dimensiones de la matriz.
   * @return array con las dimensiones de la matriz.
   */
  private static int[] preguntarDimensiones() {
    int[] dimensiones = new int[2];
    System.out.print("Ingrese filas: ");
    dimensiones[0] = scan.nextInt();
    System.out.print("Ingrese columnas: ");
    dimensiones[1] = scan.nextInt();
    System.out.printf("la dimension del vector sera %d\n", dimensiones[1]);
    scan.nextLine();
    return dimensiones;
  }

  /**
   * Metodo para crear un matriz de dimensiones n x m.
   * @param filas con las que contara la matriz.
   * @param columnas con las que contara la matriz
   * @return matriz[filas][columnas].
   */
  private static Double[][] matrizUsuario(int filas, int columnas) {
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
   * Metodo para crear un vector de dimension m.
   * @param tam tamaño del vector a crear.
   * @return vector[tam].
   */
  private static Double[] vectorUsuario(int tam) {
    Double[] vector = new Double[tam];
    System.out.println("Ingrese los elementos del vector");
    for (int i = 0; i < tam; ++i) {
      System.out.printf("Elemento (%d) = ", i);
      vector[i] = scan.nextDouble();
    }
    scan.nextLine();
    return vector;
  }

  private static Scanner scan = new Scanner(System.in);
  private static final String[] opciones = {
    "      Matriz - Vector",
    "1. Insertar Matriz - Vector",
    "2. Matriz - Vector Aleatorios",
    "S. Salir"
  };
}
