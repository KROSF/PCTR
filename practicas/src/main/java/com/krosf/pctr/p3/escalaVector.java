package com.krosf.pctr.p3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * escalaVector
 */
public class escalaVector {

  public static void rellenar(int min, int max, double[] vector) {
    Random  r = new Random();
    for (int i = 0; i < vector.length; ++i) {
      vector[i] = (r.nextInt(max + 1 -min) + min) + r.nextDouble();
    }
  } 

  public static void escalar(double[] vector, double escalar) {
    for (int i = 0; i < vector.length; ++i) {
      vector[i] *= escalar;
    }
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

  public static void main(String[] args) {
    double[] vector;
    long start;
    long end;
    for(int i = 0, j = 0; i <= 10E9; i = (int) Math.pow(10, j), ++j) {
      vector = new double[i];
      rellenar(-22, 22, vector);
      start = System.nanoTime();
      escalar(vector, 22.22);
      end = System.nanoTime();
      appendStrToFile("/Users/krosf/Desktop/mono.csv", String.format("%d, %d\n", i, end-start));
    }
  }
}