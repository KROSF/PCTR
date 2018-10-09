package pr1;

import java.util.Scanner;

public class Cesar {
  private String cadena;

  public Cesar(String cadena) {
    this.cadena = cadena;
  }

  public String getCadena() {
    return this.cadena;
  }

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
}
