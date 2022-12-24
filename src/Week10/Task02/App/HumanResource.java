package Week10.Task02.App;

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

        Scanner scanner = new Scanner(System.in);
        char menu, continueProgram;

        System.out.println(
                "\n\033[1m========================================= WELCOME - HUMAN RESOURCE =========================================\033[0m");

        do {
            System.out.println("\nPlease choose from menu below");
            System.out.println("----------------------------------------------------------");
            System.out.println("[Add Employee      = 'A'/'a']\t[Remove Employee = 'R'/'r']");
            System.out.println("[Request Promotion = 'P'/'p']\t[Find Employee   = 'F'/'f']");
            System.out.println("\t\t[Quit Program = 'Q'/'q']");

            System.out.print("Input: ");
            menu = Character.toUpperCase(scanner.next().charAt(0));

            switch (menu) {
                case 'A':
                    c.addClerk(clerks, managers, Manager.departments(managers));
                    break;

                case 'R':
                    c.removeClerk(clerks, managers);
                    break;

                case 'P':
                    c.requestPromotion(clerks, managers);
                    break;

                case 'F':
                    System.out.println("\nDo you want to search within the managers or clerks?");
                    System.out.println("[Managers = 'M'/'m']\t[Clerks = 'C'/'c']");
                    System.out.print("Search condition: ");

                    switch (Character.toUpperCase(scanner.next().charAt(0))) {
                        case 'M':
                            Manager manager = m.singleObject(managers);
                            manager.findEmp();
                            break;
                        case 'C':
                            Clerk clerk = c.singleObject(clerks);
                            clerk.findEmp();
                            break;
                        default:
                            System.out.println("\nError. Wrong entry\n");
                    }
                    break;

                case 'Q':
                    System.out.println("\nThanks for using the program\nDo you want to save changes?");
                    System.out.println("[Yes = 'Y'/'y']\t[No = 'N'/'n']");
                    System.out.print("Save: ");

                    if (Character.toUpperCase(scanner.next().charAt(0)) == 'Y') {

                        c.writeCSV(clerks);
                        m.writeCSV(managers);
                        break;
                    } else if (Character.toUpperCase(scanner.next().charAt(0)) == 'N') {
                        System.out.println("\nProgram is closed without saving changes");
                        System.exit(1);
                    } else {
                        System.out.println("Error. Wrong entry\n");
                    }
            }

            System.out.println("\nContinue/Restart program?\t[Yes = 'Y'/'y'] - [No = 'N'/'n']");
            System.out.print("Input: ");
            continueProgram = Character.toUpperCase(scanner.next().charAt(0));
        } while (continueProgram == 'Y');

        System.out.println("======================== DISPLAY ALL EMPLOYEES ========================");
        c.displayAll(clerks);
        m.displayAll(managers);

        scanner.close();
    }

}
