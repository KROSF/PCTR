package p2;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Biblioteca de pacientes.
 *
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class Biblioteca {

  /** Construye una Biblioteca vacia. */
  public Biblioteca() {
    pacientes = new ArrayList<Paciente>();
  }

  /**
   * Inserta un paciente en la Biblioteca.
   *
   * @param p paciente a insertar.
   */
  public void insertar(Paciente p) {
    if (!pacientes.contains(p)) {
      pacientes.add(p);
    }
  }

  /**
   * Elimina un paciente de la Biblioteca.
   *
   * @param dni del paciente a eliminar.
   */
  public void eliminar(String dni) {
    for (Paciente p : pacientes) {
      if (p.getDni().equals(dni)) {
        pacientes.remove(p);
      }
    }
  }

  /**
   * @param p condición para buscar en la lista.
   * @return  {@link String} con los datos del paciente que cumple la condición.
   */
  public String buscar(Predicate<Paciente> p) {
    String rsl;
    Optional<Paciente> result = pacientes.stream().filter(p).findFirst();
    try {
      rsl = result.get().toString();
    } catch (NoSuchElementException e) {
      rsl = "";
    }
    return rsl;
  }

  /**
   * @param p condición para buscar en la lista.
   * @return Lista con los pacientes que cumplen la condición.
   */
  public List<Paciente> buscarVarios(Predicate<Paciente> p) {
    return pacientes.stream().filter(p).collect(Collectors.toList());
  }

  /**
   * Función que desplega el menu.
   */
  public void menu() {
    String[] dop = {"Biblioteca", "1. Insertar.", "2. Eliminar.", "3. Buscar.", "S. Salir."};
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(dop)) {
        case "1":
          insertarPaciente();
          break;
        case "2":
          eliminarPaciente();
          break;
        case "3":
          buscarPacientes();
          break;
      }
    }
  }

  public static void main(String[] args) {
    Biblioteca b = new Biblioteca();
    b.menu();
    scan.close();
  }

  /**
   * Función para mostrar las opciones del menu.
   */
  private String displayOpciones(String[] opciones) {
    for (String opcion : opciones) {
      System.out.println(opcion);
    }
    System.out.print("Seleccione una opcion: ");
    String rsp = scan.nextLine();
    scan.reset();
    return rsp;
  }

  /**
   * Funcion auxiliar para insertar un paciente.
   */
  private void insertarPaciente() {
    String[] respuesta = new String[5];
    System.out.println("Ingrese datos del paciente");
    System.out.print("Nombre: ");
    respuesta[0] = scan.nextLine();
    System.out.print("DNI: ");
    respuesta[1] = scan.nextLine();
    System.out.print("Direccion: ");
    respuesta[2] = scan.nextLine();
    System.out.print("Telefono: ");
    respuesta[3] = scan.nextLine();
    System.out.print("Aseguradora: ");
    respuesta[4] = scan.nextLine();
    scan.reset();
    insertar(new Paciente(respuesta));
  }

  /**
   * Función auxiliar para eliminar un paciente.
   */
  private void eliminarPaciente() {
    System.out.print("Ingrese DNI: ");
    String respuesta = scan.next();
    scan.reset();
    eliminar(respuesta);
  }

  /**
   * Función auxiliar para la busqueda de pacientes.
   */
  private void buscarPacientes() {
    String[] dop = {"1. Nombre","2. DNI","3. Direccion","4. Telefono","5. Aseguradora","S. Salir."};
    String op = "";
    while (!op.equals("S") && !op.equals("s")) {
      switch (op = displayOpciones(dop)) {
        case "1":
          System.out.print("Ingrese nombre del paciente: ");
          String resp = scan.nextLine();
          System.out.println("\n"+buscar(p -> Objects.equals(p.getNombre(),resp))+"\n");
          break;
        case "2":
          System.out.print("Ingrese DNI del paciente: ");
          String resp2 = scan.nextLine();
          System.out.println("\n"+buscar(p -> Objects.equals(p.getDni(),resp2))+"\n");
          break;
        case "3":
          System.out.print("Ingrese Direccion: ");
          String resp3 = scan.nextLine();
          System.out.println("\n"+buscarVarios(p -> Objects.equals(p.getDireccion(),resp3))+"\n");
          break;
        case "4":
          System.out.print("Ingrese Telefono del paciente: ");
          int resi = scan.nextInt();
          System.out.println("\n"+buscar(p -> Objects.equals(p.getTelefono(),resi))+"\n");
          break;
        case "5":
          System.out.print("Ingrese Aseguradora: ");
          String resp4 = scan.nextLine();
          System.out.println("\n"+buscarVarios(p -> Objects.equals(p.getAseguradora(),resp4))+"\n");
          break;
      }
      scan.reset();
    }
  }

  /** Lista de pacientes en la Biblioteca. */
  private List<Paciente> pacientes;
  private static Scanner scan = new Scanner(System.in);
}
