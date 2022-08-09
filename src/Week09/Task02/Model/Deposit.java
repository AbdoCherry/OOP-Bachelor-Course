package Week09.Task2.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Week09.Task2.BusinessRules.DateConverter;

public class Deposit extends Customer {

    static DecimalFormat digit = new DecimalFormat("#");
    static DecimalFormat df = new DecimalFormat("#0.00");

    private String description, investmentDate, maturityDate;
    private double depositBalance;

    public Deposit() {

    }

    public Deposit(String description, double depositBalance, String investmentDate, String maturityDate) {
        this.description = description;
        this.depositBalance = depositBalance;
        this.investmentDate = investmentDate;
        this.maturityDate = maturityDate;
    }

    public Deposit(String customerID, String lastName, String firstName, String customerSince, String description,
            double depositBalance, String investmentDate, String maturityDate) {
        super(customerID, lastName, firstName, customerSince);
        this.description = description;
        this.depositBalance = depositBalance;
        this.investmentDate = investmentDate;
        this.maturityDate = maturityDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestmentDate() {
        return investmentDate;
    }

    public void setInvestmentDate(String investmentDate) {
        this.investmentDate = investmentDate;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public double getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(double depositBalance) {
        this.depositBalance = depositBalance;
    }

    public static void withDraw(List<Deposit> deposits) {

        digit.setMaximumFractionDigits(10);

        // Collect basic information from customer
        String[] customer = Customer.customer();
        List<Deposit> myDeposits = Customer.myDeposits(deposits, customer);

        // Defining useful variables
        int deposit = 0;
        boolean valid = false;
        String depositAccountNo = null, investmentDateFormatted = null, maturityDateFormatted = null,
                depositBalanceFormatted = null;

        // If customer has more than one deposit Account
        if (myDeposits.size() > 1) {
            System.out.println("\n\t**** " + myDeposits.size() + " Deposit Accounts found - " + customer[0] + " "
                    + customer[1] + " ****\n");

            System.out.printf("\033[1m%-30s%-30s%-30s%-25s%-20s\033[0m\n", "Deposit Account", "Investment Date",
                    "Maturity Date",
                    "Deposit Balance", "Withdrawal Possible");

            // Display customer all his deposit Accounts
            for (int i = 0; i < myDeposits.size(); i++) {
                depositAccountNo = "Deposit " + (i + 1) + ": ";
                investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getMaturityDate());
                depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";
                valid = DateConverter.dateChecker(myDeposits.get(i).getMaturityDate());

                System.out.printf("%-30s%-30s%-30s%-25s%-20s\n", depositAccountNo, investmentDateFormatted,
                        maturityDateFormatted,
                        depositBalanceFormatted, valid);
            }

            // Let customer choose from which deposit Account he want´s to withdraw
            System.out.println("\nPlease choose from deposit Accounts above you want to withdraw money");
            System.out.print("Deposit Account: ");
            deposit = Customer.getScanner().nextInt();
            valid = DateConverter.dateChecker(myDeposits.get(deposit - 1).getMaturityDate());

            // If customer has only one deposit Account
        } else if (myDeposits.size() == 1) {
            System.out.printf("\033[1m%-30s%-30s%-30s%-25s%-20s\033[0m\n", "Deposit Account 1: ", "Investment Date",
                    "Maturity Date",
                    "Deposit Balance", "Withdrawal Possible");
            investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(0).getInvestmentDate());
            maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(1).getMaturityDate());
            depositBalanceFormatted = digit.format(myDeposits.get(0).getDepositBalance()) + " $";
            valid = DateConverter.dateChecker(myDeposits.get(0).getMaturityDate());
            deposit = 1;

            System.out.printf("%-30s%-30s%-30s%-25s%-20s\n", "Deposit 1:", investmentDateFormatted,
                    maturityDateFormatted,
                    depositBalanceFormatted, valid);
        } else {
            System.out.println("\nUnfortunately there is not account which belongs to this name " + customer[0] + " "
                    + customer[1] + "\n");
            System.out.println("\nProgram cancelled. Please restart later\n");
            System.exit(0);
        }

        // Initializing / Setting variable with deposit Balance before withdrawal
        double depositBefore = myDeposits.get(deposit - 1).getDepositBalance(), depositAfter, amount;

        if (valid) {

            // Making amount selection more realistic
            System.out.println("\nChoose amount you want to withdraw or enter manually");
            System.out.println("1 <------ 10.- $\t 100.- $ ------> 6");
            System.out.println("2 <------ 20.- $\t 200.- $ ------> 7");
            System.out.println("3 <------ 30.- $\t 300,- $ ------> 8");
            System.out.println("4 <------ 40.- $\t 400.- $ ------> 9");
            System.out.println("5 <------ 50.- $\t 500.- $ ------> 10");
            System.out.println("\n\t\t[Manually = 0]\n");

            System.out.print("Choose options above: ");
            amount = Customer.getScanner().nextInt();

            // If customer chooses amount from left hand side of displayed options
            if (amount == 0) {
                System.out.print("Enter amount: ");
                amount = Customer.getScanner().nextDouble();
                depositAfter = depositBefore - amount;
            } else if (amount < 6) {
                amount = amount * 10;
                depositAfter = depositBefore - amount;

                // If customer chooses amount from right hand side of displayed options
            } else if (amount > 5) {
                amount = amount * 100 - 500;
                depositAfter = depositBefore - amount;

                // If customers enters something wrong (e.g.: String instead of numerical value)
            } else {
                depositAfter = depositBefore;
                System.out.println("\nWrong Entry");
                System.out
                        .println(
                                "\n================================ WITHDRAW - FAILED ================================\n");
                System.exit(0);
            }

            System.out
                    .println(
                            "\n================================ WITHDRAW - SUCCESSFUL ================================\n");
            System.out.printf("\033[1m%-30s%-30s%-30s\033[0m\n", "Deposit Before", "Deposit After",
                    "Amount Withdrawed");
            String depositBeforeString = digit.format(depositBefore) + " $";
            String depositAfterString = digit.format(depositAfter) + " $";
            String amountString = digit.format(depositBefore - depositAfter) + " $";

            System.out.printf("%-30s%-30s%-11s\n", depositBeforeString, depositAfterString, amountString);

            // Initializing new deposit balance to account
            for (int i = 0; i < deposits.size(); i++) {
                if (deposits.get(i).getDepositBalance() == depositBefore) {
                    deposits.get(i).setDepositBalance(depositAfter);
                    break;
                }
            }

        } else {
            System.out
                    .println("\n================================ WITHDRAW - FAILED ================================\n");
            System.out.println(
                    "\nProgram cancelled. Withdrawals refused\nReason: Maturity date ins´t reached yet\n");
            System.exit(0);
        }

    }

    public static void payIn(List<Deposit> deposits) {

        digit.setMaximumFractionDigits(10);

        // Collect basic information from customer
        String[] customer = Customer.customer();
        List<Deposit> myDeposits = myDeposits(deposits, customer);

        // Defining useful variables
        int deposit = 0;
        String depositAccountNo = null, investmentDateFormatted = null, maturityDateFormatted = null,
                depositBalanceFormatted = null;

        // If customer has more than one deposit Account
        if (myDeposits.size() > 1) {
            System.out.println("\n\t**** " + myDeposits.size() + " Deposit Accounts found - " + customer[0] + " "
                    + customer[1] + " ****\n");
            System.out.printf("\033[1m%-30s%-30s%-30s%-25s\033[0m\n", "Deposit Account", "Investment Date",
                    "Maturity Date",
                    "Deposit Balance");

            // Display customer all his deposit Accounts
            for (int i = 0; i < myDeposits.size(); i++) {
                depositAccountNo = "Deposit " + (i + 1) + " :";
                investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getMaturityDate());
                depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";

                System.out.printf("%-30s%-30s%-30s%-25s\n", depositAccountNo, investmentDateFormatted,
                        maturityDateFormatted, depositBalanceFormatted);
            }

            // Let customer choose from which deposit Account he want´s to pay in
            System.out.println("\nPlease choose from deposit Accounts above you want to pay in money");
            System.out.print("Deposit Account: ");
            deposit = Customer.getScanner().nextInt();

            // If customer has only one Account
        } else if (myDeposits.size() == 1) {
            System.out.printf("\033[1m%-30s%-30s%-30s%-25s%\033[0m\n", "Deposit Account 1: ", "Investment Date",
                    "Maturity Date",
                    "Deposit Balance");
            investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(0).getInvestmentDate());
            maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(0).getMaturityDate());
            depositBalanceFormatted = digit.format(myDeposits.get(0).getDepositBalance());
            deposit = 1;

            System.out.printf("%-30s%-30s%-30s%-25s%\n", "Deposit 1:", investmentDateFormatted,
                    maturityDateFormatted,
                    depositBalanceFormatted);
        } else {
            System.out.println("\nUnfortunately there is not account which belongs to this name " + customer[0] + " "
                    + customer[1] + "\n");
            System.out.println("\nProgram cancelled. Please restart later\n");
            System.exit(0);
        }

        // Initializing / Setting variable with deposit Balance before pay in
        double depositBefore = myDeposits.get(deposit - 1).getDepositBalance(), depositAfter, amount;

        // Making amount selection more realistic
        System.out.println("\nChoose amount you want to withdraw or enter manually");
        System.out.println("1 <------ 10.- $\t 100.- $ ------> 6");
        System.out.println("2 <------ 20.- $\t 200.- $ ------> 7");
        System.out.println("3 <------ 30.- $\t 300,- $ ------> 8");
        System.out.println("4 <------ 40.- $\t 400.- $ ------> 9");
        System.out.println("5 <------ 50.- $\t 500.- $ ------> 10");
        System.out.println("\n\t\t[Manually = 0]\n");

        System.out.print("Choose options above: ");
        amount = Customer.getScanner().nextInt();

        // If customer chooses amount from left hand side of displayed options
        if (amount == 0) {
            System.out.print("Enter amount: ");
            amount = Customer.getScanner().nextDouble();
            depositAfter = depositBefore + amount;
        } else if (amount < 6) {
            amount = amount * 10;
            depositAfter = depositBefore + amount;

            // If customer chooses amount from right hand side of displayed options
        } else if (amount > 5) {
            amount = amount * 100 - 500;
            depositAfter = depositBefore + amount;

            // If customers enters something wrong (e.g.: String instead of numerical value)
        } else {
            depositAfter = depositBefore;
            System.out.println("\nWrong Entry");
            System.out
                    .println(
                            "\n================================ PAY IN - FAILED ================================\n");
            System.exit(0);
        }

        System.out
                .println(
                        "\n================================ PAY IN - SUCCESSFUL ================================\n");
        System.out.printf("\033[1m%-30s%-30s%-30s\033[0m\n", "Deposit Before", "Deposit After", "Amount Payed In");
        String depositBeforeString = digit.format(depositBefore) + " $";
        String depositAfterString = digit.format(depositAfter) + " $";
        String amountString = digit.format(depositAfter - depositBefore) + " $";

        System.out.printf("%-30s%-30s%-11s\n", depositBeforeString, depositAfterString, amountString);

        // Initialize new deposit balance to account
        for (int i = 0; i < deposits.size(); i++) {
            if (deposits.get(i).getDepositBalance() == depositBefore) {
                deposits.get(i).setDepositBalance(depositAfter);
                break;
            }
        }

    }

    public static void getDescription(List<Deposit> deposits) {

        // declaring digit formatter for
        digit.setMaximumFractionDigits(10);
        double maxAmount = 0.0;

        // Check if parameter is null
        String[] customer = Customer.customer();
        List<Deposit> myDeposits = Customer.myDeposits(deposits, customer);

        if (myDeposits.size() > 0) {
            System.out
                    .println("\n***** " + myDeposits.size() + " Accounts found - " + customer[0]
                            + " " + customer[1] + " ****");

            System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s%-15s\033[0m\n", "Deposit", "Investment Date",
                    "Maturity Date",
                    "Deposit Balance", "Running Time (Days)");

            for (int i = 0; i < myDeposits.size(); i++) {
                maxAmount += myDeposits.get(i).getDepositBalance();

                String depositNo = "Time deposit Account " + (i + 1) + ": ";
                String investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                String maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getMaturityDate());
                String depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";
                String runtime = DateConverter.dateCalc(myDeposits.get(i).getInvestmentDate(),
                        myDeposits.get(i).getMaturityDate(), 'M');

                System.out.printf("%-30s%-30s%-30s%-30s%-15s\n", depositNo, investmentDateFormatted,
                        maturityDateFormatted, depositBalanceFormatted, runtime);
            }
            System.out.println(
                    "\n\033[1mMaximum amount of money on your account " + df.format(maxAmount) + ":\033[0m");
        } else {
            System.out.println("\nNo Deposit Account available");

            Deposit singleAccount = deposits.stream()
                    .filter(d -> d.getFirstName().equals(customer[0]))
                    .filter(d -> d.getLastName().equals(customer[1]))
                    .findFirst().get();

            System.out.printf("\n\033[1m%-30s%-30s%-30s\033[0m\n", "Deposit", "Customer ID",
                    "Customer");
            System.out.printf("%-30s%-30s%-30s\n", "Time deposit Account 1: ", singleAccount.getCustomerID(),
                    singleAccount.getFirstName() + " " + singleAccount.getLastName());

        }
    }

    public static void displayAll(List<Deposit> deposits) {

        Map<String, List<Deposit>> groupByCustomer = deposits.stream()
                .collect(Collectors.groupingBy(
                        (Deposit d) -> d.getFirstName() + " " + d.getLastName()));

        groupByCustomer.forEach((customer, accounts) -> {
            System.out.println("\n\t\t\t\t\033[1m***** Customer: " + customer + " *****\033[0m");

            List<Deposit> myDeposits = new ArrayList<Deposit>();

            double maxAmount = 0.0;

            for (Deposit d : accounts) {
                myDeposits.add(d);
            }

            System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s%-15s\033[0m\n", "Deposit", "Investment Date",
                    "Maturity Date",
                    "Deposit Balance", "Running Time (Days)");

            for (int i = 0; i < myDeposits.size(); i++) {
                String depositNo = "Time deposit account " + (i + 1) + ": ";
                String investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                String maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getMaturityDate());
                String depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";
                String runtime = DateConverter.dateCalc(myDeposits.get(i).getInvestmentDate(),
                        myDeposits.get(i).getMaturityDate(), 'M');
                maxAmount += myDeposits.get(i).getDepositBalance();

                System.out.printf("%-30s%-30s%-30s%-30s%-15s\n", depositNo, investmentDateFormatted,
                        maturityDateFormatted, depositBalanceFormatted, runtime);
            }
            System.out
                    .println(
                            "--------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("\033[1mMaximum amount of money:\033[0m " + df.format(maxAmount) + " $\n");
        });

    }

    public static void writeCSV(List<Deposit> deposits) {

        Date today = new Date();

        String pathWindows = "src\\Week9\\Task2\\Data\\Deposits_" + DateConverter.sdfParser.format(today) + "_.csv";
        String pathMacOS = "src/Week9/Task2/Data/Deposits_" + DateConverter.sdfParser.format(today) + "_.csv";
        String path = "";
        String myOS = System.getProperty("os.name");

        if (myOS.startsWith("Win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOS;
        } else {
            path = null;
        }

        try {

            digit.setMaximumFractionDigits(10);
            PrintWriter writeCSVDeposit = new PrintWriter(new File(path));
            StringBuilder sbDeposit = new StringBuilder();

            sbDeposit.append("Customer ID");
            sbDeposit.append(";");
            sbDeposit.append("Customer");
            sbDeposit.append(";");
            sbDeposit.append("Customer Since");
            sbDeposit.append(";");
            sbDeposit.append("Deposit Balance");
            sbDeposit.append(";");
            sbDeposit.append("Investment Date");
            sbDeposit.append(";");
            sbDeposit.append("Maturity Date");
            sbDeposit.append(";");
            sbDeposit.append("Running Time (Days)");
            sbDeposit.append(";");
            sbDeposit.append("\n");

            deposits.sort(Comparator.comparing(d -> Integer.parseInt(d.getCustomerID())));

            for (Deposit d : deposits) {
                sbDeposit.append(d.getCustomerID());
                sbDeposit.append(";");
                sbDeposit.append(d.getFirstName() + " " + d.getLastName());
                sbDeposit.append(";");
                sbDeposit.append(d.getCustomerSince());
                sbDeposit.append(";");
                sbDeposit.append(digit.format(d.getDepositBalance()));
                sbDeposit.append(";");
                sbDeposit.append(d.getInvestmentDate());
                sbDeposit.append(";");
                sbDeposit.append(d.getMaturityDate());
                sbDeposit.append(";");
                sbDeposit.append(DateConverter.dateCalc(d.getInvestmentDate(), d.getMaturityDate(), 'm'));
                sbDeposit.append(";");
                sbDeposit.append("\n");
            }

            writeCSVDeposit.write(sbDeposit.toString());
            writeCSVDeposit.close();
            System.out.println(
                    "\n================================ EXPORT DEPOSIT FILE SUCCESSFUL ================================\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
