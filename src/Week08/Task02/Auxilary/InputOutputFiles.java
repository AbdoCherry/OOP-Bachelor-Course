package Week08.Task02.Auxilary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Week08.Task02.Model.Customer;

public class InputOutputFiles {

    static DecimalFormat digit = new DecimalFormat("#");

    public static List<Customer> readCSV() {

        // Declaring our List where we store our records from CSV
        List<Customer> customers = new ArrayList<>();

        // Depending on our operating system we have to define the path
        String pathWindows = "src\\Week08\\Task02\\Data\\input.csv";
        String pathMacOS = "src/Week08/Task02/Data/input.csv";
        String path = "";
        String myOS = System.getProperty("os.name");
        String line = null; // Preventing reader to read the headers of csv

        // Let's find out which os we do have
        if (myOS.startsWith("Win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOS;
        } else {
            path = null;
        }

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] values = line.split(";");
                customers.add(new Customer(values[0], values[1], values[2], values[3], Double.parseDouble(values[4]),
                        Double.parseDouble(values[5]), values[6], values[7]));

            }

            readCSV.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(
                customers.size() > 0 ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return customers;
    }

    public static void writeCSV(List<Customer> customers) {

        Date today = new Date();

        String pathWindowsCustomer = "src\\Week08\\Task02\\Data\\Customer_" + DateConverter.sdfParser.format(today)
                + "_.csv";
        String pathMacOSCustomer = "src/Week08/Task02/Data/Customer_" + DateConverter.sdfParser.format(today)
                + ".csv";
        String pathCustomer = "";
        String pathWindowsChecking = "src\\Week08\\Task02\\Data\\Checking_" + DateConverter.sdfParser.format(today)
                + "_.csv";
        String pathMacOSChecking = "src/Week08/Task02/Data/Checking_" + DateConverter.sdfParser.format(today)
                + ".csv";
        String pathChecking = "";
        String pathWindowsDeposit = "src\\Week08\\Task02\\Data\\Deposit_" + DateConverter.sdfParser.format(today)
                + "_.csv";
        String pathMacOSDeposit = "src/Week08/Task02/Data/Deposit_" + DateConverter.sdfParser.format(today)
                + ".csv";
        String pathDeposit = "";

        String myOS = System.getProperty("os.name");

        if (myOS.startsWith("Win")) {
            pathCustomer = pathWindowsCustomer;
            pathChecking = pathWindowsChecking;
            pathDeposit = pathWindowsDeposit;
        } else if (myOS.startsWith("Mac")) {
            pathCustomer = pathMacOSCustomer;
            pathChecking = pathMacOSChecking;
            pathDeposit = pathMacOSDeposit;
        } else {
            pathCustomer = null;
            pathChecking = null;
            pathDeposit = null;
        }

        try {

            digit.setMaximumFractionDigits(10);

            // Defining writer for every type
            PrintWriter writeCSVCustomer = new PrintWriter(new File(pathCustomer));
            PrintWriter writeCSVChecking = new PrintWriter(new File(pathChecking));
            PrintWriter writeCSVDeposit = new PrintWriter(new File(pathDeposit));

            // Defining stringbuilder for every type
            StringBuilder sbCustomer = new StringBuilder();
            StringBuilder sbChecking = new StringBuilder();
            StringBuilder sbDeposit = new StringBuilder();

            sbCustomer.append("Customer ID");
            sbCustomer.append(";");
            sbCustomer.append("Name");
            sbCustomer.append(";");
            sbCustomer.append("Customer Since");
            sbCustomer.append("\n");

            sbChecking.append("Customer ID");
            sbChecking.append(";");
            sbChecking.append("Name");
            sbChecking.append(";");
            sbChecking.append("Customer Since");
            sbChecking.append(";");
            sbChecking.append("Checking Balance");
            sbChecking.append("\n");

            sbDeposit.append("Customer ID");
            sbDeposit.append(";");
            sbDeposit.append("Name");
            sbDeposit.append(";");
            sbDeposit.append("Customer Since");
            sbDeposit.append(";");
            sbDeposit.append("Deposit Balance");
            sbDeposit.append(";");
            sbDeposit.append("Investment Date");
            sbDeposit.append(";");
            sbDeposit.append("Maturity Date");
            sbDeposit.append("\n");

            for (Customer c : customers) {

                sbCustomer.append(c.getCustomerID());
                sbCustomer.append(";");
                sbCustomer.append(c.getFirstName()).append(" ").append(c.getLastName());
                sbCustomer.append(";");
                sbCustomer.append(c.getCustomerSince());
                sbCustomer.append("\n");

                if (c.getChecking() != null) {
                    sbChecking.append(c.getCustomerID());
                    sbChecking.append(";");
                    sbChecking.append(c.getFirstName()).append(" ").append(c.getLastName());
                    sbChecking.append(";");
                    sbChecking.append(c.getCustomerSince());
                    sbChecking.append(";");
                    sbChecking.append(digit.format(c.getChecking().getCheckingBalance()));
                    sbChecking.append("\n");
                }

                if (c.getDeposit() != null) {
                    sbDeposit.append(c.getCustomerID());
                    sbDeposit.append(";");
                    sbDeposit.append(c.getFirstName() + " " + c.getLastName());
                    sbDeposit.append(";");
                    sbDeposit.append(c.getCustomerSince());
                    sbDeposit.append(";");
                    sbDeposit.append(digit.format(c.getDeposit().getDepositBalance()));
                    sbDeposit.append(";");
                    sbDeposit.append(c.getDeposit().getInvestmentDate());
                    sbDeposit.append(";");
                    sbDeposit.append(c.getDeposit().getExpirationDate());
                    sbDeposit.append("\n");
                }

            }

            writeCSVCustomer.write(sbCustomer.toString());
            writeCSVCustomer.close();
            System.out.println(
                    "\n================================ EXPORT CUSTOMER FILE SUCCESSFUL ================================\n");
            writeCSVChecking.write(sbChecking.toString());
            writeCSVChecking.close();
            System.out.println(
                    "\n================================ EXPORT CHECKING FILE SUCCESSFUL ================================\n");
            writeCSVDeposit.write(sbDeposit.toString());
            writeCSVDeposit.close();
            System.out.println(
                    "\n================================ EXPORT DEPOSIT FILE SUCCESSFUL ================================\n");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
