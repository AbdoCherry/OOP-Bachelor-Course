package Week10.Task02.Abstract;

import java.util.List;
import java.util.Scanner;

public abstract class Employee<T> {

    private static Scanner scannerEmp = new Scanner(System.in);

    private int empID;
    private String firstName, lastName, department;
    private double salary;

    public Employee() {
    }

    public Employee(int empID, String firstName, String lastName, double salary, String department) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public abstract List<? extends T> readCSV();

    public abstract void writeCSV(List<T> t);

    public abstract T singleObject(List<T> list);

    public abstract void displayAll(List<T> list);

    public static String[] name() {

        String[] fullName = new String[2];

        System.out.println("\nPlease enter the necessary informations in the fields below");

        System.out.print("First Name: ");
        fullName[0] = scannerEmp.nextLine();
        System.out.print("Last Name: ");
        fullName[1] = scannerEmp.nextLine();

        return fullName;
    }

    public void findEmp() {

        if (this.getEmpID() != 0) {
            System.out.println("\n=========== EMPLOYEE FOUND ===========");
            System.out.println("Employee-ID: " + this.empID);
            System.out.println("Name: " + this.firstName + " " + this.lastName);
            System.out.println("Salary " + Math.round(this.salary) + " $");
            System.out.println("Department: " + this.department);
        } else {
            System.out.println("\nEmployee not in company\n");
        }
    }

}

