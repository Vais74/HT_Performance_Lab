import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {


//        для тестов
//        String circleFilePath = "src/circle.txt";
//        String pointsFilePath = "src/points.txt";

        try {

//            FileReader circleFile = new FileReader(circleFilePath);
            FileReader circleFile = new FileReader(args[0]);
            BufferedReader brCircle = new BufferedReader(circleFile);
            String[] circleData = brCircle.readLine().split(" ");
            double centerX = Double.parseDouble(circleData[0]);
            double centerY = Double.parseDouble(circleData[1]);
            double radius = Double.parseDouble(brCircle.readLine());
            brCircle.close();


//            FileReader pointsFile = new FileReader(pointsFilePath);
            FileReader pointsFile = new FileReader(args[1]);
            BufferedReader brPoints = new BufferedReader(pointsFile);
            String line;
            while ((line = brPoints.readLine()) != null) {
                String[] pointData = line.split(" ");
                double pointX = Double.parseDouble(pointData[0]);
                double pointY = Double.parseDouble(pointData[1]);


                double distanceSquared = Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2);
                double radiusSquared = Math.pow(radius, 2);


                if (distanceSquared == radiusSquared) {
                    System.out.println("0"); // Точка на окружности
                } else if (distanceSquared < radiusSquared) {
                    System.out.println("1"); // Точка внутри окружности
                } else {
                    System.out.println("2"); // Точка снаружи окружности
                }
            }
            brPoints.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }
}
