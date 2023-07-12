package hexlet.code;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static final String file1 = "./file1.json";
    private static final String file2 = "./file2.json";

    @BeforeAll
    public static void createFiles() {
        try {
            var filePath1 = Files.createFile(Path.of(file1));
            var filePath2 = Files.createFile(Path.of(file2));

            String body1 = """
                    {
                      "host": "hexlet.io",
                      "timeout": 50,
                      "proxy": "123.234.53.22",
                      "follow": false
                    }""";
            String body2 = """
                    {
                      "timeout": 20,
                      "verbose": true,
                      "host": "hexlet.io"
                    }""";

            Files.writeString(filePath1, body1);
            Files.writeString(filePath2, body2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void deleteFiles() {
        try {
            Files.delete(Path.of(file1));
            Files.delete(Path.of(file2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void DifferGenerateTest() throws IOException {
        String result = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String generated = Differ.generate("stylish", file1, file2);
        assertEquals(result, generated);
    }

    @Test
    public void DifferGenerateTest2() throws IOException {
        String result = """
                {
                  + follow: false
                    host: hexlet.io
                  + proxy: 123.234.53.22
                  - timeout: 20
                  + timeout: 50
                  - verbose: true
                }
                """;
        String generated = Differ.generate("stylish", file2, file1);
        assertEquals(result, generated);
    }
}
