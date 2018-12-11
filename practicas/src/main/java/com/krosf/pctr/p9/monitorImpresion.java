package com.krosf.pctr.p9;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class monitorImpresion {

  public monitorImpresion(int cantidad) {
    impresoras = new boolean[cantidad];
    Arrays.fill(impresoras, true);
  }

  public synchronized int inicioImpresion() {
    int id;
    while ((id = impresoraLibre()) == -1) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(Thread.currentThread().getName() + " imprimiendo en : " + id);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    impresoras[id] = false;
    notifyAll();
    return id;
  }

  public synchronized void finImpresion(int id) {
    System.out.println(Thread.currentThread().getName() + " liberando impresora: " + id);
    impresoras[id] = true;
    notifyAll();
  }

  private int impresoraLibre() {
    return IntStream.range(0, impresoras.length)
      .filter(i -> impresoras[i]).findFirst().orElse(-1);
  }

  private boolean[] impresoras;
}