package semaforo;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class SemaforoTest{
    @Test
    public void semaforoRojo(){
        Semaforo rojo = new Semaforo();
        assertEquals("Rojo", rojo.getColor());
    } 
    @Test
    public void semaforoAmbar(){
        Semaforo ambar = new Semaforo();
        ambar.setColor(1);
        assertEquals("Ambar", ambar.getColor());
    }
    @Test
    public void semaforoVerde(){
        Semaforo verde = new Semaforo();
        verde.setColor(2);
        assertEquals("Verde", verde.getColor());
    }
}