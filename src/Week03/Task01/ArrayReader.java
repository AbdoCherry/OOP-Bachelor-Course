package Week03.Task01;

import java.util.Scanner;

public class ArrayReader {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double[] accountBalances = new double[6];

        System.out.println("\nPlease fill the array with amounts\n");

        for (int i = 0; i < accountBalances.length; i++) {
            System.out.print("Amount " + (i + 1) + ": ");
            accountBalances[i] = scanner.nextDouble();
        }

        System.out.println("\n"); // New paragraph

        int counter = 1;

        // shorter way to loop through datastructures like arrays or lists
        for (double d : accountBalances) {
            System.out.println("Amount " + counter + ": " + d); // Printing the full array content out to console
            counter++;
        }
        System.out.println("\n"); // New paragraph

        // Printing only values at index 3 and 5 of Array => Index starts at 0
        System.out.println("Value at index 3: " + accountBalances[2]);
        System.out.println("Value at index 5: " + accountBalances[4]);

        scanner.close();

    }

}
