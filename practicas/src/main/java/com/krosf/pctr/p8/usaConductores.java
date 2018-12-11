package com.krosf.pctr.p8;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class usaConductores implements Runnable {

  public static void main(String[] args) {
    int cores = Runtime.getRuntime().availableProcessors();
    ExecutorService exec = Executors.newFixedThreadPool(cores);
    for (int i = 0; i < cores * 2; i++) {
      exec.execute(new usaConductores());
    }
    exec.shutdown();
  }

  @Override
  public void run() {
    int index = r.nextInt(db.size());
    switch (r.nextInt(3)) {
    case 0:
      System.out.println(Arrays.toString(db.getConductores(0, index).toArray()));
      break;
    case 1:
      System.out.println(db.getConductor(index));
      break;
    case 2:
      System.out.println(Arrays.toString(db.getConductores().toArray()));
      break;
    }
  }

  private Random r = new Random();
  private static Conductores db = new Conductores(
      "/Users/krosf/vscode/PCTR/practicas/src/main/java/com/krosf/pctr/p8/conductores.csv");

}