import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {

            FileReader file = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(file);
            String fileNumbers = br.readLine();
            br.close();


            String[] fileNumbersArray = fileNumbers.split("\\s+");
            int[] numbers = new int[fileNumbersArray.length];
            for (int i = 0; i < fileNumbersArray.length; i++) {
                numbers[i] = Integer.parseInt(fileNumbersArray[i]);
            }


            Arrays.sort(numbers);
            int median = numbers[numbers.length / 2];


            int moves = 0;
            for (int num : numbers) {
                moves += Math.abs(num - median);
            }


            System.out.println(moves);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}