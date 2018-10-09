package pr1;

import java.util.Random;
import java.util.Scanner;

public class intDefinidaMonteCarlo {
  public interface Funcion {
    public double f(double x);
  }

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
