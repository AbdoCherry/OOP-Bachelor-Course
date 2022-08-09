package Week04.Task4;

import java.util.Scanner;

public class passwordChecker {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below!");
        System.out.print("Username: ");
        String username = scanner.nextLine();

        boolean level1 = false, level2 = false, level3 = false, hasUpperCase = false, hasDigit = false,
                specialChar = false, firstCharIsUpperCase = false;

        if (username.equals("Abda")) {
            System.out.print("Password: ");
            String password = scanner.nextLine();
            char[] pass = password.toCharArray();

            // Check if password has at least 8 characters
            level1 = password.length() > 8;

            for (int i = 0; i < pass.length; i++) {
                if (Character.isUpperCase(pass[i])) {
                    hasUpperCase = true;

                }
            }
            for (int i = 0; i < pass.length; i++) {
                if (Character.isDigit(pass[i])) {
                    hasDigit = true;
                }
            }

            level2 = hasUpperCase && hasDigit;

            for (int i = 0; i < pass.length; i++) {
                specialChar = !Character.isLetter(pass[i]) && !Character.isDigit(pass[i]);
            }
            firstCharIsUpperCase = Character.isUpperCase(pass[0]);

            level3 = specialChar && firstCharIsUpperCase;

            if (level1 && level2 && level3) {
                System.out.println("\n*** Security - Level ***\n");
            } else if (level1 && level2) {
                System.out.println("\n** Security - Level **\n");
            } else if (level1) {
                System.out.println("\n* Security - Level *\n");
            } else {
                System.out.println("\nERROR - PASSWORD CHECKER NOT WORKING\n");
            }

        } else {
            System.out.println("\nWrong Username\n");
        }

        scanner.close();

    }
}
