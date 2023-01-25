package Week07.Task03;

public class Employee {

    private int employeeID;
    private String ssn, name;
    private double salary;

    public Employee() {
    }

    public Employee(int employeeID, String ssn, String name, double salary) {
        this.employeeID = employeeID;
        this.ssn = ssn;
        this.name = name;
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void displayEmployees() {

        System.out.println("\n--------------------------------------");

        System.out.println("\nEmployee ID: " + this.employeeID);
        System.out.println("Employee SSN: " + this.ssn);
        System.out.println("Employee Name: " + this.name);
        System.out.printf("Employee Salary: %.2f $\n", this.salary);
    }

    public void increaseSalary(double salaryNew) {

        // We want to work with the yearly salary
        double salaryBefore = this.salary;
        double difference = salaryNew - salaryBefore;
        double validator = difference / salaryBefore * 100;
        double exceeded = salaryNew - (salaryBefore * 1.1);

        if (validator <= 10) {
            System.out.println("\nYour requested salary increase has been approved");
            System.out.printf("%-15s%-15s%-15s%-15s\n", "Salary Before", "Salary after", "Increased by %", "Increased by $");
            System.out.printf("%-15.2f%-15.2f%-15.2f%-15.2f\n", salaryBefore, salaryNew, validator, difference);
            this.setSalary(salaryNew);
        } else {
            System.out.println(
                    "\nWe have to regretfully decline your desired salary increase because it exceeds the maximum of 10 % from the current salary");
            System.out.println(
                    "Regretfully we have to decline your desired salary increase because it exceeds the limit of 10 %");
            System.out.printf("Exceeds with %.2f %% by %.2f $\n", validator, exceeded);

        }
    }

    public void editName(String nameNew) {

        String nameBefore = this.name;
        this.name = nameNew;

        System.out.println("\nName successfully changed");
        System.out.println("\nName before\t|\tName after");
        System.out.println("------------\t|\t------------");
        System.out.println(nameBefore + "\t|\t" + this.name);
        System.out.println(" ");
    }

    public static void stockOptions(Employee[] employees) {

        System.out.println("\n\n****** Stock options per Employee ******");

        System.out.printf("\n%-6s%-20s%-6s\n", "ID", "Employee", "Stock Options");
        System.out.println("----------------------------------------------------");
        for (Employee e : employees) {
            if (e != null) {
                if (e instanceof Admin | e instanceof Admin) {
                    System.out.printf("%-6d%-20s%-6s\n", e.getEmployeeID(), e.getName(), "10");
                } else if (e instanceof Manager) {
                    System.out.printf("%-6d%-20s%-6s\n", e.getEmployeeID(), e.getName(), "100");
                } else if (e instanceof Director) {
                    System.out.printf("%-6d%-20s%-6s\n", e.getEmployeeID(), e.getName(), "1000");
                }
            }
        }
    }
}
