package pctr.automata.greenberg_hastings;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
  private static State[][] matrix;
  private static State[][] nextMatrix;
  private static Scanner scan = new Scanner(System.in);

  static void initializeRandomly(int dimension) {
    matrix = new State[dimension][dimension];
    nextMatrix = matrix.clone();
    Random r = new Random();
    for (int i = 0; i < dimension; i++) {
      for (int j = i; j < dimension; j++) {
        matrix[i][j] = State.fromValue(r.nextInt(3));
        if (i != j) {
          matrix[j][i] = State.fromValue(r.nextInt(3));
        }
      }
    }
  }

  static void initializeManually(int dimension) {
    matrix = new State[dimension][dimension];
    nextMatrix = matrix.clone();
    System.out.println("Initialize Matrix with values");
    for (int i = 0; i < dimension; i++) {
      for (int j = 0; j < dimension; j++) {
        System.out.printf("Insert value for cell (%d, %d): ", i, j);
        matrix[i][j] = State.fromValue(Math.floorMod(scan.nextInt(), 3));
      }
    }
  }

  static void processInParallel(int workers, int generations) throws InterruptedException {
    Worker.initializeSharedVariables(matrix, nextMatrix, generations, workers);
    var pool = new ThreadPoolExecutor(workers, workers, 1, TimeUnit.DAYS, new LinkedBlockingQueue<>(workers));
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

  static void menu(int dimension) throws IllegalArgumentException {
    System.out.println("*** *** MENU *** ***\n 1. Random initialization\n 2. Manual initialization");
    var option = scan.nextInt();
    switch (option) {
      case 1:
        initializeRandomly(dimension);
        break;
      case 2:
        initializeManually(dimension);
        break;
      default:
        throw new IllegalArgumentException("You must provide a valid option");
    }
  }

  static void printMatriz() {
    for (State[] states : matrix) {
      System.out.println(Arrays.toString(states));
    }
  }

  public static void main(String[] args) {
    try {
      System.out.print("Insert dimension: ");
      var dimension = scan.nextInt();
      System.out.print("Insert workers: ");
      var workers = scan.nextInt();
      System.out.print("Insert generations: ");
      var generations = scan.nextInt();
      menu(dimension);
      System.out.println("Initial Values");
      printMatriz();
      var start = System.nanoTime();
      processInParallel(workers, generations);
      var end = System.nanoTime();
      System.out.println("Final Values");
      printMatriz();
      System.out.printf("Process take %dµs", (end - start) / 1000);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.exit(1);
    }
  }
}