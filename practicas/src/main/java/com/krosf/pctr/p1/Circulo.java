package com.krosf.pctr.p1;

/**
*
* Clase con el metodo {@code main} que imprime por pantalla el volumen de un cono.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
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
