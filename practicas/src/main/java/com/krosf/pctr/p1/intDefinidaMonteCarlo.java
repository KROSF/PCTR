package com.krosf.pctr.p1;

import java.util.Random;
import java.util.Scanner;

/**
*
* Clase que modela el metode de <a href="https://es.wikipedia.org/wiki/M%C3%A9todo_de_Montecarlo"Monte Carlo</a>.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class intDefinidaMonteCarlo {

  /**
   * interfas para pasar las funciones matemáticas a los metodos de la clase.
   */
  public interface Funcion {
    public double f(double x);
  }

  /**
   *
   * @param min Valor minimo del intervalo de integración.
   * @param max Valor maximo del itervalo de integración.
   * @param iteraciones número de puntos a generar.
   * @param fx Función a la que se le aplica el metodo.
   * @return Aproximacion de la solución real.
   */
  public static double integralMonteCarlo(double min, double max, double iteraciones, Funcion fx) {
    Random r = new Random();
    double sum = 0.0;
    for (double i = 0.0; i < iteraciones; ++i) {
      sum += fx.f(min + (max - min) * r.nextDouble());
    }
    return ((max - min) / iteraciones) * sum;
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Inserte el numero de puntos para la aproximacion: ");
    int n = scan.nextInt();
    System.out.format(
        "Aproximacion para sin(x) = %f\n", integralMonteCarlo(0, 1, n, x -> Math.sin(x)));
    System.out.format("Aproximacion para x = %f\n", integralMonteCarlo(0, 1, n, x -> x));
    scan.close();
  }
}
