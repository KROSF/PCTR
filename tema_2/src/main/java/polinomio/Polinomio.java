package polinomio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinomio {
    private double[] data;
    private int grado;

    public Polinomio() {
        this.data = new double[0];
        this.grado = 0;
    }

    public Polinomio(int grado) {
        this.data = new double[grado + 1];
        this.grado = grado;
    }

    public Polinomio(String pol, int grado) {
        data = new double[grado + 1];
        this.grado = grado;
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(pol);
        while (matcher.find()) {
            String match = matcher.group(1);
            if (match.matches(".*\\^.*")) {
                String[] split = match.split("\\^");
                data[Integer.parseInt(split[1])] = Double.parseDouble(split[0].substring(0, split[0].length() - 1));
            } else {
                String existx = match.substring(match.length() - 1);
                if (existx.equals("x") || existx.equals("X"))
                    data[1] = Double.parseDouble(match.substring(0, match.length() - 1));
                else
                    data[0] = Double.parseDouble(match);
            }
        }
    }

    public int grado() {
        return this.grado;
    }

    public double coeficiente(int grado) {
        return (grado > this.grado) ? 0.0 : this.data[grado];
    }

    public void coeficiente(int grado, double coeficiente) {
        if (grado > this.grado || grado < 0)
            throw new IndexOutOfBoundsException("Fuera de Rango");
        this.data[grado] = coeficiente;
    }

    public Polinomio suma(Polinomio pol) {
        int max = Math.max(this.grado, pol.grado);
        Polinomio rslt = new Polinomio(max);
        for (int i = 0; i <= max; ++i)
            rslt.coeficiente(i, this.coeficiente(i) + pol.coeficiente(i));
        return rslt;
    }

    public Polinomio resta(Polinomio pol) {
        int max = Math.max(this.grado, pol.grado);
        Polinomio rslt = new Polinomio(max);
        for (int i = 0; i <= max; ++i)
            rslt.coeficiente(i, this.coeficiente(i) - pol.coeficiente(i));
        return rslt;
    }

    public Polinomio producto(Polinomio pol) {
        Polinomio rslt = new Polinomio(this.grado * pol.grado);
        for (int i = 0; i <= this.grado; ++i)
            for (int j = 0; j <= pol.grado; ++j)
                rslt.coeficiente(i + j, rslt.coeficiente(i + j) + this.coeficiente(i) * pol.coeficiente(j));
        return rslt;
    }

    public Polinomio derivada() {
        Polinomio rslt = new Polinomio(this.grado - 1);
        for (int i = this.grado; i >= 1; --i)
            rslt.coeficiente(i - 1, i * this.coeficiente(i));
        return rslt;
    }

    public String toString() {
        if (grado == 0)
            return "" + data[0];
        if (grado == 1)
            return data[1] + "x + " + data[0];
        String rslt = "";
        boolean first = true;
        for (int i = grado; i >= 0.0; i--) {
            if (data[i] == 0.0) {
                continue;
            } else if (data[i] > 0.0 && first) {
                rslt = rslt + data[i];
                first = false;
            } else if (data[i] > 0.0) {
                rslt = rslt + " + " + (data[i]);
            } else if (data[i] < 0.0)
                rslt = rslt + " - " + (-data[i]);
            if (i == 1.0) {
                rslt = rslt + "x";
            } else if (i > 1.0)
                rslt = rslt + "x^" + i;
        }
        return rslt;
    }

    public static void main(String[] args) {
        Polinomio pol = new Polinomio("2x^3+4x^2+5x-4", 3);
        System.out.println(pol.suma(new Polinomio("8x^3+4x^2+5x-4", 3)).toString());
    }
}