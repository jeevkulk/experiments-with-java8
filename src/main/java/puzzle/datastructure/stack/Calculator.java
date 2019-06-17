package puzzle.datastructure.stack;


class Item{
    public int number1;
    public int number2;
    public int result;
    public char operation;
}

class Stack {
    private int maxSize;
    private Item[] stackArray;
    private int top;

    public Stack(int s) {
        this.maxSize = s;
        stackArray = new Item[s];
        top = -1;
    }

    public void push(Item j) {
        if (top == maxSize - 1) {
            System.out.println("Stack overflow");
        } else {
            stackArray[++top] = j;
        }
    }

    public void displayOperationTop5() {
        for (int i = top, j = 0; i >= 0 && j < 5; i--, j++) {
            System.out.println(stackArray[i].number1+" "+stackArray[i].operation+" "+stackArray[i].number2+" = "+stackArray[i].result);
        }
    }

    public int peek() {
        int result = -1;
        if (top == -1) {
            System.out.println("Stack underflow");
        } else {
            Item item = stackArray[top];
            result = item.result;
        }
        return result;
    }

    public void add(int number1, int number2) {
        Item item = new Item();
        item.number1 = number1;
        item.number2 = number2;
        item.operation = '+';
        item.result = number1 + number2;
        push(item);
        System.out.println("Result: "+item.result);
    }

    public void subtract(int number1, int number2) {
        Item item = new Item();
        item.number1 = number1;
        item.number2 = number2;
        item.operation = '-';
        item.result = number1 - number2;
        push(item);
        System.out.println("Result: "+item.result);
    }

    public void multiply(int number1, int number2) {
        Item item = new Item();
        item.number1 = number1;
        item.number2 = number2;
        item.operation = '*';
        item.result = number1 * number2;
        push(item);
        System.out.println("Result: "+item.result);
    }

    public void divide(int number1, int number2) {
        Item item = new Item();
        item.number1 = number1;
        item.number2 = number2;
        item.operation = '/';
        item.result = number1 / number2;
        push(item);
        System.out.println("Result: "+item.result);
    }
}

public class Calculator {
    public static void main(String[] args) {
        Stack newStack = new Stack(10);
        newStack.add(2,3);
        newStack.multiply(8,9);
        newStack.displayOperationTop5();
        newStack.divide(6,3);
        newStack.subtract(45,44);
        newStack.add(18,0);
        newStack.multiply(6,newStack.peek());
        newStack.displayOperationTop5();
        newStack.divide(2,3);
        newStack.subtract(3,newStack.peek());
    }
}

