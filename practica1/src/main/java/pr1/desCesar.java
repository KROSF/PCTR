package pr1;

import java.util.Scanner;

public class desCesar {
  private String cadena;

  public desCesar(String cadena) {
    this.cadena = cadena;
  }

  public String getCadena() {
    return this.cadena;
  }

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
}
