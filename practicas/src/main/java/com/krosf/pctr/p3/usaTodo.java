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
    Triangulo triangulo = new Triangulo();
    triangulo.add(new Punto(1, 3));
    triangulo.add(new Punto(-3, 55));
    triangulo.add(new Punto(-1, 5));
    Cuadrado cuadrado = new Cuadrado();
    cuadrado.add(new Punto(-1,1));
    cuadrado.add(new Punto(-2,1));
    cuadrado.add(new Punto(-2,2));
    cuadrado.add(new Punto(-1,2));
    Pentagono pentagono = new Pentagono();
    pentagono.add(new Punto(12,3));
    pentagono.add(new Punto(72,7));
    pentagono.add(new Punto(62,8));
    pentagono.add(new Punto(52,9));
    pentagono.add(new Punto(22,6));
    Hexagono hexagono = new Hexagono();
    hexagono.add(new Punto(12,5332));
    hexagono.add(new Punto(66,34));
    hexagono.add(new Punto(34,3123));
    hexagono.add(new Punto(456,455));
    hexagono.add(new Punto(55,7));
    hexagono.add(new Punto(66,9));
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