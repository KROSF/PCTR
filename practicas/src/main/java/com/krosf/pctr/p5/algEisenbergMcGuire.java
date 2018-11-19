package com.krosf.pctr.p5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */

public class algEisenbergMcGuire implements Runnable {

  public algEisenbergMcGuire(int d) {
    i = d;
  }

  public void run() {
    int index = 0;
    do {
      flags[i] = states.WAITING;
      index = turn;
      while (index != i) { 
        if (flags[index] != states.IDLE) index = turn; 
        else index = (index+1) % n;
      }
      flags[i] = states.ACTIVE;
      index = 0;
      while ((index < n) && ((index == i) || (flags[turn] != states.ACTIVE))) { 
        index = index+1;
      } 

    } while((index >= n) && ((turn == i) || (flags[turn] == states.IDLE)));
    turn = i;
    index = (turn+1) % n;
    inCS++;
    System.out.println(Thread.currentThread().getName() + " " + inCS);
    inCS--;
    while (flags[index] == states.IDLE) { 
      index = (index+1) % n; 
    }
    flags[i] = states.IDLE;
    turn = index;
  }

  public static void main(String[] args) {
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(new algEisenbergMcGuire(0));
    executor.execute(new algEisenbergMcGuire(1));
    executor.shutdown();
  }

  public static enum states {
    IDLE, WAITING, ACTIVE
  };

  private static states[] flags = {states.IDLE,states.IDLE};
  private int i;
  private static int turn = 1;
  private static int n = 2;
  private static volatile int inCS = 0;
  //private static int iter = 0;
  //private int index = 0;
}