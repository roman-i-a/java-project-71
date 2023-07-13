package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String file1 = "./src/test/resources/fixtures/file1.json";
    private static final String file2 = "./src/test/resources/fixtures/file2.json";

    private static final String file1Yaml = "./src/test/resources/fixtures/file1.yml";
    private static final String file2Yaml = "./src/test/resources/fixtures/file2.yml";

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
            }
            """;


    @Test
    public void DifferGenerateFile1File2JsonTest() throws IOException {
        String generated = Differ.generate("stylish", file1, file2);
        assertEquals(DIFF_FILE1_FILE2, generated);
    }

    @Test
    public void DifferGenerateFile1File2YamlTest() throws IOException {
        String generated = Differ.generate("stylish", file1Yaml, file2Yaml);
        assertEquals(DIFF_FILE1_FILE2, generated);
    }
}
