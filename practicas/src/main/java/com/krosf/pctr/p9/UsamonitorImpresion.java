package com.krosf.pctr.p9;

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
    impresion.finImpresion(impresion.inicioImpresion());
  }

  public static void main(String[] args) {
    int tasks = 2 * Runtime.getRuntime().availableProcessors();
    ExecutorService exec = Executors.newFixedThreadPool(tasks);
    for (int i = 0; i < tasks; ++i) {
      exec.submit(new UsamonitorImpresion());
    }
    exec.shutdown();
  }

  private static monitorImpresion impresion = new monitorImpresion(4);
}