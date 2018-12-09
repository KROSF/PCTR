package com.krosf.pctr.p7;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * intParaleloFutureCont
 */
public class intParaleloFutureCont implements Callable<Double> {

  public intParaleloFutureCont(int iteraciones) {
    this.iteraciones = iteraciones;
  }

  public static void setFuncion(Funcion fun) {
    funcion = fun;
  }

  @Override
  public Double call() throws Exception {
    double sum = 0.;
    for (int i = 0; i < iteraciones; ++i) {
      sum += funcion.f(r.nextDouble());
    }
    return sum;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    int cores = Runtime.getRuntime().availableProcessors();
    ExecutorService service = Executors.newFixedThreadPool(cores);
    List<Callable<Double>> tasks = new ArrayList<Callable<Double>>();
    double sum = 0.;
    int iter = 100000;
    setFuncion(x -> Math.sin(x));
    for (int i = 0; i < cores; ++i) {
      tasks.add(new intParaleloFutureCont(iter));
    }
    List<Future<Double>> futures = service.invokeAll(tasks);
    service.shutdown();
    for (int i = 0; i < futures.size(); ++i) {
      sum += futures.get(i).get();
    }
    System.out.println((1.0 / (iter*cores)) * sum);
  }

  private Random r = new Random();
  private int iteraciones;
  private static Funcion funcion;
}