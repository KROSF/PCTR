package com.krosf.pctr.p2;

/**
 * Elipse
 */
public class Elipse {

  /**
   * Constructor por defecto.
   */
  public Elipse() {
    ejeMayor = ejeMenor = 0.0;
  }

  /**
   * Construye una elipse a partir de los ejes de la misma.
   * @param ejeMenor de la elipse.
   * @param ejeMayor de la elipse.
   */
  public Elipse(double ejeMenor, double ejeMayor) {
    this.ejeMayor = ejeMayor;
    this.ejeMenor = ejeMenor;
  }

  /**
   * 
   * @return El eje mayor de la elipse.
   */
  public double getEjeMayor() {
    return this.ejeMayor;
  }

  /**
   * 
   * @return El eje menor de la elipse.
   */
  public double getEjeMenor() {
    return this.ejeMenor;
  }

  /**
   * 
   * @return El semieje mayor de la elipse.
   */
  public double getSemiEjeMayor() {
    return this.ejeMayor / 2;
  }

  /**
   * 
   * @return El semieje menor de la elipse.
   */
  public double getSemiEjeMenor() {
    return this.ejeMenor / 2;
  }

  /**
   * 
   * @return El area de la elipse.
   */
  public double getArea() {
    return Math.PI * ejeMayor * ejeMenor;
  }

  /**
   * Calcula el perimetro de la elipse utilizando la aproximacion de <a href="https://arxiv.org/pdf/math/0506384.pdf">Ramanujan</a>
   * @return La circunferencia de la elipse.
   */
  public double getPerimetro() {
    double sumaEjes = (ejeMayor + ejeMenor );
    double sumaEjes_2 = Math.pow(sumaEjes, 2);
    double restaEjes = (ejeMayor - ejeMenor);
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
    return (((x * x) / ejeMayor * ejeMayor) + ((y * y) / ejeMenor * ejeMenor)) <= 1.0;
  }

  @Override
  public String toString() {
    return "{" +
      " ejeMayor='" + getEjeMayor() + "'" +
      ", ejeMenor='" + getEjeMenor() + "'" +
      ", Area='" + getArea() + "'" +
      ", Perimetro='" + getPerimetro() + "'" +
      "}";
  }

  private final double ejeMayor;
  private final double ejeMenor;
}