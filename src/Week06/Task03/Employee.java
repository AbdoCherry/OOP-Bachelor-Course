package Week06.Task03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Employee {
    private int empID;
    private String firstName, lastName;
    private String depHelper;

    public Employee() {
    }

    public Employee(int empID, String firstName, String lastName) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee(String depHelper, int empID, String firstName, String lastName) {
        this.depHelper = depHelper;
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

    public String getDepHelper() {
        return depHelper;
    }

    public void setDepHelper(String depHelper) {
        this.depHelper = depHelper;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public static Map<String, List<Employee>> readEmpsStructured() {

        String pathWin = "src\\Week06\\Task03\\DepartmentsList.csv";
        String pathMacOS = "src/Week06/Task03/DepartmentsList.csv";
        String path = null, line = null;

        List<Employee> empHolder = new ArrayList<Employee>();

        if (System.getProperty("os.name").startsWith("Win")) {
            path = pathWin;
        } else if (System.getProperty("os.name").startsWith("Mac") || System.getProperty("os.name").startsWith("Darwin")) {
            path = pathMacOS;
        } else {
            System.out.println("\nError: Unknown OS " + System.getProperty("os.name") + "\nPlease restart program");
            System.exit(1);
        }

        try {
            BufferedReader readerEmps = new BufferedReader(new FileReader(path));
            readerEmps.readLine();

            while ((line = readerEmps.readLine()) != null) {
                String[] valuesEmps = line.split(";");
                String[] nameSplitter = valuesEmps[1].split(" ");
                String fName, lName;

                switch (nameSplitter.length) {
                    case 3:
                        fName = nameSplitter[0];
                        lName = nameSplitter[1] + " " + nameSplitter[2];
                        break;
                    case 2:
                        fName = nameSplitter[0];
                        lName = nameSplitter[1];
                        break;
                    default:
                        fName = "Unknown";
                        lName = "Unknown";
                }


                empHolder.add(new Employee(
                        valuesEmps[4],
                        Integer.parseInt(valuesEmps[0]),
                        fName,
                        lName
                ));

            }

            readerEmps.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return empHolder.stream()
                .collect(groupingBy(Employee::getDepHelper));
    }

    public static Set<Employee> allEmployees(Map<String, Set<Employee>> allEmps) {

        Set<Employee> allEmployees = new HashSet<Employee>();

        for (Map.Entry<String, Set<Employee>> m : allEmps.entrySet()) {
            allEmployees.add((Employee) m.getValue());
        }
        return allEmployees;

    }

    public static void displayAllEmps(List<Department> departments) {
        System.out.printf("\n\033[1m%-25s%-25s%-25s\033[0m\n", "Employee ID", "First Name", "Last Name");
        System.out.println("---------------------------------------------------------------------------");

        departments.forEach(d -> {
            d.getEmployees().forEach(e -> {
                System.out.printf("%-25d%-25s%-25s\n", e.getEmpID(), e.getFirstName(), e.getLastName());
            });
        });
    }
}
