package Week06.Task3;

import java.util.Scanner;

public class Department {

    private final Scanner scanner = new Scanner(System.in);
    private String departmentName, lead;
    private Employee[] employees;
    private double budget;

    public Department() {

    }

    public Department(String departmentName, String lead, double budget) {
        this.departmentName = departmentName;
        this.lead = lead;
        this.budget = budget;
    }

    public Department(String departmentName, String lead, Employee[] employees, double budget) {
        this.departmentName = departmentName;
        this.lead = lead;
        this.employees = employees;
        this.budget = budget;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLead() {
        return lead;
    }

    public void setLead(String lead) {
        this.lead = lead;
    }

    public Employee[] getEmployees() {
        return employees;
    }

    public void setEmployees(Employee[] employees) {
        this.employees = employees;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public static boolean validator(Department[] departments, String departmentName) {
        boolean valid = false;

        for (Department d : departments) {
            if (d != null) {
                if (d.getDepartmentName() == departmentName) {
                    valid = false;
                    break;
                } else {
                    valid = true;
                }
            }
        }

        return valid;
    }

    public void addDepartment(Department[] departments, Employee[] employees) {

        Department newDepartment = new Department();
        Employee[] assignedEmployees = new Employee[5];
        String lead = null;
        String departmentName = null;

        System.out.println("\nPlease enter the necessary informations in the fields below");
        System.out.print("Name of department: ");
        departmentName = scanner.nextLine();

        if (Department.validator(departments, departmentName)) {
            System.out.println("\nPlease choose one employee below for the lead position\n");
            System.out.printf("%-6s%-20s%-6s\n", "ID", "First Name", "Last Name");
            for (Employee e : employees) {
                if (e != null) {
                    System.out.printf("%-6d%-20s%-6s\n", e.getEmployeeID(), e.getFirstName(), e.getLastName());
                }
            }
            System.out.print("\nEmployee name or ID: ");
            String input = scanner.nextLine();

            while (lead == null) {
                for (Employee e : employees) {
                    if (e != null) {
                        if (e.getEmployeeID() == Integer.parseInt(input)) {
                            lead = e.getFirstName() + " " + e.getLastName();
                            System.out.println("\nEmployee " + e.getFirstName() + " " + e.getLastName()
                                    + " successfully assigned with lead position");
                            break;
                        } else if (e.getFirstName().startsWith(input) || e.getLastName().startsWith(input)) {
                            lead = e.getFirstName() + " " + e.getLastName();
                            System.out.println("\nEmployee " + e.getFirstName() + " " + e.getLastName()
                                    + " successfully assigned with lead position");
                            break;
                        }
                    }
                }

            }

            System.out.print(
                    "\nYou want to assign employees to this department?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            char inputUser = scanner.next().charAt(0);

            if (inputUser == 'Y' || inputUser == 'y') {
                assignedEmployees = Employee.assignedEmployees(employees);
            } else {
                assignedEmployees = null;
            }
            System.out.println("\nPlease enter a budget you want to assign to new department");
            System.out.print("Budget: ");
            double budget = scanner.nextDouble();

            newDepartment = new Department(departmentName, lead, assignedEmployees, budget);
            if (newDepartment != null) {
                System.out.println("\nNew department was added successfully to system\n");
                System.out.println("Department Name: " + newDepartment.getDepartmentName());
                System.out.println("Lead of Dep.: " + newDepartment.getLead());
                if (newDepartment.getEmployees() != null) {
                    System.out.println("\nAssigned Employees\n");
                    System.out.printf("%-6s%-20s%-6s\n", "ID", "First Name", "Last Name");
                    for (int i = 0; i < newDepartment.getEmployees().length; i++) {
                        if (newDepartment.getEmployees()[i] != null) {
                            System.out.printf("%-6d%-20s%-6s\n", newDepartment.getEmployees()[i].getEmployeeID(),
                                    newDepartment.getEmployees()[i].getFirstName(),
                                    newDepartment.getEmployees()[i].getLastName());
                        }
                    }
                }
                System.out.println("\nBudget: " + newDepartment.getBudget() + " $");
            }

        } else {
            System.out
                    .println("\nUnfortunately the department: " + departmentName + " exists already or entry is empty");

        }

        // Adding the created department to our array at the first empty position
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] == null) {
                departments[i] = newDepartment;
                break;
            }
        }
    }

    public static void displayDepartments(Department[] departments) {

        System.out.println("\nAll departments with assigned employees");

        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null) {
                System.out.println("\nDepartment: " + departments[i].getDepartmentName());
                System.out.println("Name of lead: " + departments[i].getLead());
                if (departments[i].getEmployees()[0].getFirstName() != null) {
                    System.out
                            .println("\nAssigned Employees to Departmen " + departments[i].getDepartmentName() + "\n");
                    System.out.printf("%-6s%-20s%-6s\n", "ID", "First Name", "Last Name");
                    for (int j = 0; j < departments[i].getEmployees().length; j++) {
                        if (departments[i].getEmployees()[j] != null)
                            System.out.printf("%-6d%-20s%-6s\n", departments[i].getEmployees()[j].getEmployeeID(),
                                    departments[i].getEmployees()[j].getFirstName(),
                                    departments[i].getEmployees()[j].getLastName());
                    }

                }
                System.out.printf("\nBudget: %.2f $\n", departments[i].getBudget());
                System.out.println("------------------------------------------");
            }

        }

    }
}
