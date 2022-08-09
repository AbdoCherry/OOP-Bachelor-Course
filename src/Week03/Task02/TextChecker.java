package Week03.Task02;

import java.util.Scanner;

public class TextChecker {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("Word 1: ");
        String word1 = scanner.nextLine();
        System.out.print("Word 2: ");
        String word2 = scanner.nextLine();

        if (word1.isEmpty() && word2.isEmpty()) {
            System.out.println("\nError message: \"**** Parameter missed ****\"");

        } else if (word1.isEmpty() | word2.isEmpty()) {
            System.out.println("\nError message: \"**** One more parameter missed ****\"");

        } else {
            System.out.println("\n---------- Output ----------");
            System.out.println("Word 1: " + word1 + "\nWord 2: " + word2 + "\n");
        }

        scanner.close();
    }

}
