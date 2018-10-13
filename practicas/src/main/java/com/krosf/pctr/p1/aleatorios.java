package com.krosf.pctr.p1;
/**
*
* Clase para generar números aleatorios.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class aleatorios {
  public static void main(String[] args) {
    if (args.length == 1) {
      int iter = 0;
      try {
        iter = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("El argumento " + args[0] + " debe ser un entero.");
        System.exit(1);
      }
      for (int i = 0; i < iter; ++i) {
        System.out.println(Math.random());
      }
    }
  }
}
