package com.krosf.pctr.p4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
* Clase para realizar la multiplicación matriz - vector de forma concurrente
*
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class matVectorConcurrente implements Runnable {

  private int fila;
  private static Double[][] matriz;
  private static Double[] vector;
  private static volatile Double[] resultado;
  private static Scanner scan = new Scanner(System.in);
  private static final String[] opciones = {
    "      Matriz - Vector",
    "1. Insertar Matriz - Vector",
    "2. Matriz - Vector Aleatorios",
    "S. Salir"
  };

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
   * Metodo para rellenar un vector con datos de la entrada estandar.
   * @param tam tamaño del vector a rellenar.
   * @return vector con todos sus elementos inicializados.
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

  /**
   * Constructor que recibe la fila sobre la actuara un hilo.
   * @param fila correspodiente a la matriz.
   */
  public matVectorConcurrente(int fila) {
    this.fila = fila;
  }

  /**
   * Metodo que inicializa la matriz y vector aleatoriamente.
   * @param filas de la matriz a inicializar.
   * @param columnas de la matriz a inicializar.
   */
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

  /**
   * Metodo para inicializar la matriz y el vector con datos de la entrada estandar.
   * @param filas de la matriz a inicializar.
   * @param columnas de la matriz a inicializar.
   */
  public static void initMatrizVectorUsuario(int filas, int columnas) {
    matriz = matrizUsuario(filas, columnas);
    vector = vectorUsuario(columnas);
    resultado = new Double[filas];
    Arrays.fill(resultado, 0.0);
  }

  /**
   * Metodo para ver el vector resultado por la salida estandar.
   */
  public static void printResultado() {
    System.out.println(Arrays.toString(resultado));
  }

  @Override
  public void run() {
    for (int i = 0; i < matriz[0].length; ++i) {
      resultado[fila] += matriz[fila][i] + vector[i];
    }
  }

  /**
   * Metodo para crear los hilo
   * @param filas de la matriz a procesar.
   * @param columnas de la matriz a procesar.
   */
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