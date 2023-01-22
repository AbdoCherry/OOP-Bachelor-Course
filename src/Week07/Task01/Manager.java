package Week07.Task01;

public class Manager extends Employee {

    private String department;

    public Manager() {

    }

    public Manager(int employeeID, String ssn, String name, double salary, String department) {
        super(employeeID, ssn, name, salary);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
