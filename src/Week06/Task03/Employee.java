package Week06.Task03;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Employee {
    private int empID;
    private String firstName, lastName;

    public Employee() {
    }

    public Employee(int empID, String firstName, String lastName) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static Employee[] selectEmployees(List<Department> departments) {
        Scanner selectEmp = new Scanner(System.in);


        List<Employee> extractedEmployees = (List<Employee>) departments.stream()
                .map(Department::getEmployees).toList();
        List<Employee> selectionOfEmployees = new ArrayList<Employee>();

        System.out.printf("\n\033[1m%-15s%-20s%-20s\n\033[0m", "Employee ID", "First Name", "Last Name");
        System.out.println("-----------------------------------------------------------------------------");

        extractedEmployees.forEach(e -> {
            System.out.printf("%-15s%-20s%-20s\n",
                    e.getEmpID(),
                    e.getFirstName(),
                    e.getLastName());
        });

        char restart = 'C';
        do {
            int maxEmplSelected = 0;
            char continueSelection = 'Y';
            System.out.println("\nPlease select Employees by their ID");

            while (maxEmplSelected < 6 && continueSelection == 'Y') {
                System.out.print("ID: ");
                int select = selectEmp.nextInt();
                maxEmplSelected++;
                Employee selectionSingleEmployee = extractedEmployees.stream().filter(e -> e.getEmpID() == select).findFirst().orElse(null);
                System.out.print("\nSelected Employee: " + selectionSingleEmployee.getFirstName() + " " + selectionSingleEmployee.getLastName() + "\nYou can select " + maxEmplSelected + " more employees.");
                selectionOfEmployees.add(extractedEmployees.get(select));
                System.out.println("keep selecting employees?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
                System.out.print("Input: ");
                continueSelection = Character.toUpperCase(selectEmp.next().charAt(0));
            }

            System.out.println("You have selected those " + maxEmplSelected + " employee(s)");

            System.out.printf("\n%-15s%-20s%-20s\n", "Employee ID", "First Name", "Last Name");
            System.out.println("-----------------------------------------------------------------------------");

            selectionOfEmployees.forEach(s -> {
                System.out.printf("%-15s%-20s%-20s\n",
                        s.getEmpID(),
                        s.getLastName(),
                        s.getLastName());
            });

            System.out.println("\nYou want to continue or restart with selection?\t[Continue = \"C\"/\"c\"] - [Restart = \"R\"/\"r\"]");
            System.out.print("Input: ");
            restart = Character.toUpperCase(selectEmp.next().charAt(0));
        } while (restart == 'R');

        return (Employee[]) selectionOfEmployees.toArray();
    }


}
