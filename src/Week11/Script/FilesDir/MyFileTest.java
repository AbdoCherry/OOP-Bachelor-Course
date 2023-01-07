package Week11.Script.FilesDir;

import java.io.File;
import java.io.IOException;

public class MyFileTest {
    public static void main(String[] args) throws IOException {

        // Declare filename in our directory
        final String fName = "src/Week11/Script/FilesDir/temp.txt";

        // Create file object
        final File f = new File(fName);

        // If path doesn´t exists, create it with `mkdir()`-> 'm'a'k'e 'dir'ectory
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        }

        // If file doesn´t exists, create it
        if (!f.exists()) {
            boolean isCreated = f.createNewFile();

            // Check if file is created
            if (isCreated) {
                System.out.println("File created");
            } else {
                System.out.println("File not created");
            }
        }

        // Using different file object methods
        System.out.println("Directory name: " + f.getParent());
        System.out.println("Filename: " + f.getName());
        System.out.println("Is file: " + f.isFile());
        System.out.println("Is directory: " + f.isDirectory());
        System.out.println("File readable: " + f.canWrite());

        // Create subdirectory
        final File subDir = new File(f.getParent() + "/mySubDir");
        if (!subDir.exists()) {
            subDir.mkdir();
        }

        // Content of actual directory
        System.out.println("\n **** Content of directory ");
        String[] lis = f.getParentFile().list();

        for (String s : lis) {
            System.out.println(s);
        }

    }

}
