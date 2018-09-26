package polinomio;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PolinomioTest {
    Polinomio pol;

    @Before
    public void before() {
        pol = new Polinomio("2x^3+4x^2+5x-4", 3);
    }

    @Test
    public void testCoeficiente() {
        assertEquals(2.0, pol.coeficiente(pol.grado()), 0.001);
        assertEquals(-4.0, pol.coeficiente(0), 0.001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCoeficienteExeption() {
        pol.coeficiente(pol.grado() + 1, 1);
    }

    @Test
    public void testSuma() {
        assertEquals("4.0x^3 + 8.0x^2 + 10.0x - 8.0", pol.suma(pol).toString());
    }

    @Test
    public void testResta() {
        assertEquals("", pol.resta(pol).toString());
    }

    @Test
    public void testProducto() {
        Polinomio pool = new Polinomio("8x^3+4x^2+5x-4", 3);
        assertEquals("16.0x^6 + 40.0x^5 + 66.0x^4 - 7.0x^2 - 40.0x + 16.0", pol.producto(pool).toString());
    }

    @Test
    public void testDerivada() {
        assertEquals("6.0x^2 + 8.0x + 5.0", pol.derivada().toString());
    }
}