package Week11.Script.InputOutput;

import java.io.*;

public class MySinBStr {

    public static void main(String[] args) {
        // Example for In/OutputStream explained

        File myFile = new File("src/Week11/Script/InputOutput/Byte.byt");
        try (FileOutputStream fos = new FileOutputStream(myFile);
             FileInputStream fis = new FileInputStream(myFile)) {

            // Example for writing single-byte stream
            for (int i = 0; i < 26; i++) {
                fos.write('A' + i);
            }

            // Example for reading single-byte stream
            int b;
            int count = 0;
            while ((b = fis.read()) != -1) {
                System.out.println(b + " ");
                count++;
            }

            System.out.println("\nRead: " + count);
        } catch (FileNotFoundException f) {
            System.out.println("File not Found: " + f);
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }
}
