package Week06.Task1;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {

    private int day, month;

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

    public static String myDate(Customer c, char controller) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");
        String tripDate = c.getBookedTrip().getTravelDate().getDay() + "." + c.getBookedTrip().getTravelDate().getMonth();
        String bookingDate = c.getBookingDate().getDay() + "." + c.getBookingDate().getMonth();

        if (controller == 'T' || controller == 't') {
            java.util.Date myDateTrip = new java.util.Date();
            myDateTrip = sdf.parse(tripDate);
            SimpleDateFormat sdfFormatter = new SimpleDateFormat("dd.MMM");
            tripDate = sdfFormatter.format(myDateTrip);
        } else if (controller == 'B' || controller == 'b') {
            java.util.Date myDateBooking = new java.util.Date();
            myDateBooking = sdf.parse(bookingDate);
            SimpleDateFormat sdfFormatter = new SimpleDateFormat("dd.MMM");
            tripDate = sdfFormatter.format(myDateBooking);
        } else {
            tripDate = null;
        }
        return tripDate;

    }

}
