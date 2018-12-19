package com.krosf.pctr.p7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class ServidorHiloconPool implements Runnable {

  public ServidorHiloconPool(Socket socket) {
    this.socket = socket;
    //Thread.currentThread().start();
  }

  @Override
  public void run() {
    try {
      BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String datos = buffer.readLine();
      for (int i = 0; i < 20; i++) {
        System.out.format("El hilo %s escribiendo el dato %d\n", Thread.currentThread().getName(),
            Integer.valueOf(datos).intValue());
        Thread.sleep(1000);
      }
      socket.close();
      System.out.format("El hilo %s cierra su conexion...\n", Thread.currentThread().getName());
    } catch (IOException e) {
      System.err.println("Error...");
    } catch (InterruptedException e) {
      System.err.println("Error... sleep");
    }
  }

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    try {
      ServerSocket server = new ServerSocket(2001, 3000);
      while (true) {
        System.out.println("Esperando solicitud de conexion...");
        service.execute(new ServidorHiloconPool(server.accept()));
        System.out.println("Recibida solicitud de conexion...");
        server.close();
      }
    } catch (IOException e) {
      System.err.println("Error sockets...");
    }
  }

  private Socket socket;
}