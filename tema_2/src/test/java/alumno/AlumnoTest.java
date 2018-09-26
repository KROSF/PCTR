package alumno;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AlumnoTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void constructorAlumno()
    {
        Alumno pepe = new Alumno("Pedro", "Perez", "49892524H", "GII");
        assertEquals("Pedro", pepe.getNombre());
        assertEquals("Perez", pepe.getApellido());
        assertEquals("49892524H", pepe.getId());
        assertEquals("GII", pepe.getGrado());
    }
}
