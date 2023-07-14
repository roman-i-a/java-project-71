package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;
import hexlet.code.Formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter implements Formatter {
    @Override
    public String format(List<Diff> diffs) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            var list = listDiffToListMap(diffs);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Map<String, Object>> listDiffToListMap(List<Diff> diffs) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Diff diff : diffs) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", diff.getProperty());
            switch (diff.getKeyStatus()) {
                case ADDED -> {
                    map.put("type", "added");
                    map.put("value", diff.getSecond());
                }
                case DELETED -> {
                    map.put("type", "deleted");
                    map.put("value", diff.getFirst());
                }
                case CHANGED -> {
                    map.put("type", "changed");
                    map.put("value1", diff.getFirst());
                    map.put("value2", diff.getSecond());
                }
                default -> {
                    map.put("type", "unchanged");
                    map.put("value", diff.getFirst());
                }
            }
            result.add(map);
        }
        return result;
    }
}
