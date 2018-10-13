package com.krosf.pctr.s1;

/** raizporBiseccion */
public class raizporBiseccion {
  public interface Funcion {
    public double f(double x);
  }

  public static double biseccion(Funcion fun, double a, double b, double error) {
    double c = 0;
    double f_a = 0;
    double f_c = 0;
    while (b - a > error) {
      c = (a + b) / 2;
      f_a = fun.f(a);
      f_c = fun.f(c);
      if (f_a * f_c < 0) {
        b = c;
      } else if (f_a * f_c > 0) {
        a = c;
      } else {
        a = b = c;
      }
    }
    return c;
  }


  public static void main(String[] args) {
    //System.out.println(biseccion(x -> Math.pow((Math.E), x) - (3 * x), 0.0, 1.0, 0.0010));
    //System.out.println(biseccion(x -> Math.cos(x) - Math.pow(x, 3), 0.0, 1.0, 0.0001));
    System.out.println(biseccion(x -> Math.pow(x, 2) - 5, 2.0, 3.0, 0.0001));
  }
}
