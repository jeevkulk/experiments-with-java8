package puzzle.datastructure.array;

import java.io.IOException;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/2d-array/problem
 *
 * We define an hourglass in A to be a subset of values with indices falling in this pattern in arr's
 * graphical representation:
 * a b c
 *   d
 * e f g
 *
 * There are 16 hourglasses in arr[6][6], and an hourglass sum is the sum of an hourglass's values.
 * Calculate the hourglass sum for every hourglass in arr[6][6], then print the maximum hourglass sum.
 *
 * For example, given the 2D array:
 *
 * Given a 6 x 6 2D Array:
 *
 * e.g.
 *
 * -9 -9 -9  1 1 1
 *  0 -9  0  4 3 2
 * -9 -9 -9  1 2 3
 *  0  0  8  6 6 0
 *  0  0  0 -2 0 0
 *  0  0  1  2 4 0
 */

public class HourglassSum {

    static int hourglassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE, sum = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = 0; j < arr[i].length - 2; j++) {
                sum = oneHourglassSum(arr, i, j);
                maxSum = sum > maxSum ? sum : maxSum;
            }
        }
        return maxSum;
    }

    static int oneHourglassSum(int[][] arr, int startIPos, int startJPos) {
        int sum = 0;
        for (int i = startIPos; i < startIPos + 3 && i < arr.length; i++) {
            for (int j = startJPos; j < startJPos + 3 && j < arr[i].length; j++) {
                if (!(i == startIPos + 1 && (j == startJPos || j == startJPos + 2)))
                    sum += arr[i][j];
            }
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int[][] arr = new int[6][6];
        for (int i = 0; i < 6; i++) {
            String[] arrRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int j = 0; j < 6; j++) {
                int arrItem = Integer.parseInt(arrRowItems[j]);
                arr[i][j] = arrItem;
            }
        }
        int result = hourglassSum(arr);
        System.out.println(result);
        scanner.close();
    }
}
