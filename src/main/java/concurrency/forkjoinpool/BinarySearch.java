package concurrency.forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class BinarySearch {

    public boolean doSearch(int[] arr, int searchFor) {
        Arrays.sort(arr);
        RecursiveBinarySearchTask recursiveBinarySearchTask = new RecursiveBinarySearchTask(arr, searchFor);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);
        return forkJoinPool.invoke(recursiveBinarySearchTask);
    }
}
