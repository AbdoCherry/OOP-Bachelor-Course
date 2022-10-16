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

        Map<String, List<Employee>> allEmps = new HashMap<String, List<Employee>>();
        List<Employee> readEmployees = new ArrayList<Employee>();

        String pathMacOS = "src/Week06/Task03/Employees.csv", pathWin = "src\\Week06\\Task03\\Employees.csv", path, line;

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

                readEmployees.add(new Employee(
                        empVal[0],
                        Integer.parseInt(empVal[1]),
                        empVal[2],
                        empVal[3]
                ));
            }

            readCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        allEmps = readEmployees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));


        return allEmps;
    }

    public Set<Employee> allEmployees(Map<String, List<Employee>> assignedEmployees) {

        Set<Employee> allEmployees = new HashSet<Employee>();

        for (Map.Entry<String, List<Employee>> e : assignedEmployees.entrySet()) {
            for (Employee emp : e.getValue()) {
                allEmployees.add(new Employee
                        (emp.getEmpID(), emp.getFirstName(), emp.getLastName()));
            }
        }

        return allEmployees;
    }


    public void createEmployee() {

        Scanner createEmpScanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("Employee First Name: ");
        String firstName = createEmpScanner.nextLine();
        System.out.println("Employee Last Name: ");
        String lastName = createEmpScanner.nextLine();

        // Check if Employee is already in company


    }

    @Override
    public String toString() {
        return "Employee {" +
                " empID=" + empID +
                ", department = '" + department + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                '}';
    }
}


