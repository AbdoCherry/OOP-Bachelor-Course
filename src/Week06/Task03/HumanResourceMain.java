package Week06.Task03;

import Week06.Task03.IOFile.FileIO;
import Week06.Task03.Model.Department;
import Week06.Task03.Model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanResourceMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Department> departments = FileIO.readCSV();
        Department d = new Department();


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
                case 1:
                    d.createEmployeeProcess(departments);
                    break;

                case 3:
                    d.createDepartment(departments);
                    break;
                case 0:
                    System.out.println("\nThanks for using the program\n");
                    System.exit(0);
            }

            System.out.println("\nContinue Program\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
            scanner.nextLine();
            System.out.print("Decision: ");


            continueProgram = Character.toUpperCase(scanner.nextLine().charAt(0));
        } while (continueProgram == 'Y');

        Department.displayAll(departments);
    }
}
