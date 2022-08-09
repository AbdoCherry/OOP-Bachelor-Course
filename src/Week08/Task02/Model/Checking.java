package Week08.Task2.Model;

import java.util.List;
import java.util.stream.Collectors;

import Week08.Task2.Abstract.Account;

public class Checking extends Account {

    private static final int DISPOLIMIT = 0;

    public Checking() {

    }

    public Checking(double checkingBalance) {
        super(checkingBalance);
    }

    public static List<Checking> myCheckings(List<Customer> customers, String[] customer) {

        List<Checking> myCheckings = customers.stream()
                .filter(c -> c.getFirstName().equals(customer[0]))
                .filter(c -> c.getLastName().equals(customer[1]))
                .filter(c -> c.getChecking().getCheckingBalance() > 0).map(Customer::getChecking)
                .collect(Collectors.toList());

        return myCheckings;

    }

    public Checking createChecking() {
        Checking newChecking = new Checking();
        double amount = 0.0;
        System.out.print(
                "\nDo you want to pay in an amount into your checking Account?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
        char inputUser = Account.scanner.next().charAt(0);

        if (inputUser == 'Y' || inputUser == 'y') {
            System.out.print("\nAmount: ");
            amount = Account.scanner.nextDouble();
            newChecking.setCheckingBalance(amount);
        } else if (inputUser == 'N' || inputUser == 'n') {
            newChecking.setCheckingBalance(amount);
        }

        return newChecking;
    }

    @Override
    public void withdraw(List<Customer> customers) {

        // Collect basic information from customer
        String[] customer = Account.customer();
        List<Checking> myCheckings = myCheckings(customers, customer);
        int checking = 0;

        // If customer has more than one checking Account
        if (myCheckings.size() > 1) {
            System.out.println("\n\t**** " + myCheckings.size() + " CheckingAccounts found - "
                    + customer[0] + " " + customer[1] + " ****\n");

            System.out.printf("%-30s%-30s\n", "Checking Account", "Checking Balance");

            // Display customer all his checking Accounts
            for (int i = 0; i < myCheckings.size(); i++) {
                String checkingAccountNo = "Checking " + (i + 1) + ": ";
                String checkingBalanceString = myCheckings.get(i).getCheckingBalance() + " $";
                System.out.printf("\n%-30s%-30s\n", checkingAccountNo, checkingBalanceString);
                System.out.println("---------------------------------------------------------");
            }

            // Let customer choose from which checking Account he want´s to withdraw
            System.out.println(
                    "\nPlease choose from checking Accounts above you want to withdraw money");
            System.out.print("Checking Account: ");
            checking = Account.scanner.nextInt();

            // If customer has only one checking Account
        } else if (myCheckings.size() == 1) {
            System.out.printf("%-30s%-30s\n", "Checking Account 1: ", "Checking Balance");
            String checkingBalanceString = myCheckings.get(0).getCheckingBalance() + " $";
            System.out.printf("%-30s%-30s\n", "Checking Account 1:", checkingBalanceString);
            checking = 1;

            // If customer doesn´t exist at all
        } else {
            System.out.println("\nUnfortunately there is not account which belongs to this name "
                    + customer[0] + " " + customer[1] + "\n");
            System.out.println("\nProgram cancelled. Please restart later\n");
            System.exit(0);
        }

        // Initializing / Setting variable with checking balance before withdrawal
        double checkingBefore = myCheckings.get(checking - 1).getCheckingBalance(), checkingAfter,
                amount;

        // Making amount selection more realistic
        System.out.println("\nChoose amount you want to withdraw or enter manually");
        System.out.println("1 <------ 10.- $\t 100.- $ ------> 6");
        System.out.println("2 <------ 20.- $\t 200.- $ ------> 7");
        System.out.println("3 <------ 30.- $\t 300,- $ ------> 8");
        System.out.println("4 <------ 40.- $\t 400.- $ ------> 9");
        System.out.println("5 <------ 50.- $\t 500.- $ ------> 10");
        System.out.println("\n\t\t[Manually = 0]\n");

        System.out.print("Choose options above: ");
        amount = Account.scanner.nextDouble();

        // If customer chooses amount from left hand side of displayed options
        if (amount == 0) {
            System.out.print("Enter amount: ");
            amount = Account.scanner.nextDouble();
            checkingAfter = checkingBefore - amount;

        } else if (amount < 6) {
            checkingAfter = checkingBefore - amount * 10;

            // If customer chooses amount from right hand side of displayed options
        } else if (amount > 5) {
            checkingAfter = checkingBefore - (amount * 100 - 500);

            // If customers enters something wrong (e.g.: String instead of numerical value)
        } else {
            checkingAfter = checkingBefore;
            System.out.println("\nWrong Entry.");
            System.out.println(
                    "\n================================ WITHDRAW - FAILED ================================\n");
            System.exit(0);
        }

        // Check if withdrawal doesn´t undercuts the dispolimit of 0.- $
        if (checkingAfter > DISPOLIMIT) {
            for (int i = 0; i < customers.size(); i++) { // Loop through main list of customers
                if (customers.get(i).getChecking().getCheckingBalance() == checkingBefore) { // Search for record
                    Checking newChecking = new Checking(checkingAfter); // Creating new Checking Objet with new Value
                    customers.get(i).setChecking(newChecking); // Initializing Checking Object into founded record
                    break;
                }
            }
            System.out.println(
                    "\n================================ WITHDRAW - SUCCESSFUL ================================\n");
            System.out.printf("%-30s%-30s%-30s\n", "Balance Before", "Balance After",
                    "Amount Withdrawed");

            // Convert values into string for better formatting to table view
            String checkingBeforeString = checkingBefore + " $";
            String checkingAfterString = checkingAfter + " $";
            String amountString = checkingBefore - checkingAfter + " $";

            System.out.printf("%-30s%-30s%-11s\n", checkingBeforeString, checkingAfterString,
                    amountString);
            Account.scanner.nextLine();
        }

        // In case customer undercuts dispolimit
        else {
            System.out.println(
                    "\n================================ WITHDRAW - FAILED ================================\n");
            System.out.println(
                    "\nProgram cancelled. Withdrawals refused\nReason: Amout you want to withdraw undercuts the overdraft limit of: "
                            + DISPOLIMIT + "\n");
            System.exit(0);
        }

    }

    @Override
    public void payIn(List<Customer> customers) {

        String[] customer = Account.customer();
        List<Checking> myCheckings = myCheckings(customers, customer);
        int checking = 0;

        if (myCheckings.size() > 1) {
            System.out.println("\n\t**** " + myCheckings.size() + " Accounts found - " + customer[0]
                    + " " + customer[1] + " ****\n");

            System.out.printf("%-30s%-30s\n", "Checking Account", "Checking Balance");
            for (int i = 0; i < myCheckings.size(); i++) {
                String checkingAccountNo = "Checking " + (i + 1) + ": ";
                String checkingBalanceString = myCheckings.get(i).getCheckingBalance() + " $";
                System.out.printf("\n%-30s%-30s\n", checkingAccountNo, checkingBalanceString);
                System.out.println("---------------------------------------------------------");
            }
            System.out.println(
                    "\nPlease choose from checking Accounts above you want to pay in money");
            System.out.print("Checking Account: ");
            checking = Account.scanner.nextInt();
        } else if (myCheckings.size() == 1) {
            System.out.printf("%-30s%-30s\n", "Checking Account", "Checking Balance");
            String checkingBalanceString = myCheckings.get(0).getCheckingBalance() + " $";
            System.out.printf("%-30s%-30s\n", "Checking Account 1:", checkingBalanceString);
            checking = 0;
        } else {
            System.out.println("\nUnfortunately there is not account which belongs to this name "
                    + customer[0] + " " + customer[1] + "\n");
            System.out.println("\nProgram cancelled. Please restart later\n");
            System.exit(0);
        }

        double checkingBefore = myCheckings.get(checking).getCheckingBalance(), checkingAfter,
                amount;

        System.out.println("\nChoose amount you want to pay in or enter manually");
        System.out.println("1 <------ 10,- $\t 100,- $ ------> 6");
        System.out.println("2 <------ 20,- $\t 200,- $ ------> 7");
        System.out.println("3 <------ 30,- $\t 300,- $ ------> 8");
        System.out.println("4 <------ 40,- $\t 400,- $ ------> 9");
        System.out.println("5 <------ 50,- $\t 500,- $ ------> 10");
        System.out.println("\n\t\t[Manually = 0]\n");

        System.out.print("Choose options above: ");
        amount = Account.scanner.nextDouble();

        if (amount == 0) {
            System.out.print("Enter amount: ");
            amount = Account.scanner.nextDouble();
            checkingAfter = checkingBefore + amount;

        } else if (amount < 6) {
            checkingAfter = checkingBefore + amount * 10;

        } else if (amount > 5) {
            checkingAfter = checkingBefore + (amount * 100 - 500);

        } else {
            checkingAfter = checkingBefore;
            System.out.println("\nWrong Entry.");
            System.out.println(
                    "\n================================ PAY IN - FAILED ================================\n");
            System.exit(0);
        }

        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getChecking().getCheckingBalance() == checkingBefore) {
                Checking newChecking = new Checking(checkingAfter);
                customers.get(i).setChecking(newChecking);
                break;
            }

            System.out.println(
                    "\n================================ PAY IN - SUCCESSFUL ================================\n");
            System.out.printf("%-30s%-30s%-30s\n", "Balance Before", "Balance After",
                    "Amount Payed in");

            String checkingBeforeString = checkingBefore + " $";
            String checkingAfterString = checkingAfter + " $";
            String amountString = checkingAfter - checkingBefore + " $";

            System.out.printf("%-30s%-30s%-11s\n", checkingBeforeString, checkingAfterString,
                    amountString);

        }

    }

}
