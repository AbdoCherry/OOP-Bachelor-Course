package Week06.Task01;

import java.util.Scanner;

public class Trip {
    private static final Scanner scanner = new Scanner(System.in);
    private String destination;
    private Date tripDate;

    public Trip() {
    }

    public Trip(String destination, int day, int month) {
        this.destination = destination;
        this.tripDate = new Date(day, month);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }

    public static Trip createTrip() {

        Trip createTrip = new Trip();

        System.out.print("Destination: ");
        createTrip.setDestination(scanner.nextLine());

        createTrip.setTripDate(Date.createDate());

        return createTrip;
    }

    public static Trip editTrip(Customer selectedCustomer) {

        Trip oldTrip = selectedCustomer.getBookedTrip();
        Trip newTrip = new Trip();

        System.out.print("\nNew destination: ");
        String newDestination = scanner.nextLine();

        newTrip.setDestination(newDestination);
        newTrip.setTripDate(oldTrip.getTripDate());

        return newTrip;
    }
}
