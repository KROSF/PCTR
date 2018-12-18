package com.krosf.pctr.p9;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*
*
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class UsamonitorImpresion implements Runnable {

  public UsamonitorImpresion() {
  }

  @Override
  public void run() {
    int id = impresion.inicioImpresion();
    try {
      Thread.sleep(r.nextInt(10000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    impresion.finImpresion(id);
  }

  public static void main(String[] args) {
    int tasks = 2 * Runtime.getRuntime().availableProcessors();
    ExecutorService exec = Executors.newFixedThreadPool(tasks);
    for (int i = 0; i < tasks; ++i) {
      exec.submit(new UsamonitorImpresion());
    }
    exec.shutdown();
  }

  private Random r = new Random();
  private static monitorImpresion impresion = new monitorImpresion(3);
}