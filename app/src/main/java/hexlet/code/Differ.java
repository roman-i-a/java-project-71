package hexlet.code;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class Differ {

    private static List<Diff> genDiffList(final Map<String,
            Object> file1, final Map<String, Object> file2) {
        List<Diff> diffs = new ArrayList<>();
        var keySet = new TreeSet<>(file1.keySet());
        keySet.addAll(file2.keySet());
        for (String key : keySet) {
            boolean hasFirst = file1.containsKey(key);
            boolean hasSecond = file2.containsKey(key);
            diffs.add(new Diff(key, file1.get(key),
                    file2.get(key), hasFirst, hasSecond));
        }
        return diffs.stream().sorted().toList();
    }

    public static String generate(final String filepath1,
                                  final String filepath2,
                                  final String format) throws IOException {
        Map<String, Object> file1 = Parser.parse(filepath1);
        Map<String, Object> file2 = Parser.parse(filepath2);

        var diffs = genDiffList(file1, file2);
        return Formatter.getFormatter(format).format(diffs);
    }

    public static String generate(final String filepath1,
                                  final String filepath2) throws IOException {
        return Differ.generate(filepath1, filepath2, "stylish");
    }
}
