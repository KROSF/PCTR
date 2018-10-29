package com.krosf.pctr.p3;

import java.util.ArrayList;
import java.util.List;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Poligono {

  public Poligono(int size) {
    this.vertices = new ArrayList<Punto>(size);
    this.size = 0;
  }

  public Poligono(Punto[] puntos) {
    size = 0;
    this.vertices = new ArrayList<Punto>(puntos.length);
    for(Punto p : puntos) {
      add(p);
    }
  }

  public void add(Punto p) {
    vertices.add(size++, p);
    vertices.add(size, vertices.get(0));
  }

  public Punto getPunto(int index) throws IndexOutOfBoundsException {
    return vertices.get(index);
  }

  public double perimetro() {
    double perimetro = 0.0;
    for (int i = 0; i < size; ++i) {
      perimetro += vertices.get(i).distancia(vertices.get(i+1));
    }
    return perimetro;
  }

  public double lado(int lado) {
    if (lado > size() || lado < 1) {
      throw new IndexOutOfBoundsException("Este Poligono no dispone del lado " + lado);
    }
    return vertices.get(lado-1).distancia(vertices.get(lado));
  }

  public double area() {
    double sum = 0.0;
    for (int i = 0; i < size; ++i) {
      Punto a = vertices.get(i);
      Punto b = vertices.get(i+1);
      sum += a.getX() * b.getY() - a.getY() * b.getX();
    }
    return Math.abs(sum * 0.5);
  }

  public int size() {
    return size;
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

  private List<Punto> vertices;
  private int size;
}