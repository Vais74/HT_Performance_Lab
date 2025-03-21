import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("введите длинну массива");
        int n = sc.nextInt();
        System.out.println("введите интервал длинны");
        int m = sc.nextInt();

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