package hexlet.code.formatters;

import hexlet.code.Diff;
import hexlet.code.Formatter;

import java.util.List;

public class StylishFormatter implements Formatter {

    private final String propertyTemplate = "  %s%s: %s\n";

    @Override
    public String format(List<Diff> diffs) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Diff diff : diffs) {
            switch (diff.getKeyStatus()) {
                case ADDED -> sb.append(String.format(propertyTemplate, "+ ", diff.getProperty(), diff.getSecond()));
                case DELETED -> sb.append(String.format(propertyTemplate, "- ", diff.getProperty(), diff.getFirst()));
                case CHANGED -> {
                    sb.append(String.format(propertyTemplate, "- ", diff.getProperty(), diff.getFirst()));
                    sb.append(String.format(propertyTemplate, "+ ", diff.getProperty(), diff.getSecond()));
                }
                // UNCHANGED
                default -> sb.append(String.format(propertyTemplate, "  ", diff.getProperty(), diff.getSecond()));
            }
        }
        sb.append("}");
        return sb.toString();
    }
}
