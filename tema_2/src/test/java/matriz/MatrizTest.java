package matriz;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MatrizTest {
    private Matriz m, n;

    @Before
    public void before() {
        m = new Matriz(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
        n = new Matriz(0, 0);
    }

    @Test
    public void constructorMatriz() {
        assertEquals(3, m.filas());
        assertEquals(3, m.columnas());
        assertEquals(0, n.filas());
        assertEquals(0, n.columnas());
    }

    @Test
    public void constructorLista() {
        assertArrayEquals(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }, m.arr());
    }

    @Test
    public void getAt() {
        assertEquals(1, m.getAt(0, 0), 0.0001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getAtException() {
        m.getAt(4, 4);
    }

    @Test
    public void setAt() {
        m.setAt(0, 0, 22);
        assertEquals(22, m.getAt(0, 0), 0.0001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void setAtExeception() {
        m.setAt(4, 4, 22);
    }

    @Test
    public void testProducto() {
        double[][] rsl = new double[][] { { 30, 36, 42 }, { 66, 81, 96 }, { 102, 126, 150 } };
        assertArrayEquals(rsl, m.Producto(m).arr());
        rsl = new double[][] { { 6, 12 }, { 15, 30 }, { 24, 48 } };
        n = new Matriz(new double[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } });
        assertArrayEquals(rsl, m.Producto(n).arr());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testProductoExeption() {
        n = new Matriz(new double[][] { { 1, 2 }, { 1, 2 } });
        m.Producto(n);
    }

    @Test
    public void testSuma() {
        double[][] rsl = new double[][] { { 2, 4, 6 }, { 8, 10, 12 }, { 14, 16, 18 } };
        assertArrayEquals(rsl, m.Suma(m).arr());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSumaExeption() {
        n = new Matriz(new double[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } });
        m.Suma(n);
    }

    @Test
    public void testResta() {
        double[][] rsl = new double[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        assertArrayEquals(rsl, m.Resta(m).arr());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRestaExeption() {
        n = new Matriz(new double[][] { { 1, 2 }, { 1, 2 }, { 1, 2 } });
        m.Resta(n);
    }

    @Test
    public void testTranspuesta() {
        double[][] rsl = new double[][] { { 1, 4, 7 }, { 2, 5, 8 }, { 3, 6, 9 } };
        assertArrayEquals(rsl, m.Transpuesta().arr());
    }
}