package ps3;

import java.util.Scanner;

/** mediCalc */
public class mediCalc {

  static Scanner scan = new Scanner(System.in);
  
  public static double cockroftGault(double edad, double peso, double creatinina, boolean sexo) {
    double rslt = ((140 - edad) * peso) / (72 * creatinina);
    return (sexo) ? rslt : rslt * 0.85;
  }

  public static double[] datacg() {
    double[] data = new double[3];
    System.out.print("Ingrese Edad (a): ");
    data[0] = scan.nextDouble();
    System.out.print("Ingrese Peso (kg): ");
    data[1] = scan.nextDouble();
    System.out.print("Ingrese Creatinina (mg/dl): ");
    data[2] = scan.nextDouble();
    scan.reset();
    return data;
  }

  public static boolean sexo() {
    System.out.println("Seleccione Sexo");
    System.out.println("1 - Hombre");
    System.out.println("2 - Mujer");
    int op = scan.nextInt();
    scan.reset();
    return op == 1;
  }

  public static double imc(double peso, double altura) {
    return peso / Math.pow(altura, 2);
  }

  public static double[] dataimc() {
    double[] data = new double[2];
    System.out.print("Ingrese peso (kg): ");
    data[0] = scan.nextDouble();
    System.out.print("Ingrese altura (m): ");
    data[1] = scan.nextDouble();
    scan.reset();
    return data;
  }

  public static String display() {
    System.out.println("Calculadora Medica");
    System.out.println("1. Cockroft Gault");
    System.out.println("2. IMC");
    System.out.println("S. Salir");
    System.out.print("Seleccione una opcion: ");
    String rsp = scan.next();
    scan.reset();
    return rsp;
  }

  public static void msjCG(double op) {
    if (op >= 90.0 ) {
      System.out.println("\nFuncion Normal\n");
    } else if (op >= 60.0 && op <= 89.0) {
      System.out.println("\nDaño Renal Leve\n");
    } else if (op >= 30.0 && op <= 59.0) {
      System.out.println("\nDaño Renal Moderado\n");
    } else if (op >= 15.0 && op <= 29.0) {
      System.out.println("\nDaño Renal Grave\n");
    } else if (op < 15.0) {
      System.out.println("\nFallo Renal\n");
    }
  }

  public static void msjIMC(double op) {
    if (op < 16.0) {
      System.out.println("\nDelgadez Severa\n");
    } else if (op >= 16.0 && op <= 16.99) {
      System.out.println("\nDelgadez Moderada\n");
    } else if (op >= 17.0 && op <= 18.49) {
      System.out.println("\nDelgadez Aceptable\n");
    } else if (op >= 18.50 && op <= 24.99) {
      System.out.println("\nPeso Normal\n");
    } else if (op >= 25.0 && op <= 29.99) {
      System.out.println("\nSobrepeso\n");
    } else if (op >= 30.0 && op <= 34.99) {
      System.out.println("\nObesidad Tipo I\n");
    } else if (op >= 35.0 && op <= 40.0) {
      System.out.println("\nObesidad Tipo II\n");
    } else {
      System.out.println("\nObesidad Tipo III\n");
    }
  }

  public static void menu() {
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      op = display();
      switch (op) {
        case "1":
          double[] datos = datacg();
          msjCG(cockroftGault(datos[0], datos[1], datos[2], sexo()));
          break;
        case "2":
          double[] dat = dataimc();
          msjIMC(imc(dat[0], dat[1]));
          break;
      }
    }
  }
  public static void main(String[] args) {
    menu();
    scan.close();
  }
}
