package com.krosf.pctr.p3;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Pentagono extends Poligono {

  public Pentagono() {
    super(5);
  }

  public Pentagono(Punto[] puntos) {
    super(puntos);
    if (puntos.length > 5) {
      throw new IndexOutOfBoundsException("Número de vertices incorrectos");
    }
  }

  @Override
  public void add(Punto p) {
    if (size() >= 5) {
      throw new IndexOutOfBoundsException("Número de vertices incorrectos");
    }
    super.add(p);
  }
}