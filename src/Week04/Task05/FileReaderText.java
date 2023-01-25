package Week04.Task05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileReaderText {

    static String fileReader() {
        String os = System.getProperty("os.name");
        String pathWindows = "src\\Week04\\Task05\\Textpassage.txt"; // On Windows the file convention looks like this
        String pathMacOS = "src/Week04/Task05/Textpassage.txt"; // On macOS the file convention looks like this
        String path;
        if (os.startsWith("Mac")) {
            path = pathMacOS;
        } else {
            path = pathWindows;
        }
        Scanner myFile; // Reading german umlauts with "utf-8"
        try {
            myFile = new Scanner(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myFile.useDelimiter(" ");

        StringBuilder myText = new StringBuilder();

        while (myFile.hasNextLine()) {
            myText.append(myFile.nextLine());

        }
        return myText.toString();
    }

    static void histogram(String[] myText, char[] specialChar) {

        StringBuilder digit = new StringBuilder();
        StringBuilder words = new StringBuilder();
        StringBuilder wordsStar = new StringBuilder();
        StringBuilder digitStar = new StringBuilder();
        StringBuilder special = new StringBuilder();
        StringBuilder specialStar = new StringBuilder();

        for (String element : myText) {
            if (element.toLowerCase().equals(element.toUpperCase())) {
                digit.append(element).append(" ");
                digitStar.append("*");
            } else {
                words.append(element).append(" ");
                wordsStar.append("*");

            }
        }
        for (char c : specialChar) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isWhitespace(c)) {
                special.append(c);
                specialStar.append("*");
            }
        }

        System.out.println("\nAll " + wordsStar.length() + " words from Text file:\n" + words);
        System.out.println(wordsStar + "\n");
        System.out.println("All " + digitStar.length() + " numbers from Text file:\n" + digit);
        System.out.println(digitStar + "\n");
        System.out.println("All " + specialStar.length() + " special character from Text file:\n" + special);
        System.out.println(specialStar + "\n");

    }

    public static void main(String[] args) throws FileNotFoundException {

        String text = fileReader(); // Using our defined function above
        System.out.println("\nText File:\n" + text);

        char[] specialChar = text.toCharArray();

        // Strings are immutable => That's why you need to assign manipulated Strings to
        // it´s own variable again
        // text.replace(",", ""); wouldn´t replace anything
        text = text.replace(",", " ");
        text = text.replace(".", " ");

        String[] myText = text.split(" ");

        histogram(myText, specialChar);

    }

}
