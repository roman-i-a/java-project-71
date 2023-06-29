package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")
public class Differ implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("help");
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Differ()).execute(args);
        System.exit(exitCode);
    }
}
