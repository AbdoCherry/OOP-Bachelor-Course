package Week06.Task01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Date {
    private int day, month;

    static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
    static SimpleDateFormat parser = new SimpleDateFormat("dd.MMM");

    public Date() {

    }

    public Date(int day, int month) {
        this.day = day;
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public static Date createToday() {

        LocalDate createCurrentDate = LocalDate.now();
        int dayOfMonth = createCurrentDate.getDayOfMonth();
        int monthValue = createCurrentDate.getMonthValue();

        return new Date(dayOfMonth, monthValue);
    }

    public static Date createDate() {

        Scanner scanner = new Scanner(System.in);

        Date createDate = new Date();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("\nPlease beware of our Policy\nTrip must be at least 5 days in future of current date: " + dtf.format(LocalDateTime.now()) + "\n");

        System.out.print("Day of trip -> Format {dd}: ");
        createDate.setDay(scanner.nextInt());

        System.out.print("Month of trip -> Format {MM}: ");
        createDate.setMonth(scanner.nextInt());

        return createDate;
    }

    // Not mandatory for the task. Just to format our date to a more beautiful format
    public static String dateFormatter(Customer c, char controller) {

        String formattedDate = "";


        java.util.Date parseDate;

        // The try-catch block is necessary for the java.util.Date api
        try {
            switch (controller) {
                case 'T':
                    String getTripDate = c.getBookedTrip().getTripDate().getDay() + "." + c.getBookedTrip().getTripDate().getMonth();
                    parseDate = sdf.parse(getTripDate);
                    formattedDate = parser.format(parseDate);
                    break;
                case 'B':
                    String getBookingDate = c.getBookedDate().getDay() + "." + c.getBookedDate().getMonth();
                    parseDate = sdf.parse(getBookingDate);
                    formattedDate = parser.format(parseDate);
                    break;
                default:
                    formattedDate = " - ";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static boolean tripValidator(Customer c) {

        java.util.Date dateOfTripDate;
        java.util.Date dateOfBookedTrip;

        boolean validator = false;
        int daysBetween = 0;

        String dateOfTripString = c.getBookedTrip().getTripDate().getDay() + "." + c.getBookedTrip().getTripDate().getMonth();
        String dateOfBookedTripString = c.getBookedDate().getDay() + "." + c.getBookedDate().getMonth();

        try {
            dateOfTripDate = sdf.parse(dateOfTripString);
            dateOfBookedTrip = sdf.parse(dateOfBookedTripString);

            Long timeBetween = dateOfTripDate.getTime() - dateOfBookedTrip.getTime();
            daysBetween = (int) (timeBetween / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();

        }

        if (daysBetween >= 5) {
            validator = true;
        }

        return validator;
    }
}
