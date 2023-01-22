package Week08.Task04.de.my.util;

import java.util.Scanner;

public class MyUtil {

    public static int lengthS(String s) {

        int lengthS = 0;
        char[] myInput = s.toCharArray();
        for (char c : myInput) {
            if (Character.isLetter(c)) {
                lengthS++;
            }
        }

        return lengthS;
    }

    public static String letterS(char[] c) {

        StringBuilder myLetters = new StringBuilder();
        for (char l : c) {
            if (Character.isLetter(l)) {
                myLetters.append(l);
            }

        }
        return myLetters.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your input: ");
        String s = scanner.nextLine();

        System.out.println("The length of your input is \"" + lengthS(s) + "\"\nAll letters of your input "
                + letterS(s.toCharArray()));

        scanner.close();
    }

}
