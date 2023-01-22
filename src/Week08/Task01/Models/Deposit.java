package Week08.Task01.Models;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import Week08.Task01.Auxilary.DateConverter;

public class Deposit {

    private static final Scanner scanner = new Scanner(System.in);

    private double depositBalance;
    private String investmentDate, maturityDate;

    public Deposit() {

    }

    public Deposit(double depositBalance, String investmentDate, String maturityDate) {
        this.depositBalance = depositBalance;
        this.investmentDate = investmentDate;
        this.maturityDate = maturityDate;
    }

    public double getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(double depositBalance) {
        this.depositBalance = depositBalance;
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

    public static void withdrawMoney(List<Account> accounts) throws ParseException {

        // Get an Array of deposit accounts from user if he already possesses deposit
        // accounts
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        List<Account> myAccounts = Account.myAccounts(accounts, firstName, lastName);

        if (myAccounts.size() > 0) {

            double depositBefore, depositAfter, amount;

            System.out.println("\n***** Available Deposits - " + myAccounts.get(0).getCustomer().getFirstName() + " "
                    + myAccounts.get(0).getCustomer().getLastName() + " *****\n");
            System.out.printf("%-8s%-20s%-20s%-18s\n", "Account", "Investment Date", "Maturnaty Date", "Total Balance");
            int counter = 1;
            for (Account a : myAccounts) {

                String[] investmentDateFormatted = DateConverter.dateFormatter(a.getDeposit().getInvestmentDate());
                String[] maturnityDateFormatted = DateConverter.dateFormatter(a.getDeposit().getMaturityDate());
                String investmentDate = investmentDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                        + " of " + investmentDateFormatted[1];
                String maturnityDate = maturnityDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(maturnityDateFormatted[0]))
                        + " of " + maturnityDateFormatted[1];

                System.out.printf("%-8d%-20s%-20s%-12.2f$\n", counter, investmentDate, maturnityDate,
                        a.getDeposit().getDepositBalance());

                counter++;
            }

            System.out.println("\nPlease choose one account by it´s number which you want to debit from");
            System.out.print("Account: ");
            int index = scanner.nextInt();
            String maternityDateCleaned = myAccounts.get(index - 1).getDeposit().getMaturityDate();
            // maternityDateCleaned = maternityDateCleaned.charAt(0) + "."
            // + maternityDateCleaned.substring(maternityDateCleaned.length() - 8,
            // maternityDateCleaned.length() - 4) + "."
            // + maternityDateCleaned.substring(maternityDateCleaned.length() - 4);
            // maternityDateCleaned = maternityDateCleaned.replace(" ", "");

            boolean checkDate = false;

            try {
                checkDate = DateConverter.checkDate(maternityDateCleaned);
            } catch (Exception e) {
                checkDate = DateConverter.checkDate(myAccounts.get(index - 1).getDeposit().getMaturityDate());
            }

            if (checkDate) {
                depositBefore = myAccounts.get(index - 1).getDeposit().getDepositBalance();
                System.out.print("\nAmount you want to debit: ");
                amount = scanner.nextDouble();
                depositAfter = depositBefore - amount;

                System.out.printf("\n%-12s%-20s%-20s\n", "Account", "Balance Before", "Balance After");
                System.out.printf("%-12d%12.2f $%17.2f $\n", index, depositBefore, depositAfter);
            } else {
                System.out
                        .println(
                                "\nUnfortunately you can´t withdraw money from this account, because maturnity date isn´t reached yet\n");
            }

        } else {
            System.out.println("\nCustomer not available at our Bank\nProgram closed\n");
            System.exit(0);
        }

    }

    public static void depositMoney(List<Account> accounts) throws ParseException {

        // Get an Array of deposit accounts from user if he already possesses deposit
        // accounts
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        List<Account> myAccounts = Account.myAccounts(accounts, firstName, lastName);

        double depositBefore, depositAfter, amount;

        if (myAccounts.size() > 0) {
            System.out.println("\n***** Available Deposits - " + myAccounts.get(0).getCustomer().getFirstName() + " "
                    + myAccounts.get(0).getCustomer().getLastName() + " *****\n");
            System.out.printf("%-8s%-20s%-20s%-18s\n", "Account", "Investment Date", "Maturnaty Date", "Total Balance");
            int counter = 1;
            for (Account a : myAccounts) {

                String[] investmentDateFormatted = DateConverter.dateFormatter(a.getDeposit().getInvestmentDate());
                String[] maternityDateConverted = DateConverter.dateFormatter(a.getDeposit().getMaturityDate());
                String investmentDate = investmentDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                        + " of " + investmentDateFormatted[1];
                String maternityDate = maternityDateConverted[0]
                        + DateConverter.daySuffix(Integer.parseInt(maternityDateConverted[0]))
                        + " of " + maternityDateConverted[1];

                System.out.printf("%-8d%-20s%-20s%-12.2f$\n", counter, investmentDate, maternityDate,
                        a.getDeposit().getDepositBalance());

                counter++;
            }

            System.out.println("\nPlease choose one account by it´s number where you want to deposit money");
            System.out.print("Account: ");
            int index = scanner.nextInt();

            System.out.print("\nAmount you want to deposit: ");
            amount = scanner.nextDouble();
            depositBefore = myAccounts.get(index - 1).getDeposit().getDepositBalance();
            depositAfter = depositBefore + amount;

            System.out.println("\n");
            System.out.printf("\n%-12s%-20s%-20s\n", "Account", "Balance Before", "Balance After");
            System.out.printf("%-12d%12.2f $%17.2f $\n", index, depositBefore, depositAfter);

        } else {

            System.out.println("\nCustomer not available at our Bank\nProgram closed\n");
            System.exit(0);
        }

    }

    public static void showTotalBalance(List<Account> accounts) throws ParseException {

        // Get an Array of deposit accounts from user if he already possesses deposit
        // accounts
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        List<Account> myAccounts = Account.myAccounts(accounts, firstName, lastName);

        if (myAccounts.size() > 0) {
            System.out.println("\n***** Available Deposits - " + myAccounts.get(0).getCustomer().getFirstName() + " "
                    + myAccounts.get(0).getCustomer().getLastName() + " *****\n");
            System.out.printf("%-8s%-20s%-20s%-18s\n", "Account", "Investment Date", "Maturnaty Date", "Total Balance");
            int counter = 1;
            for (Account a : myAccounts) {

                String[] investmentDateFormatted = DateConverter.dateFormatter(a.getDeposit().getInvestmentDate());
                String[] maternityDateFormatted = DateConverter.dateFormatter(a.getDeposit().getMaturityDate());
                String investmentDate = investmentDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                        + " of " + investmentDateFormatted[1];
                String maternityDate = maternityDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(maternityDateFormatted[0]))
                        + " of " + maternityDateFormatted[1];

                System.out.printf("%-8d%-20s%-20s%-10.2f$\n", counter, investmentDate, maternityDate,
                        a.getDeposit().getDepositBalance());

                counter++;
            }

            double totalBalance = myAccounts.stream().mapToDouble(mapper -> mapper.getDeposit().getDepositBalance()).sum();

            System.out.println(
                    "\n--------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println("Total Balance for Customer " + myAccounts.get(0).getCustomer().getFirstName() + " "
                    + myAccounts.get(1).getCustomer().getLastName() + ": " + totalBalance + " $");

        }

    }

}
