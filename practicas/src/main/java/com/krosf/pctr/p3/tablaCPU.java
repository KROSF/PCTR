package com.krosf.pctr.p3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * tablaCPU
 */
public class tablaCPU {

  public static void appendStrToFile(String fileName, String str) { 
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(fileName, true));
      out.write(str); 
      out.close(); 
    } catch (IOException e) { 
      System.out.println("exception occoured" + e); 
    } 
  }

  public static void mono(double[] vector, int size, int i) {
    long start = 0;
    long end = 0;
    start = System.currentTimeMillis();
    escalaVector.escalar(vector, 22.22 + i);
    end = System.currentTimeMillis();
    appendStrToFile("/Users/krosf/Desktop/mono.csv", String.format("%d, %d\n",size,end-start));
  }

  public static void core(double[] vector, int size, int i) {
    long start = 0;
    long end = 0;
    escalaVectorParalelo.setEscalar(22.22 + i);
    escalaVectorParalelo.setVector(vector);
    int n_hilos = Runtime.getRuntime().availableProcessors();
    int len = (int) Math.ceil(escalaVectorParalelo.getSize() / n_hilos);
    escalaVectorParalelo[] hilos = new escalaVectorParalelo[n_hilos];
    start = System.currentTimeMillis();
    for (int j = 0; j < hilos.length; ++j) {
      hilos[j] = new escalaVectorParalelo(j*len, (j+1) * len);
      hilos[j].start();
    }
    for (Thread t : hilos) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    end = System.currentTimeMillis();
    appendStrToFile("/Users/krosf/Desktop/core.csv", String.format("%d, %d\n",size,end-start));
  }

  public static void main(String[] args) {
    double[] vector;
    //int size;
    //for (int i = 0, j = 0; i < 10E9; i += (int) Math.pow(2, j) , ++j) {
      //size = i;
      vector = new double[1073741823];
      escalaVectorParalelo.rellenar(-22, 22, vector);
      //mono(vector, i, j);
      core(vector, 1073741823, 34);
    //}
  }
}