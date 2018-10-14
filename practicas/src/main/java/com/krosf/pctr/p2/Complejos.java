package com.krosf.pctr.p2;

import java.util.Objects;

/**
*
* Modelo clase Complejos
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Complejos {

  /**
   * Constructor por defecto
   */
  public Complejos() {
    real = 0.0;
    imag = 0.0;
  }

  /**
   * Constructor desde doubles
   * @param real parte real.
   * @param imag parte imaginaria.
   */
  public Complejos(double real, double imag) {
    this.real = real;
    this.imag = imag;
  }

  /**
   * Contructor de copia
   * @param c Complejo a copiar.
   */
  public Complejos(Complejos c) {
    this.real = c.real;
    this.imag = c.imag;
  }

  /**
   * 
   * @return parte real del complejo.
   */
  public double getReal() {
    return this.real;
  }

  /**
   * 
   * @return parte imaginaria del complejo.
   */
  public double getImag() {
    return this.imag;
  }

  /**
   * Metodo para realiar la suma de complejos.
   * @param c Complejo a sumar.
   * @return {@code this + c}
   */
  public Complejos suma(Complejos c) {
    return new Complejos(this.real + c.real, this.imag + c.imag);
  }

  /**
   * Metodo para realizar la resta de complejos.
   * @param c Complejo a restar.
   * @return {@code this - c}
   */
  public Complejos resta(Complejos c) {
    return new Complejos(this.real - c.real, this.imag - c.imag);
  }

  /**
   * Metodo para realizar la multiplicacion de complejos.
   * @param c Complejo a multiplicar.
   * @return {@code this * c}
   */
  public Complejos producto(Complejos c) {
    return new Complejos(this.real * c.real - this.imag * c.imag,
                         this.real * c.imag + this.imag * c.real);
  }

  /**
   * Metodo para realizar el producto escalar a un complejo.
   * @param escalar a multiplicar con el complejo.
   * @return {@code this * escalar}
   */
  public Complejos productoEscalar(double escalar) {
    return new Complejos(this.real * escalar, this.imag * escalar);
  }

  /**
   * Metodo para obtener el inverso de un complejo.
   * @return this^-1
   */
  public Complejos inverso() {
    double scale = (real * real) + (imag * imag);
    return new Complejos(real / scale , -imag / scale);
  }

  /**
   * Metodo para realizar la division de complejos.
   * @param c complejo a dividir.
   * @return {@code this / c}
   */
  public Complejos division(Complejos c) {
    return new Complejos(this.producto(c.inverso()));
  }

  /**
   * Metodo para obterner el cojugado de un complejo.
   * @return {@code this.real - this.imag}
   */
  public Complejos conjugado() {
    return new Complejos(real,-imag);
  }

  /**
   * Metodo para calcular el complejo exponencial a this.
   * @return this a exp
   */
  public Complejos exp() {
    return new Complejos(Math.exp(real) * Math.cos(imag), Math.exp(real) * Math.sin(imag));
  }

  /**
   * Metodo para calcular el complejo seno a this.
   * @return this a sin
   */
  public Complejos sin() {
    return new Complejos(Math.sin(real) * Math.cosh(imag), Math.cos(real) * Math.sinh(imag));
  }

  /**
   * Metodo para calcular el complejo coseno a this.
   * @return this a cos
   */
  public Complejos cos() {
    return new Complejos(Math.cos(real) * Math.cosh(imag), -Math.sin(real) * Math.sinh(imag));
  }

  /**
   * Metodo para calcular el complejo tangente a this.
   * @return this a tan
   */
  public Complejos tan() {
    return sin().division(cos());
  }

  /**
   * Calula el modulo de un complejo.
   * @return modulo / valor absoluto
   */
  public double modulo() {
    return Math.hypot(real, imag);
  }

  /**
   * Calcula la fase de un complejo.
   * @return angulo / fase / argumento
   */
  public double fase() {
    return Math.atan2(imag, real);
  }

  @Override
  public boolean equals(Object x) {
    if (x == null) return false;
    if (this.getClass() != x.getClass()) return false;
    Complejos that = (Complejos) x;
    return (this.real == that.real) && (this.imag == that.imag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(real, imag);
  }

  @Override
  public String toString() {
    if (imag == 0) return real + "";
    if (real == 0) return imag + "i";
    if (imag <  0) return real + " - " + (-imag) + "i";
    return real + " + " + imag + "i";
  }

  /**
   * Parte real
   */
  private final double real;

  /**
   * Parte imaginaria
   */
  private final double imag;
}