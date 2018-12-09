package com.krosf.pctr.p7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
/**
*
* 
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class ServidorHilosinPool implements Runnable {
  public ServidorHilosinPool(Socket socket) {
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
        Thread.sleep(2000);
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
    try {
      ServerSocket server = new ServerSocket(2001, 3000);
      while (true) {
        System.out.println("Esperando solicitud de conexion...");
        new Thread(new ServidorHilosinPool(server.accept())).start();
        System.out.println("Recibida solicitud de conexion...");
      }
    } catch (IOException e) {
      System.err.println("Error sockets...");
    }
  }

  private Socket socket;
}