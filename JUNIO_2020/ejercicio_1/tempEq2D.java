import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class tempEq2D {
  private static int[][] matrix;
  private static int[][] nextMatrix;
  public static double k1 = 0.25;
  public static double k2 = 0.125;

  private static Scanner scan = new Scanner(System.in);

  static void initializeRandomly() {
    matrix = new int[1000][1000];
    nextMatrix = matrix.clone();
    Random r = new Random();
    for (int i = 0; i < matrix.length; i++) {
      for (int j = i; j < matrix.length; j++) {
        matrix[i][j] = r.ints(-50, 51).findFirst().getAsInt();
        if (i != j) {
          matrix[j][i] = r.ints(-50, 51).findFirst().getAsInt();
        }
      }
    }
  }

  static void initializeManually(int dimension) {
    matrix = new int[dimension][dimension];
    nextMatrix = matrix.clone();
    System.out.println("Initialize Matrix with values");
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        System.out.printf("Insert value for cell (%d, %d): ", i, j);
        matrix[i][j] = Math.floorMod(scan.nextInt(), 51);
      }
    }
    System.out.println("-----------------------");
    for (int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }
    System.out.println("-----------------------");
  }

  static void processInParallel(int workers, int generations, boolean isManual) throws InterruptedException {
    Worker.initializeSharedVariables(matrix, nextMatrix, workers, generations, isManual);
    int cores = Runtime.getRuntime().availableProcessors();
    ThreadPoolExecutor pool = new ThreadPoolExecutor(cores, cores, 1, TimeUnit.DAYS, new LinkedBlockingQueue<>());
    int chunk = (matrix.length + workers - 1) / workers;
    for (int worker = 0, begin, end; worker < workers; ++worker) {
      begin = worker * chunk;
      end = Math.min(begin + chunk, matrix.length);
      pool.execute(new Worker(begin, end));
    }
    pool.shutdown();
    while (!pool.awaitTermination(1, TimeUnit.DAYS)) {
    }
  }

  public static int applyEquation(int[][] matrix, int i, int j) {
    int main = matrix[i][j];
    double value = main + k1 * (safeIndex(matrix, i + 1, j) + safeIndex(matrix, i - 1, j) - 2 * main)
        + k2 * (safeIndex(matrix, i, j + 1) + safeIndex(matrix, i, j - 1) - 2 * main);
    return (int) (value < 0 ? Math.ceil(value) : Math.floor(value));
  }

  public static int safeIndex(int[][] matrix, int i, int j) {
    try {
      return matrix[i][j];
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }

  static void processSequential(int generations) {
    for (int g = 0; g < generations; ++g) {
      for (int i = 0; i < matrix.length; ++i) {
        for (int j = 0; j < matrix[0].length; ++j) {
          nextMatrix[i][j] = applyEquation(matrix, i, j);
        }
      }
    }
  }

  interface Callback {
    void func() throws InterruptedException;
  }

  static long caculateTime(Callback cb) throws InterruptedException {
    var start = System.nanoTime();
    cb.func();
    var end = System.nanoTime();
    return end - start;
  }

  static void menu(int workers, int generations) throws IllegalArgumentException, InterruptedException {
    System.out.println("*** *** MENU *** ***\n 1. Random initialization\n 2. Manual initialization");
    var option = scan.nextInt();
    switch (option) {
      case 1:
        initializeRandomly();
        int[][] initialState = matrix.clone();
        long sequential = caculateTime(() -> {
          processSequential(generations);
        });
        matrix = initialState.clone();
        long parallel = caculateTime(() -> {
          processInParallel(workers, generations, false);
        });
        System.out.printf("SpeedUp %d", sequential / parallel);
        break;
      case 2:
        System.out.print("Insert dimension: ");
        var dimension = scan.nextInt();
        initializeManually(dimension);
        caculateTime(() -> {
          processInParallel(workers, generations, true);
        });
        break;
      default:
        throw new IllegalArgumentException("You must provide a valid option");
    }
  }

  public static void main(String[] args) {
    try {
      int workers = Runtime.getRuntime().availableProcessors();
      System.out.print("Insert generations: ");
      int generations = scan.nextInt();
      menu(workers, generations);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }

}

class Worker implements Runnable {
  private int begin;
  private int end;

  private static int[][] matrix;
  private static int[][] nextMatrix;
  private static int generations;
  private static CyclicBarrier cb;

  Worker(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public static void initializeSharedVariables(int[][] matrix, int[][] nextMatrix, int workers, int generations,
      boolean isManual) {
    Worker.matrix = matrix;
    Worker.nextMatrix = nextMatrix;
    Worker.generations = generations;
    cb = new CyclicBarrier(workers, () -> {
      if (isManual) {
        System.out.println("-----------------------");
        for (int[] row : Worker.nextMatrix) {
          System.out.println(Arrays.toString(row));
        }
        System.out.println("-----------------------");
      }
      Worker.matrix = Worker.nextMatrix.clone();
    });
  }

  @Override
  public void run() {
    for (int g = 0; g < Worker.generations; ++g) {
      for (int i = begin; i < end; ++i) {
        for (int j = 0; j < matrix[0].length; ++j) {
          nextMatrix[i][j] = applyEquation(i, j);
        }
      }
      try {
        cb.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        e.printStackTrace();
      }
    }
  }

  public int applyEquation(int i, int j) {
    int main = matrix[i][j];
    double value = main + tempEq2D.k1 * (safeIndex(i + 1, j) + safeIndex(i - 1, j) - 2 * main)
        + tempEq2D.k2 * (safeIndex(i, j + 1) + safeIndex(i, j - 1) - 2 * main);
    return (int) (value < 0 ? Math.ceil(value) : Math.floor(value));
  }

  public int safeIndex(int i, int j) {
    try {
      return matrix[i][j];
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }
}
