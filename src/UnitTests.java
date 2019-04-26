import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * @author Oleh Marych
 */
public class UnitTests {

    @Test
    public void testTask1() {
        String startedDir = "/Users/admin/IdeaProjects/demo-simple/src/resources/test";
        int filesCount = 2;
        int threshold = 1;
        String endDir = "/Users/admin/IdeaProjects/demo-simple/src/resources/testOutput";
        int emptyFilesCount = 4;
        int fullFilesCount = 3;

        System.out.println("Create " + emptyFilesCount + " empty files and "
                + fullFilesCount + " full files in directory " + startedDir);
        try {
            createFilesInDir(startedDir, emptyFilesCount, fullFilesCount);
        } catch (IOException e) {
            System.out.println("Error with creating files");
        }

        System.out.println("Run method");
        Main.handle(startedDir, filesCount, threshold, endDir);

        assertEquals(Objects.requireNonNull(new File(startedDir).list()).length, filesCount);
        assertEquals(Objects.requireNonNull(new File(endDir).list()).length, emptyFilesCount + fullFilesCount - filesCount);
        System.out.println("Clean up");
        deleteDirectory(new File(startedDir));
        deleteDirectory(new File(endDir));
    }

    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    private void createFilesInDir(String dir, int emptyFiles, int fullFiles) throws IOException {
        File dirFile = new File(dir);
        for (int i = 0; i < emptyFiles; i++) {
            File file = new File(dirFile, "emptyFile" + i + ".txt");
            file.createNewFile();
        }
        for (int i = 0; i < fullFiles; i++) {
            File file = new File(dirFile, "fullFile" + i + ".txt");
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Some text that has more than 1 byte");
            fileWriter.close();
        }
    }


    @Test
    public void testTask2(){
        String startedDir = "/Users/admin/IdeaProjects/demo-simple/src/resources/";
        System.out.println("Creating dir");
        Pair<String, Integer> dirs = createDirs(startedDir + "/testSecondTask");

        System.out.println("Run method");
        Pair<String, Integer> handle = Main2.handle(startedDir + "/testSecondTask");

        assertEquals(handle.getKey(), dirs.getKey());
        assertEquals(handle.getValue(), dirs.getValue());
    }
    @After
    public void cleanup(){
        System.out.println("Clean Up");
        deleteDirectory(new File("/Users/admin/IdeaProjects/demo-simple/src/resources/"));
    }

    private Pair<String, Integer> createDirs(String dir){
        String sub_4 = dir + "/sub1/sub4";
        String sub_5 = dir + "/sub1/sub5";
        String sub_6 = dir + "/sub2/sub6";
        String sub_7 = dir + "/sub3/sub7";
        String sub_8 = dir + "/sub3/sub8";
        String sub_9 = dir + "/sub3/sub9";
        String sub_10 = dir + "/sub3/sub10";
        String sub_11 = sub_4 + "/sub11";
        String sub_12 = sub_4 + "/sub12";

        new File(sub_4).mkdirs();
        new File(sub_5).mkdirs();
        new File(sub_6).mkdirs();
        new File(sub_7).mkdirs();
        new File(sub_8).mkdirs();
        new File(sub_9).mkdirs();
        new File(sub_10).mkdirs();
        new File(sub_11).mkdirs();
        new File(sub_12).mkdirs();

        return new Pair<>("sub3", 4);
    }
}
