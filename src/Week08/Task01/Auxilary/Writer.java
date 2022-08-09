package Week08.Task01.Auxilary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import Week08.Task01.Models.Account;

public class Writer {

    /**
     * @param accounts
     */
    public static void writerCSV(List<Account> accounts) {

        Date today = new Date();

        String windowsPath = "src\\Week8\\Task1\\Data\\output_" + DateConverter.getSdfFormatter().format(today)
                + ".csv";
        String macOsPath = "src/Week8/Task1/Data/output_" + DateConverter.getSdfFormatter().format(today) + ".csv";
        String myOS = System.getProperty("os.name");
        String path;

        if (myOS.startsWith("Win")) {
            path = windowsPath;
        } else if (myOS.startsWith("Mac")) {
            path = macOsPath;
        } else {
            path = null;
        }

        try {

            PrintWriter writeAccounts = new PrintWriter(new File(path));
            StringBuilder sbAccounts = new StringBuilder();

            sbAccounts.append("Employee ID");
            sbAccounts.append(";");
            sbAccounts.append("Name");
            sbAccounts.append(";");
            sbAccounts.append("Customer since");
            sbAccounts.append(";");
            sbAccounts.append("Deposit Balance");
            sbAccounts.append(";");
            sbAccounts.append("Investment Date");
            sbAccounts.append(";");
            sbAccounts.append("Maturity Date");
            sbAccounts.append("\n");

            for (Account a : accounts) {
                sbAccounts.append(a.getCustomer().getCustomerID()); // 0
                sbAccounts.append(";");
                sbAccounts.append(a.getCustomer().getFirstName() + " " + a.getCustomer().getLastName()); // 1+2
                sbAccounts.append(";");
                String[] customerSince = DateConverter.dateFormatter(a.getCustomer().getCustomerSince());
                sbAccounts
                        .append(customerSince[0] + DateConverter.daySuffix(Integer.parseInt(customerSince[0])) + " of "
                                + customerSince[1]); // 3
                sbAccounts.append(";");
                sbAccounts.append(a.getDeposit().getDepositBalance() + " $"); // 4
                sbAccounts.append(";");
                String[] investmentDay = DateConverter.dateFormatter(a.getDeposit().getInvestmentDate());
                sbAccounts
                        .append(investmentDay[0] + DateConverter.daySuffix(Integer.parseInt(investmentDay[0])) + " of "
                                + investmentDay[1]); // 5
                sbAccounts.append(";");
                String[] maturityDate = DateConverter.dateFormatter(a.getDeposit().getMaturityDate());
                sbAccounts.append(maturityDate[0] + DateConverter.daySuffix(Integer.parseInt(maturityDate[0])) + " of "
                        + maturityDate[1]); // 6
                sbAccounts.append(";");
                sbAccounts.append("\n");
            }

            writeAccounts.write(sbAccounts.toString());
            writeAccounts.close();

            System.out.println("\nCSV file was written successfully\nIn total " + accounts.size() + " records\n");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
