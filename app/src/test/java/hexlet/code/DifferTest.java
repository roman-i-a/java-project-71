package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static hexlet.code.Formatter.PLAIN;
import static hexlet.code.Formatter.STYLISH;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String FILE1 = "./src/test/resources/fixtures/file1.json";
    private static final String FILE2 = "./src/test/resources/fixtures/file2.json";

    private static final String FILE1YML = "./src/test/resources/fixtures/file1.yml";
    private static final String FILE2YML = "./src/test/resources/fixtures/file2.yml";

    private static final String DIFF_FILE1_FILE2 = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";

    private final String plainFormatted = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";


    @Test
    public void differGenerateFile1File2JsonTest() throws IOException {
        String generated = Differ.generate(FILE1, FILE2, STYLISH);
        assertEquals(DIFF_FILE1_FILE2, generated);
    }

    @Test
    public void differGenerateFile1File2YamlTest() throws IOException {
        String generated = Differ.generate(FILE1YML, FILE2YML);
        assertEquals(DIFF_FILE1_FILE2, generated);
    }

    @Test
    public void differTestPlainFormat() throws IOException {
        String generated = Differ.generate(FILE1, FILE2, PLAIN);
        assertEquals(plainFormatted, generated);
    }
}
