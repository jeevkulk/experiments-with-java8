package concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrintOddEvenNumbers {

    volatile int counter = 0;
    int maxCount = 25;
    Object object = new Object();

    public static void main(String[] args) {
        PrintOddEvenNumbers printOddEvenNumbers = new PrintOddEvenNumbers();
        printOddEvenNumbers.process();
    }

    public void process() {
        Callable<Integer> printOddNumbersTask = () -> {
            try {
                while (counter < maxCount) {
                    synchronized (object) {
                        if (counter % 2 != 0) {
                            System.out.println(Thread.currentThread() + " : " + counter++);
                            object.notify();
                            if (counter < maxCount)
                                object.wait();
                        }
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Caught ="+ex.getMessage());
                ex.printStackTrace();
            }
            return counter;
        };
        Callable<Integer> printEvenNumbersTask = () -> {
            try {
                while (counter < maxCount) {
                    synchronized (object) {
                        if (counter % 2 == 0) {
                            System.out.println(Thread.currentThread() + " : " + counter++);
                            object.notify();
                            if (counter < maxCount)
                                object.wait();
                        }
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Caught ="+ex.getMessage());
                ex.printStackTrace();
            }
            return counter;
        };
        List<Callable<Integer>> tasks = new ArrayList<>(2);
        tasks.add(printEvenNumbersTask);
        tasks.add(printOddNumbersTask);
        ExecutorService service = Executors.newFixedThreadPool(2);
        try {
            List<Future<Integer>> futures = service.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
