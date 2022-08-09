package Week01.Task05;

import java.util.Scanner;

public class Calculate3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int CABLEPRICE = 2, WAGE = 50;

        double laborTime, cableMeter;

        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Meter of cable spent: ");
        cableMeter = scanner.nextDouble();
        System.out.print("Expenses in hours: ");
        laborTime = scanner.nextDouble();

        double bill = cableMeter * CABLEPRICE + laborTime * WAGE;

        System.out.printf("\nThe complete costs amount to: %.2f $\n", bill);

        scanner.close();

    }

}
