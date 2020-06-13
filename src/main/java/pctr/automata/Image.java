package pctr.automata;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;

public class Image {
  private static int[][] matrix;
  private static int[][] nextMatrix;

  private static Scanner scan = new Scanner(System.in);

  static void initializeRandomly() {
    matrix = new int[500][500];
    nextMatrix = matrix.clone();
    Random r = new Random();
    for (int i = 0; i < 500; i++) {
      for (int j = i; j < 500; j++) {
        matrix[i][j] = r.nextInt(21);
        if (i != j) {
          matrix[j][i] = r.nextInt(21);
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
        matrix[i][j] = Math.floorMod(scan.nextInt(), 21);
      }
    }
  }

  static void paint(int[][] matrix, String path) {
    BufferedImage image = new BufferedImage(matrix.length, matrix[0].length, BufferedImage.TYPE_INT_RGB);
    Color color = new Color(0, 0, 0);
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        color = new Color(matrix[i][j] + 51, matrix[i][j] + 51, matrix[i][j] + 51);
        image.setRGB(i, j, color.getRGB());
      }
    }

    File ImageFile = new File(path);
    try {
      ImageIO.write(image, "png", ImageFile);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void processInParallel(int workers) throws InterruptedException {
    Worker.initializeSharedVariables(matrix, nextMatrix);
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

  static boolean menu() throws IllegalArgumentException {
    System.out.println("*** *** MENU *** ***\n 1. Random initialization\n 2. Manual initialization");
    var option = scan.nextInt();
    switch (option) {
      case 1:
        initializeRandomly();
        return false;
      case 2:
        System.out.print("Insert dimension: ");
        var dimension = scan.nextInt();
        initializeManually(dimension);
        return true;
      default:
        throw new IllegalArgumentException("You must provide a valid option");
    }
  }

  public static void main(String[] args) {
    try {
      int workers = Runtime.getRuntime().availableProcessors();
      var isManual = menu();
      if (!isManual) {
        paint(matrix, "inital.png");
      }
      processInParallel(workers);
      if (!isManual) {
        paint(nextMatrix, "final.png");
      }
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

  Worker(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  public static void initializeSharedVariables(int[][] matrix, int[][] nextMatrix) {
    Worker.matrix = matrix;
    Worker.nextMatrix = nextMatrix;
  }

  @Override
  public void run() {
    for (int x = begin; x < end; ++x) {
      for (int y = 0; y < matrix[0].length; ++y) {
        nextMatrix[x][y] = applyRules(x, y);
      }
    }
  }

  private int applyRules(int x, int y) {
    int neighbourhood = 0;
    for (Neighbour neighbour : Neighbour.values()) {
      neighbourhood += safeIndex(x + neighbour.x, y + neighbour.y);
    }
    return (4 * safeIndex(x, y) + neighbourhood) / 8;
  }

  private int safeIndex(int x, int y) {
    try {
      return matrix[x][y];
    } catch (ArrayIndexOutOfBoundsException e) {
      return 0;
    }
  }
}

enum Neighbour {
  A(1, 0), B(0, 1), C(-1, 0), D(0, -1);

  public final int x;
  public final int y;

  Neighbour(int x, int y) {
    this.x = x;
    this.y = y;
  }
}