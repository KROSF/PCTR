package pctr.automata.greenberg_hastings;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
  private int begin;
  private int end;

  private static State[][] matrix;
  private static State[][] nextMatrix;
  private static int generations;
  private static CyclicBarrier cb;

  Worker(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public static void initializeSharedVariables(State[][] matrix, State[][] nextMatrix, int generations, int workers) {
    Worker.matrix = matrix;
    Worker.nextMatrix = nextMatrix;
    Worker.generations = generations;
    cb = new CyclicBarrier(workers, () -> {
      Worker.matrix = Worker.nextMatrix.clone();
    });
  }

  @Override
  public void run() {
    for (int g = 0; g < generations; ++g) {
      for (int x = begin; x < end; ++x) {
        for (int y = 0; y < matrix.length; ++y) {
          nextMatrix[x][y] = matrix[x][y].applyRules(matrix, x, y);
        }
      }
      try {
        cb.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }

}

enum Neighbour {
  N(-1, 0), E(1, 0), S(1, 0), W(-1, 0), NE(-1, 1), SE(1, 1), SW(1, -1), NW(-1, -1);

  public final int x;
  public final int y;

  Neighbour(int x, int y) {
    this.x = x;
    this.y = y;
  }
}

enum State {
  EXCITED(0), REFRACTORY(1), RESTING(2);

  private final int value;

  private static Map<Integer, State> map = new HashMap<>(3);

  static {
    for (State c : State.values()) {
      map.put(c.value, c);
    }
  }

  State(int value) {
    this.value = value;
  }

  public static State fromValue(int c) {
    return map.get(c);
  }

  public Boolean hasExcitedNeighbour(State[][] matrix, int x, int y) {
    var hasExcitedNeighbour = false;
    State state = null;
    for (Neighbour neighbour : Neighbour.values()) {
      state = safeGet(matrix, x + neighbour.x, y + neighbour.y);
      if (state != null && state.equals(State.EXCITED)) {
        hasExcitedNeighbour = true;
        break;
      }
    }
    return hasExcitedNeighbour;
  }

  public State applyRules(State[][] matrix, int x, int y) {
    switch (matrix[x][y]) {
      case EXCITED:
        return State.REFRACTORY;
      case REFRACTORY:
        return State.RESTING;
      case RESTING:
        return matrix[x][y].hasExcitedNeighbour(matrix, x, y) ? State.EXCITED : State.RESTING;
      default:
        return State.RESTING;
    }
  }

  private State safeGet(State[][] matrix, int x, int y) {
    try {
      return matrix[x][y];
    } catch (Exception e) {
      return null;
    }
  }
}