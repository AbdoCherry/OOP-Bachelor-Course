package Week02.Task05;

import java.util.Scanner;

public class Grades {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter your grade\n");

        char inputContinue;

        do {

            System.out.print("Grade: ");
            int grade = scanner.nextInt();

            switch (grade) {

                case 1:

                    System.out.println("\nVery good - A\n");

                    break;

                case 2:
                    System.out.println("\nGood - B\n");

                    break;

                case 3:
                    System.out.println("\nSatisfactory - C\n");

                    break;

                case 4:
                    System.out.println("\nSufficient - D\n");

                    break;

                case 5:
                    System.out.println("\nPoor - F\n"); // Only available in germany

                    break;

                case 6:
                    System.out.println("\nInsufficient\n"); // Only available in germany

                    break;
            }

            System.out.print("You want to keep adding grades?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            inputContinue = Character.toUpperCase(scanner.next().charAt(0)); // User input gets converted to char
        } while (inputContinue == 'Y');
        System.out.println("\nThank you for using the program\n");

        scanner.close();

    }

}
