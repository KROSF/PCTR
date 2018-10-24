package com.krosf.pctr.p2;

import java.util.stream.DoubleStream;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.function.Function;

/**
*
* Modelo de clase con metodos estadisticos.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Estadistica {

  /**
   * Metodo para calcular la media arimetica.
   * @param datos con los cuales calcular la media.
   * @return  media arimetica para la muestra.
   */
  public static double mediaArimetica(double[] datos) {
    return DoubleStream.of(datos).average().getAsDouble();
  }

  /**
   * Metodo para calcular la media Geometrica.
   * @param datos con los cuales calcular la media.
   * @return media geometrica de la muestra.
   */
  public static double mediaGeometrica(double[] datos) {
    double producto = 1.0;
    for (double dato : datos) {
      if (dato == 0.0) {
        return 0.0;
      }
      producto *= dato;
    }
    return Math.pow(producto, 1.0 / datos.length);
  }

  /**
   * Metodo para calcular la media Armonica
   * @param datos con los cuales calcular la media.
   * @return media armonica de la muestra.
   * @throws ArithmeticException
   */
  public static double mediaArmonica(double[] datos) throws ArithmeticException {
    double suma = 0.0;
    for (double dato : datos) {
      suma += (1 / dato);
    }
    return datos.length / suma;
  }

  /**
   * Metodo para calcular la moda/s.
   * @param datos con los cuales calcular la moda/s.
   * @return moda/s de la muestra.
   */
  public static List<Double> moda(double[] datos) {
    List<Double> listDatos =  DoubleStream.of(datos).boxed().collect(Collectors.toList());
    final Map<Double, Long> frecuencias = listDatos.stream()
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
      
    final long frecuenciaMaxima = frecuencias.values().stream()
      .mapToLong(count -> count)
      .max().orElse(-1);

    return frecuencias.entrySet().stream()
      .filter(tuple -> tuple.getValue() == frecuenciaMaxima)
      .map(Map.Entry::getKey).sorted()
      .collect(Collectors.toList());
  }

  /**
   * Metodo para calcular la varianza.
   * @param datos con los cuales calcular la varianza.
   * @return varianza de la muestra.
   */
  public static double varianza(double[] datos) {
    double media_2 = Math.pow(mediaArimetica(datos), 2);
    double sum_2 = 0.0;
    for (double dato : datos) {
      sum_2 += Math.pow(dato, 2);
    }
    return (sum_2 / datos.length ) - media_2;
  }

  /**
   * Metodo para calcular la desviacion tipica.
   * @param datos con los cuales calcular la desviacion tipica.
   * @return desviacion tipica que siguen los datos.
   */
  public static double desviacionTipica(double[] datos) {
    return Math.sqrt(varianza(datos));
  }

  /**
   * Metodo auxiliar para leer por la entrada estandar {@code n} elementos.
   * @param eltos Número de elementos a leer.
   * @return vector con los elementos leidos.
   */
  public static double[] leerDatos(int eltos) {
    double[] datos = new double[eltos];
    for (int i = 0; i < eltos; ++i) {
      datos[i] = scan.nextDouble();
    }
    scan.nextLine();
    return datos;
  }

  /**
   * Metodo auxliar para mostrar la opciones disponibles del menu.
   * @param opciones vector con las opciones para este menu.
   * @return opcion que inserta el usuario por la entrada estandar.
   */
  public static String mostrarOpciones(String[] opciones) {
    for (String opcion : opciones) {
      System.out.println(opcion);
    }
    System.out.print("Seleccione una opcion: ");
    String rsp = scan.nextLine();
    return rsp;
  }

  public static void main(String[] args) {
    if (args.length == 1) {
      int eltos = 0;
      try {
        eltos = Integer.parseInt(args[0]);
      } catch (NumberFormatException e) {
        System.err.println("El argumento " + args[0] + " debe ser un entero.");
        System.exit(1);
      }
      System.out.println("Introduce " + eltos + " elementos: ");
      double[] datos = leerDatos(eltos);
      String[] opciones = {"\nEstadisticos","1. Media Arimetica","2. Media Geometrica","3. Media Armonica","4. Varianza","5. Desviacion Tipica","S. Salir"};
      String op = "";
      while (!op.equals("S") && !op.equals("s")) {
        switch (op = mostrarOpciones(opciones)) {
          case "1":
            System.out.format("\n La Media Arimetica es %f\n", mediaArimetica(datos));
            break;
          case "2":
            System.out.format("\n La Media Geometrica es %s\n", mediaGeometrica(datos));
            break;
          case "3":
            System.out.format("\n La Media Armonica es %s\n", mediaArmonica(datos));
            break;
          case "4":
            System.out.format("\n La Varianza es %f\n", varianza(datos));
            break;
          case "5":
            System.out.format("\n La Desviacion Tipica es %f\n", desviacionTipica(datos));
            break;
        }
      }
      scan.close();
    }
  }

  /**
   * Evitar instanciar objetos de esta clase.
   */
  private Estadistica() {}

  /**
   * Scanner global detro de la funcion.
   */
  private static Scanner scan = new Scanner(System.in);
}
