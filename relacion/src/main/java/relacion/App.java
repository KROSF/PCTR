package relacion;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    /**
     * 
     * @param n numero de enteros a sumar.
     * @return
     */
    public static int sumaCuadrado(int n) {
        return (n * (n + 1) * ((2 * n) + 1)) / 6;
    }

    /**
     * 
     * @param n numero a partir del cual obtener la suma.
     * @return suma desde n+1 hasta n+100
     */
    public static int sumaNintervalo(int n) {
        return (100) * (n + 1 + (100 + n)) / 2;
    }

    /**
     * 
     * @param a entero
     * @param b entero
     * @return mcd
     */
    public static int mcd(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        while (b > 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    public static double[] ecuacucionSegundoGrado(int a, int b, int c) {
        if (a == 0)
            return null;
        return new double[] { (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a),
                (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a) };
    }

    public static int factorial(int n) {
        if (n <= 1)
            return 1;
        else
            return n * factorial(n - 1);
    }

    public static int Ackermann(int m, int n) {
        if (m == 0) {
            return n + 1;
        } else if (n == 0) {
            return Ackermann(m - 1, 1);
        } else {
            return Ackermann(m - 1, Ackermann(m, n - 1));
        }
    }

    public static int maxTres(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    public static double farenheitCelsius(double f) {
        return (5 * (f - 32)) / 9;
    }

    public static void mainFC() {
        Scanner scan = new Scanner(System.in);
        System.out.print("°F: ");
        double a = scan.nextDouble();
        while (a != 999) {
            System.out.format("%.2f °F = %.2f °C\n", a, farenheitCelsius(a));
            System.out.print("°F: ");
            a = scan.nextDouble();
        }
        scan.close();
    }

    public static void main(String[] args) {
        mainFC();
    }
}
