package Week03.Task04;

import java.util.Scanner;

public class CalcMetricsMethods {

    /**
     * @param max
     * @param min
     * @param avg
     */
    static void display(double max, double min, double avg) {
        System.out.println("\nMaximum\t\tMinimum\t\tAverage");
        System.out.printf("%.2f $\t%.2f $\t%.2f $\t%n", max, min, avg);
    }

    /**
     * @param accountBalance
     * @return double
     */
    static double maxVal(double[] accountBalance) {

        double result = 0;

        for (double d : accountBalance) {
            if (result < d) {
                result = d;
            }
        }

        return result;
    }

    /**
     * @param accountBalance
     * @return double
     */
    static double minVal(double[] accountBalance) {

        double result = accountBalance[0];
        for (double d : accountBalance) {
            if (result > d) {
                result = d;
            }
        }

        return result;
    }

    /**
     * @param accountBalance
     * @return double
     */
    static double avgVal(double[] accountBalance) {

        double result = 0;
        for (double d : accountBalance) {
            result += d;
        }

        return result / accountBalance.length;
    }

    /**
     * @param args
     */
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
