package pr1;

public class Circulo {

  public static void main(String[] args) {
    final double PI = Math.PI;
    double diametro = 14.2;
    double altura = 20;
    System.out.format(
        "%.2f", 
        ((PI * Math.pow((diametro / 2), 2) * altura) / 3));
  }
}
