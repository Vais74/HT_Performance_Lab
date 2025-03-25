import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Main {


//        String valuesFile = args[0];
//        String testsFile = args[1];
//        String reportFile = args[2];

//        String valuesFile = "src/values.json";
//        String testsFile = "src/tests.json";
//        String reportFile = "src/report.json";

    private final static Map<Integer, String> valuesMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        String testsJson = readFile(args[1]);
        String valuesJson = readFile(args[0]);

        parseValuesJson(valuesJson);

        String updatedTestsJson = updateTestsJson(testsJson);

        writeFile(args[2], updatedTestsJson);
    }


    private static String readFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private static void writeFile(String filename, String content) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
        bw.write(content);
        bw.close();
    }

    private static void parseValuesJson(String json) {

        String substring = json.substring(json.indexOf("[") + 1, json.indexOf("]"));
        // Убираем ненужные символы, кроме кавычек
        substring = substring.replaceAll("[{}]", "").trim();
        String[] pairs = substring.split(",");

        Integer currentId = null;
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length < 2) continue;

            String key = keyValue[0].trim().replaceAll("\"", "");
            String value = keyValue[1].trim().replaceAll("\"", "");

            if (key.equals("id")) {
                currentId = Integer.parseInt(value);
            } else if (key.equals("value") && currentId != null) {
                valuesMap.put(currentId, value);
                currentId = null;
            }
        }
    }

    private static String updateTestsJson(String json) {
        String idString = "\"id\": ";
        String valueString = "\"value\": \"\"";
        int lengthValueString = valueString.length();
        int lengthIdString = idString.length();

        StringBuilder result = new StringBuilder();
        int index = 0;
        int id = -1;

        while (index < json.length()) {

            char ch = json.charAt(index);

            // Ищем "id": <число>
            if (json.startsWith(idString, index)) {
                int start = index + lengthIdString;
                int end = start;
                while (end < json.length() && Character.isDigit(json.charAt(end))) {
                    end++;
                }
                id = Integer.parseInt(json.substring(start, end));
                result.append(json, index, end);
                index = end;
                continue;
            }

            if (json.startsWith(valueString, index) && id != -1) {
                String newValue = valuesMap.getOrDefault(id, "");
                String replacement = "\"value\": \"" + newValue + "\"";

                result.append(replacement);
                index += lengthValueString; // Пропускаем `"value": ""`
                id = -1; // Сбрасываем текущий id
                continue;
            }

            result.append(ch);
            index++;
        }

        return result.toString();
    }


}

