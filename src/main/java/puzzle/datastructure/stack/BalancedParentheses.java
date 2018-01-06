package puzzle.datastructure.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BalancedParentheses {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Character> deque = new ArrayDeque<>();
        boolean balanced = true;
        char balancedBracket = 'a';

        while (scanner.hasNextLine()) {
            balanced = true;
            String input = scanner.nextLine();
            deque.clear();
            for (char c : input.toCharArray()) {
                if (c == '{' || c == '[' || c == '(') {
                    deque.push((Character) c);
                } else {
                    if (deque.isEmpty()) {
                        balanced = false;
                        break;
                    }
                    switch (c) {
                        case ')':
                            balancedBracket = '(';
                            break;
                        case ']':
                            balancedBracket = '[';
                            break;
                        case '}':
                            balancedBracket = '{';
                            break;
                    }
                    if (balancedBracket != deque.pop()) {
                        balanced = false;
                    }
                }
            }
            if (!deque.isEmpty() && balanced)
                balanced = false;
            System.out.println(balanced);
        }
        scanner.close();
    }
}
