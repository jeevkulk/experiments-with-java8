package concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class FindPrimeNumbers {

    private int rangeLower;
    private int rangeUpper;
    private int batchSize = 10;
    private Set<Integer> primeNumbersSet = new ConcurrentSkipListSet<>();

    public FindPrimeNumbers(int rangeLower, int rangeUpper) {
        this.rangeLower = rangeLower;
        this.rangeUpper = rangeUpper;
    }

    public Set<Integer> getPrimeNumbers() {
        List<Future<List<Integer>>> futureList = null;
        List<Callable<List<Integer>>> taskList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        final List<Batch> batches = taskSplitter();

        for (int i = 0; i < batches.size(); i++) {
            final Batch batch = batches.get(i);
            Callable<List<Integer>> callable = () -> {
                return findPrimeNumbers(batch);
            };
            taskList.add(callable);
        }
        try {
            futureList = executorService.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Future<List<Integer>> future : futureList) {
            try {
                List<Integer> primeNumberList = future.get();
                primeNumbersSet.addAll(primeNumberList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
        return primeNumbersSet;
    }

    private List<Batch> taskSplitter() {
        List<Batch> batches = new ArrayList<>();
        for (int i = rangeLower; i < rangeUpper; i=i+batchSize) {
            batches.add(new Batch(i, i+batchSize-1));
        }
        return batches;
    }

    private List<Integer> findPrimeNumbers(final Batch batch) {
        List<Integer> primeNumberList = new ArrayList<>();
        for (int i = batch.fromNumber; i < batch.toNumber+1; i++) {
            boolean isPrime = true;
            if (i < 0 || (i != 2 && i % 2 == 0)) {
                isPrime = false;
            } else if (i < 3) {
                isPrime = true;
            } else {
                for (int j = 3; j < i / 2; j=j+2) {
                    if (i % j == 0) {
                        isPrime = false;
                    }
                }
            }
            if (isPrime) {
                primeNumberList.add(i);
            }
        }
        return primeNumberList;
    }

    private class Batch {
        private int fromNumber;
        private int toNumber;

        public Batch(int fromNumber, int toNumber) {
            this.fromNumber = fromNumber;
            this.toNumber = toNumber;
        }

        public int getFromNumber() {
            return fromNumber;
        }

        public void setFromNumber(int fromNumber) {
            this.fromNumber = fromNumber;
        }

        public int getToNumber() {
            return toNumber;
        }

        public void setToNumber(int toNumber) {
            this.toNumber = toNumber;
        }
    }
}
