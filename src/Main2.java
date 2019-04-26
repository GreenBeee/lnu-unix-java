import javafx.util.Pair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Oleh Marych
 */
public class Main2 {

    public static void main(String... args) {
        boolean isTest = args.length == 1 && args[0].equals("--test");
        if (args.length != 1) {
            helpText();
        }
        if (!isTest) {
            Pair<String, Integer> handle = handle(args[0]);
            System.out.println(handle.getKey() + " " + handle.getValue());
        }
    }

    public static void helpText() {
        System.out.println("usage: java Main [startedDir]");
        System.out.println("[startedDir] - started directory");
        System.out.println("To run tests: java Main --test");
        System.exit(1);
    }

    public static Pair<String, Integer> handle(String startedDir) {
        final int[] maxNumber = {0};
        final String[] maxName = {startedDir};

        try {
            Files.walk(Paths.get(startedDir))
                    .forEach(x -> {
                        File file = x.toFile();
                        if (!file.isDirectory())
                            return;
                        File[] subdirs = file.listFiles(File::isDirectory);
                        assert subdirs != null;
                        if (subdirs.length > maxNumber[0]) {
                            maxNumber[0] = subdirs.length;
                            maxName[0] = file.getName();
                        }
                    });
            return new Pair<>(maxName[0], maxNumber[0]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
