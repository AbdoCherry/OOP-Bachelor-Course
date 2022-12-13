package Week08.Task02.Model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Week08.Task02.Abstract.Account;
import Week08.Task02.Auxilary.DateConverter;

public class Customer {

    static DecimalFormat digit = new DecimalFormat("#");
    DecimalFormat df = new DecimalFormat("#0.00");

    private String customerID, lastName, firstName, customerSince;
    private Checking checking;
    private Deposit deposit;

    public Customer() {

    }

    public Customer(String customerID, String lastName, String firstName, String customerSince, double checkingBalance,
                    double depositBalance, String investmentDate, String maturityDate) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.customerSince = customerSince;
        this.checking = new Checking(checkingBalance);
        this.deposit = new Deposit(depositBalance, investmentDate, maturityDate);
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(String customerSince) {
        this.customerSince = customerSince;
    }

    public Checking getChecking() {
        return checking;
    }

    public void setChecking(Checking checking) {
        this.checking = checking;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public List<Customer> myAccounts(List<Customer> customers, String[] customer) {

        // Searching user by First- and Lastname and return list with all of his accounts
        List<Customer> myAccounts = customers.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(customer[0]) && c.getLastName().equalsIgnoreCase(customer[1]))
                .collect(Collectors.toList());

        return myAccounts;
    }

    public String maxID(List<Customer> customers) {
        int max = 0;
        String maxID = null;

        for (Customer c : customers) {
            if (max < Integer.parseInt(c.getCustomerID())) {
                max = Integer.parseInt(c.getCustomerID());
            }
        }

        maxID = String.valueOf(max + 1);

        return maxID;
    }

    public void addCustomer(List<Customer> customers) {

        // Collecting basic information about customer
        String[] customer = Account.customer();
        List<Customer> myAccounts = myAccounts(customers, customer);

        // Declaring Objects and variables as placeholder
        String customerSince = null, customerID;
        Checking newChecking = new Checking();
        Deposit newDeposit = new Deposit();
        Customer newCustomer = new Customer();
        char createAccount;

        // If customer exists and has at least one account
        if (myAccounts.size() > 0) {
            System.out.println("\n\t***** " + myAccounts.size() + " Accounts found - " + customer[0] + " " + customer[1]
                    + " *****\n");

            System.out.print("\nDo you want to add an Account?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            customerID = myAccounts.get(0).getCustomerID();
            customerSince = myAccounts.get(0).getCustomerSince();
            createAccount = Account.scanner.next().charAt(0);

            // If customer doesn´t exists
        } else {

            /**
             * Ask user again if he wants to create an account at our Bank
             * (not necessary, but better user experience)
             */
            System.out.println("\n***** Welcome to our bank. Please follow instruction in creation process *****");
            System.out.print("\nDo you want to create an Account?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            createAccount = Account.scanner.next().charAt(0);
            customerID = maxID(customers);
            customerSince = DateConverter.createDate('C');
        }

        if (createAccount == 'Y' || createAccount == 'y') {

            DecimalFormat digit = new DecimalFormat("#");
            digit.setMaximumFractionDigits(10);
            newChecking = newChecking.createChecking();
            newDeposit = newDeposit.createDeposit();

            newCustomer.setCustomerID(customerID);
            newCustomer.setFirstName(customer[0]);
            newCustomer.setLastName(customer[1]);
            newCustomer.setCustomerSince(customerSince);
            newCustomer.setChecking(newChecking);
            newCustomer.setDeposit(newDeposit);

            String customerSinceFormatted = "0";
            String checkingBalanceFormatted = "0";
            String depositBalanceFormatted = "0";
            String investmentDateFormatted = "0";
            String maturityDateFormatted = "0";

            // Checking Account Error-Handling
            try {
                customerSinceFormatted = DateConverter.dateFormatter(newCustomer.getCustomerSince());
                checkingBalanceFormatted = digit.format(newChecking.getCheckingBalance()) + " $";
                depositBalanceFormatted = digit.format(newDeposit.getDepositBalance()) + " $";
                investmentDateFormatted = DateConverter.dateFormatter(newDeposit.getInvestmentDate());
                maturityDateFormatted = DateConverter.dateFormatter(newDeposit.getExpirationDate());

            } catch (Exception ignored) {

            }


            System.out
                    .println(
                            "\n========================================================== Account Creation - SUCCESSFUL ==========================================================\n");
            System.out.printf("\033[1m%-30s%-30s%-30s%-30s%-30s\033[0m\n", "Date of Creation",
                    "Checking Balance", "Deposit Balance", "Investment Date", "Maturity Date");

            System.out.printf("%-30s%-30s%-30s%-30s%-30s\n", customerSinceFormatted,
                    checkingBalanceFormatted, depositBalanceFormatted, investmentDateFormatted, maturityDateFormatted);

            customers.add(newCustomer);
        } else if (createAccount == 'N' || createAccount == 'n') {
            System.out.println("\nThanks for using the program. Creation of account is cancelled");
        }
    }

    public void removeAccount(List<Customer> customers) {

        // Collect basic information about user
        String[] customer = Account.customer();
        List<Customer> myAccounts = myAccounts(customers, customer);
        List<Deposit> myDeposits = Deposit.myDeposits(customers, customer);
        List<Checking> myCheckings = Checking.myCheckings(customers, customer);

        // Display all Accounts of customers
        System.out.println("\n***** " + myAccounts.size() + " Accounts found - " + customer[0]
                + " " + customer[1] + " ****");
        System.out.println(
                "Do you really want to delete your account completely or only single accounts?\nAll existing accounts are cancelled and the process is not revocable\t[All = \"A\"/\"a\"] - [Single = \"S\"/\"s\"]: ");
        System.out.println("Choose option below");
        System.out.println("[All Accounts = \"A\"]\t[Cancel deletion = \"N\"]");
        System.out.println("[All Deposits = \"D\"]\t[Single Deposit = \"d\"]");
        System.out.println("[All Checkings = \"C\"]\t[Single Checking = \"c\"]");
        System.out.print("\nOption: ");
        char inputUser = Account.scanner.next().charAt(0);

        double maxAmount = 0.0;

        switch (inputUser) {

            case 'A':

                // Get max amount of customers money from all accounts
                maxAmount = myAccounts.stream()
                        .mapToDouble(c -> c.getChecking().getCheckingBalance() + c.getChecking().getCheckingBalance())
                        .sum();

                // Remove custoemr from our main customer list
                customers.removeIf(c -> c.getFirstName().equals(customer[0]) && c.getLastName().equals(customer[1]));

                // Delete temporary created list with individual records extracted from customer
                myAccounts.clear();

                System.out.println(
                        "\nAll accounts terminated. Total amount of " + df.format(maxAmount)
                                + " will be handed to you\n");
                Account.scanner.nextLine();

                break;

            case 'D':

                maxAmount = myDeposits.stream()
                        .mapToDouble(c -> c.getDepositBalance())
                        .sum();

                customers.removeIf((c -> c.getFirstName().equals(customer[0]) && c.getLastName().equals(customer[1])
                        && c.getDeposit().getDepositBalance() > 0.0));

                myDeposits.clear();

                System.out.println(
                        "\nAll deposits terminated. Total amount of " + df.format(maxAmount)
                                + " $ will be handed to you\n");

            case 'd':

                System.out.println("\nPlease choose deposit you want to terminate by it´s number below");

                System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s\033[0m\n", "Deposit", "Investment Date",
                        "Maturity Date",
                        "Deposit Balance");
                for (int i = 0; i < myDeposits.size(); i++) {

                    String depositNo = "Deposit " + (i + 1) + ": ";
                    String investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                    String maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getExpirationDate());
                    String depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";

                    System.out.printf("%-30s%-30s%-30s%-30s\n", depositNo, investmentDateFormatted,
                            maturityDateFormatted,
                            depositBalanceFormatted);
                }

                int inputDeposit = Account.scanner.nextInt();

                maxAmount = myDeposits.get(inputDeposit - 1).getDepositBalance();
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getDeposit().equals(myDeposits.get(inputDeposit - 1))) {
                        customers.remove(i);
                    }
                }

                System.out.println(
                        "\nAccount terminated. Total amount of " + df.format(maxAmount) + " $ will be handed to you\n");

                break;

            case 'C':

                maxAmount = myCheckings.stream()
                        .mapToDouble(c -> c.getDepositBalance())
                        .sum();

                customers.removeIf((c -> c.getFirstName().equals(customer[0]) && c.getLastName().equals(customer[1])
                        && c.getChecking().getCheckingBalance() > 0.0));

                myCheckings.clear();

                System.out.println(
                        "\nAll checkings terminated. Total amount of " + df.format(maxAmount)
                                + " will be handed to you\n");

                break;

            case 'c':

                System.out.println("\nPlease choose deposit you want to terminate by it´s number below");

                System.out.printf("\n\033[1m%-30s%-30s\033[0m\n", "Checking", "Checking Balance");
                for (int i = 0; i < myCheckings.size(); i++) {

                    String depositNo = "Checking " + (i + 1) + ": ";

                    String checkingBalanceFormatted = digit.format(myCheckings.get(i).getCheckingBalance()) + " $";

                    System.out.printf("%-30s%-30ss\n", depositNo, checkingBalanceFormatted);
                }

                int inputChecking = Account.scanner.nextInt();

                maxAmount = myDeposits.get(inputChecking - 1).getDepositBalance();
                for (int i = 0; i < customers.size(); i++) {
                    if (customers.get(i).getChecking().equals(myCheckings.get(inputChecking - 1))) {
                        customers.remove(i);
                    }
                }

                System.out.println(
                        "\nChecking terminated. Total amount of " + df.format(maxAmount)
                                + " $ will be handed to you\n");

                break;

            case 'N':
            case 'n':

                System.out.println("\nDeletion process cancelled. Thanks for using the program");

            default:

                System.out.println("\nWrong entry. Please close and restart program again\n");
        }

    }

    public void getCustomer(List<Customer> customers) {

        // declaring digit formatter
        digit.setMaximumFractionDigits(10);
        double maxAmount = 0.0;

        // Check if parameter is null
        String[] customer = Account.customer();
        List<Customer> myAccounts = myAccounts(customers, customer);

        // Getting separate list of all checking Accounts from customer
        List<Checking> myCheckings = Checking.myCheckings(customers, customer);

        // Getting separate list of all deposit Accounts from customer
        List<Deposit> myDeposits = Deposit.myDeposits(customers, customer);

        // Display all Accounts of customers if available
        if (myAccounts.size() > 0) {
            System.out
                    .println("\n***** " + (myDeposits.size() + myCheckings.size()) + " Accounts found - " + customer[0]
                            + " " + customer[1] + " ****");

            System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s\033[0m\n", "Deposit", "Investment Date", "Maturity Date",
                    "Deposit Balance");

            for (int i = 0; i < myDeposits.size(); i++) {
                if (myDeposits.get(i) != null) {
                    maxAmount += myDeposits.get(i).getDepositBalance();

                    String depositNo = "Deposit " + (i + 1) + ": ";
                    String investmentDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                    String maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getExpirationDate());
                    String depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";
                    System.out.printf("%-30s%-30s%-30s%-30s\n", depositNo, investmentDateFormatted,
                            maturityDateFormatted,
                            depositBalanceFormatted);
                }

            }
            System.out
                    .println(
                            "---------------------------------------------------------------------------------------------------------");
            System.out.printf("\033[1m%-30s%-30s\033[0m\n", "Checking Account", "Checking Balance");

            for (int i = 0; i < myCheckings.size(); i++) {

                maxAmount += myCheckings.get(i).getCheckingBalance();
                String checkinNo = "Checking " + (i + 1) + ": ";
                String checkingBalanceFormatted = digit.format(myCheckings.get(i).getCheckingBalance()) + " $";

                System.out.printf("%-30s%-30s\n", checkinNo, checkingBalanceFormatted);
            }

            System.out.println(
                    "\n\033[1mMaximum amount of money on your account " + df.format(maxAmount) + ":\033[0m");
        } else {
            System.out.println("\nUser doesn´t exists at our bank at all\n");
        }

    }

    public void displayAll(List<Customer> customers) {

        Map<String, List<Customer>> groupByCustomer = customers.stream()
                .collect(Collectors.groupingBy(
                        (Customer c) -> c.getFirstName() + " " + c.getLastName()));

        groupByCustomer.forEach((customer, accounts) -> {
            System.out.println("\n\t\t\t\033[1m***** Customers: " + customer + " *****\033[0m");

            List<Deposit> deposits = new ArrayList<Deposit>();
            List<Checking> checkings = new ArrayList<Checking>();

            double maxAmount = 0.0;

            for (Customer c : accounts) {
                deposits.add(c.getDeposit());
                checkings.add(c.getChecking());
            }

            System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s\033[0m\n", "Deposit", "Investment Date", "Maturity Date",
                    "Deposit Balance");
            for (int i = 0; i < deposits.size(); i++) {
                String depositNo = "Deposit " + (i + 1) + ": ";
                String investmentDateFormatted = DateConverter.dateFormatter(deposits.get(i).getInvestmentDate());
                String maturityDateFormatted = DateConverter.dateFormatter(deposits.get(i).getExpirationDate());
                String depositBalanceFormatted = digit.format(deposits.get(i).getDepositBalance()) + " $";

                maxAmount += deposits.get(i).getDepositBalance();

                System.out.printf("%-30s%-30s%-30s%-30s\n", depositNo, investmentDateFormatted, maturityDateFormatted,
                        depositBalanceFormatted);
            }
            System.out
                    .println(
                            "---------------------------------------------------------------------------------------------------------");
            System.out.printf("\033[1m%-30s%-30s\033[0m\n", "Checking Account", "Checking Balance");
            for (int i = 0; i < checkings.size(); i++) {
                if (checkings.get(i).getCheckingBalance() > 0.0) {
                    String checkingNo = "Checking " + (i + 1) + ": ";
                    String checkingBalance = digit.format(checkings.get(i).getCheckingBalance()) + " $";
                    maxAmount += checkings.get(i).getCheckingBalance();

                    System.out.printf("%-30s%-30s\n", checkingNo, checkingBalance);
                }

            }

            System.out.println("\033[1mMaximum amount of money:\033[0m " + df.format(maxAmount) + "\n");

        });
    }

}
