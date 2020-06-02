package examples.em;

public class SharedVariable implements Runnable {
    private final int name;
    private static int turn = 1;

    public SharedVariable(int name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            while (turn != name){};
            System.out.println("Hilo "+ turn);
            if (turn == 1) {
                turn = 2;
            } else if (turn == 2) {
                turn = 1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[2];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new SharedVariable(i+1));
            threads[i].start();
        }

        for (Thread thread : threads) thread.join();
    }
}
