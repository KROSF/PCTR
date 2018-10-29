package com.krosf.pctr.p3;

import java.util.ArrayList;
import java.util.List;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class usaTodo {

  public static void main(String[] args) {
    Triangulo triangulo = new Triangulo(new Punto[]{
      new Punto(1, 3), new Punto(-3, 55), new Punto(-1, 5)});

    Cuadrado cuadrado = new Cuadrado(new Punto[]{
      new Punto(-1,1), new Punto(-2,1),
      new Punto(-2,2), new Punto(-1,2)});

    Pentagono pentagono = new Pentagono(new Punto[]{
      new Punto(12,3),
      new Punto(72,7),
      new Punto(62,8),
      new Punto(52,9),
      new Punto(22,6)
    });
    Hexagono hexagono = new Hexagono(new Punto[]{
      new Punto(12,5332),
      new Punto(66,34),
      new Punto(34,3123),
      new Punto(456,455),
      new Punto(55,7),
      new Punto(66,9)
    });
    List<Poligono> poligonos = new ArrayList<Poligono>();
    poligonos.add(triangulo);
    poligonos.add(cuadrado);
    poligonos.add(pentagono);
    poligonos.add(hexagono);
    for(Poligono p : poligonos) {
      System.out.println(p);
    }
  }
}