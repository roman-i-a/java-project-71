package hexlet.code.formatters;

import hexlet.code.Diff;
import hexlet.code.Formatter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PlainFormatter implements Formatter {

    @Override
    public String format(List<Diff> diffs) {
        StringBuilder sb = new StringBuilder();
        for (Diff diff : diffs) {
            String value1 = checkValue(diff.getFirst());
            String value2 = checkValue(diff.getSecond());

            String templateRemoved = "Property '%s' was removed\n";
            switch (diff.getKeyStatus()) {
                case ADDED -> {
                    String templateAdded = "Property '%s' was added with value: %s\n";
                    sb.append(String.format(templateAdded, diff.getProperty(), value2));
                }
                case DELETED -> sb.append(String.format(templateRemoved, diff.getProperty()));
                case CHANGED -> {
                    String templateUpdated = "Property '%s' was updated. From %s to %s\n";
                    sb.append(String.format(
                            templateUpdated,
                            diff.getProperty(),
                            value1,
                            value2));
                }
                default -> {}
            }
        }
        return sb.toString();
    }

    private String checkValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof Collection<?> || value instanceof Map<?, ?>) {
            return "[complex value]";
        } else if (value instanceof String) {
            return "'" + value + "'";
        }
        return value.toString();
    }
}
