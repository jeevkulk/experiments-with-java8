package puzzle.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MeanCalculator {

    public static void main(String[] args) {
        MeanCalculator meanCalculator = new MeanCalculator();
        System.out.println(meanCalculator.calculate());
    }

    private double calculate() {
        int number, count = 0;
        double sum = 0.0;
        Scanner scanner = null;
        File dataFile = new File("E:\\technology_workspace\\data\\in\\quiz_numbers.txt");
        try {
            scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                number = scanner.nextInt();
                sum = sum + number;
                count++;
            }
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
        return sum / count;
    }
}
