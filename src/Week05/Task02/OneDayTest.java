package Week05.Task02;

import java.util.Scanner;

public class OneDayTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        OneDay[] date = new OneDay[10]; // Array to store our dates
        char input;

        System.out.println("\nPlease enter the necessary information in the fields below");
        int counter = 0;

        // Using the IPO - Principle (Input Processing Outgoing / Output)
        do {

            // Input
            System.out.print("\nDay: ");
            int day = scanner.nextInt();
            System.out.print("Month: ");
            String month = scanner.next();
            System.out.print("Year: ");
            int year = scanner.nextInt();

            // Processing => Initializing the setted variables into the array
            date[counter] = new OneDay(day, month, year);
            counter++;

            // Asking user if he wants to add more dates
            System.out.print("Do you want to add more dates? You can add upt to " + (10 - counter)
                    + "\n[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            input = scanner.next().charAt(0);
        } while ((input == 'Y' || input == 'y') && counter <= 10); // Condition

        // Printing out the result => All available dates
        for (OneDay d : date) {
            if (d != null) { // If array is empty, don´t print. Otherwise error message
                d.displayDate();
            }
        }

        System.out.println(" ");
        scanner.close();

    }

}
