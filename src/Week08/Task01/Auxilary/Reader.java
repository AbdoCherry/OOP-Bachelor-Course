package Week08.Task1.Auxilary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Week08.Task1.Models.Account;

public class Reader {

    /**
     * @return List<Account>
     */
    public static List<Account> readCSV() {

        List<Account> accounts = new ArrayList<Account>();

        // Filepath of our csv. Depending on your OS I´ve built these branching
        String windowsPath = "src\\Week08\\Task1\\Data\\input.csv";
        String macOsPath = "src/Week08/Task1/Data/input.csv";
        String myOS = System.getProperty("os.name");
        String path;
        String line = null; // To prevent not reading the header

        // Let java choose the correct filepath convention of csv file
        if (myOS.startsWith("Win")) {
            path = windowsPath;
        } else if (myOS.startsWith("Mac")) {
            path = macOsPath;
        } else {
            path = null;
        }

        try {
            BufferedReader readAccounts = new BufferedReader(new FileReader(path));
            readAccounts.readLine();

            while ((line = readAccounts.readLine()) != null) {
                String[] accountVal = line.split(";");
                accounts.add(new Account(accountVal[0], accountVal[1], accountVal[2], accountVal[3],
                        Double.parseDouble(accountVal[4]),
                        accountVal[5], accountVal[6]));

            }
            readAccounts.close();
            System.out.println("\nCSV data was read successfully\nIn total " + accounts.size() + " are read\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }
}
