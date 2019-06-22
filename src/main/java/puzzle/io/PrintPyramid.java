package puzzle.io;

import java.util.Scanner;

public class PrintPyramid {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a;
        a = in.nextInt();
        for (int i = a; i > 0; i--) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                sb.append("*");
                if (j < i - 1)
                    sb.append(" ");
                else
                    sb.append("\n");
            }
            System.out.print(sb.toString());
        }
    }
}
