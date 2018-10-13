package com.krosf.pctr.p1;

import java.util.Scanner;
/**
*
* Clase para modelar es descifrado de cesar.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class desCesar {

  /**
   * Constructor desde {@link String}
   * @param cadena cifrada.
   */
  public desCesar(String cadena) {
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
   * Funcion que descifra la cadena.
   * @param n Clave con la que se descifra la cadena.
   */
  public void descifrar(int n) {
    String descifrada = "";
    for (int i = 0; i < cadena.length(); ++i) {
      descifrada += (char) (cadena.charAt(i) - n % 26);
    }
    cadena = descifrada;
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    System.out.println("Ingrese cadena a descifrar");
    desCesar cesar = new desCesar(s.nextLine());
    System.out.print("Introduzca n: ");
    cesar.descifrar(s.nextInt());
    System.out.println(cesar.getCadena());
    s.close();
  }

  private String cadena;
}
