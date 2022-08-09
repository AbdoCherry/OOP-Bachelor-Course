package Week02.Task4;

import java.util.Scanner;

public class ConditionalReader {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary amounts in the fields below");
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        if (amount < 100) {
            double difference = 100 - amount;
            System.out.printf("Error input ! - Undercut: < %.2f $>\n", difference);
        } else {
            System.out.println("Your entered amount: " + amount + " $");
        }
        scanner.close();

    }

}
