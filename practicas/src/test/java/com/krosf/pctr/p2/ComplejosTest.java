package com.krosf.pctr.p2;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Test;
import org.junit.Before;

/**
 * ComplejosTest
 */
public class ComplejosTest {
  private Complejos a;
  private Complejos b;

  @Before
  public void before() {
    a = new Complejos(5.0, 6.0);
    b = new Complejos(-3.0, 4.0);
  }

  @After
  public void after() {
    a = new Complejos();
    b = new Complejos();
  }

  @Test
  public void testConstructorDefault() {
    assertEquals(new Complejos(), new Complejos());
  }

  @Test
  public void testConstructor() {
    assertEquals(new Complejos(5.0, 6.0), a);
    assertEquals(new Complejos(-3.0, 4.0), b);
  }

  @Test
  public void testConstructorCopia() {
    assertEquals(new Complejos(a), a);
    assertEquals(new Complejos(b), b);
  }

  @Test
  public void testSuma() {
    assertEquals(new Complejos(2.0, 10.0), b.suma(a));
  }

  @Test
  public void testResta() {
    assertEquals(new Complejos(8.0, 2.0), a.resta(b));
  }

  @Test
  public void testProducto() {
    assertEquals(new Complejos(-39.0, 2.0), a.producto(b));
    assertEquals(new Complejos(-39.0, 2.0), b.producto(a));
  }

  @Test
  public void testProductoEscalar() {
    assertEquals(new Complejos(110.0, 132.0), a.productoEscalar(22.0));
  }

  @Test
  public void testInverso() {
    assertEquals(new Complejos(0.08196721311475409, -0.09836065573770492),  a.inverso());
  }

  @Test
  public void testDivision() {
    assertEquals(new Complejos(0.36, -1.52), a.division(b));
    assertEquals(new Complejos(5.0, 6.0), a.division(b).producto(b));
  }

  @Test
  public void testCojugado() {
    assertEquals(new Complejos(5.0, -6.0), a.conjugado());
  }

  @Test
  public void testExp(){
    assertEquals(new Complejos(142.50190551820737, -41.468936789922886), a.exp());
  }

  @Test
  public void testSin(){
    assertEquals(new Complejos(-193.43002005693958, 57.21839505634109), a.sin());
  }

  @Test
  public void testCos(){
    assertEquals(new Complejos(57.21909818460074, 193.4276431213065), a.cos());
  }

  @Test
  public void testTan(){
    assertEquals(new Complejos(-6.685231390246571E-6, 1.0000103108981198), a.tan());
  }

  @Test
  public void testModulo() {
    assertEquals(7.810249675906654, a.modulo(), 0);
  }

  @Test
  public void testFase() {
    assertEquals(0.8760580505981934, a.fase(), 0);
  }
}