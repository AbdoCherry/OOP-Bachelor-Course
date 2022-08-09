package Week08.Task01.Models;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import Week08.Task01.Auxilary.*;


public class Customer {

    private static final Scanner scanner = new Scanner(System.in);

    private String customerID, lastName, firstName, customerSince;

    public Customer() {

    }

    public Customer(String customerID, String lastName, String firstName, String customerSince) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.customerSince = customerSince;
    }

    /**
     * @return String
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID
     */
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    /**
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String
     */
    public String getCustomerSince() {
        return customerSince;
    }

    /**
     * @param customerSince
     */
    public void setCustomerSince(String customerSince) {
        this.customerSince = customerSince;
    }

    /**
     * @param accounts
     * @throws ParseException
     */
    public static void addAccount(List<Account> accounts) throws ParseException {

        // Object placeholder
        Customer newCustomer = new Customer();
        Deposit newDeposit = new Deposit();
        Account newAccount = new Account();

        // Get an Array of deposit accounts from user if he already possess deposit accounts
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        List<Account> myAccounts = Account.myAccounts(accounts, firstName, lastName);

        char createAccount;

        // Getting the next possible CustomerID which is not taken by an
        String customerID;

        if (myAccounts.size() > 0) {

            // Ask user if he still wants to create an account
            System.out.println(
                    "\nYou do have already " + myAccounts.size() + " accounts\nDo you still want to create a new one?");
            System.out.print("[Yes = \"Y\" | \"y\"] - [No = \"N\" | \"n\"]: ");
            createAccount = scanner.next().charAt(0);

            // Customer ID, First Name and Last Name stays the same
            customerID = myAccounts.get(0).getCustomer().getCustomerID();

            // Initializing the first and last name of Customer
            firstName = myAccounts.get(0).getCustomer().getFirstName();
            lastName = myAccounts.get(0).getCustomer().getLastName();

        } else if (myAccounts.size() == 0) {

            // If he is a brand new Customer
            System.out.println("\nThanks for joining this bank as our new Customer. Please follow the instructions\n");
            createAccount = 'Y';
            customerID = Account.maxID(accounts);

        } else {
            // If he changes his mind and doesn´t want to create a deposit account
            createAccount = 'N';
            customerID = null;
        }
        if (createAccount == 'Y' || createAccount == 'y') {

            // Let user choose when he wants to open his new deposit account
            String[] customerSinceFormatted = DateConverter.dateFormatter(DateConverter.createDate('C'));
            String customerSince = customerSinceFormatted[0]
                    + DateConverter.daySuffix(Integer.parseInt(customerSinceFormatted[0])) + " of "
                    + customerSinceFormatted[1];

            // Let user type in amount he want´s to deposit
            System.out.println("\nHow much money would you like to deposit");
            System.out.print("Amount: ");
            double depositBalance = scanner.nextDouble();

            // Return an exception (see Class "UnderCutDeposit") if amount is less than 100
            // $
            try {
                while (depositBalance < 100) {
                    throw new UnderCutDeposit(depositBalance);

                }
            } catch (UnderCutDeposit e) {
                System.out.print(e.getMessage());
                depositBalance = scanner.nextDouble();
            }

            // Let user choose when he wants to start date of investment in deposit account
            String[] investmentDateFormatted = DateConverter.dateFormatter(DateConverter.createDate('I'));
            String investmentDate = investmentDateFormatted[0]
                    + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                    + " of " + investmentDateFormatted[1];

            // Let user choose how long he wants to hold investment
            String[] maturityDateFormatted = DateConverter
                    .dateFormatter(DateConverter.maternityDay(investmentDateFormatted));
            String maturityDate = maturityDateFormatted[0]
                    + DateConverter.daySuffix(Integer.parseInt(maturityDateFormatted[0])) + " of "
                    + maturityDateFormatted[1];

            // Initializing the Customer variables into our Customer Objects
            newCustomer.setCustomerID(customerID);
            newCustomer.setLastName(lastName);
            newCustomer.setFirstName(firstName);
            newCustomer.setCustomerSince(customerSince);

            // Initializing the Deposit variables into our Deposit Objects
            newDeposit.setDepositBalance(depositBalance);
            newDeposit.setInvestmentDate(investmentDate);
            newDeposit.setMaturityDate(maturityDate);

            // Initializing our Customer & Deposit Objects into our Account Object
            newAccount.setCustomer(newCustomer);
            newAccount.setDeposit(newDeposit);

        } else {
            newAccount = null;
        }

        // Adding initialized Account to acounts ArrayList
        accounts.add(newAccount);

    }

    /**
     * @param accounts
     * @throws ParseException
     */
    public static void getAccount(List<Account> accounts) throws ParseException {

        // Get an Array of deposit accounts from user if he already possess deposit
        // accounts
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();

        List<Account> myAccounts = Account.myAccounts(accounts, firstName, lastName);

        if (myAccounts.size() != 0) {

            System.out.println(
                    "\n------------------------------------------------------------------------------------------------\n");
            System.out.println("\n**** " + myAccounts.size() + " Available Deposits - "
                    + myAccounts.get(0).getCustomer().getFirstName()
                    + " " + myAccounts.get(0).getCustomer().getLastName() + " - *****\n");

            for (Account a : myAccounts) {

                try {
                    String[] customerSinceFormatted = DateConverter.dateFormatter(a.getCustomer().getCustomerSince());
                    String customerSince = customerSinceFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(customerSinceFormatted[0]))
                            + " of " + customerSinceFormatted[1];
                    System.out.printf("%-18s%-10s\n", "Customer Since: ", customerSince);

                } catch (ParseException e) {
                    System.out.printf("%-18s%-10s\n", "Customer Since: ", a.getCustomer().getCustomerSince());

                }

                System.out.printf("%-18s%-10.2f$\n", "Deposit Balance: ", a.getDeposit().getDepositBalance());

                try {
                    String[] investmentDateFormatted = DateConverter.dateFormatter(a.getDeposit().getInvestmentDate());
                    String investmentDate = investmentDateFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0])) + " of "
                            + investmentDateFormatted[1];
                    System.out.printf("%-18s%-10s\n", "Investment Date: ", investmentDate);
                } catch (ParseException e) {
                    System.out.printf("%-18s%-10s\n", "Investment Date: ", a.getDeposit().getInvestmentDate());
                }
                try {
                    String[] maturityDateFormatted = DateConverter.dateFormatter(a.getDeposit().getMaturityDate());
                    String maturityDate = maturityDateFormatted[0]
                            + DateConverter.daySuffix(Integer.parseInt(maturityDateFormatted[0]))
                            + " of " + maturityDateFormatted[1];
                    System.out.printf("%-18s%-10s\n", "Maturity Date: ", maturityDate);
                    System.out.println("\n-------------------------------------------------");
                } catch (ParseException e) {
                    System.out.printf("%-18s%-10s\n", "Maturity Date: ", a.getDeposit().getMaturityDate());
                    System.out.println("\n-------------------------------------------------");
                }

            }
        } else {
            System.out.println("\nCustomer not available at our Bank\nProgram closed\n");
            System.exit(0);
        }
    }

    /**
     * @param accounts
     * @throws ParseException
     */
    public static void removeAccount(List<Account> accounts) throws ParseException {

        // Get an Array of deposit accounts from user if he already possess deposit
        // accounts
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("\nLast Name: ");
        String lastName = scanner.nextLine();
        List<Account> myAccounts = Account.myAccounts(accounts, firstName, lastName);

        if (myAccounts.size() > 0) {
            System.out.println("\n***** Available Deposits - " + myAccounts.get(0).getCustomer().getFirstName() + " "
                    + myAccounts.get(0).getCustomer().getLastName() + " *****\n");
            System.out.printf("%-8s%-20s%-20s%-18s\n", "Account", "Investment Date", "Maternity Date", "Total Balance");
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

            System.out.println("\nPlease select Account you want to delete");
            System.out.print("Deposit Account: ");
            int input = scanner.nextInt();

            // Retrieving the deposit account index we want to remove from list
            accounts.remove(myAccounts.get(input - 1));

            System.out.println("\nDeposit Account deleted successfully\n");
        }

    }

    /**
     * @param accounts
     * @throws ParseException
     */
    public static void showTotalBalance(List<Account> accounts) throws ParseException {

        // Get an Array of deposit accounts from user if he already possess deposit
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
                String[] maturnityDateFormatted = DateConverter.dateFormatter(a.getDeposit().getMaturityDate());
                String investmentDate = investmentDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(investmentDateFormatted[0]))
                        + " of " + investmentDateFormatted[1];
                String maturnityDate = maturnityDateFormatted[0]
                        + DateConverter.daySuffix(Integer.parseInt(maturnityDateFormatted[0]))
                        + " of " + maturnityDateFormatted[1];

                System.out.printf("%-8d%-20s%-20s%-10.2f$\n", counter, investmentDate, maturnityDate,
                        a.getDeposit().getDepositBalance());

                counter++;
            }

            double totalBalance = myAccounts.stream().mapToDouble(mapper -> {
                return mapper.getDeposit().getDepositBalance();
            }).sum();

            System.out.println(
                    "\n--------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println("Total Balance for Customer " + myAccounts.get(0).getCustomer().getFirstName() + " "
                    + myAccounts.get(1).getCustomer().getLastName() + ": " + totalBalance + " $");

        }

    }

}
