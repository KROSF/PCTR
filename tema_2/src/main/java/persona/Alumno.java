package persona;

public class Alumno extends Persona {
    private String id_alum;

    public Alumno() {
        super();
    }

    public Alumno(String nombre, String apellido, String dni, String id_alum) {
        super(nombre,apellido,dni);
        this.id_alum = id_alum;
    }

    public String getId_alum() {
        return this.id_alum;
    }

    public void setId_alum(String id_alum) {
        this.id_alum = id_alum;
    }
}