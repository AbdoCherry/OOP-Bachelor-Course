package Week06.Task03;

import Week06.Task03.IOFile.FileIO;
import Week06.Task03.Model.Department;
import Week06.Task03.Model.Employee;

import java.util.List;
import java.util.Scanner;

public class HumanResourceMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Department> departments = FileIO.readCSV();
        Department d = new Department();
        Employee e = new Employee();


        int menu;
        char continueProgram = 'Y';

        System.out.println("=============================== Welcome to the Human Resource Program ===============================");

        do {
            System.out.println("Please select from menu");
            System.out.println("[1  =   Add Employee]\t  [3 = Create Department]");
            System.out.println("[2 = Remove Employee]\t  [4 = Remove Department]");
            System.out.println("\t\t\t  [0 = Exit Program]");
            System.out.print("\nMenu: ");


            menu = scanner.nextInt();


            switch (menu) {
                case 0:
                    System.out.println("\nThanks for using the program\n");
                    System.exit(0);
                    break;
                case 1:
                    d.assignEmployeeToDepartment(departments);
                    break;
                case 2:
                    e.removeEmployee(departments);
                    break;
                case 3:
                    d.createDepartment(departments);
                    break;
                case 4:
                    d.removeDepartment(departments);
                    break;

                default:
                    System.out.println("\nError input.\nPlease restart program, input could not be recognized by program.\n");
                    System.exit(1);
            }

            System.out.println("\nContinue Program\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
            scanner.nextLine();
            System.out.print("Decision: ");

            continueProgram = Character.toUpperCase(scanner.nextLine().charAt(0));
        } while (continueProgram == 'Y');

        // Display whole dataset -> All department and their attribute values
        Department.displayAll(departments);

        System.out.print("\nDo you want to save edited data\n[Yes = 'Y'/'y']\t[No =  'N'/'n']: ");
        if (Character.toUpperCase(scanner.next().charAt(0)) == 'Y') {
            FileIO.writeCSV(departments);
        } else {
            System.out.println("\nThanks for using the program\n");
        }
    }
}
