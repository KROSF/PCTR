package com.krosf.pctr.p3;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Hilo extends Thread {

  public Hilo(boolean type) {
    this.type = type;
  }

  @Override
  public void run() {
    if(type) {
      for (int i = 0; i < iter; ++i, ++n) {
        System.out.println(n);
      }
    } else  {
      for (int i = 0; i < iter; ++i, --n) {
        System.out.println(n);
      }
    }

  }

  private static int n = 0;
  private final int iter = (int) 10E2;
  private boolean type;
}