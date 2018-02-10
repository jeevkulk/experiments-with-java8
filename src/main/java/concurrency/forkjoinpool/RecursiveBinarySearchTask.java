package concurrency.forkjoinpool;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class RecursiveBinarySearchTask extends RecursiveTask<Boolean> {

    private int[] arr;
    private int searchFor;

    public RecursiveBinarySearchTask(int[] arr, int searchFor) {
        this.arr = arr;
        this.searchFor = searchFor;
    }

    @Override
    protected Boolean compute() {

        int mid = arr.length / 2;

        if (arr[mid] == searchFor) {
            return true;
        } else if (mid == 1 || mid == arr.length) {
            return false;
        } else if (arr[mid] > searchFor) {
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            RecursiveBinarySearchTask recursiveBinarySearchTask = new RecursiveBinarySearchTask(left, searchFor);
            recursiveBinarySearchTask.fork();
            return recursiveBinarySearchTask.join();
        } else if (arr[mid] < searchFor) {
            int[] right = Arrays.copyOfRange(arr, mid + 1, arr.length);
            RecursiveBinarySearchTask recursiveBinarySearchTask = new RecursiveBinarySearchTask(right, searchFor);
            recursiveBinarySearchTask.fork();
            return recursiveBinarySearchTask.join();
        }
        return null;
    }
}
