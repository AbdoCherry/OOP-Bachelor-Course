package Week10.Task02.App;

import java.util.List;

import Week10.Task02.Abstract.Employee;
import Week10.Task02.Model.*;


public class HumanResources {
    public static void main(String[] args) {

        Clerk cl = new Clerk();
        Manager ma = new Manager();
        List<Clerk> clerk = cl.readCSV();
        List<Manager> manager = ma.readCSV();

        System.out.println(
                "\n---------------------------------------------------------------- WELCOME TO THE HR - MENU ----------------------------------------------------------------\n");

        char continueMenu;

        do {
            System.out.println("\033[1m\nPlease choose one option displayed below\033[0m");
            System.out.println(
                    "[Add Employee     = 1]\t[Find Employee = 3]\n[Remove Employee  = 2]\t[Display Staff = 4]");
            System.out.printf("%-15s%s\n", "", "[Quit Program = 0]");
            System.out.print("\nMenu: ");
            int menu = Employee.getScanner().nextInt();

            switch (menu) {

                case 0:
                    System.out.println("\nThanks for using the program\n");
                    System.exit(0);

                case 1:
                    System.out.println(
                            "\n----------------------------------------------------------------- ADD EMPLOYEE -----------------------------------------------------------------\n");
                    Employee.getScanner().nextLine();
                    ma.addEmployee(manager, clerk);

                    break;

                case 2:
                    System.out.println(
                            "\n---------------------------------------------------------------- REMOVE EMPLOYEE ----------------------------------------------------------------\n");
                    Employee.getScanner().nextLine();
                    ma.removeEmployee(manager, clerk);

                    break;

                case 3:
                    System.out.println(
                            "\n----------------------------------------------------------------- FIND EMPLOYEE -----------------------------------------------------------------\n");
                    Employee.getScanner().nextLine();
                    ma.findEmployee(manager, clerk);

                    break;

                case 4:
                    System.out.println(
                            "\n----------------------------------------------------------- DISPLAY EMPLOYEES IN STAFF -----------------------------------------------------------\n");
                    Employee.getScanner().nextLine();
                    ma = ma.employee(manager);
                    ma.displayStaff(clerk);
                    break;

                default:
                    System.out.println("\nWrong entry!\nPlease close or restart the program later");
            }

            System.out.print(
                    "\nDo you want to restart the menu?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            continueMenu = Employee.getScanner().next().charAt(0);
        } while (continueMenu == 'Y' || continueMenu == 'y');

        ma.displayAllStaffs(manager, clerk);

    }
}
