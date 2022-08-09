package Week06.Task03;

import java.util.Scanner;

public class Employee {

    private static final Scanner scanner = new Scanner(System.in);

    private int employeeID;
    private String lastName, firstName;

    public Employee() {

    }

    public Employee(int employeeID, String lastName, String firstName) {
        this.employeeID = employeeID;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static Employee[] assignedEmployees(Employee[] employees) {

        // User can choose up to 5 employees, not more
        Employee[] assignedEmployees = new Employee[5];

        System.out.println("\nPlease choose employees below by their ID\nDO NOT ENTER DUPLICATES!!!\n");
        int counter = 0;

        // Header for the employees list formatted as list
        System.out.printf("%-6s%-20s%-6s\n", "ID", "First Name", "Last Name");

        // Display user all employees formatted as table
        for (Employee e : employees) {
            if (e != null) {
                System.out.printf("%-6d%-20s%-6s\n", e.getEmployeeID(), e.getFirstName(), e.getLastName());
            }
        }

        char inputUser;

        // To avoid duplicate input from user we could implement a Set<Employees>. But
        // that collection is part of the further sections at Week10
        do {
            System.out.print("\nAssign employee: ");
            int assign = scanner.nextInt();

            // As long the input is within the range of the length of our employees array
            // return true

            assignedEmployees[counter] = new Employee(employees[assign - 1].getEmployeeID(),
                    employees[assign - 1].getLastName(), employees[assign - 1].getFirstName());

            counter++;

            // Ask user if he wants to add more employees
            System.out.println("\nYou want to add more employees? You´re allowed to add up to " + (5 - counter)
                    + " employees more");
            System.out.print("[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            inputUser = scanner.next().charAt(0);

        } while ((inputUser == 'Y' || inputUser == 'y') && (counter < 5));

        return assignedEmployees;

    }

}
