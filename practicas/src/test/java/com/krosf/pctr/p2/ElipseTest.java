package com.krosf.pctr.p2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ElipseTest
 */
public class ElipseTest {

  private Elipse elip;

  @Before
  public void before() {
    elip = new Elipse(1, 1);
  }
  
  @After
  public void after() {
    elip = new Elipse();
  }

  @Test
  public void testArea() {
    assertEquals(3.141592653589793, elip.getArea(), 0);
  }

  @Test
  public void testPerimetro() {
    assertEquals(6.283185307179586, elip.getPerimetro(), 0);
  }

  @Test
  public void testEjes() {
    assertEquals(2.0, elip.getEjeMayor(), 0);
    assertEquals(2.0, elip.getEjeMenor(), 0);
  }

  @Test
  public void testSemiEjes() {
    assertEquals(1.0, elip.getSemiEjeMayor(), 0);
    assertEquals(1.0, elip.getSemiEjeMenor(), 0);
  }

  @Test
  public void testPertenece() {
    assertTrue(elip.perteneceAlaElipse(0.6, 0.7));
  }
}