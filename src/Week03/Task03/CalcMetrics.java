package Week03.Task03;

import java.util.Scanner;

public class CalcMetrics {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double[] accountBalance = new double[5];

        System.out.println("\nPlease enter account balances");
        for (int i = 0; i < accountBalance.length; i++) {
            System.out.print("Amount " + (i + 1) + ": ");
            accountBalance[i] = scanner.nextDouble();
        }

        // Get minimum balance from array accountBalance
        double min = accountBalance[0];

        for (double x : accountBalance) {
            // Assign variable min with next value of array if min is bigger
            if (min > x) {
                min = x;
            }
        }

        // Get maximum balance from array accountBalance
        double max = 0; // We work only with positive numbers. In real live you do have negative
                        // balances too

        for (double x : accountBalance) {
            if (max < x) {
                max = x;
            }
        }

        // Get average balance from array accountBalance

        double avg = 0;

        for (double x : accountBalance) {
            avg += x;
        }
        avg /= accountBalance.length;

        System.out.printf("\nMaximum value: %.2f $", max);
        System.out.printf("\nMinimum value: %.2f $", min);
        System.out.printf("\nAverage value: %.2f $\n", avg);

        scanner.close();

    }

}
