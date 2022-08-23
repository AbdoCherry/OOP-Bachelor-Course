package Week06.Task03;

import java.io.*;
import java.util.*;

public class Department {
    private String depName;
    private String teamLead;
    private Set<Employee> employees;
    private double budget;

    public Department() {
    }

    public Department(String depName, String teamLead, Set<Employee> employees, double budget) {
        this.depName = depName;
        this.teamLead = teamLead;
        this.employees = employees;
        this.budget = budget;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getTeamLead() {
        return teamLead;
    }

    public void setTeamLead(String teamLead) {
        this.teamLead = teamLead;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public static List<Department> readDepartments() {

        List<Department> departments = new ArrayList<Department>();

        String pathMacOs = "src/Week06/Task03/Departments.csv";
        String pathWindows = "src\\Week06\\Task03\\Departments.csv";
        String path, line;
        String myOS = System.getProperty("os.name");

        if (myOS.startsWith("Win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            System.out.println("\nError: Unknown OS: " + myOS + "\nPlease restart program");
            path = null;
            System.exit(1);
        }

        boolean readSuccessfully = false;

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] valuesDepartment = line.split(";");



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(
                readSuccessfully ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return departments;
    }

    public void createDepartment(List<Department> departments, Set<Employee> employees) {
        Scanner createDepScanner = new Scanner(System.in);
        Boolean depDuplicate = true;
        System.out.println("\nPlease enter the necessary information in the fields below");

        Department newDepartment = new Department();

        System.out.print("Name of Department: ");
        newDepartment.setDepName(createDepScanner.nextLine());

        // Check if department already exists
        depDuplicate = departments.stream()
                .allMatch(d -> d.getDepName().equals(newDepartment.getDepName()));

        while (depDuplicate) {
            char edit = 'Y';
            System.out.println("\nDepartment already exists.\nDo you want to change edit department name?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
            System.out.print("Input: ");
            edit = Character.toUpperCase(createDepScanner.next().charAt(0));

            if (edit == 'Y') {
                System.out.print("Name of Department: ");
                newDepartment.setDepName(createDepScanner.nextLine());
            } else if (edit == 'N') {
                System.out.println("\nCreation of department cancelled. Please restart program\n");
                System.exit(1);
            }

            // Refresh boolean value
            depDuplicate = departments.stream()
                    .allMatch(d -> d.getDepName().equals(newDepartment.getDepName()));
        }

        // Select team lead from employees list
        System.out.println("Please select Team Lead by Employee ID from list below");
        System.out.printf("\n%-35s%-15s%-25s%-25s%-25s%-20s\n", "Department", "Employee ID", "First Name", "Last Name", "Budget", "Team Lead");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        departments.forEach(d -> {
            String teamRole = d.getTeamLead().equals(null) ? "Staff Member" : "Team Lead";
            System.out.printf("%-65s%-25s%-20s\n", d.getDepName(), d.getBudget(), teamRole);

            d.getEmployees().forEach(e -> {
                System.out.printf("%-35s%-15d%-25s%-25s\n", " ", e.getEmpID(), e.getFirstName(), e.getLastName());
            });
        });

        System.out.print("Team Lead: ");
        int createEmpID = createDepScanner.nextInt();

        Iterator<Employee> emp = employees.iterator();

        while (emp.hasNext()) {
            if (emp.next().getEmpID() == createEmpID) {
                newDepartment.setTeamLead(emp.next().getFirstName() + " " + emp.next().getLastName());
                break;
            }
        }

        // Selecting maximum 5 staff member
        System.out.print("Please select staff member by their no from refresheed list below,\nyou are allowed to select up to 5 members");


        System.out.printf("\n%-35s%-15s%-25s%-25s%-25s%-20s\n", "Department", "Employee ID", "First Name", "Last Name", "Budget", "Team Lead");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        departments.forEach(d -> {
            String teamRole = d.getTeamLead().equals(null) ? "Staff Member" : "Team Lead";
            System.out.printf("%-65s%-25s%-20s\n", d.getDepName(), d.getBudget(), teamRole);

            d.getEmployees().forEach(e -> {
                System.out.printf("%-35s%-15d%-25s%-25s\n", " ", e.getEmpID(), e.getFirstName(), e.getLastName());
            });
        });

        Set<Employee> selectEmployees = new HashSet<Employee>();

        int maxMember = 0;
        char continueSelection = 'Y';
        while (maxMember < 6 && continueSelection == 'Y') {

            System.out.print("ID: ");
            int selection = createDepScanner.nextInt();

            Iterator<Employee> empSelection = employees.iterator();
            while (empSelection.hasNext()) {
                if (empSelection.next().getEmpID() == selection) {
                    selectEmployees.add(empSelection.next());
                    maxMember++;

                    System.out.print("Add Employee?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]");
                    continueSelection = Character.toUpperCase(createDepScanner.next().charAt(0));
                }
            }
            newDepartment.setEmployees(selectEmployees);

            System.out.print("Budget: ");
            newDepartment.setBudget(createDepScanner.nextDouble());

            departments.add(newDepartment);

        }
    }

    public void displayAll(List<Department> departments) {

        System.out.printf("\n%-35s%-15s%-25s%-25s%-25s%-20s\n", "Department", "Employee ID", "First Name", "Last Name", "Budget", "Team Lead");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        departments.forEach(d -> {
            String teamRole = d.getTeamLead().equals(null) ? "Staff Member" : "Team Lead";
            System.out.printf("%-65s%-25s%-20s\n", d.getDepName(), d.getBudget(), teamRole);

            d.getEmployees().forEach(e -> {
                System.out.printf("%-35s%-15d%-25s%-25s\n", " ", e.getEmpID(), e.getFirstName(), e.getLastName());
            });
        });
    }


}
