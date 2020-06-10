package pctr.laplacian;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Laplacian {
  private int[][] matrix;
  public int[][] nextMatrix;
  private static Scanner scan = new Scanner(System.in);

  Laplacian(int n, boolean random) {
    if (random) {
      this.initializeRandom(n);
    } else {
      this.initializeManual(n);
    }
  }

  private void initializeRandom(int n) {
    matrix = new int[n][n];
    Random r = new Random();
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        matrix[i][j] = r.ints(0, 256).findFirst().getAsInt();
      }
    }
    nextMatrix = matrix.clone();
  }

  private void initializeManual(int n) {
    matrix = new int[n][n];
    System.out.println("Insert matrix values");
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        System.out.printf("[%d][%d]: ", i, j);
        matrix[i][j] = Integer.valueOf(Math.floorMod(Math.abs(scan.nextInt()), 256));
      }
    }
    nextMatrix = matrix.clone();
  }

  private static Integer tasks(String[] args) {
    if (args.length < 1) {
      throw new IllegalArgumentException("You must provide the number of parallel tasks");
    }
    return Integer.parseInt(args[0]);
  }

  private static Integer dimesion() {
    System.out.println("Insert matrix dimesion: ");
    return Integer.valueOf(Math.abs(scan.nextInt()));
  }

  public void printMatrix() {
    for (int[] row : this.nextMatrix) {
      System.out.println(Arrays.toString(row));
    }
  }

  public void processInParallel(int m) throws InterruptedException {
    var pool = new ThreadPoolExecutor(m, m, 1, TimeUnit.DAYS, new LinkedBlockingQueue<>(m));
    int chunk = (matrix.length + m + 1) / m;
    for (int task = 0, begin, end; task < m; ++task) {
      begin = task * chunk;
      end = Math.min(begin + chunk, matrix.length);
      pool.execute(new Worker(begin, end, matrix, nextMatrix));
    }
    pool.shutdown();
    pool.awaitTermination(1, TimeUnit.DAYS);
  }

  public static void main(String[] args) {
    try {
      System.out.println("*** *** MENU *** ***\n 1. Random initialization\n 2. Manual initialization");
      Integer option = scan.nextInt();
      boolean random = false;

      switch (option) {
        case 1:
          random = true;
          break;
        case 2:
          random = false;
          break;
        default:
          throw new IllegalArgumentException("You must provide a valid option");
      }

      var n = dimesion();
      var m = tasks(args);
      var laplacian = new Laplacian(n, random);
      Long start = System.nanoTime();
      laplacian.processInParallel(m);
      Long end = System.nanoTime();
      laplacian.printMatrix();
      System.out.printf("Process take %dns", end - start);
    } catch (IllegalArgumentException | InterruptedException e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }
}