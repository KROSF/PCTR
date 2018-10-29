package com.krosf.pctr.s3;

import java.util.Random;


/**
 * monteCarlo
 */
public class monteCarlo extends Thread {

  public monteCarlo(int iteraciones) {
    this.iteraciones = iteraciones;
  }

  public void setFuncion(Funcion fun) {
    fx = fun;
  }

  public static void setLimits(double mini, double maxi) {
    min = mini;
    max = maxi;
  }

  public static double getSum() {
    return (max-min) * sum;
  }

  public interface Funcion {
    public double f(double x);
  }

  public static void main(String[] args) {
    int iteraciones = 100000000;
    int nhilos = 2000;
    setLimits(0, 1);
    monteCarlo[] hilos = new monteCarlo[nhilos];
    long start = System.currentTimeMillis();
    for(int i = 0; i < hilos.length; ++i) {
      hilos[i] = new monteCarlo((int)iteraciones/nhilos);
      hilos[i].setFuncion(x -> Math.sin(x));
      hilos[i].start();
    }
    for(monteCarlo h : hilos) {
      try {
        h.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    long end = System.currentTimeMillis();
    System.out.println(getSum()/iteraciones);
    System.out.println("Tiempo: " + (end-start));
    start = System.currentTimeMillis();
    System.out.println(intDefinidaMonteCarlo.integralMonteCarlo(0, 1, iteraciones, x -> Math.sin(x)));
    end = System.currentTimeMillis();
    System.out.println("Tiempo: " + (end-start));
  }

  @Override
  public void run() {
    for(int i = 0; i < iteraciones; ++i) {
      synchronized(lock){
        sum += fx.f(min + (max - min) * r.nextDouble());
      }
    }
  }

  private Random r = new Random();
  private int iteraciones;
  private static double sum = 0;
  private static double min;
  private static double max;
  private Funcion fx;
  private static Object lock = new Object();
}