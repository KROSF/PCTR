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

  public static void rellenar(int min, int max, int[] vector) {
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
            vector[j] = (r.nextInt(max + 1 -min) + min);
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

  public static void setEscalar(int e) {
    escalar = e;
  }

  public static void setVector(int[] v) {
    vector = v;
  }

  public static void setSize(int size) {
    vector = new int[size];
  }

  public static int getSize() {
    return vector.length;
  }

  public static void escalar() {
    int[] vector;
    for(int i = 0, j = 0; i < Integer.MAX_VALUE; i = (int) Math.pow(2, j), ++j) {
      vector = new int[i];
      rellenar(-22, 22, vector);
      setEscalar(22);
      setVector(vector);
      int n_hilos = Runtime.getRuntime().availableProcessors();
      int len = (int) Math.ceil(getSize() / n_hilos);
      escalaVectorParalelo[] hilos = new escalaVectorParalelo[n_hilos];

      for (int k = 0; k < hilos.length; ++k) {
        hilos[k] = new escalaVectorParalelo(k*len, (k+1) * len);
        hilos[k].start();
      }
      for (Thread t : hilos) {
        try {
          t.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void main(String[] args) {
    escalar();
  }

  private static int[] vector;
  private static int escalar;
  private int inicio;
  private int fin;
}