package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public interface Formatter {

    String STYLISH = "stylish";

    static Formatter getFormatter(String format) {
        return switch (format) {
            case STYLISH -> new StylishFormatter();
            default -> new PlainFormatter();
        };
    }

    String format(List<Diff> diffs);
}
