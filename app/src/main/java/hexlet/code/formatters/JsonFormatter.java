package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Diff;
import hexlet.code.Formatter;

import java.util.List;

public class JsonFormatter implements Formatter {
    @Override
    public String format(List<Diff> diffs) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(diffs);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
