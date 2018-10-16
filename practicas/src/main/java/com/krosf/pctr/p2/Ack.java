package com.krosf.pctr.p2;

import java.util.Stack;
/**
* Clase que contiene la función de Ackermann.
* @author Carlos Rodigo Sanabria Flores
* @version 1.0
*/
public class Ack {

  /**
   * Función recursiva no primitiva de Ackermann.
   * @param m número natural.
   * @param n número natural.
   * @return número generado por la función de Ackermann.
   */
  public static int ack(int m, int n) {
    Stack<Integer> stack = new Stack<Integer>();
    stack.add(m);
    while (!stack.isEmpty()) {
      m = stack.pop();
      if (m == 0) {
        n += m + 1;
      } else if (n == 0) {
        n += 1;
        stack.add(--m);
      } else {
        stack.add(--m);
        stack.add(++m);
        n--;
      }
    }
    return n;
  }

  public static void main(String[] args) {
    if (args.length == 2) {
      int m = 0;
      int n = 0;
      try {
        m = Integer.parseInt(args[0]);
        n = Integer.parseInt(args[1]);
      } catch (NumberFormatException e) {
        System.err.println("\nLos argumentos deben ser enteros.\n");
        System.exit(1);
      }
      System.out.format("\nAck(%d, %d) = %d\n\n", m, n, ack(m, n));
    } else {
      System.err.println("\nNúmero de argumentos incorrecto.\n");
    }
  }
}
