package com.krosf.pctr.p6;
public class ThreadTasks {

  public static void main(String[] args) {
    int cores = Runtime.getRuntime().availableProcessors();
    int sizeTask = 10;
    Error(cores, sizeTask);
    Solucion(cores, sizeTask);
  }

  /**
   * Método utilizado al asignar rango a los hilos en los ejemplos
   * proporcionados en las carpetas.
   */
  public static void Error(int cores, int sizeTask) {
    long coef = sizeTask / cores;
    long start = 0;
    long end = coef;
    System.out.println("Error");
    for (int i = 0; i < cores; ++i) {
      System.out.format("%d -- %d\n", start, end);
      System.out.format("Número de tareas sin completar: %d\n", sizeTask - end);
      start = end+1;
      end += coef;
    }
  }

  /**
   * Alternativa que propongo para solucionar el problema
   * aunque deja núcleos sin utilizar pero no tareas sin realizar.
   */
  public static void Solucion(int cores, int sizeTask) {
    int coef = (int) Math.ceil((sizeTask * 1.) / cores);
    int start = 0;
    int end = 0;
    boolean tasks = true;
    System.out.println("\nSolución");
    for (int i = 0; i < cores; ++i) {
      start = i * coef;
      end = (i + 1) * coef;
      if (tasks) {
        tasks = (end < sizeTask);
        end = (tasks) ? end : sizeTask;
        System.out.format("%d -- %d\n", start, end);
        System.out.format("Número de tareas sin completar: %d\n", sizeTask - end);
      }
    }
  }
}