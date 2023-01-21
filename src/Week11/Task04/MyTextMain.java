package Week11.Task04;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class MyTextMain {

    static String readText() {

        String path = "src/Week11/Task04/Data/RawText.txt";
        String line = null;
        StringBuilder rawText = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while ((line = reader.readLine()) != null) rawText.append(line);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rawText.toString();
    }

    static void reverseText(@NotNull String rawText) {

        StringBuilder rt = new StringBuilder(rawText);
        String revertText = String.valueOf(rt.reverse());

        System.out.println("Original Text: \"" + rawText + "\" Reverted Text: \"" + revertText + "\"");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/Week11/Task04/Data/Reversed.txt"));
            writer.write(revertText);
            writer.close();

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String countChars(String rawText) {

        int counterChars = 0;
        char[] textToChar = rawText.toCharArray();

        for (char c : textToChar) if (c != ' ') counterChars++;
        rawText = rawText.toUpperCase();
        System.out.println("Number of total chars incl. whitespace : \"" + rawText.length() + "\"");
        System.out.println("Number of total chars excl. whitespace : \"" + counterChars + "\"");
        System.out.println("\nAll lower case characters to uppercase");
        System.out.println("Rawtext: \"" + String.valueOf(textToChar) + "\" -> to uppercase: -> \"" + rawText + "\"");

        return rawText;
    }

    public static void main(String[] args) {

        // Read from txt file
        String rawText = readText();

        // Convert uppercase
        String convRawText = countChars(rawText);

        // Reverse String and write to text file
        reverseText(convRawText);
    }
}
