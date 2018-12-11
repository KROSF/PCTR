package com.krosf.pctr.p8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class usalectorEscritor implements Runnable {

  public usalectorEscritor(boolean type) {
    this.type = type;
  }

  @Override
  public void run() {
    while (true) {
      if (type) {
        shared.iniciaEscritura();
        shared.finEscritura();
      } else {
        shared.iniciaLectura();
        shared.finLectura();
      }
    }
  }

  public static void main(String[] args) {
    int cores = Runtime.getRuntime().availableProcessors();
    ExecutorService service = Executors.newFixedThreadPool(cores);
    for (int i = 0; i < cores * 2; ++i) {
      service.execute(new usalectorEscritor(i % 4 == 0));
    }
    service.shutdown();
  }

  private boolean type;
  private static lectorEscritor shared = new lectorEscritor();
}