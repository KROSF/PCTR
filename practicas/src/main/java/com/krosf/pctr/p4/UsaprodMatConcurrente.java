package com.krosf.pctr.p4;

import java.util.Scanner;

/**
 * UsaprodMatConcurrente
 */
public class UsaprodMatConcurrente {

  private static Scanner scan = new Scanner(System.in);
  private static final String[] opciones = {
    "      Producto Matrices",
    "1. Insertar Matrices",
    "2. Matrices Aleatorias",
    "S. Salir"
  };

  private static void start(int[] dimension) {
    prodMatConcurrente[] hilos = new prodMatConcurrente[dimension[0]];
    for (int i = 0; i < hilos.length; i++) {
      hilos[i] = new prodMatConcurrente(i);
      hilos[i].start();
    }
    try {
      for (prodMatConcurrente h : hilos) {
        h.join();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
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
  public static void main(String[] args) {
    int[] dim = null;
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(opciones)) {
        case "1":
          dim = preguntarDimensiones();
          prodMatConcurrente.initMatrizUsuario(dim);
          start(dim);
          System.out.println("*** *** Matriz Resultado *** ***");
          prodMatConcurrente.printMatriz();
        break;
        case "2":
          dim = preguntarDimensiones();
          prodMatConcurrente.initMatrizAleatoria(dim);
          start(dim);
          System.out.println("*** *** Matriz Resultado *** ***");
          prodMatConcurrente.printMatriz();
        break;
      }
    }
  }
}