package Week02.Task03;

import java.util.Scanner;

public class InterestRateTable {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Seed capital: ");
        double seedCapital = scanner.nextDouble();
        System.out.print("Interest rate: ");
        double interestRate = scanner.nextDouble();
        System.out.print("Investment period: ");
        int years = scanner.nextInt();

        System.out.printf("%-10s%-22s%-22s%-22s\n", "Year", "Seed Capital $", "Final Capital $", "Revenue $");
        // System.out.println("\nYear\tSeed Capital\tFinal Capital\tRevenue");

        if (years <= 7 && years > 0) {
            for (int i = 0; i <= years; i++) {
                seedCapital = seedCapital * Math.pow((1 + interestRate / 100), i);
                double finalCapital = seedCapital * Math.pow((1 + interestRate / 100), i + 1);
                double revenue = finalCapital - seedCapital;

                System.out.printf("%-10d%-22.2f%-22.2f%-22.2f\n", i, seedCapital, finalCapital, revenue);
                //System.out.printf("%d\t%.2f $\t%.2f $\t%.2f $\n", i, seedCapital, finalCapital, revenue);
            }
        } else if (years > 7) {
            System.out.println("\nWe´re sorry, but your investment period is too high => max. 7 years\n");

        } else {
            System.out.println("We´re sorry. But you´re entry couldn´t get interpreted");
        }
        scanner.close();
    }

}
