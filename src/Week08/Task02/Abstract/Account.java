package Week08.Task02.Abstract;

import java.util.List;
import java.util.Scanner;

import Week08.Task02.Model.Customer;

public abstract class Account {

    public static Scanner scanner = new Scanner(System.in);

    private double checkingBalance, depositBalance;
    private String investmentDate, expirationDate;

    public Account() {

    }

    public Account(double checkingBalance) {
        this.checkingBalance = checkingBalance;
    }

    public Account(double depositBalance, String investmentDate, String expirationDate) {
        this.depositBalance = depositBalance;
        this.investmentDate = investmentDate;
        this.expirationDate = expirationDate;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public void setCheckingBalance(double checkingBalance) {
        this.checkingBalance = checkingBalance;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public static String[] customer() {

        // Collect basic information about user
        String[] customer = new String[2];

        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        customer[0] = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Last Name: ");
        customer[1] = scanner.nextLine();

        return customer;
    }

    public abstract void withdraw(List<Customer> customers);

    public abstract void payIn(List<Customer> customers);

}
