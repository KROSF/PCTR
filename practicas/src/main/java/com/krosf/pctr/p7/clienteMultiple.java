package com.krosf.pctr.p7;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class clienteMultiple {
  public static void main(String[] args) {
    int j = 0;
    while (j < 20) {
      int i = (int) (Math.random() * 10);
      int puerto = 2001;
      try {
        System.out.println("Realizando conexion...");
        Socket cable = new Socket(args[0], puerto);
        System.out.println("Realizada conexion a " + cable);
        PrintWriter salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(cable.getOutputStream())));
        salida.println(i);
        salida.flush();
        System.out.println("Cerrando conexion...");
        cable.close();
      } catch (Exception e) {
        System.out.println("Error en sockets...");
      }
      j++;
    }
  }
}