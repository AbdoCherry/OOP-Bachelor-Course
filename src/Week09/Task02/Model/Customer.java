package Week09.Task02.Model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Week09.Task02.BusinessRules.*;

public class Customer {

    private static Scanner scanner = new Scanner(System.in);
    static DecimalFormat df = new DecimalFormat("#0.00");
    static DecimalFormat digit = new DecimalFormat("#");
    private String customerID, lastName, firstName, customerSince;

    public Customer() {

    }

    public Customer(String customerID, String lastName, String firstName, String customerSince) {
        this.customerID = customerID;
        this.lastName = lastName;
        this.firstName = firstName;
        this.customerSince = customerSince;
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

    public static Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        Customer.scanner = scanner;
    }

    public static String[] customer() {

        String[] customer = new String[2];

        scanner.nextLine();
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("First Name: ");
        customer[0] = scanner.nextLine();
        System.out.print("Last Name: ");
        customer[1] = scanner.nextLine();

        return customer;
    }

    public static List<Deposit> myDeposits(List<Deposit> deposits, String[] customer) {

        List<Deposit> myDeposits = deposits.stream()
                .filter(d -> d.getFirstName().equals(customer[0]))
                .filter(d -> d.getLastName().equals(customer[1]))
                .collect(Collectors.toList());

        return myDeposits;

    }

    public static String maxID(List<Deposit> deposits) {
        int max = 0;

        for (Deposit d : deposits) {
            if (max < Integer.parseInt(d.getCustomerID())) {
                max = Integer.parseInt(d.getCustomerID());
            }
        }

        String maxID = String.valueOf(max + 1);

        return maxID;
    }

    public static void addAccount(List<Deposit> deposits) {

        String[] customer = customer();
        Deposit newDeposit = new Deposit();
        String customerID;
        String customerSince;

        List<Deposit> myDeposits = myDeposits(deposits, customer);

        char createAccount;

        if (myDeposits.size() > 0) {
            System.out.println("\n\t***** " + myDeposits.size() + " Deposits found - " + customer[0] + " " + customer[1]
                    + " *****\n");

            System.out.print("\nDo you want to add an Account?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            customerID = myDeposits.get(0).getCustomerID();
            customerSince = myDeposits.get(0).getCustomerSince();

            createAccount = scanner.next().charAt(0);
        } else {
            System.out.println("\n***** Welcome to our bank. Please follow instruction in creation process *****");
            System.out.print("\nDo you want to create an Account?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            createAccount = scanner.next().charAt(0);

            customerID = maxID(deposits);
            customerSince = DateConverter.createDate('C');

        }

        char createDeposit;
        if (createAccount == 'Y' || createAccount == 'y') { // Ask user when he wants to create a deposit
            System.out.print(
                    "Do you want to create an deposit to your account now?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            createDeposit = scanner.next().charAt(0);
        } else if (createAccount == 'N' || createAccount == 'n') {
            System.out.println("\nThanks for creating an account at our bank\n");
            createDeposit = 'N';
        } else {
            System.out.println("\nWrong entry. Please close and restart program later\n");
            createDeposit = 'N';
        }

        if (createDeposit == 'Y' || createDeposit == 'y') {

            System.out.println("\nPlease enter the necessary deposit information in the fields below");

            // Description of deposit
            System.out.println("\nEnter description");
            System.out.print("Description: ");
            String description = scanner.nextLine();
            scanner.nextLine();

            // Deposit Balance
            System.out.println("\nEnter balance");
            System.out.print("\nBalance: ");
            double depositBalance = scanner.nextDouble();

            // Deposit Investment and Maturity Date
            String investmentDate = DateConverter.createDate('I');
            System.out.println("\nPlease choose on of the two options for the maturity date");
            System.out.println("1 <---- 90 Days\t|\tDays 180 ----> 2");

            System.out.print("Maturity period: ");
            int maturity = scanner.nextInt();
            String maturityDate = String.valueOf(90);

            if (maturity == 1) {
                maturityDate = String.valueOf(DAYS.A.getNumValues());
                maturityDate = DateConverter.dateCalc(investmentDate, maturityDate, 'p');
            } else if (maturity == 2) {
                maturityDate = String.valueOf(DAYS.B.getNumValues());
                maturityDate = DateConverter.dateCalc(investmentDate, maturityDate, 'p');
            } else {
                System.out.println("\nWrong input. Either 90 or 180 days\nYour maturity date of your deposit "
                        + description + " will be setted intially to 90 days from today\n");
            }

            // Intitialize object newDeposit
            newDeposit = new Deposit(customerID, customer[1], customer[0], customerSince, description, depositBalance,
                    investmentDate, maturityDate);

            deposits.add(newDeposit);
        } else if (createDeposit == 'N' || createDeposit == 'n') {
            deposits.add(new Deposit(customerID, customer[1], customer[0], customerSince, "", 0.0, "", ""));
            System.out.println("\nThanks for using the program. Account was created without any deposits\n");

        } else {
            System.out.println("\nWrong entry. Please close and restart program later\n");
        }

    }

    public static void removeAccount(List<Deposit> deposits) {

        // Collect basic information about user
        String[] customer = Customer.customer();
        List<Deposit> myDeposits = Customer.myDeposits(deposits, customer);

        if (myDeposits.size() > 0) {
            // Display all Accounts of customers
            System.out.println("\n***** " + myDeposits.size() + " Accounts found - " + customer[0]
                    + " " + customer[1] + " ****");
            System.out.println(
                    "\nDo you really want to delete your account completely or only single accounts?");
            System.out.println(
                    "All existing accounts are cancelled and the process is not revocable\t[All = \"A\"/\"a\"] - [Single = \"S\"/\"s\"] - [Exit = \"N\"/\"n\"]: ");
            System.out.print("\nOption: ");
            char inputUser = Customer.getScanner().next().charAt(0);

            double maxAmount = 0.0;

            switch (inputUser) {

                case 'A':
                case 'a':

                    maxAmount = myDeposits.stream()
                            .mapToDouble(d -> d.getDepositBalance())
                            .sum();

                    deposits.removeIf(
                            d -> (d.getFirstName().equals(customer[0]) && d.getLastName().equals(customer[1])));

                    myDeposits.clear();

                    System.out.println(
                            "\nAll deposits terminated. Total amount of " + df.format(maxAmount)
                                    + " $ will be handed to you\n");
                    break;

                case 'S':
                case 's':

                    System.out.println("\nPlease choose deposit you want to terminate by it´s number below");

                    System.out.printf("\n\033[1m%-30s%-30s%-30s%-30s\033[0m\n", "Deposit", "Investment Date",
                            "Maturity Date",
                            "Deposit Balance");
                    for (int i = 0; i < myDeposits.size(); i++) {

                        String depositNo = "Deposit " + (i + 1) + ": ";
                        String investmentDateFormatted = DateConverter
                                .dateFormatter(myDeposits.get(i).getInvestmentDate());
                        String maturityDateFormatted = DateConverter.dateFormatter(myDeposits.get(i).getMaturityDate());
                        String depositBalanceFormatted = digit.format(myDeposits.get(i).getDepositBalance()) + " $";

                        System.out.printf("%-30s%-30s%-30s%-30s\n", depositNo, investmentDateFormatted,
                                maturityDateFormatted,
                                depositBalanceFormatted);
                    }
                    System.out.print("Account No.: ");
                    int inputDeposit = Customer.getScanner().nextInt();

                    maxAmount = myDeposits.get(inputDeposit - 1).getDepositBalance();
                    for (int i = 0; i < deposits.size(); i++) {
                        if (deposits.get(i).equals(myDeposits.get(inputDeposit - 1))) {
                            deposits.remove(i);
                        }
                    }
                    System.out.println(
                            "\nAccount terminated. Total amount of " + df.format(maxAmount)
                                    + " $ will be handed to you\n");

                    break;

                case 'N':
                case 'n':

                    System.out.println("\nDeletion process cancelled. Thanks for using the program");

                default:

                    System.out.println("\nWrong entry. Please close and restart program again\n");
            }
        } else {
            System.out.println("\nCustomer not available at bank\n");
        }

    }

}
