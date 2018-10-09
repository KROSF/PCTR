package p2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Biblioteca
 */
public class Biblioteca {
  private List<Paciente> pacientes;

  public Biblioteca(){
    pacientes = new ArrayList<Paciente>();
  }

  public void insertar(Paciente p) {
    pacientes.add(p);
  }

  public void eliminar(String dni) {
    for (Paciente p : pacientes) {
      if (p.getDni().equals(dni)) {
        pacientes.remove(p);
      }
    }
  }

  public void buscar(String n) {
    Optional<Paciente> result = pacientes.stream()
    .filter(a -> (Objects.equals(a.dni, n)))
    .findFirst();
    System.out.println(result.get());
  }

  public String buscar(int tlfn){
    Optional<Paciente> result = pacientes.stream()
    .filter(a -> (Objects.equals(a.telefono, tlfn)))
    .findFirst();
    return result.get().toString();
  }

  public List<Paciente> buscarPorDireccion(String direccion) {
    return pacientes.stream()
    .filter(a -> (Objects.equals(a.direccion, direccion)))
    .collect(Collectors.toList());
  }

  public List<Paciente> buscarPorNombre(String nombre) {
    return pacientes.stream()
    .filter(a -> (Objects.equals(a.nombre, nombre)))
    .collect(Collectors.toList());
  }

  public static void main(String[] args) {
    Biblioteca b = new Biblioteca();
    b.insertar(new Paciente("Rodrigo Sanabria", "Y3782680E", "Carabela la niña", 684363683, "AXA"));
    b.insertar(new Paciente("Felicidad Flores", "49892524G", "Carabela la niña", 646369797, "AXA"));
    b.buscar("49892524G");
  }

}