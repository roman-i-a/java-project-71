package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private static Map<String, Object> expectedFile1;

    public static Map<String, Object> getExpectedFile1() {
        return expectedFile1;
    }

    public static void setExpectedFile1(Map<String, Object> expectedFile1) {
        ParserTest.expectedFile1 = expectedFile1;
    }

    public static final int TIMEOUT = 50;

    @BeforeAll
    public static void init() {
        var map = new TreeMap<String, Object>();
        map.put("host", "hexlet.io");
        map.put("timeout", TIMEOUT);
        map.put("proxy", "123.234.53.22");
        map.put("follow", false);
        setExpectedFile1(map);
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
