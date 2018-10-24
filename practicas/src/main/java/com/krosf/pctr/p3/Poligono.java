package com.krosf.pctr.p3;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Poligono {

  public Poligono(int size) {
    this.puntos = new Punto[size];
    this.size = 0;
  }

  public void add(Punto p) {
    if (size >= puntos.length-1) {
      redimensionar();
    }
    puntos[size++] = p;
    puntos[size] = puntos[0];

  }

  public Punto getPunto(int index) throws IndexOutOfBoundsException {
    return puntos[index];
  }

  public double perimetro() {
    double perimetro = 0.0;
    for (int i = 0; i < size; ++i) {
      perimetro +=  puntos[i].distancia(puntos[i+1]);
    }
    return perimetro;
  }

  public double lado(int lado) {
    if (lado > size() || lado < 1) {
      throw new IndexOutOfBoundsException("Este Poligono no dispone del lado " + lado);
    }
    return puntos[lado-1].distancia(puntos[lado]);
  }

  public double area() {
    double sum = 0.0;
    for (int i = 0; i < size; ++i) {
      sum += (puntos[i].getX() * puntos[i+1].getY()) - (puntos[i].getY() * puntos[i+1].getX());
    }
    return Math.abs(sum * 0.5);
  }

  public int size() {
    return size;
  }

  private void redimensionar() {
    Punto[] tmp = new Punto[2 * size + 1];
    for (int i = 0; i <= size; ++i) {
      tmp[i] = puntos[i];
    }
    puntos = tmp;
  }

  public boolean esRegular() {
    double lado = lado(1);
    for (int i = 2; i <= size; ++i) {
      if (lado != lado(i)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public String toString() {
    return  getClass().getSimpleName() + ((esRegular()) ? " Regular " : " ") +"de " + size + " lados\n" +
           "Area: " + area() + "\n" +
           "Perimetro: " + perimetro();
  }

  private Punto[] puntos;
  private int size;
}