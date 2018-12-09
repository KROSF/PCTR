package com.krosf.pctr.p7;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * intParalelauniCont
 */
public class intParalelauniCont implements Runnable {

  public intParalelauniCont(int iteraciones) {
    this.iteraciones = iteraciones;
    iteracionesGlobal += iteraciones;
  }

  public static void setFuncion(Funcion fun) {
    funcion = fun;
  }

  public static double getResult() {
    return (1.0/ iteracionesGlobal) * sum;
  }

  @Override
  public void run() { 
    synchronized(funcion) {
      for (int i = 0; i < iteraciones; ++i) {
        sum += funcion.f(r.nextDouble());
      }
    }
  }

  public static void main(String[] args) {
    int cores = 2 * Runtime.getRuntime().availableProcessors();
    ExecutorService service = Executors.newFixedThreadPool(cores);
    setFuncion(x -> Math.sin(x));
    for (int i = 0; i < cores; ++i) {
      service.execute(new intParalelauniCont(100000));
    }
    service.shutdown();
    while(!service.isTerminated());
    System.out.println(getResult());
  }

  private Random r = new Random();
  private int iteraciones;
  private static int iteracionesGlobal = 0;
  private static double sum = 0.0;
  private static Funcion funcion;
}

interface Funcion {
  public double f(double x);
}