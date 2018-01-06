package puzzle.datastructure.array;

import java.util.Scanner;

public class JumpingGame {

    public static boolean isSolvable(int leap, int i, int[] game) {
        int len = game.length;

        if (i < 0 || game[i] != 0) {
            return false;
        } else if (leap + i > len - 1 || i == len - 1) {
            return true;
        }
        game[i] = 1;
        return isSolvable(leap,i+1, game) || isSolvable(leap,i-1, game) || isSolvable(leap, i+leap, game);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();

        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();

            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }
            System.out.println( (isSolvable(leap, 0, game)) ? "YES" : "NO" );
        }
        scan.close();
    }
}
