package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;

public interface Formatter {

    String STYLISH = "stylish";
    String PLAIN = "plain";
    String JSON = "json";

    static Formatter getFormatter(String format) {
        return switch (format) {
            case STYLISH, JSON -> new StylishFormatter();
            //case JSON -> new JsonFormatter();
            default -> new PlainFormatter();
        };
    }

    String format(List<Diff> diffs);
}
