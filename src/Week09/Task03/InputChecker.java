package Week09.Task3;

import java.util.Scanner;

public class InputChecker {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPleae enter two inputs in the fields below");
        System.out.print("\nInput 1: ");
        String input1 = scanner.nextLine();
        System.out.print("\nInput 2: ");
        String input2 = scanner.nextLine();

        try {
            if (input1 == "" && input2 == "") {
                throw new MissingParameter("\n**** Parameter missing ****");
            } else if (input1 == "" || input2 == "") {
                throw new MissingParameter("\n**** One more parameter missing");
            }

        } catch (MissingParameter e) {
            System.out.println("\n" + e.getMessage());
        } finally {
            System.out.println("\nString input 1: " + input1);
            System.out.println("String input 2: " + input2);
            scanner.close();
        }

    }

}
