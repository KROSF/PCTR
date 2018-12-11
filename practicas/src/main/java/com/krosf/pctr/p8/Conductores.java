package com.krosf.pctr.p8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *
 * 
 * @author Carlos Rodigo Sanabria Flores
 * @version 1.0
 */
public class Conductores {

  public Conductores() {
    this.drivers = new ArrayList<Conductor>();
  }

  public Conductores(String filepath) {
    this.filepath = filepath;
    try {
      File file = new File(filepath);
      InputStream in = new FileInputStream(file);
      BufferedReader br = new BufferedReader(new InputStreamReader(in));
      drivers = br.lines().skip(1).map(mapToConductor).collect(Collectors.toList());
      br.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public synchronized Conductor getConductor(int index) {
    return drivers.get(index);
  }

  public synchronized List<Conductor> getConductores(int fromIndex, int toIndex) {
    return drivers.subList(fromIndex, toIndex);
  }

  public synchronized List<Conductor> getConductores() {
    return drivers;
  }

  public synchronized void setConductor(Conductor conductor) {
    if (drivers.contains(conductor)) {
      drivers.add(conductor);
    }
  }

  public synchronized void save() {
    String all = drivers.stream().map(mapToString).collect(Collectors.joining("\n"));
    try {
      File file = new File(filepath);
      BufferedWriter output = new BufferedWriter(new FileWriter(file));
      output.write("name, dni, validUntil, points\n");
      output.write(all);
      output.close();
    } catch (IOException e) {
      System.err.println(e);
    }
  }

  public synchronized int size() {
    return drivers.size();
  }

  private static Function<String, Conductor> mapToConductor = (line) -> {
    String[] fields = line.split(", ");
    return new Conductor(fields[0], fields[1], fields[2], fields[3]);
  };

  private static Function<Conductor, String> mapToString = (conductor) -> {
    return String.format("%s, %s, %s, %d", conductor.getName(), conductor.getDni(), conductor.getValidUntil(),
        conductor.getPoints());
  };

  private String filepath;
  private List<Conductor> drivers;
}