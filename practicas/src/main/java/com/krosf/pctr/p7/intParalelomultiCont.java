package com.krosf.pctr.p7;

import java.util.Random;

/**
 * intParalelomultiCont
 */
public class intParalelomultiCont extends Thread {
  public intParalelomultiCont(int iteraciones) {
    this.iteraciones = iteraciones;
    this.sum = 0;
    iteracionesGlobal += iteraciones;
  }

  public static void setFuncion(Funcion fun) {
    funcion = fun;
  }

  public static double getResult(intParalelomultiCont[] threads) {
    double total = 0.;
    for (int i = 0; i < threads.length; ++i) {
      total += threads[i].getSum();
    }
    return (1.0 / iteracionesGlobal) * total;
  }

  public double getSum() {
    return sum;
  }

  public static void main(String[] args) {
    int cores = Runtime.getRuntime().availableProcessors();
    intParalelomultiCont[] threads = new intParalelomultiCont[cores];
    setFuncion(x -> Math.sin(x));
    for (int i = 0; i < cores; ++i) {
      threads[i] = new intParalelomultiCont(100000);
      threads[i].start();
    }
    for (intParalelomultiCont t : threads) {
      try {
        t.join();
      } catch (Exception e) {
        System.err.println("Error join...");
      }
    }
    System.out.println(getResult(threads));
  }

  @Override
  public void run() {
    for (int i = 0; i < iteraciones; ++i) {
      sum += funcion.f(r.nextDouble());
    }
  }

  private Random r = new Random();
  private int iteraciones;
  private static int iteracionesGlobal = 0;
  private double sum;
  private static Funcion funcion;
}

interface Funcion {
  public double f(double x);
}