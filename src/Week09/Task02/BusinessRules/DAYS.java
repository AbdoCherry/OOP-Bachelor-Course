package Week09.Task2.BusinessRules;

public enum DAYS {
    A(90), B(180);

    private final int numValues;

    DAYS(int numValues) {
        this.numValues = numValues;
    }

    public int getNumValues() {
        return numValues;
    }

}
