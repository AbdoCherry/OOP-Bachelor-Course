package Week02.Task06;

import java.util.Scanner;

public class Calculate2Method {

    static final double EXCHANGERATE = 1.0971, RATE = 1.19;

    /**
     * @param amount
     * @param RATE
     * @return double
     */
    static double taxCalc(double amount, double RATE) {

        double result = amount * (1 + RATE / 100);
        return result;
    }

    /**
     * @param amount
     * @param EXCHANGERATE
     * @return double
     */
    static double exchangeCalc(double amount, double EXCHANGERATE) {

        double result = amount * (1 + EXCHANGERATE / 100);
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the amount int the field below: ");
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        double taxedAmount = taxCalc(amount, RATE);
        double exchangedAmount = exchangeCalc(taxedAmount, EXCHANGERATE);

        System.out.println("\nNet amount\tGross amount\tExchanged");
        System.out.printf("%.2f €\t%.2f €\t%.2f $\n", amount, taxedAmount, exchangedAmount);

        scanner.close();

    }

}
