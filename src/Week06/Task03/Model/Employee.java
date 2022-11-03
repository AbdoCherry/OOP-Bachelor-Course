package Week06.Task03.Model;

import java.util.*;

public class Employee {

    private int empID;
    private String firstName, lastName;
    private String depHelper; // We use this helper to assign employees to the right department object

    public Employee() {
    }

    public Employee(int empID, String firstName, String lastName, String depHelper) {
        this.empID = empID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.depHelper = depHelper;
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

    public String getDepHelper() {
        return depHelper;
    }

    public void setDepHelper(String depHelper) {
        this.depHelper = depHelper;
    }

    public static Set<Employee> allEmployees(List<Department> departments) {
        Set<Employee> allEmployees = new HashSet<>();

        for (Department d : departments) {
            allEmployees.addAll(d.getStaff());
        }
        return allEmployees;
    }

    public static Set<Employee> randomEmployee(Set<Employee> allEmployees) {

        Set<Employee> randomEmployees = new HashSet<>();
        List<Employee> empsToList = new ArrayList<>(allEmployees);
        Random random = new Random();

        for (int i = 0; i < 11; i++) {
            int randInteger = random.nextInt(allEmployees.size());
            randomEmployees.add(empsToList.get(randInteger));
        }

        return randomEmployees;
    }

    private static Boolean checkExistence(Set<Employee> allEmps, int empID, String firstName, String lastName) {

        if (empID == 0) {
            return allEmps.stream()
                    .anyMatch(e -> e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName));
        } else {
            return allEmps.stream()
                    .anyMatch(e -> e.getEmpID() == empID);
        }
    }

    private static int maxIDincr(Set<Employee> allEmps) {

        return allEmps.stream()
                .max(Comparator.comparingInt(Employee::getEmpID))
                .get().getEmpID() + 1;
    }

    public static Employee createEmployee(List<Department> departments) {

        Scanner createEmployee = new Scanner(System.in);
        Employee newEmployee = new Employee();
        System.out.println("\nPlease enter the necessary information in the fields below\n");

        System.out.print("First Name: ");
        String firstName = createEmployee.nextLine();
        System.out.print("Last Name: ");
        String lastName = createEmployee.nextLine();

        Set<Employee> allEmployees = allEmployees(departments);

        if (!checkExistence(allEmployees, 0, firstName, lastName)) {
            newEmployee.setEmpID(maxIDincr(allEmployees));
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);

            System.out.println("\nNew Employee added succesfully in emp database");
            System.out.println("------------------------------------------------");
            System.out.println(newEmployee.toString());
        } else {
            System.out.println("\nEmployee already Exist and cant be created again");
            return null;
        }

        return newEmployee;
    }

    public void removeEmployee(List<Department> departments) {

        Scanner remEmpScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.println("Please select input variant to get employee\n[1 = By Employee - ID]\t[2 = By Name]");
        System.out.print("\nInput: ");
        int input = remEmpScanner.nextInt();

        // Display all Employees
        displayAllEmployees(departments);

        // By user input we select our employee
        switch (input) {
            case 1:
                System.out.print("\nEmployee - ID: ");
                int empID = remEmpScanner.nextInt();

                while (checkExistence(allEmployees(departments), empID, null, null)) {
                    for (Department d : departments) {
                        d.getStaff().removeIf(e -> e.getEmpID() == empID);
                    }
                    break;
                }
                break;
            case 2:
                remEmpScanner.nextLine();
                System.out.print("\nFirst Name: ");
                String firstName = remEmpScanner.nextLine();
                System.out.print("Last Name: ");
                String lastName = remEmpScanner.nextLine();

                for (Department d : departments) {
                    d.getStaff().removeIf(e -> e.getFirstName().equalsIgnoreCase(firstName) && e.getLastName().equalsIgnoreCase(lastName));
                    System.out.println("\nEmployee " + firstName + " " + lastName + " successfully removed from staff.\n");
                    break;
                }
                break;
            default:
                System.out.println("\nError input.\nPlease restart removing employee again");
                System.exit(1);
        }

    }

    public void displayAllEmployees(List<Department> departments) {
        List<Employee> allEmpsSorted = new ArrayList<>(allEmployees(departments));
        allEmpsSorted.sort(Comparator.comparing(Employee::getFirstName));
        System.out.println("|========================================================|");
        System.out.printf("\n\033[1m%-2s%-15s%-20s%-20s%-2s\033[0m\n", "|", "Employee-ID", "First Name", "Last Name", "|");
        System.out.println("|========================================================|");
        allEmpsSorted.forEach(e -> {
            System.out.printf("%-2s%-15d%-20s%-20s%-2s\n", "|", e.getEmpID(), e.getFirstName(), e.getLastName(), "|");
        });
    }


    @Override
    public String toString() {
        return "Employee { " +
                "firstName ='" + firstName + '\'' +
                ", lastName ='" + lastName + '\'' +
                ", depHelper ='" + depHelper + '\'' +
                '}';
    }
}
