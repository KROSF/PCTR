package com.krosf.pctr.p1;

import java.util.Scanner;

/**
*
* Clase que contine los metodos necesarios para aplicar <a href="https://es.wikipedia.org/wiki/M%C3%A9todo_de_Newton">NewtonRaphson</a>
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class NewtonRaphson {

  /**
   * interfas para pasar las funciones matemáticas a los metodos de la clase.
   */
  public interface Funcion {
    public double f(double x);
  }

  /**
   * Metodo Newton Raphson
   * @param fun función matemática.
   * @param dfun derivada de la función matemática.
   * @param x0 aproximación incial.
   * @param e error que se asume en la solución.
   */
  public static void newton(Funcion fun, Funcion dfun, double x0, double e) {
    double delta = Math.abs(fun.f(x0));
    while (delta > e) {
      x0 = x0 - fun.f(x0) / dfun.f(x0);
      delta = Math.abs(fun.f(x0));
      System.out.format("x:    %f \nf(x): %f\n", x0, fun.f(x0));
    }
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.format("Funcion 1\nIngrese aproximacion inicial: ");
    newton(
        x -> Math.cos(x) - Math.pow(x, 3),
        x -> (-Math.sin(x)) - 3 * Math.pow(x, 2),
        s.nextDouble(),
        0.00001);
    System.out.format("Funcion 2\nIngrese aproximacion inicial: ");
    newton(
        x -> Math.pow(x, 2) - 5,
        x -> 2 * x,
        s.nextDouble(),
        0.000001);
    s.close();
  }
}
