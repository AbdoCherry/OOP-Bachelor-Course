package Week06.Task03;

import java.util.List;
import java.util.Scanner;

public class Department {
    private String depName;
    private String lead;
    private Employee[] employees;
    private double budget;

    public Department() {
    }

    public Department(String depName, String lead, Employee[] employees, double budget) {
        this.depName = depName;
        this.lead = lead;
        this.employees = employees;
        this.budget = budget;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
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

    public void createDepartment(List<Department> departments) {
        Scanner createDepScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("Name of department: ");
        String createDep = createDepScanner.nextLine();

        Employee[] selectedEmployee = Employee.selectEmployees(departments);
        int selectLead;

        System.out.println("\nPlease select team lead from the selected employees by ID");

        System.out.printf("\n\033[1m%-15s%-20s%-20s\033[0m\n", "Employee ID", "First Name", "Last Name");
        System.out.println("-----------------------------------------------------------------------------");
        for (Employee e : selectedEmployee) {
            System.out.printf("%-15s%-20s%-20s\n",
                    e.getEmpID(),
                    e.getLastName(),
                    e.getLastName());
        }
        System.out.print("ID: ");
        selectLead = createDepScanner.nextInt();
        String fullName = null;

        for (Employee e : selectedEmployee) {
            if (e.getEmpID() == selectLead) {
                fullName = e.getFirstName() + " " + e.getLastName();
            }
        }

        System.out.println("Budget: ");
        double createBudget = createDepScanner.nextDouble();

        departments.add(new Department(createDep, fullName, selectedEmployee, createBudget));

        System.out.println("\nCreation of new department successfully\n");
    }

    public void display(List<Department> departments) {
        System.out.printf("\n%-15s%-20s%-20s%-20s%-20s%-20s\n", "Employee ID", "First Name", "Last Name", "Department", "Budget", "Team Lead");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");

        for (Department d : departments) {
            for (Employee e : d.getEmployees()) {
                System.out.printf("%-15s%-20s%-20s%-20s%-20s%-20s\n",
                        e.getEmpID(),
                        e.getFirstName(),
                        e.getLastName(),
                        d.getDepName(),
                        d.getBudget(),
                        d.getLead());
            }
        }
    }
}
