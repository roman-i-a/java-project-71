package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
)
public final class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    private String filepath1;
    @CommandLine.Parameters(index = "1")
    private String filepath2;

    @CommandLine.Option(
            names = {"-f", "--format"},
            description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }

    public static void main(final String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    public String getFilepath1() {
        return filepath1;
    }

    public void setFilepath1(final String filepath1) {
        this.filepath1 = filepath1;
    }

    public String getFilepath2() {
        return filepath2;
    }

    public void setFilepath2(final String filepath2) {
        this.filepath2 = filepath2;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(final String format) {
        this.format = format;
    }
}
