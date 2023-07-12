package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;


public class Differ {
    public static final String STYLISH = "stylish";

    private static Map<String, Object> getData(final String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path path = Paths.get(filepath).toAbsolutePath().normalize();

        var json = Files.readString(path);
        return mapper.readValue(json, new TypeReference<>() {
        });
    }

    public static Map<String, KeyStatus> genDiff(Map<String, Object> file1, Map<String, Object> file2) {
        Map<String, KeyStatus> result = new HashMap<>();
        var keySet = new TreeSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        for (String key : keySet) {
            if (!file1.containsKey(key)) {
                result.put(key, KeyStatus.ADDED);
            } else if (!file2.containsKey(key)) {
                result.put(key, KeyStatus.DELETED);
            } else if (file1.get(key).equals(file2.get(key))) {
                result.put(key, KeyStatus.UNCHANGED);
            } else {
                result.put(key, KeyStatus.CHANGED);
            }
        }
        return result;
    }

    public static String generate(String format, String filepath1,
                                  String filepath2) throws IOException {
        Map<String, Object> file1 = getData(filepath1);
        Map<String, Object> file2 = getData(filepath2);

        var diff = genDiff(file1, file2);
        return formatDiffResult(format, diff, file1, file2);
    }

    private static String formatDiffResult(String format,
                                           Map<String, KeyStatus> diff,
                                           Map<String, Object> file1,
                                           Map<String, Object> file2) {
        switch (format) {
            case STYLISH -> {
                return formatStylish(diff, file1, file2);
            }
        }
        return "";
    }

    private static String formatStylish(
            Map<String, KeyStatus> diff,
            Map<String, Object> file1,
            Map<String, Object> file2) {
        var keySet = diff.keySet();
        var keys = new ArrayList<>(keySet);
        Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (String key : keys) {
            switch (diff.get(key)) {
                case ADDED -> sb.append("  ").append("+ ").append(key).append(": ").append(file2.get(key)).append("\n");
                case DELETED -> sb.append("  ").append("- ").append(key).append(": ").append(file1.get(key)).append("\n");
                case CHANGED -> {
                    sb.append("  ").append("- ").append(key).append(": ").append(file1.get(key)).append("\n");
                    sb.append("  ").append("+ ").append(key).append(": ").append(file2.get(key)).append("\n");
                }
                case UNCHANGED -> sb.append("  ").append("  ").append(key).append(": ").append(file2.get(key)).append("\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}
