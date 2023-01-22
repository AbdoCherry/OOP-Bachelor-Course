package Week04.Task07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvReader {

    public static void main(String[] args) throws FileNotFoundException {

        String path;
        String pathWindows = "src\\Week4\\Task7\\OrderList.csv";
        String pathMacOs = "src/Week4/Task7/OrderList.csv";
        String myOs = System.getProperty("os.name");

        // Depending on our operating system we select the right path convention of the
        // csv file
        if (myOs.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            path = pathWindows;
        }

        String[] data = new String[10];

        String[] orderVolume = new String[10];
        String[] discount = new String[10];
        String[] currency = new String[10];

        int counter = 0;
        Scanner myFile = new Scanner(new File(path));

        while (myFile.hasNextLine()) {
            data[counter] = myFile.nextLine();
            counter++;
        }

        int[] start = new int[10];
        int[] end = new int[10];

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                data[i] = data[i].replaceAll(",", ".");
                start[i] = data[i].indexOf(";");
                end[i] = data[i].lastIndexOf(";");

                orderVolume[i] = data[i].substring(0, start[i]);
                discount[i] = data[i].substring(start[i] + 1, end[i] - 1);
                currency[i] = data[i].substring(start[i] + 1);

            }

        }

        double[] calculate = new double[10];
        double[] orderVolume2 = new double[10];
        double[] discount2 = new double[10];

        System.out.println("\nReinitialized csv into arrays");

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                orderVolume[i] = orderVolume[i + 1];
                orderVolume2[i] = Double.parseDouble(orderVolume[i]);

                discount[i] = discount[i + 1];
                discount2[i] = Double.parseDouble(discount[i]);

                currency[i] = "EUR";

                calculate[i] = orderVolume2[i] - (orderVolume2[i] * discount2[i] / 100);
                System.out.println(orderVolume2[i] + "; " + discount2[i] + "; " + calculate[i] + "; " + currency[i]);

            }

        }
        System.out.println(
                "First line: " + orderVolume[0] + "; " + discount2[0] + "; " + calculate[0] + ": " + currency[0]);

        myFile.close();

    }

}
