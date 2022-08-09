package Week08.Task01.App;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import Week08.Task01.Auxilary.Reader;
import Week08.Task01.Models.*;
import Week08.Task01.Auxilary.Writer;

public class CustomerReport {

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);

        // Reading csv into our list
        List<Account> accounts = Reader.readCSV();

        System.out.println(
                "\n---------------------------------------------------------------- Banking App ----------------------------------------------------------------\n");

        char inputContinue;

        do {

            System.out.println("\nPlease choose one of the following options\n");
            System.out.printf("%-20s\t%-20s\n%-20s\t%-20s\n%-20s\t%-20s\n%30s", "[Add Account    = 1]",
                    "[Withdraw Money     = 4]",
                    "[Remove Account = 2]", "[Pay In Money       = 5]", "[Get Info       = 3]",
                    "[Show tot. Balance  = 6]",
                    "[Cancel and Exit = 0]");
            System.out.print("\nMenu: ");
            int menu = scanner.nextInt();

            switch (menu) {

                case 0:

                    // Closing or stopping the program, so user won´t get asked if he want´s to
                    // restart again
                    System.out.println("\nThanks for using the menu\n");
                    Writer.writerCSV(accounts);
                    System.exit(0);

                    break;

                case 1:

                    System.out.println(
                            "\n---------------------------------------------------------------- Add Account ----------------------------------------------------------------\n");
                    Customer.addAccount(accounts);
                    break;

                case 2:
                    System.out.println(
                            "\n--------------------------------------------------------------- Remove Account ----------------------------------------------------------------\n");
                    Customer.removeAccount(accounts);

                    break;

                case 3:
                    System.out.println(
                            "\n-------------------------------------------------------------- Get Account-Info --------------------------------------------------------------\n");
                    Customer.getAccount(accounts);

                    break;

                case 4:
                    System.out.println(
                            "\n--------------------------------------------------------------- Withdraw Money ---------------------------------------------------------------\n");
                    Deposit.withdrawMoney(accounts);

                    break;

                case 5:
                    System.out.println(
                            "\n--------------------------------------------------------------- Pay in Money ---------------------------------------------------------------\n");
                    Deposit.depositMoney(accounts);

                    break;

                case 6:
                    System.out.println(
                            "\n------------------------------------------------------------- Show total Balance -------------------------------------------------------------\n");
                    Deposit.showTotalBalance(accounts);

                    break;

                default:

                    System.out.println("\nWrong entry. Please close and / or start program later\n");
            }

            System.out.println("\nDo you want to restart the program\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
            System.out.print("Continue: ");
            inputContinue = scanner.next().charAt(0);
        } while (inputContinue == 'Y' || inputContinue == 'y');

        System.out.println(
                "\n------------------------------------------------------ Display all Customers and their deposits ------------------------------------------------------\n");
        Account.displayAllDeposits(accounts);

        // Writer.writerCSV(accounts);

        scanner.close();
    }

}
