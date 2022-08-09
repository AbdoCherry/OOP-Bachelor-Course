package Week08.Task1.Auxilary;

public class UnderCutDeposit extends Exception {

    public UnderCutDeposit(double amount) {
        super("\nYou have undercut the amount by " + (100 - amount) + " $\nPlease enter amount again: ");
    }

    public UnderCutDeposit(String message) {
        super(message);
    }

}
