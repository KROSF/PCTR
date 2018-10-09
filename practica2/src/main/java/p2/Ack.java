package p2;

import java.util.Stack;

public class Ack {

  public static double ack(double m, double n) {
    Stack<Double> stack = new Stack<Double>();
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
    System.out.println(ack(4.0, 1.0));
  }
}
