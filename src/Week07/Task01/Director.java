package Week07.Task1;

public class Director extends Manager {

    private double budget;

    public Director() {

    }

    public Director(int employeeID, String ssn, String name, double salary, String department, double budget) {
        super(employeeID, ssn, name, salary, department);
        this.budget = budget;
    }

    /**
     * @return double
     */
    public double getBudget() {
        return budget;
    }

    /**
     * @param budget
     */
    public void setBudget(double budget) {
        this.budget = budget;
    }

}
