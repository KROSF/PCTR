package com.krosf.pctr.p3;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Hexagono extends Poligono {

  public Hexagono() {
    super(6);
  }

  @Override
  public void add(Punto p) {
    if (size() >= 6) {
      throw new IndexOutOfBoundsException("Número de vertices incorrectos");
    }
    super.add(p);
  }
}