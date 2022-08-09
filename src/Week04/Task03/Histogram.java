package Week04.Task03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Histogram {

    /**
     * @param accountBalance
     */
    static void hist(double[] accountBalance) {

        String star = "*";
        for (double d : accountBalance) {
            System.out.printf("Account balance of %.2f $%n", d);
            int counter = (int) d / 10; // casting amount to int
            System.out.println(counter + " stars");
            for (int i = 0; i <= counter; i++) {
                System.out.print(star);
            }
            System.out.println("\n");

        }
    }

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String pathMacOs = "src/Week4/Task3/CheckingBalance.txt";
        String pathWin = "src\\Week4\\Task3\\CheckingBalance.txt";
        String path = "";
        String myOS = System.getProperty("os.name");

        if (myOS.startsWith("Win")) {
            path = pathWin;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            path = null;
        }

        Scanner myFile = new Scanner(new File(path));

        double[] accountBalance = new double[10];
        int counter = 0;

        while (myFile.hasNext()) {
            accountBalance[counter] = Double.parseDouble(myFile.nextLine().replaceAll(",", "."));
            counter++;
        }
        hist(accountBalance);

        myFile.close();

    }

}
