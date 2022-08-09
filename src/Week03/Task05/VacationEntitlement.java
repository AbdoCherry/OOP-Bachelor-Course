package Week03.Task05;

import java.util.Scanner;

public class VacationEntitlement {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[][] vacationEntitlement = { { 1, 15 }, { 2, 20 }, { 3, 20 }, { 4, 25 }, { 5, 25 }, { 6, 30 } };

        System.out.println("\nPlease enter the duration of your company affiliation");
        System.out.print("Years: ");
        int years = scanner.nextInt();

        if (years > 6) {
            years = 6;
        }

        System.out
                .println("\nYou´re entitled for " + vacationEntitlement[years - 1][1] + " days of vacation per year\n");

        scanner.close();

    }

}
