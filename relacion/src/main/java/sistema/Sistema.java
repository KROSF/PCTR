package sistema;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Sistema {
    private static final double G = 6.674 * Math.pow(10, -11);
    private List<Cuerpos> solar;

    public Sistema() {
        solar = new ArrayList<Cuerpos>();
    }

    public void insetarCuerpos(Cuerpos p) {
        solar.add(p.getId(), p);
    }

    public Cuerpos getCuerpo(int i) {
        return solar.get(i);
    }

    public void insertarCuerpos() {
        String file = "/Users/krosf/vscode/PCTR/relacion/src/main/java/sistema/planetas.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] planeta = line.split(",");
                Cuerpos plnt = new Cuerpos(Double.parseDouble(planeta[0]), Double.parseDouble(planeta[1]),
                        Double.parseDouble(planeta[2]), Double.parseDouble(planeta[3]), Integer.parseInt(planeta[4]),
                        planeta[5]);
                solar.add(plnt.getId(), plnt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double atraccionGravitacional(Cuerpos c1, Cuerpos c2) {
        int a = solar.indexOf(c1);
        int b = solar.indexOf(c2);
        if (a != -1 && b != -1) {
            Cuerpos pa = solar.get(a);
            Cuerpos pb = solar.get(b);
            return (pa.getMasa() * pb.getMasa() * G)/Math.pow(Math.abs(pa.getDistancia() - pb.getDistancia()), 2);
        } else
            return 0.0;
    }

    public static void main(String[] args) {
        Sistema solar = new Sistema();
        solar.insertarCuerpos();
        for (int i = 0; i < 9; ++i){
            System.out.println(solar.getCuerpo(i).getNombre());
            for(int j = 0; j < 9; ++j){
                if (i != j){
                    System.out.print(solar.getCuerpo(j).getNombre()+" ");
                    System.out.println(solar.atraccionGravitacional(solar.getCuerpo(i), solar.getCuerpo(j)));
                }
            }
        }
    }
}

class Cuerpos {
    private double densidad;
    private double diametro;
    private double distancia;
    private double masa;
    private int id;
    private String nombre;

    @Override
    public String toString() {
        return "{" + " \"densidad\": " + getDensidad() + ",\n\"diametro\": " + getDiametro() + ",\n\"distancia\": "
                + getDistancia() + ",\n\"masa\": " + getMasa() + ",\n\"id\": " + getId() + ",\n\"nombre\": \""
                + getNombre() + "\"\n" + "},";
    }

    public double getDensidad() {
        return this.densidad;
    }

    public double getDiametro() {
        return this.diametro;
    }

    public double getDistancia() {
        return this.distancia;
    }

    public double getMasa() {
        return this.masa;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Cuerpos(double densidad, double diametro, double distancia, double masa, int id, String nombre) {
        this.densidad = densidad;
        this.diametro = diametro;
        this.distancia = distancia;
        this.masa = masa;
        this.id = id;
        this.nombre = nombre;
    }
}
