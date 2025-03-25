import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] arr1 = new int[n];


        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i + 1;
        }

        int arrayIndex = 0;

        do {
            System.out.print(arr1[arrayIndex]);
            arrayIndex = (arrayIndex + m - 1) % n;

        } while (arrayIndex != 0);
       





    }
}