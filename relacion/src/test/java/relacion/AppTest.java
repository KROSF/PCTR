package relacion;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void sumaCuadradoTest(){
        assertEquals(91, App.sumaCuadrado(6));
    }

    @Test
    public void sumaNintervaloTest(){
        assertEquals(5150, App.sumaNintervalo(1));
    }

    @Test
    public void mcdTest() {
        assertEquals(20, App.mcd(80, 100));
    }

    @Test
    public void ecuacucionSegundoGradoTest(){
        assertArrayEquals(new double[]{3,2}, App.ecuacucionSegundoGrado(1, -5, 6), 0.00001);
        assertArrayEquals(new double[]{3,0.5}, App.ecuacucionSegundoGrado(2, -7, 3), 0.00001);
        assertArrayEquals(new double[]{2,5}, App.ecuacucionSegundoGrado(-1, 7, -10), 0.00001);
        assertTrue(Double.isNaN(App.ecuacucionSegundoGrado(1, 3, 3)[0]));
        assertTrue(Double.isNaN(App.ecuacucionSegundoGrado(1, 3, 3)[1]));
    }

    @Test
    public void factorialTest(){
        assertEquals(3628800, App.factorial(10));
    }

    @Test
    public void AckermannTest(){
        assertEquals(61, App.Ackermann(3, 3));
    }

    @Test
    public void maxTresTest(){
        assertEquals(3, App.maxTres(1, 2, 3));
    }
}
