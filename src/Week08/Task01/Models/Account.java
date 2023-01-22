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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Account.scanner = scanner;
    }

    public static List<Account> myAccounts(List<Account> accounts, String firstName, String lastName) {

        return accounts.stream().filter(ac -> ac.getCustomer().getFirstName().equals(firstName))
                .filter(ac -> ac.getCustomer().getLastName().equals(lastName)).collect(Collectors.toList());

    }

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

    public static void displayAllDeposits(List<Account> accounts) {

        Map<String, List<Account>> groupByCustomer = accounts.stream()
                .collect(Collectors.groupingBy(
                        (Account a) -> a.getCustomer().getFirstName() + " " + a.getCustomer().getLastName()));

        groupByCustomer.forEach((customer, deposits) -> {
            System.out.println("Customer: " + customer);
            for (int i = 0; i < deposits.size(); i++) {

                DecimalFormat df = new DecimalFormat("#0.00");

                String termDepositBalance = "Term Deposit Deposit " + (i + 1) + ": ";
                String investmentDate;
                String maturityDate;
                String depositBalance = df.format(deposits.get(i).getDeposit().getDepositBalance());

                try {
                    String[] investmentDateFormatted = DateConverter
                            .dateFormatter(deposits.get(i).getDeposit().getInvestmentDate());
                    investmentDate = investmentDateFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                            + " of " + investmentDateFormatted[1];

                } catch (ParseException e) {
                    investmentDate = deposits.get(i).getDeposit().getInvestmentDate();
                }
                try {
                    String[] maturityDateFormatted = DateConverter
                            .dateFormatter(deposits.get(i).getDeposit().getMaturityDate());
                    maturityDate = maturityDateFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(maturityDateFormatted[0])) + " of "
                            + maturityDateFormatted[1];

                } catch (ParseException e) {
                    maturityDate = deposits.get(i).getDeposit().getMaturityDate();
                }
                System.out.printf("%-20s%-20s%-20s%-12s%-2s\n", termDepositBalance, investmentDate, maturityDate,
                        depositBalance, "$");

            }

            System.out.println("\n--------------------------------------------------------------------\n");
        });

    }

}
