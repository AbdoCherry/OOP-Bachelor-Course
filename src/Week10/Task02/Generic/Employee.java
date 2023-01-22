package Week10.Task02.Generic;


import Week10.Task02.Model.Clerk;
import Week10.Task02.Model.Manager;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.*;

public abstract class Employee<T> {

    public Scanner scannerEmp = new Scanner(System.in);

    private int empId;
    private String firstName, lastName, ssn;
    private double salary;


    public Employee() {
    }

    public Employee(int empId, String firstName, String lastName, String ssn, double salary) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.salary = salary;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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

    // We do need in almost every function the possibility of entering the full name
    public String[] fullName() {
        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("First Name: ");
        String firstName = scannerEmp.nextLine();
        System.out.print("Last Name: ");
        String lastName = scannerEmp.nextLine();
        scannerEmp.nextLine();
        return new String[]{firstName, lastName};

    }

    // Almost every function requires an object / element of our employees lists
    public T selecObject(@NotNull List<? extends Employee> t, String[] fullName) {
        return (T) t.stream()
                .filter(e -> e.getFirstName().equalsIgnoreCase(fullName[0]) && e.getLastName().equalsIgnoreCase(fullName[1]))
                .findFirst()
                .orElse(null);
    }

    // Create employee id according to list of employees
    public int maxIdIncr(@NotNull List<? extends Employee<T>> t) {
        return t.stream()
                .max(Comparator.comparingInt(Employee<T>::getEmpId)).get().getEmpId() + 1;
    }

    // Create random ssn which does not exist already
    public String generateSsn(@NotNull List<? extends Employee<T>> t) {

        StringBuilder sbSsn = new StringBuilder();
        Random random = new Random();

        do {
            for (int i = 0; i < 3; i++) {
                sbSsn.append(random.nextInt(999));
            }
            sbSsn.deleteCharAt(sbSsn.length() - 1);

        } while (t.stream().anyMatch(e -> e.getSsn().contentEquals(sbSsn)));

        return sbSsn.toString();
    }

    // Create salary under certain condition
    public double generateSalary(@NotNull List<? extends Employee<T>> t) {

        double maxRaise;
        double averageSalary;
        double newSalary = 0;
        boolean declined = false;

        // Determine which list we are dealing with
        if (t.get(0) instanceof Manager) {
            averageSalary = t.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            maxRaise = 0.25;
        } else if (t.get(0) instanceof Clerk) {
            averageSalary = t.stream().mapToDouble(Employee::getSalary).average().orElse(0);
            maxRaise = 0.5;
        } else {
            System.out.println("\nType not identified\n");
            averageSalary = 0;
            maxRaise = 0;
            System.exit(1);
        }

        do {
            System.out.printf("\nPlease enter the desired amount of yearly gross compensation.\nBehold that you cannot exceed the average salary of %.2f $ by %.1f %%\n", averageSalary, (maxRaise * 100));
            System.out.print("Salary: ");
            newSalary = scannerEmp.nextDouble();
            declined = false;

            if ((newSalary - averageSalary) / averageSalary * 100 > (maxRaise * 100)) {

                System.out.printf("\nWe are sorry to decline your request of salary. It exceeds the limit of %.2f%% by %.2f%%\n",
                        maxRaise, (newSalary - averageSalary) / averageSalary * 100);
                System.out.println("You are allowed to enter again");
                declined = true;
            }

        } while (declined);

        return newSalary;
    }

    // Generic method to create object of type generic 'Manager' / 'Clerk'
    public T addEmp(@NotNull List<? extends Employee> t) {

        Scanner createScanner = new Scanner(System.in);

        Employee<T> emp = null;
        // Check type of Employee
        String employeeType = null;
        if (t.get(0) instanceof Manager) {
            employeeType = "Manager";
            emp = (Employee<T>) new Manager();
        } else if (t.get(0) instanceof Clerk) {
            employeeType = "Clerk";
            emp = (Employee<T>) new Clerk();

        } else {
            System.out.println("\nError: Employee type not known\n");
            System.exit(1);
        }

        // Check if employee exists or not
        boolean exists = true;
        String firstName, lastName;
        do {
            String[] fullName = fullName();
            T obj = selecObject(t, fullName);

            if (obj != null) {
                System.out.println("\n" + employeeType + " already exists\n");
                System.out.println("Please retry by typing 'R' or type 'X' to exit program");
                System.out.print("Retry / Exit: ");
                char input = Character.toUpperCase(createScanner.next().charAt(0));
                exists = true;
                if (input == 'X') {
                    System.out.println("\nThanks for using the program\n");
                    System.exit(1);
                }
            }

            exists = false;
            firstName = fullName[0];
            lastName = fullName[1];

        } while (exists);

        // Create generic object and pass to class type (Manager or Clerk)
        emp.setEmpId(maxIdIncr((List<? extends Employee<T>>) t));
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setSsn(generateSsn((List<? extends Employee<T>>) t));
        emp.setSalary(generateSalary((List<? extends Employee<T>>) t));

        return (T) emp; // Return generic object
    }

    // Remove object of given type
    public void removeEmp(List<T> t) {
        String[] fullName = fullName();
        T obj = selecObject((List<? extends Employee>) t, fullName);
        boolean exists = !obj.equals(null);

        if (exists) {
            System.out.println("\nError. Employee does not exist");
            System.exit(1);
        }
        t.remove(obj);
    }

    // Find explicit employee of givent type
    public void findEmployee(List<T> t) {
        T obj = selecObject((List<? extends Employee>) t, fullName());
        if (obj != null) {
            System.out.println(obj.toString());
        } else {
            System.out.println("\nEmployee not in staff\n");
        }
    }

    // Display all elements of given list
    public void displayAll(@NotNull List<T> t) {
        List<Manager> managers;
        List<Clerk> clerks;
        if (t.get(0) instanceof Manager) {
            managers = (List<Manager>) t;
            // int empId, String firstName, String lastName, String ssn, double salary, String depName, double depBudget, Set<Clerk> staffSize
            System.out.printf("\n%-5s%-10s%-30s%-10s%-10s%-40s%-10s%-10s%-5s\n", "|", "ID", "Name", "SSN", "Salary", "Department", "Budget", "Staff-Size", "|");
            System.out.println("|===========================================================================================================================|");
            managers.forEach(m -> System.out.printf("%-5s%-10d%-30s%-10s%-10.2f%-40s%-10.2f%-10s%-5s\n",
                    "|", m.getEmpId(), m.getFirstName() + " " + m.getLastName(), m.getSsn(), m.getSalary(), m.getDepName(), m.getDepBudget(), m.getStaffSize().size(), "|"));
        } else if (t.get(0) instanceof Clerk) {
            clerks = (List<Clerk>) t;
            // int empId, String firstName, String lastName, String ssn, double salary, int manId
            System.out.printf("\n%-5s%-10s%-30s%-10s%-10s%-10s%-5s\n", "|", "ID", "Name", "SSN", "Salary", "Manager-ID", "|");
            System.out.println("|===============================================================|");
            clerks.forEach(c -> System.out.printf("%-5s%-10d%-30s%-10s%-10.2f%-10d%-5s\n",
                    "|", c.getEmpId(), c.getFirstName() + " " + c.getLastName(), c.getSsn(), c.getSalary(), c.getManId(), "|"));
        }
    }

    // Update and refresh manager list
    public void updateLists(@NotNull List<Manager> managers, List<Clerk> clerks) {

        for (Manager m : managers) {
            Set<Clerk> clerkStaff = new HashSet<>();
            for (Clerk c : clerks) {
                if (m.getEmpId() == c.getManId()) {
                    clerkStaff.add(c);
                }
                m.setStaffSize(clerkStaff);
            }
        }
        System.out.println("\nStaffsize updated");
        System.out.printf("%-5s%-10s%-30s%-20s%-5s\n", "|", "ID", "Manager", "Size of Staff", "|");
        System.out.println("|===============================================================|");
        managers.forEach(m -> System.out.printf("%-5s%-10d%-30s%-20d%-5s\n", "|", m.getEmpId(), m.getFirstName() + " " + m.getLastName(), m.getStaffSize().size(), "|"));
    }

    // Reading csv file int arraylist
    public abstract List<T> readCSV();

    // Writing arraylist to into csv file
    public abstract void writeCsv(List<T> t);

    // Simple toString method for output
    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("#.##");
        String empType = null;
        if (this instanceof Manager) {
            empType = "Manager";
        } else if (this instanceof Clerk) {
            empType = "Clerk";
        } else {
            return "Error. Type of employee not known";
        }
        return "'" + empType + "' = " +
                " empId=" + empId +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", salary = " + df.format(salary) + " $ ";
    }
}
