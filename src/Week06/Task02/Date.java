package Week06.Task02;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Date {

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
    private static SimpleDateFormat parser = new SimpleDateFormat("dd.MMM");
    private int day;
    private String month;

    public Date() {
    }

    public Date(int day, String month) {
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public static Date createEmptyDate() {

        return new Date(0, null);
    }

    public static Date createReturnDate() {

        Scanner borrowScanner = new Scanner(System.in);

        System.out.println("\nPlease choose your desired period to borrow the book");
        System.out.println("[1 = 1 Week]\t[5 = 5 Weeks]");
        System.out.println("[2 = 2 Week]\t[6 = 6 Weeks]");
        System.out.println("[3 = 3 Week]\t[7 = 7 Weeks]");
        System.out.println("[4 = 4 Week]\t[8 = 8 Weeks]");

        System.out.print("\nWeeks to borror: ");
        int weeks = borrowScanner.nextInt();

        LocalDate returnAt = LocalDate.now().plusWeeks(weeks);

        return new Date(returnAt.getDayOfMonth(), String.valueOf(returnAt.getMonthValue()));

    }

    public static String formatDate(Date returnDate) {
        String formatted = "";

        if (returnDate.getDay() == 0) {
            formatted = "  -  ";
        } else {

            try {
                java.util.Date parseDate = sdf.parse(String.valueOf(returnDate.getDay()) + "." + returnDate.getMonth());
                formatted = parser.format(parseDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return formatted;
    }
}
