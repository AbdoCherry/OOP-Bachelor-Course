package Week06.Task01;

import java.util.List;
import java.util.Scanner;

public class TravelAgencyMain {
    public static void main(String[] args) {



        // Reading csv-file into ArrayList
        List<Customer> customers = Customer.readObjectsFromCSV();

        Customer c = new Customer();

        System.out.println("\n---------------------------------------------------------------- \033[1mWELCOME TO TRAVEL-AGENCY\033[0m ----------------------------------------------------------------");

        char continueProgram;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease choose menu below");
            System.out.println("[Alter Trip  = 'A'/'a']\t[Book Trip    = 'B'/'b']");
            System.out.println("[Cancel Trip = 'C'/'c']\t[Display Trip = 'D'/'d']");
            System.out.println("\t [Exit Program = 'E'/'e']");

            System.out.print("Menu: ");
            char menu = Character.toUpperCase(scanner.next().charAt(0));

            switch (menu) {
                case 'A':
                    c.editTrip(customers);
                    break;
                case 'B':
                    c.bookTrip(customers);
                    break;
                case 'C':
                    c.cancelTrip(customers);
                    break;
                case 'D':
                    c.displayByProperty(customers);
                    break;
                case 'E':
                    System.exit(0);
                default:
                    System.out.println("Unknown input. Please restart program");
            }

            System.out.println("\nRestart program?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
            scanner.nextLine();
            System.out.print("Restart: ");


            continueProgram = Character.toUpperCase(scanner.next().charAt(0));

        } while (continueProgram == 'Y');


        c.displayAll(customers);


    }
}
