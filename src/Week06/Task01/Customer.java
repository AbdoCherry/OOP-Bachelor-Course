package Week06.Task01;

import java.text.ParseException;

public class Customer {

    private String firstName, lastName;
    private Trip bookedTrip;
    private Date bookingDate;

    public Customer() {

    }

    public Customer(String firstName, String lastName, String destination, int day, int month, int dayBooked,
            int monthBooked) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookedTrip = new Trip(destination, day, month);
        this.bookingDate = new Date(dayBooked, monthBooked);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Trip getBookedTrip() {
        return bookedTrip;
    }

    public void setBookedTrip(Trip bookedTrip) {
        this.bookedTrip = bookedTrip;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void display(Customer c, String valid) throws ParseException {

        String name = c.getFirstName() + " " + c.getLastName();
        String destination = c.getBookedTrip().getDestination();
        String travelDate = Date.myDate(c, 'T');
        String bookingDate = Date.myDate(c, 'B');

        System.out.printf("%-20s%-20s%-20s%-20s%-20s\n", name, destination, travelDate, bookingDate, valid);
    }

    public static void tripValidator(Customer[] customer) throws ParseException {

        String tripIsValid = "Trip is valid";
        String tripIsNotValid = "*** Trip is not valid ***";

        System.out.printf("\n%-20s%-20s%-20s%-20s%-20s\n", "Name", "Destination", "Date of Trip", "Date of Booking",
                "Valid Trip");

        for (Customer c : customer) {
            if (c.getBookedTrip().getTravelDate().getMonth() == c.getBookingDate().getMonth()) {
                if (c.getBookedTrip().getTravelDate().getDay() - c.getBookingDate().getDay() >= 5) {
                    c.display(c, tripIsValid);
                } else {
                    c.display(c, tripIsNotValid);
                }

            } else if (c.getBookedTrip().getTravelDate().getMonth() > c.getBookingDate().getMonth()) {
                int dayCalc = 30 - c.getBookingDate().getDay() + c.getBookedTrip().getTravelDate().getDay();
                if (dayCalc >= 5) {

                    c.display(c, tripIsValid);
                } else {
                    c.display(c, tripIsNotValid);
                }
            } else {
                System.out.println("\nError\n");
            }
        }

    }

}
