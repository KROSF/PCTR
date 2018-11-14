package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * matVectorConcurrente
 */
public class matVectorConcurrente implements Runnable {

  private int fila;
  private static volatile Double[][] matriz;
  private static volatile Double[] vector;
  private static volatile Double[] resultado;
  private static Scanner scan = new Scanner(System.in);
  private static final String[] opciones = {
    "      Matriz - Vector",
    "1. Insertar Matriz - Vector",
    "2. Matriz - Vector Aleatorios",
    "S. Salir"
  };

  private static String displayOpciones(String[] opciones) {
    for (String opcion : opciones) {
      System.out.println(opcion);
    }
    System.out.print("Seleccione una opcion: ");
    String rsp = scan.nextLine();
    scan.reset();
    return rsp;
  }

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

  public static void initMatrizVectorUsuario(int filas, int columnas) {
    matriz = matrizUsuario(filas, columnas);
    vector = vectorUsuario(columnas);
    resultado = new Double[filas];
    Arrays.fill(resultado, 0.0);
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
    int[] dim = null;
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(opciones)) {
        case "1":
          dim = preguntarDimensiones();
          initMatrizVectorUsuario(dim[0], dim[1]);
          start(dim[0], dim[1]);
          System.out.println("*** *** Vector Resultado *** ***");
          printResultado();
        break;
        case "2":
          dim = preguntarDimensiones();
          initMatrizVectorAleatorios(dim[0], dim[1]);
          start(dim[0], dim[1]);
          System.out.println("*** *** Vector Resultado *** ***");
          printResultado();
        break;
      }
    }
  }

}