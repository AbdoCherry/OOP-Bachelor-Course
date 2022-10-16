package Week06.Task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    private int empID;
    private String department, firstName, lastName;

    private Employee() {
    }

    public Employee(int empID, String firstName, String lastName) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String department, int empID, String firstName, String lastName) {
        this.empID = empID;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    public static Map<String, List<Employee>> readCSV() {

        Map<String, List<Employee>> assignedEmployees = new HashMap<String, List<Employee>>();
        List<Employee> readEmployees = new ArrayList<Employee>();

        String pathMacOS = "src/Week06/Task03/Employees.csv", pathWin = "src\\Week06\\Task03\\Employees.csv", path,
                line;

        if (System.getProperty("os.name").startsWith("Mac")) {
            path = pathMacOS;
        } else if (System.getProperty("os.name").startsWith("Win")) {
            path = pathWin;
        } else {
            path = null;
        }

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] empVal = line.split(";");

                readEmployees.add(new Employee(empVal[0], Integer.parseInt(empVal[1]), empVal[2], empVal[3]));
            }

            readCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        assignedEmployees = readEmployees.stream().collect(Collectors.groupingBy(Employee::getDepartment));

        return assignedEmployees;
    }

    public static Set<Employee> allEmployees() {

        Map<String, List<Employee>> assignedEmployees = readCSV();
        Set<Employee> allEmployees = new HashSet<Employee>();

        for (Map.Entry<String, List<Employee>> e : assignedEmployees.entrySet()) {
            for (Employee emp : e.getValue()) {
                allEmployees.add(new Employee(emp.getEmpID(), emp.getFirstName(), emp.getLastName()));
            }
        }

        return allEmployees;
    }

    public boolean checkEmployee(Set<Employee> allEmployees, String firstName, String lastName) {

        // If employee exists return true, else false
        boolean exist = allEmployees.stream().anyMatch(
                e -> e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName));

        return exist;
    }

    public int createEmpID (Set<Employee> allEmployees){

        TreeSet<Employee> sortedEmployees = new TreeSet<Employee>(allEmployees);
        return (sortedEmployees.first().getEmpID() + 1);
    }

    public void createEmployee(Set<Employee> allEmployees) {

        Scanner createEmpScanner = new Scanner(System.in);
        Employee newEmployee = new Employee();

        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("Employee First Name: ");
        String firstName = createEmpScanner.nextLine();
        System.out.print("Employee Last Name: ");
        String lastName = createEmpScanner.nextLine();

        // Check if Employee is already in company

        if (!checkEmployee(allEmployees, firstName, lastName)) {
            newEmployee.setEmpID(createEmpID(allEmployees));
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);
        }else{
            System.out.println("\nEmployee already in company\n");
        }

        // Add employee to Collection of Employees
        allEmployees.add(newEmployee);

        createEmpScanner.close();

    }

    @Override
    public String toString() {
        return "Employee {" + " empID=" + empID + ", department = '" + department + '\'' + ", firstName = '" + firstName
                + '\'' + ", lastName = '" + lastName + '\'' + '}';
    }
}
