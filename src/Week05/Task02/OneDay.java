package Week05.Task02;

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void displayDate() {
        System.out.println("\nDay of date: " + this.day);
        System.out.println("Month of date: " + this.month);
        System.out.println("Year of date: " + this.year);
    }
}
