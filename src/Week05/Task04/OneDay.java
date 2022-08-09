package Week05.Task04;

public class OneDay {

    private int day, year;
    private String month;

    public OneDay() {

    }

    public OneDay(int day, String month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * @return int
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return int
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return String
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return boolean
     */
    public boolean monthValidator() {
        boolean valid = false;

        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };

        for (String element : months) {
            if (this.month.equals(element)) {
                valid = true;
                break;
            }
        }

        return valid;
    }

    /**
     * @param validator
     */
    public void displayDate(Boolean validator) {
        System.out.println("\nDay of date: " + this.day);
        if (validator) {
            System.out.println("Month of date: " + this.month);
        } else {
            System.out.println("Wrong month format");
        }
        System.out.println("Year of date: " + this.year);
    }

}
