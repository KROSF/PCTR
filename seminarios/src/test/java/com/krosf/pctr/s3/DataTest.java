package com.krosf.pctr.s3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * testData
 */
public class DataTest {

  @Test
  public void testString2SQL() {
    assertEquals("hola",Data.String2sql("hola", false, false));
    assertEquals("'hola'",Data.String2sql("hola", true, false));
    assertEquals("%hola%",Data.String2sql("hola", false, true));
    assertEquals("'%hola%'",Data.String2sql("hola", true, true));
    assertEquals("O''Connell",Data.String2sql("O'Connell", false, false));
    assertEquals("'O''Connell'",Data.String2sql("O'Connell", true, false));
    assertEquals("%''Smith ''%",Data.String2sql("'Smith '", false, true));
    assertEquals("'''Smith '''",Data.String2sql("'Smith '", true, false));
    assertEquals("'%''Smith ''%'",Data.String2sql("'Smith '", true, true));
  }

  @Test
  public void testBoolean2SQL() {
    assertEquals(1, Data.Boolean2Sql(true));
    assertEquals(0, Data.Boolean2Sql(false));
  }
}