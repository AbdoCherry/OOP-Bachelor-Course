package Week08.Task4.de.my.util;

import java.util.Scanner;

public class MyUtil {

    /**
     * @param s
     * @return int
     */
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

    /**
     * @param c
     * @return String
     */
    public static String letterS(char[] c) {

        String myLetters = "";
        for (char l : c) {
            if (Character.isLetter(l)) {
                myLetters += l;
            }

        }
        return myLetters;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nEnter your input: ");
        String s = scanner.nextLine();

        System.out.println("The length of your input is \"" + lengthS(s) + "\"\nAll letters of your input "
                + letterS(s.toCharArray()));

        scanner.close();
    }

}
