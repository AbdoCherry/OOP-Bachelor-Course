package Week08.Task02.Model;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

import Week08.Task02.Abstract.Account;
import Week08.Task02.Auxilary.DateConverter;

public class Deposit extends Account {

    DecimalFormat digit = new DecimalFormat("#");

    public Deposit() {

    }

    public Deposit(double depositBalance, String investmentDate, String maturityDate) {
        super(depositBalance, investmentDate, maturityDate);
    }

    public static List<Deposit> myDeposits(List<Customer> customers, String[] customer) {

        return customers.stream()
                .filter(c -> c.getFirstName().equals(customer[0]))
                .filter(c -> c.getLastName().equals(customer[1])).map(Customer::getDeposit)
                .collect(Collectors.toList());
    }

    public Deposit createDeposit() {

        Deposit newDeposit = new Deposit();
        double depositBalance = 0.0;
        String investmentDate, maturityDate;

        System.out.print(
                "\nDo you want to create a deposit Account?\n[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
        char inputUser = Account.scanner.next().charAt(0);

        // If Customer want´s to create a deposit
        if (inputUser == 'Y' || inputUser == 'y') {
            System.out.println("\nPlease enter an amount you want to invest");
            System.out.print("Amount: ");
            depositBalance = Account.scanner.nextDouble();
            investmentDate = DateConverter.createDate('I');
            maturityDate = DateConverter.expirationDate(investmentDate);

            newDeposit = new Deposit(depositBalance, investmentDate, maturityDate);
        } else if (inputUser == 'N' || inputUser == 'n') {
            newDeposit = null;
        } else {
            System.out.println("\nWrong entry. Cancel program and restart later\n");
        }

        return newDeposit;
    }

    @Override
    public void withdraw(List<Customer> customers) {

        digit.setMaximumFractionDigits(10);

        // Collect basic information from customer
        String[] customer = Account.customer();
        List<Deposit> myDeposits = myDeposits(customers, customer);

        // Defining useful variables
        int deposit = 0;
        boolean valid = false;
        String depositAccountNo = null, investmentDate = null, maturityDate = null,
                depositBalance = null;

        // If customer has more than one deposit Account
        if (myDeposits.size() > 1) {
            System.out.println("\n\t**** " + myDeposits.size() + " Deposit Accounts found - "
                    + customer[0] + " " + customer[1] + " ****\n");

            System.out.printf("%-30s%-30s%-30s%-25s%-20s\n", "Deposit Account", "Investment Date",
                    "Maturity Date", "Deposit Balance", "Withdrawal Possible");

            // Display customer all his deposit Accounts
            for (int i = 0; i < myDeposits.size(); i++) {
                depositAccountNo = "Deposit " + (i + 1) + ": ";
                investmentDate = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                maturityDate = DateConverter.dateFormatter(myDeposits.get(i).getExpirationDate());
                depositBalance = digit.format(myDeposits.get(i).getDepositBalance()) + " $";
                valid = DateConverter.dateChecker(myDeposits.get(i).getExpirationDate());

                System.out.printf("%-30s%-30s%-30s%-25s%-20s\n", depositAccountNo, investmentDate,
                        maturityDate, depositBalance, valid);
            }

            // Let customer choose from which deposit Account he want´s to withdraw
            System.out.println(
                    "\nPlease choose from deposit Accounts above you want to withdraw money");
            System.out.print("Deposit Account: ");
            deposit = Account.scanner.nextInt();
            valid = DateConverter.dateChecker(myDeposits.get(deposit - 1).getExpirationDate());

            // If customer has only one deposit Account
        } else if (myDeposits.size() == 1) {
            System.out.printf("%-30s%-30s%-30s%-25s%-20s\n", "Deposit Account 1: ",
                    "Investment Date", "Maturity Date", "Deposit Balance", "Withdrawal Possible");
            investmentDate = DateConverter.dateFormatter(myDeposits.get(0).getInvestmentDate());
            maturityDate = DateConverter.dateFormatter(myDeposits.get(0).getExpirationDate());
            depositBalance = digit.format(myDeposits.get(0).getDepositBalance()) + " $";
            valid = DateConverter.dateChecker(myDeposits.get(0).getExpirationDate());
            deposit = 1;

            System.out.printf("%-30s%-30s%-30s%-25s%-20s\n", "Deposit 1:", investmentDate,
                    maturityDate, depositBalance, valid);

            // If customer doens´t exist at all
        } else {
            System.out.println("\nUnfortunately there is not account which belongs to this name "
                    + customer[0] + " " + customer[1] + "\n");
            System.out.println("\nProgram cancelled. Please restart later\n");
            System.exit(0);
        }

        // Initializing / Setting variable with deposit Balance before withrawal
        double depositBefore = myDeposits.get(deposit - 1).getDepositBalance(), depositAfter,
                amount;
        valid = DateConverter.dateChecker(myDeposits.get(deposit - 1).getExpirationDate());

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
            amount = Account.scanner.nextInt();

            // If customer chooses amount from left-hand side of displayed options
            if (amount == 0) {
                System.out.print("Enter amount: ");
                amount = Account.scanner.nextDouble();
                depositAfter = depositBefore - amount;

            } else if (amount < 6) {
                amount = amount * 10;
                depositAfter = depositBefore - amount * 10;

                // If customer chooses amount from right-hand side of displayed options
            } else if (amount > 5) {

                depositAfter = depositBefore - (amount * 100 - 500);

                // If customers enters something wrong (e.g.: String instead of numerical value)
            } else {
                depositAfter = depositBefore;
                System.out.println("\nWrong Entry.");
                System.out.println(
                        "\n================================ WITHDRAW - FAILED ================================\n");
                System.exit(0);
            }

            System.out.println(
                    "\n================================ WITHDRAW - SUCCESSFUL ================================\n");
            System.out.printf("%-30s%-30s%-30s\n", "Deposit Before", "Deposit After",
                    "Amount Withdrawed");
            String depositBeforeString = digit.format(depositBefore) + " $";
            String depositAfterString = digit.format(depositAfter) + " $";
            String amountString = digit.format(amount) + " $";

            System.out.printf("%-30s%-30s%-11s\n", depositBeforeString, depositAfterString,
                    amountString);
            Account.scanner.nextLine();

            // Initialize new Deposit values in correct record
            for (Customer value : customers) {
                if (value.getDeposit().getDepositBalance() == depositBefore) {
                    Deposit newDeposit = new Deposit(depositAfter,
                            value.getDeposit().getInvestmentDate(),
                            value.getDeposit().getExpirationDate());
                    value.setDeposit(newDeposit);
                    break;
                }
            }
        }

        // In case customer can´t withdraw because maturity date isn´t reached yet
        else {
            System.out.println(
                    "\n================================ WITHDRAW - FAILED ================================\n");
            System.out.println(
                    "\nProgram cancelled. Withdrawals refused\nReason: Maturity date ins´t reached yet\n");
            System.exit(0);
        }

    }

    @Override
    public void payIn(List<Customer> customers) {

        digit.setMaximumFractionDigits(10);

        // Collect basic information from customer
        String[] customer = Account.customer();
        List<Deposit> myDeposits = myDeposits(customers, customer);

        // Definint useful variables
        int deposit = 0;
        Deposit newDeposit = new Deposit();
        String depositAccountNo = null, investmentDate = null, maturityDate = null,
                depositBalance = null;

        // If customer has more than one deposit Account
        if (myDeposits.size() > 1) {
            System.out.println("\n\t**** " + myDeposits.size() + " Deposit Accounts found - "
                    + customer[0] + " " + customer[1] + " ****\n");

            System.out.printf("%-30s%-30s%-30s%-25s\n", "Deposit Account", "Investment Date",
                    "Maturity Date", "Deposit Balance");

            // Display customer all his deposit Accounts
            for (int i = 0; i < myDeposits.size(); i++) {
                depositAccountNo = "Deposit " + (i + 1) + ": ";
                investmentDate = DateConverter.dateFormatter(myDeposits.get(i).getInvestmentDate());
                maturityDate = DateConverter.dateFormatter(myDeposits.get(i).getExpirationDate());
                depositBalance = digit.format(myDeposits.get(i).getDepositBalance()) + " $";

                System.out.printf("%-30s%-30s%-30s%-25s%-25s\n", depositAccountNo, investmentDate,
                        maturityDate, depositBalance, depositBalance);
            }

            // Let customer choose from which deposit Account he want´s to withdraw
            System.out.println(
                    "\nPlease choose from deposit Accounts above you want to withdraw money");
            System.out.print("Deposit Account: ");
            deposit = Account.scanner.nextInt();

            // If customer has only one deposit Account
        } else if (myDeposits.size() == 1) {
            System.out.printf("%-30s%-30s%-30s%-25s\n", "Deposit Account 1: ", "Investment Date",
                    "Maturity Date", "Deposit Balance");
            investmentDate = DateConverter.dateFormatter(myDeposits.get(0).getInvestmentDate());
            maturityDate = DateConverter.dateFormatter(myDeposits.get(0).getExpirationDate());
            depositBalance = digit.format(myDeposits.get(0).getDepositBalance()) + " $";

            deposit = 1;

            System.out.printf("%-30s%-30s%-30s%-25s\n", "Deposit 1:", investmentDate, maturityDate,
                    depositBalance);

            // If customer doens´t exist at all
        } else {
            System.out.println("\nUnfortunately there is not account which belongs to this name "
                    + customer[0] + " " + customer[1] + "\n");
            System.out.println("\nProgram cancelled. Please restart later\n");
            System.exit(0);
        }

        // Initializing / Setting variable with deposit Balance befor withrawal
        double depositBefore = myDeposits.get(deposit - 1).getDepositBalance(), depositAfter,
                amount;

        // Making amount selection more realistic
        System.out.println("\nChoose amount you want to pay in or enter manually");
        System.out.println("1 <------ 10.- $\t 100.- $ ------> 6");
        System.out.println("2 <------ 20.- $\t 200.- $ ------> 7");
        System.out.println("3 <------ 30.- $\t 300,- $ ------> 8");
        System.out.println("4 <------ 40.- $\t 400.- $ ------> 9");
        System.out.println("5 <------ 50.- $\t 500.- $ ------> 10");
        System.out.println("\n\t\t[Manually = 0]\n");

        System.out.print("Choose options above: ");
        amount = Account.scanner.nextInt();

        // If customer chooses amount from left-hand side of displayed options
        if (amount == 0) {
            System.out.print("Enter amount: ");
            amount = Account.scanner.nextDouble();
            depositAfter = depositBefore + amount;

        } else if (amount < 6) {
            amount = amount * 10;
            depositAfter = depositBefore + amount;

            // If customer chooses amount from right-hand side of displayed options
        } else if (amount > 5) {
            amount = amount * 100 - 500;
            depositAfter = depositBefore + amount;

            // If customers enters something wrong (e.g.: String instead of numerical value)
        } else {
            depositAfter = depositBefore;
            System.out.println("\nWrong Entry.");
            System.out.println(
                    "\n================================ PAY IN - FAILED ================================\n");
            System.exit(0);
        }

        System.out.println(
                "\n================================ PAY IN - SUCCESSFUL ================================\n");
        System.out.printf("%-30s%-30s%-30s\n", "Deposit Before", "Deposit After",
                "Amount payed in");
        String depositBeforeString = digit.format(depositBefore) + " $";
        String depositAfterString = digit.format(depositAfter) + " $";
        String amountString = digit.format(amount) + " $";
        System.out.printf("%-30s%-30s%-11s\n", depositBeforeString, depositAfterString,
                amountString);
        Account.scanner.nextLine();

        for (Customer c : customers) {
            if (c.getDeposit().getCheckingBalance() == depositBefore) {
                newDeposit.setDepositBalance(depositAfter);
                newDeposit.setInvestmentDate(c.getDeposit().getInvestmentDate());
                newDeposit.setInvestmentDate(c.getDeposit().getExpirationDate());
                c.setDeposit(newDeposit);
                break;
            }
        }

    }

}
