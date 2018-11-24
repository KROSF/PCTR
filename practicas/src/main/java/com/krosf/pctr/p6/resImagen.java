package com.krosf.pctr.p6;

import java.io.IOException;

/**
 * resImagen
 */
public class resImagen {

  public static void resaltar(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
      for (int j = 0; j < matrix[0].length; ++j) {
        if ((i != 0 && j != 0) && (i != matrix.length -1 && j != matrix.length -1))
          matrix[i][j] = resaltado(matrix, i, j);
      }
    }
  }

  public static int resaltado(int[][] matrix, int i, int j) {
    int n = matrix.length;
    return (4 * matrix[i][j] - matrix[i][(j + 1) % n] - matrix[i][(j - 1) % n] - matrix[(i - 1) % n][j]) / 8;
  }

  public static void main(String[] args) throws IOException {
    //System.out.println(-1%0);
    int[][] matrix = CargaImagen.cargar("/Users/krosf/vscode/PCTR/practicas/src/main/java/com/krosf/pctr/p6/uca_gris.png");
    resaltar(matrix);
    CargaImagen.guardar(matrix,"/Users/krosf/vscode/PCTR/practicas/src/main/java/com/krosf/pctr/p6/uca_gris_procesada.png");
  }
}