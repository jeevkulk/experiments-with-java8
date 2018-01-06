package puzzle.generic;

import java.lang.reflect.Method;

public class PrintArray {
    public static void main( String args[] ) {
        Printer printer = new Printer();
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = {"Hello", "World"};
        printer.printArray(intArray);
        printer.printArray(stringArray);
        int count = 0;

        for (Method method : PrintArray.class.getDeclaredMethods()) {
            String name = method.getName();

            if(name.equals("printArray"))
                count++;
        }
        if(count > 1)
            System.out.println("Method overloading is not allowed!");
    }
}

class Printer<T> {

    public void printArray(T[] t) {
        for (T t1 : t) {
            System.out.println(t1);
        }
    }
}
