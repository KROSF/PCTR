package com.krosf.pctr.s2;

import java.util.Objects;

/**
 * Matriz
 */
public class Matriz {

  public Matriz(int filas, int columnas) {
    this.eltos = new double[filas][columnas];
    this.filas = filas;
    this.columnas = columnas;
  }

  public Matriz(double[][] data) {
    this.eltos = data.clone();
    this.filas = this.eltos.length;
    this.columnas = this.eltos[0].length;
  }

  public int getFilas() {
    return this.filas;
  }

  public int getColumnas() {
    return this.columnas;
  }

  public double getAt(int i, int j) {
    if (i > filas || j > columnas)
        throw new IndexOutOfBoundsException("Fuera de Rango");
    return this.eltos[i][j];
  }

  public void setAt(int i, int j, double e) {
    if (i > filas || j > columnas)
        throw new IndexOutOfBoundsException("Fuera de rango.");
    this.eltos[i][j] = e;
  }

  public Matriz Producto(Matriz d) {
    Matriz result;
    if (this.columnas != d.filas) {
      throw new IndexOutOfBoundsException("Matrices de dimesiones distintas.");
    } else {
      result = new Matriz(this.filas, d.columnas);
        for (int i = 0; i < filas; ++i)
            for (int j = 0; j < d.columnas; ++j)
                for (int k = 0; k < columnas; ++k)
                    result.eltos[i][j] += this.eltos[i][k] * d.eltos[k][j];
    }
    return result;
  }

  public Matriz Suma(Matriz d) {
    Matriz result;
    if (!igualDimension(d)) {
        throw new IndexOutOfBoundsException("Dimesion de matrices no iguales");
    } else {
        result = new Matriz(filas, columnas);
        for (int i = 0; i < filas; ++i)
            for (int j = 0; j < columnas; ++j)
                result.eltos[i][j] = this.eltos[i][j] + d.eltos[i][j];
    }
    return result;
  }

  public Matriz Resta(Matriz d) {
      Matriz result;
      if (!igualDimension(d)) {
          throw new IndexOutOfBoundsException("Dimesion de matrices no iguales");
      } else {
          result = new Matriz(filas, columnas);
          for (int i = 0; i < filas; ++i)
              for (int j = 0; j < columnas; ++j)
                  result.eltos[i][j] = this.eltos[i][j] - d.eltos[i][j];
      }
      return result;
  }

  public Matriz Transpuesta() {
      Matriz result = new Matriz(columnas, filas);
      for (int fila = 0; fila < filas; ++fila)
          for (int col = 0; col < columnas; ++col)
              result.eltos[col][fila] = eltos[fila][col];
      return result;
  }

  public boolean igualDimension(Matriz d) {
    return this.filas == d.filas && this.columnas == d.columnas;
  }

  @Override
  public String toString() {
    String matriz = "[";
    for (int fila = 0; fila < filas; ++fila) {
      if (fila != 0) {
          matriz += " ";
      }
      matriz += "[";
      for (int columna = 0; columna < columnas; ++columna) {
        matriz += String.format("%8.3f", eltos[fila][columna]);
          if (columna != columnas - 1) {
            matriz +=" ";
          }
      }
      matriz += "]";
      if (fila == filas - 1) {
        matriz += "]";
      }
      matriz += "\n";
    }
    return matriz;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) return false;
    if (this.getClass() != o.getClass()) return false;
    Matriz that = (Matriz) o;
    if (!igualDimension(that)) return false;
    for (int i = 0; i < filas; ++i) {
      for (int j = 0; j < columnas; ++j) {
        if (this.eltos[i][j] != that.eltos[i][j]) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(eltos, filas, columnas);
  }

  public static void main(String[] args) {
    Matriz m = new Matriz(new double[][]{{1,2,3},{1,2,3},{1,2,3}});
    Matriz n = new Matriz(new double[][]{{1,2,3},{1,2,3},{1,2,3}});
    System.out.println(m == n);
  }

  private double[][] eltos;
  private int filas;
  private int columnas;
}