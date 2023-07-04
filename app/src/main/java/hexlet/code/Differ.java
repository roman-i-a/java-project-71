package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;


public class Differ {
    private static final String TAB = "  ";
    private static final String PLUS = "+ ";
    private static final String MINUS = "- ";


    private static Map<String, Object> getData(final String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        var json = Files.readString(path);
        return mapper.readValue(json, new TypeReference<>() {
        });
    }

    public static String generate(String format, String filepath1,
                                  String filepath2) throws IOException {
        Map<String, Object> file1 = getData(filepath1);
        Map<String, Object> file2 = getData(filepath2);

        // Объединим все уникальные ключи в одно множество
        var keySet = new HashSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        var keys = new ArrayList<>(keySet);
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : keys) {
            if (!file1.containsKey(key)) {
                // Добавлено
                sb.append(TAB).append("+ ").append(key).append(": ").append(file2.get(key)).append("\n");
                continue;
            }
            // file1 содержит ключ
            // file2 не содержит ключ
            if (!file2.containsKey(key)) {
                sb.append(TAB).append(PLUS).append(key).append(": ").append(file1.get(key)).append("\n");
                continue;
            }
            // file2 содержит значение без изменений
            if (file1.get(key).equals(file2.get(key))) {
                sb.append(TAB).append(TAB).append(key).append(": ").append(file1.get(key)).append("\n");
                continue;
            }
            // file2 значение изменено
            sb.append(TAB).append(MINUS).append(key).append(": ").append(file1.get(key)).append("\n");
            sb.append(TAB).append(PLUS).append(key).append(": ").append(file2.get(key)).append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
