package alumno;

/**
 * Hello world!
 *
 */
public class Alumno {

    private String nombre, apellido, id, grado;

    public Alumno(String nombre, String apellido, String id, String grado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
        this.grado = grado;
    }
    
    public String getNombre(){
        return this.nombre;
    }

    public String getApellido(){
        return this.apellido;
    }

    public String getId(){
        return this.id;
    }

    public String getGrado(){
        return this.grado;    
    }
    
    public static void main(String[] args) {
        Alumno pepe = new Alumno("Pedro", "Perez", "23252432G", "GII");
        System.out.println(pepe.getNombre());
    }
}
