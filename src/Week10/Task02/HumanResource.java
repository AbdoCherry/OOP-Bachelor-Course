package Week10.Task02;

import Week10.Task02.Model.Clerk;
import Week10.Task02.Model.Manager;

import java.util.List;
import java.util.Scanner;

public class HumanResource {
    public static void main(String[] args) {

        Clerk c = new Clerk();
        Manager m = new Manager();
        List<Clerk> clerks = c.readCSV();
        List<Manager> managers = m.readCSV();
        m.updateLists(managers, clerks);

        System.out.println("\n========================== WELCOME TO HR PROGRAM ==========================");

        char continueProgram;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nPlease select from menu below");
            System.out.println("[Manager Menu : 'M'/'m']\t[Clerk Menu : 'C'/'c']");
            System.out.println("\t\t\t[Exit : 'X'/'x']");
            System.out.print("Menu: ");
            char menu = Character.toUpperCase(scanner.next().charAt(0));

            switch (menu) {
                case 'M':
                    Scanner scannerManager = new Scanner(System.in);
                    System.out.println("\nPlease select from submenu below");
                    System.out.println("[Add Manager : 'A'/'a']\t[Remove Manager      : 'R'/'r']");
                    System.out.println("[Find Manager: 'F'/'f']\t[Display all Managers: 'D'/'d']");
                    System.out.println("\t\t\t[Exit: 'X'/'x']");
                    System.out.print("Manager Menu: ");

                    char managerMenu = Character.toUpperCase(scannerManager.next().charAt(0));

                    switch (managerMenu) {
                        case 'A':
                            m.addManager(managers, clerks);
                            break;

                        case 'R':
                            m.removeManager(managers, clerks);
                            break;

                        case 'F':
                            m.findManager(managers);
                            break;

                        case 'D':
                            m.displayAll(managers);
                            break;

                        case 'X':
                            System.out.println("\nThank you for using the program");
                            scannerManager.close();
                            scanner.close();
                            System.exit(0);

                        default:
                            System.out.println("\nError. Input is invalid\n");
                            System.exit(1);

                            scannerManager.close();
                    }

                    break;

                case 'C':
                    Scanner scannerClerk = new Scanner(System.in);
                    System.out.println("\nPlease select from submenu below");
                    System.out.println("[Add Clerk : 'A'/'a']\t[Remove Clerk      : 'R'/'r']");
                    System.out.println("[Find Clerk: 'F'/'f']\t[Display all Clerk: 'D'/'d']");
                    System.out.println("\t\t\t[Exit: 'X'/'x']");
                    System.out.print("Clerk Menu: ");

                    char clerkMenu = Character.toUpperCase(scannerClerk.next().charAt(0));

                    switch (clerkMenu) {
                        case 'A':
                            c.addClerk(clerks, managers);
                            break;

                        case 'R':
                            c.removeClerk(clerks, managers);
                            break;

                        case 'F':
                            c.findClerk(clerks);
                            break;

                        case 'D':
                            c.displayAll(clerks);
                            break;

                        case 'X':
                            System.out.println("\nThank you for using the program\n");
                            scannerClerk.close();
                            scanner.close();
                            System.exit(0);

                        default:
                            System.out.println("\nError. Input is invalid\n");
                            scannerClerk.close();
                            System.exit(1);

                    }

                    break;

                case 'X':
                    System.out.println("\nThank you for using the program\n");
                    System.exit(0);

                default:
                    System.out.println("\nError. Input is invalid\n");
                    scanner.close();
                    System.exit(1);
            }


            System.out.println("\nContinue Program?\n[Yes = 'Y'/'y']\t[No = 'N'/'n']");
            System.out.print("Contiue: ");
            continueProgram = Character.toUpperCase(scanner.next().charAt(0));

        } while (continueProgram == 'Y');
        System.out.println("\nThank you for using the program\n");

        c.writeCsv(clerks);
        m.writeCsv(managers);
    }

}
