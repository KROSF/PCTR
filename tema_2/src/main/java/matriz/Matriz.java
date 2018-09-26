package matriz;

public class Matriz {
    private double[][] matriz;
    private int filas, columnas;

    public Matriz(int filas, int columnas) {
        this.matriz = new double[filas][columnas];
        this.filas = filas;
        this.columnas = columnas;
    }

    public Matriz(double[][] data) {
        this.matriz = data.clone();
        this.filas = this.matriz.length;
        this.columnas = this.matriz[0].length;
    }

    public double[][] arr() {
        return matriz;
    }

    public int filas() {
        return this.filas;
    }

    public int columnas() {
        return this.columnas;
    }

    public double getAt(int i, int j) {
        if (i > filas || j > columnas)
            throw new IndexOutOfBoundsException("Fuera de Rango");
        return this.matriz[i][j];
    }

    public void setAt(int i, int j, double e) {
        if (i > filas || j > columnas)
            throw new IndexOutOfBoundsException("Fuera de Rango");
        this.matriz[i][j] = e;
    }

    public Matriz Producto(Matriz d) {
        Matriz result;
        if (this.columnas != d.filas)
            throw new IndexOutOfBoundsException("Multiplicacion Imposible");
        else {
            result = new Matriz(this.filas, d.columnas);
            for (int i = 0; i < filas; ++i)
                for (int j = 0; j < d.columnas; ++j)
                    for (int k = 0; k < columnas; ++k)
                        result.matriz[i][j] += this.matriz[i][k] * d.matriz[k][j];
        }
        return result;
    }

    public Matriz Suma(Matriz d) {
        Matriz result;
        if (!igualDimension(d))
            throw new IndexOutOfBoundsException("Dimesion de matrices no iguales");
        else {
            result = new Matriz(filas, columnas);
            for (int i = 0; i < filas; ++i)
                for (int j = 0; j < columnas; ++j)
                    result.matriz[i][j] = this.matriz[i][j] + d.matriz[i][j];
        }
        return result;
    }

    public Matriz Resta(Matriz d) {
        Matriz result;
        if (!igualDimension(d))
            throw new IndexOutOfBoundsException("Dimesion de matrices no iguales");
        else {
            result = new Matriz(filas, columnas);
            for (int i = 0; i < filas; ++i)
                for (int j = 0; j < columnas; ++j)
                    result.matriz[i][j] = this.matriz[i][j] - d.matriz[i][j];
        }
        return result;
    }

    public Matriz Transpuesta() {
        Matriz result = new Matriz(columnas, filas);
        for (int fila = 0; fila < filas; ++fila)
            for (int col = 0; col < columnas; ++col)
                result.matriz[col][fila] = matriz[fila][col];
        return result;
    }

    public boolean esCuadrada() {
        return filas == columnas;
    }

    public boolean igualDimension(Matriz d) {
        return this.filas == d.filas && this.columnas == d.columnas;
    }

    public void display() {
        System.out.print("[");
        for (int fila = 0; fila < filas; ++fila) {
            if (fila != 0) {
                System.out.print(" ");
            }
            System.out.print("[");
            for (int columna = 0; columna < columnas; ++columna) {
                System.out.printf("%8.3f", matriz[fila][columna]);
                if (columna != columnas - 1) {
                    System.out.print(" ");
                }
            }
            System.out.print("]");
            if (fila == filas - 1) {
                System.out.print("]");
            }
            System.out.println();
        }
    }
}