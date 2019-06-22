package puzzle.datastructure.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap: Two-sum problem
 * ------------------------------------------------------------------------------------------------
 * Description:
 * Design an algorithm for the two-sum problem using HashMap. Use the built in Java APIs for the HashMap class. You
 * have to count the number of all the unique pairs possible by picking two elements from the array which sum up to the
 * given target sum.
 *
 * The input will consist of three lines:
 * - The size of the array
 * - Elements of the array (space-separated)
 * - The target sum
 *
 * Use a HashMap whose key is the element of the array and value determines if the occurrence of that element is single
 * or multiple. You have to output the number of distinct pairs possible. Print 0 if no such pairs are possible.
 * Remember the ordering in the pair doesn't matter, so (1,3) and (3,1) will be considered the same pair and not two
 * distinct pairs. Complete the main method and the getPairsCount method accordingly.
 * ------------------------------------------------------------------------------------------------
 * Sample Input 1:
 * 5
 * 1 2 3 4 5
 * 10
 *
 * Output:
 * 0
 *
 * Explanation: No pair of elements sum up to 10
 * ------------------------------------------------------------------------------------------------
 * Sample Input 2:
 * 4
 * 1 1 1 1
 * 2
 *
 * Output:
 * 1
 *
 * Explanation: There is only 1 unique pair (1,1) which sums up to 2
 * ------------------------------------------------------------------------------------------------
 */
public class TwoSumProblem {

    static int getPairsCount(int sum, int[] arr) {
        Map<String, Integer> sumMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    String key = arr[i] < arr[j] ? arr[i] + "-" + arr[j] : arr[j] + "-" + arr[i];
                    if (sumMap.containsKey(key)) {
                        int numberOfPairs = sumMap.get(key);
                        sumMap.put(key, numberOfPairs + 1);
                    } else {
                        sumMap.put(key, 1);
                    }
                }
            }
        }
        return sumMap.size();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int arrLen = Integer.parseInt(br.readLine());
        String[] inputArrStr = br.readLine().split(" ");
        int[] inputArr = new int[arrLen];
        for (int i = 0; i < inputArrStr.length; i++) {
            inputArr[i] = Integer.parseInt(inputArrStr[i]);
        }
        int targetSum = Integer.parseInt(br.readLine());
        System.out.println(getPairsCount(targetSum, inputArr));
    }
}
