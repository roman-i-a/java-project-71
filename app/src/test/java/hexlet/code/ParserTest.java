package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    public static Map<String, Object> expectedFile1;

    @BeforeAll
    public static void init() {
        expectedFile1 = new TreeMap<>();
        expectedFile1.put("host", "hexlet.io");
        expectedFile1.put("timeout", 50);
        expectedFile1.put("proxy", "123.234.53.22");
        expectedFile1.put("follow", false);
    }


    @Test
    public void testParsingJson() throws IOException {
        var jsonFile = "./src/test/resources/file1.json";
        assertEquals(Parser.parse(jsonFile), expectedFile1);
    }

    @Test
    public void testParsingYaml() throws IOException {
        var yamlFile = "./src/test/resources/file1.yml";
        assertEquals(Parser.parse(yamlFile), expectedFile1);
    }

    @Test
    public void testCheckExtension() {
        var jsonFile = "./src/test/resources/file1.json";
        var yamlFile = "./src/test/resources/file1.yml";
        assertEquals(Parser.getExtension(Path.of(jsonFile)), "json");
        assertEquals(Parser.getExtension(Path.of(yamlFile)), "yml");
    }
}
