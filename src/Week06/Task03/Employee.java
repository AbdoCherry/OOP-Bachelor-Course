package Week06.Task03;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Employee {

    private int empID;
    private String firstName, lastName;

    /* To have a mapping support for the reading process late
     * we do need this private string property.
     * Because we want to group our HashMap later by department and map it into several set
     */
    private String department;

    public Employee() {
    }

    // For the reading process
    public Employee(String department, int empID, String firstName, String lastName) {
        this.department = department;
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public static Map<String, Set<Employee>> readEmployees() {

        List<Employee> readEmps = new ArrayList<Employee>();
        Map<String, Set<Employee>> employees = new HashMap<String, Set<Employee>>();

        // Let assure that both OS can use the csv file in same directory
        String pathMacOs = "src/Week06/Task03/Departments.csv";
        String pathWindows = "src\\Week06\\Task03\\Departments.csv";
        String path, line;
        String myOS = System.getProperty("os.name");

        int lineNumber = 0;

        if (myOS.startsWith("Win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            System.out.println("\nError: Unknown OS: " + myOS + "\nPlease restart program");
            path = null;
            System.exit(1);
        }

        // Checking if the read process was successfully
        boolean readSuccessfully = false;

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] employeeReader = line.split(";");

                if (!Boolean.parseBoolean(employeeReader[5])) {
                    String[] nameSplitter = employeeReader[1].split(" ");
                    String castFirstName = null;
                    String castLastName = null;

                    switch (nameSplitter.length) {
                        case 3:
                            castFirstName = nameSplitter[0];
                            castLastName = nameSplitter[1] + " " + nameSplitter[2];
                            break;
                        case 2:
                            castFirstName = nameSplitter[0];
                            castLastName = nameSplitter[1];
                            break;
                        default:
                            System.out.println("Invalid Name at line: " + lineNumber + " and Employee ID: " + employeeReader[0]);
                    }

                    readEmps.add(
                            new Employee(employeeReader[4],
                                    Integer.parseInt(employeeReader[0]),
                                    castFirstName,
                                    castLastName));
                }
            }


            employees = readEmps.stream()
                    .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.toSet()));


            readCSV.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        readSuccessfully = employees.size() > 0;

        System.out.println(
                readSuccessfully ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return employees;
    }

    public int incrMaxID(Set<Employee> employees) {

        int maxID = employees.stream()
                .max(Comparator.comparingInt(Employee::getEmpID))
                .orElse(null).getEmpID();
        maxID++;

        return maxID;
    }

    public void addEmployee(Set<Employee> employees) {

        Scanner createScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below");

        Employee newEmployee = new Employee();

        newEmployee.setEmpID(incrMaxID(employees));

        System.out.print("First Name: ");
        newEmployee.setFirstName(createScanner.nextLine());

        System.out.print("Last Name: ");
        newEmployee.setLastName(createScanner.nextLine());

        int sizeBefore = employees.size();
        employees.add(newEmployee);
        int sizeAfter = employees.size();

        String success = sizeBefore < sizeAfter ? "New employee added successfully" : "Error in addition of new employee";
        System.out.println(newEmployee.toString() + "\n");
    }

    public void removeEmployee(List<Department> departments) {

        Scanner removeScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("First Name: ");
        String firstName = removeScanner.nextLine();

        System.out.print("Last Name: ");
        String lastName = removeScanner.nextLine();

        System.out.printf("\033[1m%-15s%-25s%-25s\033[0m\n", "Emp ID", "First Name", "Last Name");
        System.out.println("------------------------------------------------------------------------------------------------");

        for (Department department : departments) {
            Iterator<Employee> emp = department.getEmployees().iterator();

            char confirmDecision = 'Y';
            while (emp.hasNext()) {
                if (emp.next().getFirstName().equals(firstName) && emp.next().getLastName().equals(lastName)) {
                    System.out.printf("%-15d%-25s%-25s\n", emp.next().getEmpID(), emp.next().getFirstName(), emp.next().getLastName());
                    System.out.println("\nAre you sure?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\n\"]");
                    System.out.print("Input: ");
                    confirmDecision = Character.toUpperCase(removeScanner.next().charAt(0));

                    if (confirmDecision == 'Y') {
                        emp.remove();
                        System.out.println("\nEmployee removed from Emp Database\n");
                        break;
                    }
                } else if (emp.next().getFirstName().contains(firstName) && emp.next().getLastName().contains(lastName)) {
                    System.out.printf("%-15d%-25s%-25s\n", emp.next().getEmpID(), emp.next().getFirstName(), emp.next().getLastName());
                    confirmDecision = 'N';
                    System.out.println("\nAre you sure?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\n\"]");
                    System.out.print("Input: ");
                    confirmDecision = Character.toUpperCase(removeScanner.next().charAt(0));

                    if (confirmDecision == 'Y') {
                        emp.remove();
                        System.out.println("\nEmployee removed from Emp Database\n");
                        break;
                    }
                }
            }
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

    @Override
    public String toString() {
        return "Employee {" +
                "empID = " + empID +
                ", firstName ='" + firstName + '\'' +
                ", lastName ='" + lastName + '\'' +
                '}';
    }
}
