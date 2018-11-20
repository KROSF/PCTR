package com.krosf.pctr.p5;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Lamport
 *
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class algoLamport implements Runnable {
  public algoLamport() {
    flags.add(false);
    pid = cola.size();
    cola.add(0);
  }

  public void run() {
    lock(pid);
    shared++;
    System.out.println(Thread.currentThread().getName() + " " + shared);
    shared--;
    unlock(pid);
  }

  public static void main(String[] args) {
    // ExecutorService exec = Executors.newFixedThreadPool(4);
    // for (int i = 0; i < 4; ++i) {
    //   exec.execute(new algoLamport());
    // }
    // exec.shutdown();
    new Thread(new algoLamport()).start();
    new Thread(new algoLamport()).start();
    new Thread(new algoLamport()).start();
    new Thread(new algoLamport()).start();
  }

  private void lock(int index) {
    flags.set(index, true);
    int max = Collections.max(cola);
    cola.set(index, 1 + max);
    flags.set(index, false);
    for (int i = 0; i < flags.size(); ++i) {
      if (i != index) {
        while (flags.get(i)) {
          Thread.yield();
        }
        while (cola.get(i) != 0
            && ((cola.get(i) < cola.get(index) || (cola.get(index) == cola.get(i) && index > i)))) {
          Thread.yield();
        }
      }
    }
  }

  private void unlock(int index) {
    cola.set(index, 0);
  }

  private static volatile Vector<Boolean> flags = new Vector<Boolean>(1);
  private static volatile Vector<Integer> cola = new Vector<Integer>(1);
  private static int shared = 0;
  private int pid;
}
