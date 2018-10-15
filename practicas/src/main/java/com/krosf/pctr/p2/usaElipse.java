package com.krosf.pctr.p2;

import java.util.List;
import java.util.ArrayList;

/**
 * usaElipse
 */
public class usaElipse {

  public static void main(String[] args) {
    List<Elipse> elipses = new ArrayList<Elipse>(10);
    for (int i = 1; i <= 10; ++i) {
      elipses.add(new Elipse(5 * i, i/2));
    }
    for (Elipse elip : elipses) {
      double x = Math.random() * 10;
      double y = Math.random() * 10;
      if (elip.perteneceAlaElipse(x,y)) {
        System.out.format("El punto (%f, %f) pertene a la elipse %s\n", x, y, elip);
      }
    }
  }
}