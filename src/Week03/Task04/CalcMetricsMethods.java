package Week03.Task04;

import java.util.Scanner;

public class CalcMetricsMethods {

    static void display(double max, double min, double avg) {

        System.out.printf("%-20s%-20s%-20s\n", "Maximum", "Minimum", "Average");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s%-20s%-20s\n", String.valueOf(max), String.valueOf(min), String.valueOf(avg));
    }

    static double maxVal(double[] accountBalance) {

        double result = 0;

        for (double d : accountBalance) {
            if (result < d) {
                result = d;
            }
        }

        return result;
    }

    static double minVal(double[] accountBalance) {

        double result = accountBalance[0];
        for (double d : accountBalance) {
            if (result > d) {
                result = d;
            }
        }

        return result;
    }

    static double avgVal(double[] accountBalance) {

        double result = 0;
        for (double d : accountBalance) {
            result += d;
        }

        return result / accountBalance.length;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below");

        double[] sharePrices = new double[5];

        for (int i = 0; i < sharePrices.length; i++) {
            System.out.print("Share price " + (i + 1) + ": ");
            sharePrices[i] = scanner.nextDouble();
        }

        double max = maxVal(sharePrices);
        double min = minVal(sharePrices);
        double avg = avgVal(sharePrices);

        display(max, min, avg);

        scanner.close();

    }

}
