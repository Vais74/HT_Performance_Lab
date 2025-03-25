import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        String fileName = args[0];
//        String fileName = "src/numbers.txt";


        List<Integer> numsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                numsList.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int[] nums = numsList.stream().mapToInt(Integer::intValue).toArray();
        int result = minMovesToEqual(nums);
        System.out.println(result);
    }

    public static int minMovesToEqual(int[] nums) {

        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}
