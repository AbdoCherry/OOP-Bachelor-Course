package Week10.Task2.Abstract;

import java.util.List;
import java.util.Scanner;

public abstract class Employee {

    private static Scanner scanner = new Scanner(System.in);
    private int employeeID;
    private String name, ssn, depName;
    private double salary;

    public Employee() {

    }

    public Employee(int employeeID, String name, String ssn, double salary, String depName) {
        this.employeeID = employeeID;
        this.name = name;
        this.ssn = ssn;
        this.salary = salary;
        this.depName = depName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        Employee.scanner = scanner;
    }

    public String pathImport() {

        String pathWin = "src\\Week10\\Task2\\Data\\input.csv";
        String pathMacOS = "src/Week10/Task2/Data/input.csv";
        String path = "";
        String myOS = System.getProperty("os.name");

        if (myOS.startsWith("Win")) {
            path = pathWin;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOS;
        } else {
            path = null;
        }

        return path;
    }

    public abstract <T extends Employee> T employee(List<? extends Employee> list);

    public abstract List<?> readCSV();

}
