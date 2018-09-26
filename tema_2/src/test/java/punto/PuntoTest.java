package punto;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class PuntoTest{
    @Test
    public void constructorPunto(){
        Punto origen = new Punto(0, 0);
        assertEquals(0, origen.getX());
        assertEquals(0, origen.getY());
    }
}