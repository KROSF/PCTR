package com.krosf.pctr.p8;

/**
 *
 * 
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class lectorEscritor {

  public lectorEscritor() {
    this.escribiendo = false;
    this.lectores = 0;
  }

  public synchronized void iniciaLectura() {
    while (escribiendo) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    ++lectores;
    for (int i = 0; i < 60; i++) {
      System.out.println("Reading..." + Thread.currentThread().getName());
    }
    notifyAll();
  }

  public synchronized void finLectura() {
    --lectores;
    if (lectores == 0) {
      notifyAll();
    }
  }

  public synchronized void iniciaEscritura() {
    while (escribiendo || lectores != 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    escribiendo = true;
    for (int i = 0; i < 120; i++) {
      System.out.println("Writing..." + Thread.currentThread().getName());
    }
  }

  public synchronized void finEscritura() {
    escribiendo = false;
    notifyAll();
  }

  private volatile int lectores;
  private volatile boolean escribiendo;
}