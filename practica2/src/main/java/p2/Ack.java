package p2;

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
    System.out.println(ack(2, 1));
  }
}
