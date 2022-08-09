package Week04.Task05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class FileReaderText {

    /**
     * @return String
     * @throws FileNotFoundException
     */
    static String fileReader() throws FileNotFoundException {
        String os = System.getProperty("os.name");
        String pathWindows = "src\\Week4\\Task5\\Textpassage.txt"; // On Windows the file convention looks like this
        String pathMacOS = "src/Week4/Task5/Textpassage.txt"; // On MacOS the file convention looks like this
        String path;
        if (os.startsWith("Mac")) {
            path = pathMacOS;
        } else {
            path = pathWindows;
        }
        Scanner myFile = null; // Reading german umlauts with "utf-8"
        try {
            myFile = new Scanner(new File(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        myFile.useDelimiter(" ");

        String myText = "";

        while (myFile.hasNextLine()) {
            myText += myFile.nextLine();

        }
        return myText;
    }

    /**
     * @param myText
     * @param specialChar
     */
    static void histogram(String[] myText, char[] specialChar) {

        String digit = "", words = "", wordsStar = "", digitStar = "", special = "", specialStar = "";

        for (String element : myText) {
            if (element.toLowerCase().equals(element.toUpperCase())) {
                digit += element + " ";
                digitStar += "*";
            } else {
                words += element + " ";
                wordsStar += "*";

            }
        }
        for (char c : specialChar) {
            if (!Character.isAlphabetic(c) && !Character.isDigit(c) && !Character.isWhitespace(c)) {
                special += c;
                specialStar += "*";
            }
        }

        System.out.println("\nAll " + wordsStar.length() + " words from Text file:\n" + words);
        System.out.println(wordsStar + "\n");
        System.out.println("All " + digitStar.length() + " numbers from Text file:\n" + digit);
        System.out.println(digitStar + "\n");
        System.out.println("All " + specialStar.length() + " special character from Text file:\n" + special);
        System.out.println(specialStar + "\n");

    }

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String text = fileReader(); // Using our defined function above
        System.out.println("\nText File:\n" + text);

        char[] specialChar = text.toCharArray();

        // Strings are immutable => Thats why you need to assign manipulated Strings to
        // it´s own variable again
        // text.replace(",", ""); wouldn´t replace anything
        text = text.replace(",", " ");
        text = text.replace(".", " ");

        String[] myText = text.split(" ");

        histogram(myText, specialChar);

    }

}
