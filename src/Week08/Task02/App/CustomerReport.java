package Week08.Task02.App;

import java.util.List;

import Week08.Task02.Abstract.Account;
import Week08.Task02.Auxilary.InputOutputFiles;
import Week08.Task02.Model.*;


public class CustomerReport {
    public static void main(String[] args) {

        Checking checking = new Checking();
        Deposit deposit = new Deposit();
        Customer customer = new Customer();
        List<Customer> customers = InputOutputFiles.readCSV();

        System.out.println(
                "\n---------------------------------------------------------------- Welcome - Banking Menu ----------------------------------------------------------------\n");

        char continueProgram;

        do {

            System.out.println("\nPlease choose one of the following options below");
            System.out.println("[Account     = 1]\t[Checking = 3]\n[Information = 2]\t[Deposit  = 4]");
            System.out.println("\t\t[Exit Program = 0]");

            System.out.print("\nOption: ");
            int menuOption = Account.scanner.nextInt();

            switch (menuOption) {

                case 0:

                    System.out.println("\nThanks for using the Program\n");

                    break;

                case 1:

                    System.out.println(
                            "\n-------------------------------------------------------------------- Account Menu --------------------------------------------------------------------\n");

                    System.out.println("\n[Add Account = 1]\t[Remove Account = 2]");
                    System.out.print("Account option: ");
                    int accountOption = Account.scanner.nextInt();

                    switch (accountOption) {

                        case 1:
                            System.out.println(
                                    "\n-------------------------------------------------------------------- Create Account --------------------------------------------------------------------\n");

                            customer.addCustomer(customers);

                            break;

                        case 2:
                            System.out.println(
                                    "\n-------------------------------------------------------------------- Remove Account --------------------------------------------------------------------\n");

                            customer.removeAccount(customers);

                            break;

                        default:

                            System.out.println("\nWrong entry. Please close or restart program later\n");
                    }

                    break;

                case 2:
                    System.out.println(
                            "\n----------------------------------------------------------------- Get Account Information -----------------------------------------------------------------\n");

                    customer.getCustomer(customers);

                    break;

                case 3:
                    System.out.println(
                            "\n--------------------------------------------------------------------- Checking Account ---------------------------------------------------------------------\n");

                    System.out.println("\n[Withdraw Money = 1]\t[Pay in Money = 2]");
                    System.out.print("Checking option: ");
                    int checkingOption = Account.scanner.nextInt();

                    switch (checkingOption) {

                        case 1:
                            System.out.println(
                                    "\n---------------------------------------------------------------- Withdraw from Checking ----------------------------------------------------------------\n");

                            checking.withdraw(customers);

                            break;

                        case 2:
                            System.out.println(
                                    "\n---------------------------------------------------------------- Payin in Checking ----------------------------------------------------------------\n");

                            checking.payIn(customers);

                            break;

                        default:

                            System.out.println("\nWrong entry. Please close or restart program later\n");

                    }

                    break;

                case 4:

                    System.out.println(
                            "\n---------------------------------------------------------------------- Deposit Account ----------------------------------------------------------------------\n");

                    System.out.println("\n[Withdraw Money = 1]\t[Pay in Money = 2]");
                    System.out.print("Checking option: ");
                    int depositOption = Account.scanner.nextInt();

                    switch (depositOption) {

                        case 1:
                            System.out.println(
                                    "\n-------------------------------------------------------------- Withdraw from Deposit --------------------------------------------------------------\n");

                            deposit.withdraw(customers);

                            break;

                        case 2:
                            System.out.println(
                                    "\n----------------------------------------------------------------- Payin in Deposit -----------------------------------------------------------------\n");

                            deposit.payIn(customers);

                            break;

                        default:

                            System.out.println("\nWrong entry. Please close or restart program later\n");
                    }
                    break;

                default:

                    System.out.println("\nWrong entry. Please close or restart program later\n");
            }

            System.out.print("\nDo you want to restart program?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            continueProgram = Account.scanner.next().charAt(0);
        } while (continueProgram == 'Y' || continueProgram == 'y');

        System.out
                .println(
                        "\n========================================================== DISPLAY ALL CUSTOMERS IN BANK ==========================================================\n");
        customer.displayAll(customers);
        //InputOutputFiles.writeCSV(customers);

    }

}
