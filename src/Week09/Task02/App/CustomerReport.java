package Week09.Task2.App;

import java.util.List;
import Week09.Task2.BusinessRules.Reader;
import Week09.Task2.Model.*;

public class CustomerReport {
    public static void main(String[] args) {

        List<Deposit> deposits = Reader.readCSV();

        System.out.println(
                "\n---------------------------------------------------------------- Deposit Menu ----------------------------------------------------------------\n");

        char continueProgram;

        do {

            System.out.println("\nPlease choose one of the following options below");
            System.out.println("[Account  = 1]\t[Information  = 2]\n[Deposit  = 3]\t[Exit Program = 0]");

            System.out.print("\nOption: ");
            int menuOption = Customer.getScanner().nextInt();

            switch (menuOption) {

                case 0:

                    System.out.println("\nThanks for using the program\n");
                    System.exit(0);

                    break;

                case 1:

                    System.out.println(
                            "\n-------------------------------------------------------------------- Account Menu --------------------------------------------------------------------\n");

                    System.out.println("\n[Add Account = 1]\t[Remove Account = 2]");
                    System.out.print("Account option: ");
                    int accountOption = Customer.getScanner().nextInt();

                    switch (accountOption) {

                        case 1:
                            System.out.println(
                                    "\n-------------------------------------------------------------------- Create Account --------------------------------------------------------------------\n");
                            Customer.addAccount(deposits);

                            break;

                        case 2:

                            System.out.println(
                                    "\n-------------------------------------------------------------------- Remove Account --------------------------------------------------------------------\n");
                            Customer.removeAccount(deposits);

                            break;

                        default:
                            System.out.println("\nWrong entry. Please close or restart program later\n");
                    }
                    break;

                case 2:

                    System.out.println(
                            "\n----------------------------------------------------------------- Get Account Information -----------------------------------------------------------------\n");
                    Deposit.getDescription(deposits);
                    break;

                case 3:
                    System.out.println(
                            "\n---------------------------------------------------------------------- Deposit Account ----------------------------------------------------------------------\n");

                    System.out.println("\n[Withdraw Money = 1]\t[Pay in Money = 2]");
                    System.out.print("Checking option: ");

                    int depositOption = Customer.getScanner().nextInt();
                    switch (depositOption) {

                        case 1:
                            System.out.println(
                                    "\n------------------------------------------------------------------ Withdraw Money from Deposit ----------------------------------------------------------------------\n");

                            Deposit.withDraw(deposits);
                            break;

                        case 2:
                            System.out.println(
                                    "\n------------------------------------------------------------------ Pay in Money from Deposit ----------------------------------------------------------------------\n");

                            Deposit.payIn(deposits);
                            break;

                        default:

                            System.out.println("\nWrong entry. Please close or restart program later\n");
                    }

                    break;

                default:

                    System.out.println("\nWrong entry. Please close or restart program later\n");
            }

            System.out.println("\nDo you want to restart the program?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            continueProgram = Customer.getScanner().next().charAt(0);
        } while (continueProgram == 'Y' || continueProgram == 'y');

        Deposit.displayAll(deposits);
        Deposit.writeCSV(deposits);

    }

}
