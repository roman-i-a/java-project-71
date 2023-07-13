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
                case ADDED -> sb.append(String.format(propertyTemplate, "+ ", diff.getProperty(), diff.getAfterValue()));
                case DELETED -> sb.append(String.format(propertyTemplate, "- ", diff.getProperty(), diff.getBeforeValue()));
                case CHANGED -> {
                    sb.append(String.format(propertyTemplate, "- ", diff.getProperty(), diff.getBeforeValue()));
                    sb.append(String.format(propertyTemplate, "+ ", diff.getProperty(), diff.getAfterValue()));
                }
                // UNCHANGED
                default -> sb.append(String.format(propertyTemplate, "  ", diff.getProperty(), diff.getAfterValue()));
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
}
