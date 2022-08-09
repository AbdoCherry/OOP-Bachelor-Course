package Week04.Task01;

import java.util.Scanner;

public class CharacterAnalysis {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Enter text: ");
        String input = scanner.nextLine();

        int start, end;

        if (input.indexOf("(") < input.indexOf(")")) {
            start = input.indexOf("(");
            end = input.indexOf(")");
            System.out.println("\nText between parenthesis: " + input.substring(start + 1, end));
        } else if (input.lastIndexOf(")") > input.indexOf("(")) {
            start = input.indexOf("(");
            end = input.lastIndexOf(")");
            System.out.println("\nText between parenthesis: " + input.substring(start + 1, end));

        } else {
            System.out.println();

        }

        scanner.close();

    }
}
