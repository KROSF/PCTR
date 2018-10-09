package pr1;

import java.util.Scanner;

public class NewtonRaphson {

  public interface Funcion {
    public double f(double x);
  }

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
