package Week11.Task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
 * Please keep in mind that the file path is dependent on your operating system
 * Like in previous tasks I used a filedialogue which returns you the file path by your operating system
 * But you can use of course java io libraries to output your file and navigate through your directory
 */
public class ReadChrFile {
    public static void main(String[] args) {

        List<Character> myFileInput = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/Week11/Task01/Data/datIn.chr"));
            int fileInput;

            while ((fileInput = reader.read()) != -1) {
                myFileInput.add((char) fileInput); // Converting int to char and storing into arraylist of Character
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Simple for loop to check if characters are lowercase
        // Don´t be confused, the file input is in german `Java ist toll` (engl. Java is great)
        System.out.println("\nInput of our file:");
        System.out.println("---------------------");
        for (Character character : myFileInput) {
            if (Character.isLowerCase(character)) {
                char toUpper = Character.toUpperCase(character);
                System.out.print(toUpper);
            } else {
                System.out.print(character);
            }
        }
        System.out.println("\nTotal amount of characters: " + myFileInput.size());

        // Reversing the input of our Arraylist and storing in output file
        Collections.reverse(myFileInput);

        // Writing our reversed results
        try {
            File file = new File("src/Week11/Task01/Data/datOut.chr");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (char c : myFileInput) {
                writer.write(c);
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
