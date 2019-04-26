import java.io.File;

public class Main {

    public static void main(String... args) {
        boolean isTest = args.length == 1 && args[0].equals("--test");
        if (args.length != 4 && !isTest) {
            helpText();
        }
        if (!isTest) {
            handle(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
        }
    }

    public static void helpText() {
        System.out.println("usage: java Main [startedDir] [filesCount] [threshold] [endDir]\n");
        System.out.println("[startedDir] - started directory\n");
        System.out.println("[filesCount] - count of files\n");
        System.out.println("[threshold] - threshold size of file in byte\n");
        System.out.println("[endDir] - directory, where all files that don\'t pass threshold will be copied\n");
        System.out.println("To run tests: java Main --test\n");
        System.exit(1);
    }

    public static void handle(String startedDir, int filesCount, int threshold, String endDir) {
        int counter = 0;
        File fileDir = new File(startedDir);
        File[] files = fileDir.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.length() <= threshold && counter < filesCount) {
                counter++;
            } else {
                file.renameTo(new File(endDir + "/" + file.getName()));
            }
        }
    }
}