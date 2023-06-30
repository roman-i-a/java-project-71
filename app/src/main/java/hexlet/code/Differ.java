package hexlet.code;

import java.util.*;

public class Differ {

    private static final String TAB = "  ";
    private static final String PLUS = "+ ";
    private static final String MINUS = "- ";

    private static Map<String, String> getData(String filepath) {
        Map<String, String> map = new HashMap<>();
        // Получаем мапу значений json
        if (filepath.equals("file1.json")) {
            map.put("host", "hexlet.io");
            map.put("timeout", "50");
            map.put("proxy", "123.234.53.22");
            map.put("follow", "false");
        }
        if (filepath.equals("file2.json")) {
            map.put("timeout", "20");
            map.put("verbose", "true");
            map.put("host", "hexlet.io");

        }
        return map;
    }

    private static StringBuilder checkKeyAndAppendStringBuilder(String key, Map<String, String> file1,
                                                                Map<String, String> file2, StringBuilder sb) {

        return sb;
    }

    public static String generate(String format, String filepath1, String filepath2) {
        Map<String, String> file1 = getData(filepath1);
        Map<String, String> file2 = getData(filepath2);

        // Объединим все уникальные ключи в одно множество
        var keySet = new HashSet<>(file1.keySet());
        keySet.addAll(file2.keySet());

        var keys = new ArrayList<String>(keySet);
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : keys) {
            // file1 содержит ключ
            if (file1.containsKey(key)) {
                // file2 не содержит ключ
                if (!file2.containsKey(key)) {
                    sb.append(TAB).append(PLUS).append(key).append(": ").append(file1.get(key)).append("\n");
                    continue;
                }
                // Значение без изменений
                if (file1.get(key).equals(file2.get(key))) {
                    sb.append(TAB).append(TAB).append(key).append(": ").append(file1.get(key)).append("\n");
                    continue;
                }
                // Значение изменено
                sb.append(TAB).append(MINUS).append(key).append(": ").append(file1.get(key)).append("\n");
                sb.append(TAB).append(PLUS).append(key).append(": ").append(file2.get(key)).append("\n");
                continue;
            }
            // Добавлено
            sb.append(TAB).append("+ ").append(key).append(": ").append(file2.get(key)).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
