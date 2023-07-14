package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {
    public static final String JSON = "json";
    public static final String YAML = "yml";

    public static Map<String, Object> parse(final String filepath) throws IOException {
        Path path = Paths.get(filepath).toAbsolutePath().normalize();
        String extension = getExtension(path);
        ObjectMapper mapper;
        if (extension.equals(JSON)) {
            mapper = new JsonMapper();
        } else if (extension.equals(YAML)) {
            mapper = new YAMLMapper();
        } else {
            mapper = new ObjectMapper();
        }

        var fileString = Files.readString(path);
        return mapper.readValue(fileString, new TypeReference<>() {
        });
    }

    public static String getExtension(Path path) {
        var filename = path.getFileName().toString();
        var dot = filename.lastIndexOf(".");
        return filename.substring(dot + 1);
    }
}
