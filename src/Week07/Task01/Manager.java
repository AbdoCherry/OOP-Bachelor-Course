package Week07.Task1;

public class Manager extends Employee {

    private String department;

    public Manager() {

    }

    public Manager(int employeeID, String ssn, String name, double salary, String department) {
        super(employeeID, ssn, name, salary);
        this.department = department;
    }

    /**
     * @return String
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

}
