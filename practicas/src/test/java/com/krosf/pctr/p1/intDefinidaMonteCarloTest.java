package com.krosf.pctr.p1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** intDefinidaMonteCarloTest */
public class intDefinidaMonteCarloTest {
  @Test
  public void testSinx() {
    assertEquals(
        0.45969769413, intDefinidaMonteCarlo.integralMonteCarlo(0, 1, 1000000, x -> Math.sin(x)), 0.001);
  }

  @Test
  public void testXcuadrado() {
    assertEquals(
        0.33333333333, intDefinidaMonteCarlo.integralMonteCarlo(0, 1, 1000000, x -> Math.pow(x, 2)), 0.003);
  }

  @Test
  public void testX() {
    assertEquals(0.5, intDefinidaMonteCarlo.integralMonteCarlo(0, 1, 1000000, x -> x), 0.01);
  }
}
