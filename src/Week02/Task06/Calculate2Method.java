package Week02.Task06;

import java.util.Scanner;

public class Calculate2Method {

    static final double EXCHANGERATE = 1.0971, RATE = 1.19;

    static double taxCalc(double amount) {

        return amount * (1 + Calculate2Method.RATE / 100);
    }

    static double exchangeCalc(double amount) {

        return amount * (1 + Calculate2Method.EXCHANGERATE / 100);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the amount int the field below: ");
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();

        double taxedAmount = taxCalc(amount);
        double exchangedAmount = exchangeCalc(taxedAmount);

        System.out.println("\nNet amount\tGross amount\tExchanged");
        System.out.printf("%.2f €\t%.2f €\t%.2f $\n", amount, taxedAmount, exchangedAmount);

        scanner.close();

    }

}
