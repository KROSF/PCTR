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
    double[] vector;
    for(int i = 0, j = 0; i < Integer.MAX_VALUE; i = (int) Math.pow(2, j), ++j) {
      vector = new double[i];
      rellenar(-22, 22, vector);
      escalar(vector, 22.22);
    }
  }
}