package Week07.Task03;

public class Director extends Manager {

    private double budget;

    public Director() {

    }

    public Director(int employeeID, String ssn, String name, double salary, String department, double budget) {
        super(employeeID, ssn, name, salary, department);
        this.budget = budget;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void displayEmployees() {
        super.displayEmployees();
        System.out.printf("Budget: %.2f $\n", this.budget);
    }

}
