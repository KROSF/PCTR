package persona;

public class Empleado extends Persona {
    private double salario;
    private String cargo;

    public Empleado() {
        super();
    }

    public Empleado(String nombre, String apellido, String dni, String cargo, double salario) {
        super(nombre,apellido,dni);
        this.cargo = cargo;
        this.salario = salario;
    }

    public double getSalario() {
        return this.salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}