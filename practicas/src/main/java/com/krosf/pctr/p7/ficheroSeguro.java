package com.krosf.pctr.p7;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ficheroSeguro
 */
public class ficheroSeguro implements Runnable {

  public ficheroSeguro(int id) {
    this.id = id;
  }

  @Override
  public void run() {
    synchronized(file) {
      for (int i = 0; i < 10 ;++i) {
        try {
          file.writeBytes(id + "-" + i +"-" + Thread.currentThread().getName() + "\n");
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void setFile(String name) {
    File newfile = new File(name);
    try {
      newfile.createNewFile();
      file = new RandomAccessFile(name, "rw");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void closeFile() {
    try {
      file.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    setFile("file.txt");
    for(int i = 0; i < 222; ++i) {
      service.execute(new ficheroSeguro(i));
    }
    service.shutdown();
    while (!service.isTerminated());
    closeFile();
  }

  public int id;
  private static RandomAccessFile file;

}