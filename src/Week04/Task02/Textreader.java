package Week04.Task02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Textreader {

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String pathMacOs = "src/Week4/Task2/CheckingBalance.txt";
        String pathWindows = "src\\Week4\\Task2\\CheckingBalance.txt";
        String path = "";
        String myOS = System.getProperty("os.name"); // Getting my OS name to choose the path convention later

        if (myOS.startsWith("Win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            path = null;
        }

        Scanner myFile = new Scanner(new File(path));

        // The size is Intuitive setted at 10 to store all elements from file in array
        double[] accountBalance = new double[10];
        int counter = 0;

        System.out.println("\n----- Only balances over 100.- $ -------\n");
        while (myFile.hasNextLine()) {
            accountBalance[counter] = Double.parseDouble(myFile.nextLine().replaceAll(",", ".")); // Converting string
                                                                                                  // input into double
                                                                                                  // output
            if (accountBalance[counter] > 100) {
                System.out.println("Amount at line " + (counter + 1) + ": " + accountBalance[counter] + " $");
            }
            counter++;
        }
        System.out.println(" ");

        myFile.close();

    }

}
