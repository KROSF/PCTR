package com.krosf.pctr.p2;

/**
*
* Representacion del concepto matemático elipse.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Elipse {

  /**
   * Constructor por defecto.
   */
  public Elipse() {
    semiEjeMayor = semiEjeMenor = 0.0;
  }

  /**
   * Construye una elipse con los semiejes proporcionados.
   * @param semiEjeMayor de la elipse.
   * @param semiEjeMenor de la elipse.
   */
  public Elipse(double semiEjeMayor, double semiEjeMenor) {
    this.semiEjeMayor = semiEjeMayor;
    this.semiEjeMenor = semiEjeMenor;
  }

  /**
   * 
   * @return El eje mayor de la elipse.
   */
  public double getEjeMayor() {
    return this.semiEjeMayor * 2;
  }

  /**
   * 
   * @return El eje menor de la elipse.
   */
  public double getEjeMenor() {
    return this.semiEjeMenor * 2;
  }

  /**
   * 
   * @return El semieje mayor de la elipse.
   */
  public double getSemiEjeMayor() {
    return this.semiEjeMayor;
  }

  /**
   * 
   * @return El semieje menor de la elipse.
   */
  public double getSemiEjeMenor() {
    return this.semiEjeMenor;
  }

  /**
   * 
   * @return El area de la elipse.
   */
  public double getArea() {
    return Math.PI * semiEjeMayor * semiEjeMenor;
  }

  /**
   * Calcula el perimetro de la elipse utilizando la aproximacion de <a href="https://arxiv.org/pdf/math/0506384.pdf">Ramanujan</a>
   * @return La circunferencia de la elipse.
   */
  public double getPerimetro() {
    double sumaEjes = (semiEjeMayor + semiEjeMenor );
    double sumaEjes_2 = Math.pow(sumaEjes, 2);
    double restaEjes = (semiEjeMayor - semiEjeMenor);
    double restaEjes_2 = Math.pow(restaEjes, 2);
    double raiz = Math.sqrt(-3 * (restaEjes_2 / sumaEjes_2) + 4 + 10);
    return Math.PI * sumaEjes * (3 * (restaEjes_2/(sumaEjes_2 * (raiz))) + 1);
  }

  /**
   * Determina si un punto se encuentra dentro de una elipse.
   * @param x eje x del punto.
   * @param y eje y del punto.
   * @return {@code true} si el punto se encuentra dentro de la elipse.
   *         {@code false} en otro caso.
   */
  public boolean perteneceAlaElipse(double x, double y) {
    return (((x * x) / (semiEjeMayor * semiEjeMayor)) + ((y * y) / (semiEjeMenor * semiEjeMenor))) <= 1.0;
  }

  @Override
  public String toString() {
    return "{" +
      " semiEjeMayor='" + getSemiEjeMayor() + "'" +
      ", semiEjeMenor='" + getSemiEjeMenor() + "'" +
      ", Area='" + getArea() + "'" +
      ", Perimetro='" + getPerimetro() + "'" +
      "}";
  }

  private final double semiEjeMayor;
  private final double semiEjeMenor;
}