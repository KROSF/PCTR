package pctr.laplacian;

public class Worker implements Runnable {
  private int begin;
  private int end;
  private int[][] matrix;
  private int[][] nextMatrix;

  public Worker(int begin, int end, int[][] matrix, int[][] nextMatrix) {
    this.begin = begin;
    this.end = end;
    this.matrix = matrix;
    this.nextMatrix = nextMatrix;
  }

  private void applyEquation(int i, int j) {
    nextMatrix[i][j] = Math.floorMod(
        (safeValue(i + 1, j) + safeValue(i - 1, j) + safeValue(i, j + 1) + safeValue(i, j - 1)) - 4 * matrix[i][j],
        256);
  }

  private int safeValue(int i, int j) {
    int value;
    try {
      value = matrix[i][j];
    } catch (ArrayIndexOutOfBoundsException e) {
      value = 0;
    }
    return value;
  }

  @Override
  public void run() {
    for (int i = begin; i < end; ++i) {
      for (int j = 0; j < matrix.length; ++j) {
        applyEquation(i, j);
      }
    }
  }
}