package com.krosf.pctr.p3;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Cuadrado extends Poligono {

  public Cuadrado() {
    super(4);
  }

  public double lado() {
    return super.lado(1);
  }

  public double diagonal() {
    return lado() * Math.sqrt(2);
  }

  @Override
  public void add(Punto p) {
    if (size() >= 4) {
      throw new IndexOutOfBoundsException("Número de vertices incorrectos");
    }
    super.add(p);
    if ( size() == 4 && !esRegular()) {
        throw new IllegalArgumentException("Los vertices no corresponden con los de un cuadrado");
    }
  }

  @Override
  public String toString() {
    return "Cuadrado\n" +
           "Area: " + area() + "\n" +
           "Lado: " + lado() + "\n" +
           "Diagonal: " + diagonal() + "\n" +
           "Perimetro: " + perimetro();
  }
}