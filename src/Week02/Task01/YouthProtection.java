package Week02.Task01;

import java.util.Scanner;

public class YouthProtection {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nPlease enter your age: ");
        int age = scanner.nextInt();

        if (age < 16) {
            System.out.println("\nWe´re sorry, but you´re underaged and not allowed to drink alcohol\n");
        } else if (age >= 18) {
            System.out.println("\nWelcome and enjoy the party\n");
        } else if (age >= 16 && age < 18) {
            System.out.println("\nWelcome you´re allowed to drink beer and wine, but no spritouses\n");
        }

        scanner.close();
    }
}
