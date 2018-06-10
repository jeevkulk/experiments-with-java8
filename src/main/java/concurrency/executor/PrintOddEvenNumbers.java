package concurrency.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrintOddEvenNumbers {

    volatile int counter = 0;

    public static void main(String[] args) {
        PrintOddEvenNumbers printOddEvenNumbers = new PrintOddEvenNumbers();
        printOddEvenNumbers.process();
    }

    public void process() {
        Callable<Integer> printOddNumbersTask = () -> {
            if (counter % 2 != 0)
                System.out.println(Thread.currentThread() + " : " + counter++);
            return counter;
        };
        Callable<Integer> printEvenNumbersTask = () -> {
            if (counter % 2 == 0)
                System.out.println(Thread.currentThread() + " : " + counter++);
            return counter;
        };
        List<Callable<Integer>> tasks = new ArrayList<>(200);
        for (int i = 0; i < 100; i ++) {
            tasks.add(printOddNumbersTask);
            tasks.add(printEvenNumbersTask);
        }
        ExecutorService service = Executors.newFixedThreadPool(2);
        try {
            List<Future<Integer>> futures = service.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
