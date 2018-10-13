package com.krosf.pctr.p1;

import java.util.Scanner;

/**
*
* Clase para modelar el cifrado cesar.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Cesar {

  /**
   * Constructor desde {@link String}
   * @param cadena sin cifrar
   */
  public Cesar(String cadena) {
    this.cadena = cadena;
  }

  /**
   *
   * @return devuelve la cadena sin cifrar o cifrada.
   */
  public String getCadena() {
    return this.cadena;
  }

  /**
   * Funcion que cifra la cadena.
   * @param n Clave con la que se cifra la cadena.
   */
  public void cifrar(int n) {
    String cifrada = "";
    for (int i = 0; i < cadena.length(); ++i) {
      cifrada += (char) (cadena.charAt(i) + n % 26);
    }
    cadena = cifrada;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("Ingrese cadena a cifrar");
    Cesar cesar = new Cesar(s.nextLine());
    System.out.print("Introduzca n: ");
    cesar.cifrar(s.nextInt());
    System.out.println(cesar.getCadena());
    s.close();
  }

  private String cadena;
}
