package Week08.Task01.Models;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import Week08.Task01.Auxilary.DateConverter;

public class Account {

    private static Scanner scanner = new Scanner(System.in);

    private Customer customer;
    private Deposit deposit;

    public Account() {

    }

    public Account(String customerID, String lastName, String firstName, String customerSince, double depositBalance,
            String investmentDate,
            String maturityDate) {
        this.customer = new Customer(customerID, lastName, firstName, customerSince);
        this.deposit = new Deposit(depositBalance, investmentDate, maturityDate);
    }

    /**
     * @return Customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return Deposit
     */
    public Deposit getDeposit() {
        return deposit;
    }

    /**
     * @param deposit
     */
    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    /**
     * @return Scanner
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * @param scanner
     */
    public static void setScanner(Scanner scanner) {
        Account.scanner = scanner;
    }

    /**
     * @param accounts
     * @param firstName
     * @param lastName
     * @return List<Account>
     */
    public static List<Account> myAccounts(List<Account> accounts, String firstName, String lastName) {

        List<Account> myAccounts = accounts.stream().filter(ac -> ac.getCustomer().getFirstName().equals(firstName))
                .filter(ac -> ac.getCustomer().getLastName().equals(lastName)).collect(Collectors.toList());

        return myAccounts;

    }

    /**
     * @param accounts
     * @return String
     */
    public static String maxID(List<Account> accounts) {

        int max = 0;
        String maxID;

        for (Account a : accounts) {
            if (max < Integer.parseInt(a.getCustomer().getCustomerID())) {
                max = Integer.parseInt(a.getCustomer().getCustomerID());
            }
        }

        maxID = String.valueOf(max + 1);

        return maxID;
    }

    /**
     * @param accounts
     */
    public static void displayAllDeposits(List<Account> accounts) {

        Map<String, List<Account>> groupByCustomer = accounts.stream()
                .collect(Collectors.groupingBy(
                        (Account a) -> a.getCustomer().getFirstName() + " " + a.getCustomer().getLastName()));

        groupByCustomer.forEach((customer, deposits) -> {
            System.out.println("Customer: " + customer);
            List<Account> myAccount = deposits;
            for (int i = 0; i < myAccount.size(); i++) {

                DecimalFormat df = new DecimalFormat("#0.00");

                String termDepositBalance = "Term Deposit Deposit " + (i + 1) + ": ";
                String investmentDate;
                String maturityDate;
                String depositBalance = df.format(myAccount.get(i).getDeposit().getDepositBalance());

                try {
                    String[] investmentDateFormatted = DateConverter
                            .dateFormatter(myAccount.get(i).getDeposit().getInvestmentDate());
                    investmentDate = investmentDateFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                            + " of " + investmentDateFormatted[1];

                } catch (ParseException e) {
                    investmentDate = myAccount.get(i).getDeposit().getInvestmentDate();
                }
                try {
                    String[] maturityDateFormatted = DateConverter
                            .dateFormatter(myAccount.get(i).getDeposit().getMaturityDate());
                    maturityDate = maturityDateFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(maturityDateFormatted[0])) + " of "
                            + maturityDateFormatted[1];

                } catch (ParseException e) {
                    maturityDate = myAccount.get(i).getDeposit().getMaturityDate();
                }
                System.out.printf("%-20s%-20s%-20s%-12s%-2s\n", termDepositBalance, investmentDate, maturityDate,
                        depositBalance, "$");

            }

            System.out.println("\n--------------------------------------------------------------------\n");
        });

    }

}
