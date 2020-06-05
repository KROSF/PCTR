package pctr.automata;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class OneDimension implements Runnable {
    private static Integer[] cells, nextGenerationCells;
    private static Integer generations;
    private static CyclicBarrier barrier;
    private Integer begin, end;

    /**
     * @param begin of cells to this worker
     * @param end   of cell to this worker
     */
    public OneDimension(Integer begin, Integer end) {
        this.begin = begin;
        this.end = end;
    }

    /**
     * @param size        of cells.
     * @param generations of cells to calculate.
     * @param workers     to use on calculation.
     */
    private static void init(Integer size, Integer generations, Integer workers) {
        OneDimension.cells = new Random().ints(size, 0, 2).boxed().toArray(Integer[]::new);
        OneDimension.nextGenerationCells = new Integer[size];
        OneDimension.generations = generations;
        OneDimension.barrier = new CyclicBarrier(workers, () -> {
            System.out.println(Arrays.toString(cells));
            OneDimension.cells = nextGenerationCells.clone();
        });
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 10;
        int generations = 10;
        int workers = 4;
        int chunkSize = (size + workers - 1) / workers;
        OneDimension.init(size, generations, workers);
        ExecutorService executor = Executors.newFixedThreadPool(workers);
        for (int worker = 0, begin, end; worker < workers; worker++) {
            begin = worker * chunkSize;
            end = Math.min(begin + chunkSize, size);
            executor.execute(new OneDimension(begin, end));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.HOURS);
    }

    /**
     * @param cell position on cells array.
     * @return next generation value.
     */
    private Integer transition(Integer cell) {
        return (cells[Math.floorMod(cell - 1, cells.length)] + cells[cell]
                + cells[Math.floorMod(cell + 1, cells.length)]) % 3;
    }

    @Override
    public void run() {
        for (int generation = 0; generation < OneDimension.generations; ++generation) {
            for (Integer cell = begin; cell < end; ++cell) {
                OneDimension.nextGenerationCells[cell] = transition(cell);
            }
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
