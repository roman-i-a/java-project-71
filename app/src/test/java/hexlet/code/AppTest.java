package hexlet.code;


import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void DifferGenerateTest() throws IOException {
        String file1 = "/home/avilovdev/java-project-71/app/src/main/resources/file1.json";
        String file2 = "/home/avilovdev/java-project-71/app/src/main/resources/file2.json";
        String result = """
                {
                  + follow: false
                    host: hexlet.io
                  + proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String generated = Differ.generate("stylish", file1, file2);
        assertEquals(result, generated);
    }
}
