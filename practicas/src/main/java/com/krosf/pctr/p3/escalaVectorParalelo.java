package com.krosf.pctr.p3;

import java.util.Random;

/**
 * escalaVectorParalelo
 */
public class escalaVectorParalelo extends Thread {

  public escalaVectorParalelo(int inicio, int fin) {
    this.inicio = inicio;
    this.fin = fin;
  }

  public static void rellenar(int min, int max, double[] vector) {
    int n_hilos = Runtime.getRuntime().availableProcessors();
    int len = (int) Math.ceil(vector.length / n_hilos);
    
    Thread[] hilos = new Thread[Runtime.getRuntime().availableProcessors()];
    for(int i = 0; i < hilos.length; ++i) {
      int s = i;
      hilos[i] =  new Thread( new Runnable() {
        @Override
        public void run() {
          Random  r = new Random();
          for(int j = s * len; j < (s+1) * len; ++j) {
            vector[j] = (r.nextInt(max + 1 -min) + min) + r.nextDouble();
          }
        }
      });
      hilos[i].start();
    }
    for (Thread t : hilos) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void run() {
    for(int i = inicio; i < fin; ++i) {
      vector[i] *= escalar;
    }
  }

  public static void setEscalar(double e) {
    escalar = e;
  }

  public static void setVector(double[] v) {
    vector = v;
  }

  public static void setSize(int size) {
    vector = new double[size];
  }

  public static int getSize() {
    return vector.length;
  }

  public static void main(String[] args) {
    int iter = Integer.MAX_VALUE/3;
    setEscalar(22.22);
    double[] vector = new double[iter];
    rellenar(-22, 22,vector);
    setVector(vector);
    int n_hilos = Runtime.getRuntime().availableProcessors();
    int len = (int) Math.ceil(getSize() / n_hilos);
    escalaVectorParalelo[] hilos = new escalaVectorParalelo[n_hilos];
    long start = System.currentTimeMillis();
    for (int i = 0; i < hilos.length; ++i) {
      hilos[i] = new escalaVectorParalelo(i*len, (i+1) * len);
      hilos[i].start();
    }
    for (Thread t : hilos) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    long end = System.currentTimeMillis();
    System.out.println(end-start + "ms");
  }

  private static double[] vector;
  private static double escalar;
  private int inicio;
  private int fin;
}