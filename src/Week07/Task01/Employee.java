package Week07.Task01;

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

    /**
     * @return int
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return String
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * @param ssn
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return double
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary
     */
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

    /**
     * @param salaryNew
     */
    public void increaseSalary(double salaryNew) {

        // We want to work with the monthly salary
        double salaryBefore = this.salary / 12;
        double difference = salaryNew - salaryBefore;
        double validator = difference / salaryBefore * 100;
        double exceeded = salaryNew - (salaryBefore * 1.1);

        if (validator <= 10) {
            System.out.println("\nYour requested salary increase has been approved");
            System.out.println("Salary before\tSalary after\tIncrease by %\t\tIncrease by $");
            System.out.printf("%.2f $\t%.2f $\t%.2f %%\t%.2f %%\n", salaryBefore, salaryNew, validator, difference);
        } else {
            System.out.println(
                    "\nWe have to regretfully decline your desired salary increase because it exceeds the maximum of 10 % from the current salary");
            System.out.println(
                    "Regretfully we have to decline your desired salary increase because it exceeds the limit of 10 %");
            System.out.printf("Exceeds with %.2f %% by %.2f $\n", validator, exceeded);

        }

    }

    /**
     * @param nameNew
     */
    public void editName(String nameNew) {

        String nameBefore = this.name;
        this.name = nameNew;

        System.out.println("\nName successfully changed");
        System.out.println("\nName before\t|\tName after");
        System.out.println("------------\t|\t------------");
        System.out.println(nameBefore + "\t|\t" + this.name);
        System.out.println(" ");
    }

}
