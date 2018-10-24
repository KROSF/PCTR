package com.krosf.pctr.p3;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Triangulo extends Poligono {

  /**
   * 
   */
  public Triangulo() {
    super(3);
  }

  /**
   * 
   * @return
   */
  public double ladoA() {
    return super.lado(1);
  }

  /**
   * 
   * @return
   */
  public double ladoB() {
    return super.lado(2);
  }

  /**
   * 
   * @return
   */
  public double ladoC() {
    return super.lado(3);
  }

  /**
   * 
   * @return
   */
  public double anguloA() {
    return Math.toDegrees(Math.acos((Math.pow(ladoA(), 2) - Math.pow(ladoB(), 2) - Math.pow(ladoC(), 2))/ (2 * ladoB() * ladoC())));
  }

  /**
   * 
   * @return
   */
  public double anguloB() {
    return Math.toDegrees(Math.acos((Math.pow(ladoB(), 2) - Math.pow(ladoA(), 2) - Math.pow(ladoC(), 2))/ (2 * ladoA() * ladoC())));
  }

  /**
   * 
   * @return
   */
  public double anguloC() {
    return Math.toDegrees(Math.acos((Math.pow(ladoC(), 2) - Math.pow(ladoA(), 2) - Math.pow(ladoB(), 2))/ (2 * ladoB() * ladoA())));
  }

  /**
   * 
   * @return
   */
  public String type() {
    String type = "Isoceles";
    if (esRegular()) {
      type = "Equilatero";
    } else if (ladoA() != ladoB() && ladoB() != ladoC() && ladoC() != ladoA()) {
      type = "Escaleno";
    }
    return type;
  }

  @Override
  public void add(Punto p) {
    if (size() >= 3) {
      throw new IndexOutOfBoundsException("Número de vertices incorrectos");
    }
    super.add(p);
  }

  @Override
  public String toString() {
    return "Triangulo " + type() + "\n" +
           "Lados: a = " + ladoA() + ", b = " + ladoB() + ", c = " + ladoC() + "\n" +
           "Angulos: a = " + anguloA() + ", b = " + anguloB() + ", c = " + anguloC() + "\n" +
           "Area: " + area() + "\n" + "Perimetro: " + perimetro();
  }
}