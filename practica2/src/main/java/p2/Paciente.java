package p2;

/** Paciente */
public class Paciente {

  String nombre;
  String dni;
  String direccion;
  int telefono;
  String aseguradora;

  public Paciente(String nombre, String dni, String direccion, int telefono, String aseguradora) {
    this.nombre = nombre;
    this.dni = dni;
    this.direccion = direccion;
    this.telefono = telefono;
    this.aseguradora = aseguradora;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDni() {
    return this.dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }

  public String getDireccion() {
    return this.direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public int getTelefono() {
    return this.telefono;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public String getAseguradora() {
    return this.aseguradora;
  }

  public void setAseguradora(String aseguradora) {
    this.aseguradora = aseguradora;
  }

  @Override
  public String toString() {
    return "{" +
      " nombre='" + getNombre() + "'" +
      ", dni='" + getDni() + "'" +
      ", direccion='" + getDireccion() + "'" +
      ", telefono='" + getTelefono() + "'" +
      ", aseguradora='" + getAseguradora() + "'" +
      "}";
  }
}
