package Week06.Task03.Model;

import java.text.DecimalFormat;
import java.util.*;

public class Department {
    private String department;
    private String teamLead;
    private Set<Employee> staff;
    private double budget;

    private static DecimalFormat df = new DecimalFormat("#.##");

    public Department() {
    }

    public Department(String department, String teamLead, Set<Employee> staff, double budget) {
        this.department = department;
        this.teamLead = teamLead;
        this.staff = staff;
        this.budget = budget;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public Set<Employee> getStaff() {
        return staff;
    }

    public void setStaff(Set<Employee> staff) {
        this.staff = staff;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    private Boolean checkExistence(List<Department> departments, String department) {

        return departments.stream()
                .anyMatch(d -> d.getDepartment().equalsIgnoreCase(department));
    }

    private String selectDepartment(List<Department> departments) {

        Scanner selectDepartment = new Scanner(System.in);

        System.out.println("\nPlease select department below by ID");
        int counter = 1;

        System.out.println("\n|===============================|");
        System.out.printf("\033[1m%-2s%-5s%-25s%-2s\033[0m\n", "|", "ID", "Department", "|");
        System.out.println("|===============================|");
        for (Department d : departments) {
            System.out.printf("%-2s%-5d%-25s%-2s\n", "|", counter, d.getDepartment(), "|");
            counter++;
        }

        System.out.print("\nSelect: ");
        int selection = selectDepartment.nextInt() - 1;

        return departments.get(selection).getDepartment();

    }

    public void assignEmployeeToDepartment(List<Department> departments) {

        Employee newEmployee = Employee.createEmployee(departments);

        System.out.println("\nFor newly created employee " + newEmployee.getFirstName() + " " + newEmployee.getLastName() + " select department");

        newEmployee.setDepHelper(selectDepartment(departments));
        for (Department d : departments) {
            if (d.getDepartment().equals(newEmployee.getDepHelper())) {
                d.staff.add(newEmployee);
                System.out.println("\nEmployee added to staff of manager: " + d.getTeamLead() + " successfully\n");
                break;
            }
        }
    }

    public void createDepartment(List<Department> departments) {

        Scanner createDepartment = new Scanner(System.in);
        Department newDepartment = new Department();
        int sizeBefore = departments.size();
        System.out.println("\nPlease enter the necessary information in the fields below\n");

        int counter = 0;
        while (counter < 3) {
            System.out.print("Name of Department: ");
            String department = createDepartment.nextLine();

            if (!checkExistence(departments, department)) {
                newDepartment.setDepartment(department);
                counter = 3;
            } else {
                System.out.println("\nDepartment " + department + " already exists\nPlease select enter another department name\n");
                counter++;
            }
        }

        // Select a teamlead
        System.out.println("\nPlease select employee by ID");
        Set<Employee> selectTeamlead = Employee.randomEmployee(Employee.allEmployees(departments));

        System.out.printf("\033[1m%-2s%-12s%-20s%-20s%-2s\033[0m\n", "|", "Emp-ID", "First Name", "Last Name", "|");
        System.out.println("|-----------------------------------------------------|");
        selectTeamlead.forEach(e -> {
            System.out.printf("%-2s%-12s%-20s%-20s%-2s\n", "|", e.getEmpID(), e.getFirstName(), e.getLastName(), "|");
        });
        System.out.print("\nTeamlead: ");
        int teamlead = createDepartment.nextInt();

        Employee teamLead = selectTeamlead.stream()
                .filter(e -> e.getEmpID() == teamlead).findFirst().orElseThrow(null);

        newDepartment.setTeamLead(teamLead.getFirstName() + " " + teamLead.getLastName());

        // Select 5 distinct employees as staff
        Set<Employee> allEmployees = Employee.allEmployees(departments);
        Employee[] selectStaff = new Employee[5];
        char stopSelection = 'Y';

        System.out.printf("\033[1m%-2s%-12s%-20s%-20s%-2s\033[0m\n", "|", "Emp-ID", "First Name", "Last Name", "|");
        System.out.println("|-----------------------------------------------------|");

        while (stopSelection == 'Y' && selectStaff[4] == null) {
            allEmployees.forEach(e -> {
                System.out.printf("%-2s%-12s%-20s%-20s%-2s\n", "|", e.getEmpID(), e.getFirstName(), e.getLastName(), "|");
            });

            int freeSpace = 0;
            System.out.print("\nEmployee ID: ");
            int staffMemberSelect = createDepartment.nextInt();

            for (int i = 0; i < selectStaff.length; i++) {
                if (selectStaff[i] == null) {
                    selectStaff[i] = allEmployees.stream()
                            .filter(e -> e.getEmpID() == staffMemberSelect).findFirst().get();
                    allEmployees.remove(selectStaff[i]);
                    freeSpace++;
                    break;
                }


            }

            System.out.println("Continue Selection. You can add up to " + (5 - freeSpace) + " members for staff\n");
            createDepartment.nextLine();
            System.out.print("Input 'Y' / 'N': ");
            stopSelection = Character.toUpperCase(createDepartment.nextLine().charAt(0));
        }

        // Assign array to staff
        Set<Employee> myStaff = new HashSet<>();
        for (Employee emp : selectStaff) {
            if (emp != null) {

                myStaff.add(emp);
            }
        }
        newDepartment.setStaff(myStaff);

        // Select budget for department
        System.out.println("\nPlease enter amount of budget");
        System.out.print("Budget: ");
        newDepartment.setBudget(createDepartment.nextDouble());

        departments.add(newDepartment);

        System.out.println(sizeBefore < departments.size() ? "\nDepartment created successfully\n" : "\nDepartment not created successfully");
    }

    public void removeDepartment(List<Department> departments) {
        Scanner remDepScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below");

        String depName = selectDepartment(departments);
        departments.removeIf(d -> d.getDepartment().equalsIgnoreCase(depName));

        System.out.println("\nDepartment removed successfully\n");
    }


    public static void displayAll(List<Department> departments) {

        // \033[1m //\033[0m

        departments.forEach(d -> {
            System.out.println("\n|============================================================================|");
            System.out.printf("\033[1m%-2s%-25s%-25s%-25s%-2s\033[0m\n", "|", "Department", "Teamlead", "Budget", "|");
            String formattedBudget = df.format(d.getBudget()) + " $";
            System.out.printf("%-2s%-25s%-25s%-25s%-2s\n", "|", d.getDepartment(), d.getTeamLead(), formattedBudget, "|");
            System.out.println("|============================================================================|");
            System.out.printf("\033[1m%-2s%-25s%-25s%-25s%-2s\033[0m\n", "|", "Employee - ID", "First Name", "Last Name", "|");
            System.out.println("|----------------------------------------------------------------------------|");
            d.getStaff().forEach(e -> {
                System.out.printf("%-2s%-25d%-25s%-25s%-2s\n", "|", e.getEmpID(), e.getFirstName(), e.getLastName(), "|");
            });
            System.out.println("|----------------------------------------------------------------------------|");
        });
    }
}