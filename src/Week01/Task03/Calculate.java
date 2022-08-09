package Week01.Task3;

import java.util.Scanner;

public class Calculate {

    public static void main(String[] args) {

        // Our way to input values during the program => Check the Scanner class for
        // more information
        Scanner scanner = new Scanner(System.in);

        final double EXCHANGERATE = 1.1567; // Final variable => Their value won´t change

        double euro, dollar;

        System.out.print("\nPlease enter the amount in euro: ");
        euro = scanner.nextDouble();
        dollar = euro * EXCHANGERATE;

        // printf lets us format our numerical output
        System.out.printf("The amount you have entered has the dollar value of %.2f $", dollar);

        System.out.println("\n");

        scanner.close();

    }

}
