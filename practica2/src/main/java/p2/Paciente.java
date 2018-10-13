package p2;

/**
*
* Modelo Paciente.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Paciente {

  /**
   * Constructor Paciente.
   * @param nombre completo del paciente.
   * @param dni con letra.
   * @param direccion del paciente.
   * @param telefono movil del paciente.
   * @param aseguradora
   */
  public Paciente(String nombre, String dni, String direccion, int telefono, String aseguradora) {
    this.nombre = nombre;
    this.dni = dni;
    this.direccion = direccion;
    this.telefono = telefono;
    this.aseguradora = aseguradora;
  }

  public Paciente(String[] datos) {
    this.nombre = datos[0];
    this.dni = datos[1];
    this.direccion = datos[2];
    this.telefono = Integer.parseInt(datos[3]);
    this.aseguradora = datos[4];
  }

  /**
   * 
   * @return nombre del paciente
   */
  public String getNombre() {
    return this.nombre;
  }

  /**
   * 
   * @param nombre a actualizar en un paciente.
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * 
   * @return DNI del paciente.
   */
  public String getDni() {
    return this.dni;
  }

  /**
   * 
   * @return Direccion del paciente.
   */
  public String getDireccion() {
    return this.direccion;
  }

  /**
   * 
   * @param direccion nueva que porporciona el paciente.
   */
  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  /**
   * 
   * @return Telefono de contacto del paciente.
   */
  public int getTelefono() {
    return this.telefono;
  }

  /**
   * 
   * @param telefono de contacto para actualizar.
   */
  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  /**
   * 
   * @return Nombre de la aseguradora del cliente.
   */
  public String getAseguradora() {
    return this.aseguradora;
  }

  /**
   * 
   * @param aseguradora nueva que proporciona el paciente.
   */
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

  private String nombre;
  private String dni;
  private String direccion;
  private int telefono;
  private String aseguradora;
}
