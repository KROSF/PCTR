package com.krosf.pctr.p3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

  public static void appendStrToFile(String fileName, String str) { 
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
      out.write(str); 
      out.close(); 
    } catch (IOException e) { 
      System.out.println("exception occoured" + e); 
    } 
  }

  public static void escalar() {
    double[] vector;
    long start;
    long end;
    for(int i = 0, j = 0; i < 10E9+1; i = (int) Math.pow(10, j), ++j) {
      vector = new double[i];
      rellenar(-22, 22, vector);
      setEscalar(22.22);
      setVector(vector);
      int n_hilos = Runtime.getRuntime().availableProcessors();
      int len = (int) Math.ceil(getSize() / n_hilos);
      escalaVectorParalelo[] hilos = new escalaVectorParalelo[n_hilos];
      start = System.nanoTime();
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
      end = System.nanoTime();
      appendStrToFile("/Users/krosf/Desktop/core.csv", String.format("%d, %d\n", i, end-start));
    }
  }

  public static void main(String[] args) {
    escalar();
  }

  private static double[] vector;
  private static double escalar;
  private int inicio;
  private int fin;
}