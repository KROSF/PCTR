package com.krosf.pctr.p8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * drakkarVikingo
 */
public class drakkarVikingo implements Runnable {

  public drakkarVikingo(boolean type) {
    this.type = type;
  }

  public static void main(String[] args) {
    ExecutorService exec = Executors.newFixedThreadPool(4);
    for (int i = 0; i < 4 ; ++i) {
      exec.submit(new drakkarVikingo(i % 4 == 0));
    }
    exec.shutdown();
  }

  @Override
  public void run() {
    while (true) {
      if (type) {
        m.cocinar();
      } else {
        m.comer();
      }
    }
  }
  private static Marmita m = new Marmita(3);
  private boolean type;
}

class Marmita {

  public Marmita(int inicial) {
    this.anguilas = inicial;
  }

  public synchronized void comer() {
    while (anguilas == 0) {
      notifyAll();
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.printf("Vikingo %s comiendo la anguila %d\n", Thread.currentThread().getName(), anguilas);
    --anguilas;
  }

  public synchronized void cocinar() {
    while (anguilas > 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    anguilas = 8;
    System.out.printf("Vikingo %s Cocinando %d anguilas\n", Thread.currentThread().getName(), anguilas);
    notifyAll();
  }

  private int anguilas;
}