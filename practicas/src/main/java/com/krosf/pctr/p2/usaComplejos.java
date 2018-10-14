package com.krosf.pctr.p2;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;;

/**
*
* usaComplejos
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class usaComplejos {

  public static void main(String[] args) {
    String op = "";
    List<Complejos> listcomplejos;
    Complejos complejo;
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(opciones)) {
        case "1":
          listcomplejos = leerComplejos();
          System.out.println("\n" + listcomplejos.get(0).suma(listcomplejos.get(1)) + "\n");
          break;
        case "2":
          listcomplejos = leerComplejos();
          System.out.println("\n" + listcomplejos.get(0).resta(listcomplejos.get(1)) + "\n");
          break;
        case "3":
          listcomplejos = leerComplejos();
          System.out.println("\n" + listcomplejos.get(0).producto(listcomplejos.get(1)) + "\n");
          break;
        case "4":
          complejo = leerComplejo();
          System.out.print("Ingrese escalar: ");
          double escalar = scan.nextDouble();
          System.out.println("\n" + complejo.productoEscalar(escalar) + "\n");
          break;
        case "5":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.inverso() + "\n");
          break;
        case "6":
          listcomplejos = leerComplejos();
          System.out.println("\n" + listcomplejos.get(0).division(listcomplejos.get(1)) + "\n");
          break;
        case "7":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.conjugado() + "\n");
          break;
        case "8":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.exp() + "\n");
          break;
        case "9":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.cos() + "\n");
          break;
        case "10":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.sin() + "\n");
          break;
        case "11":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.tan() + "\n");
          break;
        case "12":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.modulo() + "\n");
          break;
        case "13":
          complejo = leerComplejo();
          System.out.println("\n" + complejo.fase() + "\n");
          break;
      }
    }
    scan.close();
  }

  /**
   * 
   * @return un complejo leido de la entrada estandar
   */
  private static Complejos leerComplejo() {
    double real = 0, imag = 0;
    System.out.print("Ingrese parte real: ");
    real = scan.nextDouble();
    System.out.print("Ingrese parte imaginaria: ");
    imag = scan.nextDouble();
    scan.nextLine();
    return new Complejos(real, imag);
  }

  /**
   * 
   * @return par de complejos leidos de la entrada estandar.
   */
  private static List<Complejos> leerComplejos() {
    List<Complejos> complejos = new ArrayList<Complejos>(2);
    System.out.println("Ingrese el Complejo A");
    complejos.add(leerComplejo());
    System.out.println("Ingrese el Complejo B");
    complejos.add(leerComplejo());
    return complejos;
  }

  /**
   * Funcion auxiliar para mostrar las opciones del menu.
   * @param opciones que presenta el menu
   * @return opcion elegida por el usuario.
   */
  private static String displayOpciones(String[] opciones) {
    for (String opcion : opciones) {
      System.out.println(opcion);
    }
    System.out.print("Seleccione una opcion: ");
    String rsp = scan.nextLine();
    return rsp;
  }

  /**
   * Evitar la instacian de esta clase.
   */
  private usaComplejos() {}

  /**
   * opciones del menu.
   */
  private static final String[] opciones = {
    "            Complejos",
    "1. Suma                 8. EXP",
    "2. Resta                9. COS",
    "3. Producto            10. SIN",
    "4. Producto escalar    11. TAN",
    "5. Inverso             12. Modulo",
    "6. Division            13. Fase",
    "7. Conjugado            S. Salir"
  };

  private static Scanner scan = new Scanner(System.in);
}