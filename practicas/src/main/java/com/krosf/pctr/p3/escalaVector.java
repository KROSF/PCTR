package com.krosf.pctr.p3;

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

  public static void main(String[] args) {
    double[] vector = new double[Integer.MAX_VALUE/3];
    long start = System.currentTimeMillis();
    rellenar(-22, 22,vector);
    long end = System.currentTimeMillis();
    System.out.println(end-start + "ms");
    start = System.currentTimeMillis();
    escalar(vector, 22.22);
    end = System.currentTimeMillis();
    System.out.println(end-start + "ms");
  }
}