package com.krosf.pctr.p3;

/**
 * usaHilo
 */
public class usaHilo {

  public static void main(String[] args) {
    Hilo hilo  = new Hilo(true);
    Hilo hilo2 = new Hilo(false);

    hilo.start();
    hilo2.start();

    try {
      hilo.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      hilo2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
