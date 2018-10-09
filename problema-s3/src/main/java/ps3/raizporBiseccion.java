package ps3;

/** raizporBiseccion */
public class raizporBiseccion {
  public interface Funcion {
    public double f(double x);
  }

  public static double raiz(Funcion fun, double a, double b, double error, int iteraciones) {
    double c = 0;
    double f_a = 0;
    double f_c = error;
    for (int i = 1; i <= iteraciones && Math.abs(f_c) >= 0; ++i) {
      c = (a + b) / 2;
      f_a = fun.f(a);
      f_c = fun.f(c);
      if (f_c * f_a < 0) {
        b = c;
        f_a = fun.f(a);
        c = (a + b) / 2;
        f_c = fun.f(c);
      } else {
        a = c;
        f_a = fun.f(a);
        c = (a + b) / 2;
        f_c = fun.f(c);
      }
    }
    return c;
  }

  public static void main(String[] args) {
    System.out.println(raiz(x -> Math.pow((Math.E), x) - (3 * x), 0.0, 1.0, 0.0010, 100));
    System.out.println(raiz(x -> Math.cos(x) - Math.pow(x, 3), 0.0, 1.0, 0.0001, 100));
    System.out.println(raiz(x -> Math.pow(x, 2) - 5, 2.0, 3.0, 0.0001, 100));
  }
}
