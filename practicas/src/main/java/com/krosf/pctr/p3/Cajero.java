package com.krosf.pctr.p3;

/**
 * Cajero
 */
public class Cajero implements Runnable {

  private static Cuenta_Banca cb = new Cuenta_Banca(1, 1E3);
  private static int iter;
  private boolean operacion;

  public Cajero(boolean operacion) {
    this.operacion = operacion;
  }

  public static double saldo() {
    return cb.Saldo();
  }

  /**
   * @param iter the iter to set
   */
  public static void setIter(int iter) {
    Cajero.iter = iter;
  }

  @Override
  public void run() {
    for (int i = 0; i < iter; ++i) {
      if (operacion) {
        cb.Deposito(100);
      } else {
        cb.Reintegro(100);
      }
    }
  }

}