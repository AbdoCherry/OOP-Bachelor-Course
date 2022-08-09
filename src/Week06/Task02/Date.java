package Week06.Task02;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("MMM");
    private int day;
    private String month;

    public Date() {

    }

    public Date(int day, String month) {
        this.day = day;
        this.month = month;
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
     * @param input
     * @return Date
     * @throws ParseException
     */
    public static Date returnDate(int input) throws ParseException {

        int day = 0;
        String month = null;
        Date returnDate = new Date();
        java.util.Date today = new java.util.Date();
        Calendar dayOfReturn = Calendar.getInstance();
        dayOfReturn.setTime(today);

        switch (input) {

            case 1:
                // Adding 2 weeks to current date
                dayOfReturn.add(Calendar.DAY_OF_MONTH, 14);
                day = dayOfReturn.get(Calendar.DAY_OF_MONTH);
                month = sdf.format(dayOfReturn.getTime());
                returnDate = new Date(day, month);

                break;

            case 2:
                // Adding 2 months to current date
                dayOfReturn.add(Calendar.MONTH, 2);
                day = dayOfReturn.get(Calendar.DAY_OF_MONTH);
                month = sdf.format(dayOfReturn.getTime());
                returnDate = new Date(day, month);

                break;

            case 3:
                // Adding 4 weeks to current date
                dayOfReturn.add(Calendar.DAY_OF_MONTH, 28);
                day = dayOfReturn.get(Calendar.DAY_OF_MONTH);
                month = sdf.format(dayOfReturn.getTime());
                returnDate = new Date(day, month);

                break;

            case 4:
                // Adding 3 months to current date
                dayOfReturn.add(Calendar.MONTH, 3);
                day = dayOfReturn.get(Calendar.DAY_OF_MONTH);
                month = sdf.format(dayOfReturn.getTime());
                returnDate = new Date(day, month);

                break;

            case 5:
                // Adding 6 weeks to current date
                dayOfReturn.add(Calendar.DAY_OF_MONTH, 42);
                day = dayOfReturn.get(Calendar.DAY_OF_MONTH);
                month = sdf.format(dayOfReturn.getTime());
                returnDate = new Date(day, month);

                break;

            case 6:
                // Adding 4 months to current date
                dayOfReturn.add(Calendar.MONTH, 4);
                day = dayOfReturn.get(Calendar.DAY_OF_MONTH);
                month = sdf.format(dayOfReturn.getTime());
                returnDate = new Date(day, month);

                break;

            default:

                returnDate = new Date(0, null);
        }
        return returnDate;
    }

}
