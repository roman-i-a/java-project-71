package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @CommandLine.Parameters(index = "0")
    String filepath1;
    @CommandLine.Parameters(index = "1")
    String filepath2;

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    String format = "stylish";

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(filepath1, filepath2, format));
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
