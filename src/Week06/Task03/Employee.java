package Week06.Task03;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public static Set<Employee> readEmployees() {

        Set<Employee> employees = new HashSet<Employee>();

        String pathMacOs = "src/Week06/Task03/DepartmentsList.csv";
        String pathWindows = "src\\Week06\\Task03\\DepartmentsList.csv";
        String path, line;
        String myOS = System.getProperty("os.name");

        switch (myOS.substring(0, 3)) {
            case "Windows":
                path = pathWindows;
                break;
            case "MacOs":
            case "Darwin":
                path = pathMacOs;
                break;
            default:
                path = null;
                System.out.println("\nError: Unknown OS: " + myOS + " Path not parseble\n");
                System.exit(1);
        }

        boolean readSuccessfully = false;

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] valuesEmployee = line.split(";");

                employees.add(new Employee(Integer.parseInt(valuesEmployee[2]), valuesEmployee[3], valuesEmployee[4]));
            }
            readSuccessfully = true;
            readCSV.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
