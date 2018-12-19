package com.krosf.pctr.p10;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 *
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class triatlonBarreras implements Runnable {

  public triatlonBarreras(int dorsal) {
    this.dorsal = dorsal;
  }

  @Override
  public void run() {
    for (int i = 0; i < 3; ++i) {
      participantes.set(dorsal, participantes.get(dorsal) + r.nextInt(10000) + 1);
      try {
        Thread.sleep(participantes.get(dorsal));
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      synchronized (posta) {
        System.out.format("Finaliza %s: %d\n", posta[i], dorsal);
      }
      try {
        postas.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }

  public static void ganador() {
    int win = participantes.indexOf(Collections.min(participantes));
    System.out.format("El ganador es %d con un tiempo de: %ds\n",win, TimeUnit.MILLISECONDS.toSeconds(participantes.get(win)));
  }

  public static void main(String[] args) {
    Thread[] participantes = new Thread[100];
    for (int i = 0; i < participantes.length; ++i) {
      participantes[i] = new Thread(new triatlonBarreras(i));
      participantes[i].start();
    }
    for (Thread t : participantes) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    ganador();
  }

  private int dorsal;
  private Random r = new Random();
  private static String[] posta = new String[] { "natacion", "ciclismo", "carrera" };
  private static CyclicBarrier postas = new CyclicBarrier(100);
  private static ArrayList<Integer> participantes = new ArrayList<Integer>(Collections.nCopies(100, 0));
}