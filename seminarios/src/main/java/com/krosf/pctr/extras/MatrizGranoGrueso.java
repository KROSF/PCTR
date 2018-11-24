package com.krosf.pctr.extras;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Matriz Grano Grueso
 *
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class MatrizGranoGrueso implements Runnable {
  public MatrizGranoGrueso(int inicio, int fin) {
    this.inicio = inicio;
    this.fin = fin;
  }

  /**
   * Metodo que inicializa la matriz y vector aleatoriamente.
   *
   * @param filas    de la matriz a inicializar.
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
   * Metodo para ver el vector resultado por la salida estandar.
   */
  public static void printResultado() {
    System.out.println(Arrays.toString(resultado));
  }

  @Override
  public void run() {
    for (int i = inicio; i < fin; ++i) {
      for (int j = 0; j < matriz[0].length; ++j) {
        resultado[i] += matriz[i][j] + vector[j];
      }
    }
  }

  public static void main(String[] args) {
    int cores = Runtime.getRuntime().availableProcessors();
    int filas = 7;
    int columnas = 4;
    int len = (int) Math.ceil((filas * 1.) / cores);
    int endix = 0;
    boolean tasks = true;
    initMatrizVectorAleatorios(filas, columnas);
    ExecutorService exec = Executors.newFixedThreadPool(cores);
    for (int i = 0; i < cores; ++i) {
      endix = (i + 1) * len;
      if (tasks) {
        tasks = (endix < filas);
        exec.execute(new MatrizGranoGrueso(i * len, tasks ? endix : filas));
      }
    }
    exec.shutdown();
    printResultado();
  }

  private int inicio;
  private int fin;
  private static Double[] vector;
  private static Double[] resultado;
  private static Double[][] matriz;
}