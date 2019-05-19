package puzzle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class ReversingNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        Reverse reverse = new Reverse(a);
        System.out.println(reverse.reverseNumber());
    }
}

class Reverse{
    private int num;

    public Reverse(int num) {
        this.num = num;
    }

    public int reverseNumber() {
        Deque<Integer> deque = new ArrayDeque<>();
        int divider = 10;
        while (num != 0) {
            deque.add(num % divider);
            num = num / 10;
        }
        num = 0;
        while (deque.size() > 0) {
            num = (int) (num + (deque.pop() * Math.pow(10, deque.size())));
        }
        return num;
    }

    public int reverseNumberWithNoSpaceComplexity()
    {
        int rev = 0;
        while(num>0)
        {
            int rem = num%10;
            rev = (rev*10)+rem;
            num = num/10;
        }
        return rev;
    }
}