package Week06.Task3;

import java.util.Arrays;
import java.util.Scanner;

public class HumanResources {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Employee[] employees = new Employee[20];

        employees[0] = new Employee(1, "Yerbouti", "Sheikh");
        employees[1] = new Employee(2, "Dover", "Ben");
        employees[2] = new Employee(3, "Shaddap", "Yakkant");
        employees[3] = new Employee(4, "InmasHol", "Ramit");
        employees[4] = new Employee(5, "Hawk", "Mike");
        employees[5] = new Employee(6, "Litoris", "Mike");
        employees[6] = new Employee(7, "Cyder", "Dickins");
        employees[7] = new Employee(8, "Tickles", "Tess");
        employees[8] = new Employee(9, "Mux", "Yi-Long");
        employees[9] = new Employee(10, "Zib", "Akbar");
        employees[10] = new Employee(11, "Nmakrak", "Maposi");
        employees[11] = new Employee(12, "Tador", "Conqueef");
        employees[12] = new Employee(13, "Dek", "Sukma");
        employees[13] = new Employee(14, "Bich", "Phuc dat");
        employees[14] = new Employee(15, "Sempa", "Pastor Martin");
        employees[15] = new Employee(16, "Buubsgo", "Juerdo");

        Department department = new Department();
        Department[] departments = new Department[6];

        departments[0] = new Department("Management", employees[0].getFirstName() + " " + employees[0].getLastName(),
                Arrays.copyOfRange(employees, 0, 4), 2000000.00);
        departments[1] = new Department("Compliance & Regulatory",
                employees[9].getFirstName() + " " + employees[9].getLastName(), Arrays.copyOfRange(employees, 4, 9),
                1000000.00);
        departments[2] = new Department("Sales & Marketing",
                employees[12].getFirstName() + " " + employees[12].getLastName(), Arrays.copyOfRange(employees, 12, 15),
                450000);
        departments[3] = new Department("Human Resource",
                employees[3].getFirstName() + " " + employees[3].getLastName(), Arrays.copyOfRange(employees, 0, 3),
                150000);

        System.out.println(
                "\n---------------------------------------------------------------- Add Department ----------------------------------------------------------------\n");

        department.addDepartment(departments, employees);

        System.out
                .println(
                        "\n------------------------------------------------------------- Display all Department -------------------------------------------------------------\n");
        Department.displayDepartments(departments);

        System.out.println(" ");

        scanner.close();
    }

}
