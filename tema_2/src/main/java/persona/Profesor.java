package persona;

public class Profesor extends Empleado {
    private String id_prof;

    public Profesor() {
        super();
    }

    public Profesor(String nombre, String apellido, String dni, String cargo, double salario, String id_prof) {
        super(nombre,apellido,dni,cargo,salario);
        this.id_prof = id_prof;
    }

    public String getId_prof() {
        return this.id_prof;
    }

    public void setId_prof(String id_prof) {
        this.id_prof = id_prof;
    }
}