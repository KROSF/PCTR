package ps3;

import java.util.Scanner;

/** interesTipoFijo */
public class interesTipoFijo {
  

  public static double interes(float i, float c) {
    return c * (i * 180.0/365.0);
  }
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.print("Ingrese tipo de interes: ");
    float interes = scan.nextFloat()/100;
    System.out.print("Ingrese montante: ");
    float cantidad = scan.nextFloat();
    System.out.println("Intereses Ganados: " + interes(interes, cantidad));
    scan.close();
  }
}
