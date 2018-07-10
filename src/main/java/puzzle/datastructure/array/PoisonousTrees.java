package puzzle.datastructure.array;

import java.io.IOException;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/poisonous-plants/problem
 * Sample Input
 * 4                <-- Number of trees
 * 3 2 5 4          <-- Pesticide level
 *
 * If pesticide level in the tree left to the tree is less than itself - then tree under consideration dies each day.
 * Need to output number of days after which no tree will die.
 */

public class PoisonousTrees {

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
        int noOfDays = 0;
        boolean treeDied = true;
        int[] currTreeStatus = new int[p.length];
        int[] prevDayTreeStatus = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            currTreeStatus[i] = 1;
            prevDayTreeStatus[i] = 1;
        }
        while (treeDied) {
            treeDied = false;
            for (int i = 0; i < p.length - 1; i++) {
                if (prevDayTreeStatus[i] == 0)
                    continue;
                for (int j = i + 1; j < p.length; j++) {
                    if (prevDayTreeStatus[j] == 1) {
                        if (p[i] < p[j]) {
                            currTreeStatus[j] = 0;
                            treeDied = true;
                            break;
                        }
                    }
                }
            }
            if (treeDied) {
                noOfDays++;
                for (int k = 0; k < p.length; k++) {
                    prevDayTreeStatus[k] = currTreeStatus[k];
                }
            }
        }
        return noOfDays;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int result = poisonousPlants(p);
        scanner.close();
    }
}
