package hilos;

class RunnableDemo implements Runnable {
    private Thread t;
    private String threadName;

    RunnableDemo(String name) {
        threadName = name;
        System.out.println("Creando " + threadName);
    }

    public void run() {
        System.out.println("Ejecutando" + threadName);
        try {
            for (int i = 4; i > 0; i--) {
                System.out.println("Hilo: " + threadName + ", " + i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("Hilo" + threadName + " interrumpido.");
        }
        System.out.println("Hilo " + threadName + " saliendo.");
    }

    public void start() {
        System.out.println("Iniciando " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class TestThread {
    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo("Hilo-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo("Hilo-2");
        R2.start();
    }
}