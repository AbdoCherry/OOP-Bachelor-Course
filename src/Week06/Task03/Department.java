package Week06.Task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Department {
    private String department;
    private String teamLead;
    private Set<Employee> employees;
    private double budget;

    public Department() {
    }

    public Department(String department, String teamLead, Set<Employee> employees, double budget) {
        this.department = department;
        this.teamLead = teamLead;
        this.employees = employees;
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

    /*
     * Reading the csv file into our ArrayList of Department objects
     * Behold that we want to aggregate the employees into a HashSet, so we assure that no department contains duplicates
     */

    public static List<Department> readDepartments() {

        String pathWin = "src\\Week06\\Task03\\DepartmentsList.csv";
        String pathMacOS = "src/Week06/Task03/DepartmentsList.csv";
        String path = null, line = null;

        List<Department> departments = new ArrayList<Department>();
        Map<String, List<Employee>> employees = Employee.readEmpsStructured();

        if (System.getProperty("os.name").startsWith("Win")) {
            path = pathWin;
        } else if (System.getProperty("os.name").startsWith("Mac") || System.getProperty("os.name").startsWith("Darwin")) {
            path = pathMacOS;
        } else {
            System.out.println("\nError: Unknown OS " + System.getProperty("os.name") + "\nPlease restart program");
            System.exit(1);
        }

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] values = line.split(";");

                if (Boolean.parseBoolean(values[5]) && departments.stream().noneMatch(d -> d.getDepartment().equals(values[4]))) {
                    Set<Employee> myEmps = new HashSet<Employee>(employees.get(values[4]));
                    departments.add(new Department(
                            values[4],
                            values[1],
                            myEmps,
                            Double.parseDouble(values[6])
                    ));
                }
            }

            readCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(departments.size() > 0 ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                : "\n================================ IMPORT FAILED ================================\n");

        return departments;
    }

    public void createDepartment(List<Department> departments) {
        int sizeBefore = departments.size(), sizeAfter;
        Scanner scannerCreate = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary informations in the fields below\n");

        // Check if department already exists
        System.out.print("Name of Department: ");
        String newDepartment = scannerCreate.nextLine();

        boolean alreadyExists = departments.stream().noneMatch(d -> d.getDepartment().equals(newDepartment));
        if (!alreadyExists) {
            System.out.println("\nDepartment already exists.\nPlease restart program\n");
            System.exit(1);
        }

        String newTeamLead = null;
        System.out.print("Please choose teamlead from below by his employee ID");

        Employee[] emps = new Employee[5];
        char skip = 'N';
        int counter = 0;
        System.out.println("\nYou can choose up to 5 employees");

        Employee.displayAllEmps(departments);

        while (counter < emps.length && skip == 'N') {
            System.out.print("\nEmployee-ID: ");
            int id = scannerCreate.nextInt();
            for (Employee e : emps) {
                if (e != null && e.getEmpID() == id) {
                    emps[counter] = e;
                    System.out.print("\n" + e.getFullName() + " becoming teamlead of department " + newDepartment + "?\n[Yes = 'Y'/'y'] - [No = 'N'/'n']: ");
                    if (Character.toUpperCase(scannerCreate.next().charAt(0)) == 'Y') {
                        newTeamLead = e.getFullName();

                    }
                    break;
                }
                System.out.println("\nContinue picking employees\t[Yes = 'Y'/'y'] - [No = 'N'/'n']");
                skip = Character.toUpperCase(scannerCreate.next().charAt(0));
            }
            Set<Employee> newEmployees = Set.of(emps);
            System.out.print("\nBudget for new department: ");
            double newBudget = scannerCreate.nextDouble();

            if (teamLead.equals(null)) {
                Random random = new Random();
                int randIndex = random.nextInt(0, emps.length);

                teamLead = emps[randIndex].getFullName();
            }

            departments.add(new Department(
                    newDepartment,
                    teamLead,
                    newEmployees,
                    newBudget
            ));
            sizeAfter = departments.size();

            System.out.println(sizeBefore < sizeAfter ? "Department created successfully\n" : "Department not successfully created\n");

        }

    }

    public void displayAll(List<Department> departments) {


        departments.forEach(d -> {
            System.out.println("\n|====================================================================================|");
            System.out.printf("\033[1m%-10s%-25s%-25s%-25s%-10s\033[0m\n", "|", "Department", "Teamlead", "Budget $", "|");
            System.out.printf("%-10s\033[1m%-25s\033[0m%-25s%-25.2f%-10s\n", "|", d.getDepartment(), d.getTeamLead(), d.getBudget(), "|");
            System.out.println("-------------------------------------------------------------------------------------|");
            System.out.printf("\033[1m%-10s%-25s%-25s%-25s%-10s\033[0m\n", "|", "Emp-ID: ", "First Name", "Last Name", "|");
            d.getEmployees().forEach(e -> {
                System.out.printf("%-10s%-25s%-25s%-25s%-10s\n", "|", e.getEmpID(), e.getFirstName(), e.getLastName(), "|");
            });
        });
    }

}
