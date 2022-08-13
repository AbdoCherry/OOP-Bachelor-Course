package Week06.Task01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {

    public Scanner scanner = new Scanner(System.in);

    private String firstName, lastName;
    private Trip bookedTrip;
    private Date bookedDate;

    public Customer() {
    }

    public Customer(String firstName, String lastName, Trip bookedTrip, Date bookedDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookedTrip = bookedTrip;
        this.bookedDate = bookedDate;
    }

    public Customer(String firstName, String lastName, String destination, int tripDay, int tripMonth, int bookingDay, int bookingMonth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookedTrip = new Trip(destination, tripDay, tripMonth);
        this.bookedDate = new Date(bookingDay, bookingMonth);
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

    public Date getBookedDate() {
        return bookedDate;
    }

    public void setBookedDate(Date bookedDate) {
        this.bookedDate = bookedDate;
    }

    public static List<Customer> readObjectsFromCSV() {

        List<Customer> customers = new ArrayList<Customer>();

        String pathMacOs = "src/Week06/Task01/TripAgencyDB.csv", pathWindows = "src\\Week06\\Task01\\TripAgencyDB.csv", path;
        String myOS = System.getProperty("os.name");
        String line = null;

        if (myOS.startsWith("win")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            path = null;
            System.out.println("\nError: Unknown OS: " + myOS + "\n");
        }

        // Read process
        boolean readSuccess = false;

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] values = line.split(";");
                customers.add(new Customer(
                        values[0], // First Name
                        values[1], // Last Name
                        values[2], // Destination
                        Integer.parseInt(values[3]), // Day of Trip
                        Integer.parseInt(values[4]), // Month of Trip
                        Integer.parseInt(values[5]), // Day of Booking
                        Integer.parseInt(values[6]))); // Month of Booking
            }
            readSuccess = customers.size() > 0;
            readCSV.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(
                readSuccess ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return customers;
    }

    /*
    We want to return only the selected trips of one queried customer
     */
    public List<Customer> selectCustomers(List<Customer> customers) {

        List<Customer> selectedCustomers = new ArrayList<Customer>();

        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("\nFirst Name: ");
        String searchFirstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String searchLastName = scanner.nextLine();

        for (Customer c : customers) {
            if (c.getFirstName().equals(searchFirstName) && c.getFirstName().equals(searchLastName)) {
                selectedCustomers.add(c);
            }
        }

        return selectedCustomers;
    }

    public int returnselectedCustomer(List<Customer> customers) {
        List<Customer> searchedCustomer = selectCustomers(customers);

        int decision = 0;
        boolean checkIfError = true;

        if (searchedCustomer.size() > 0) {

            System.out.printf("\n\033[1m%-10s%-20s%-35s%-25s%-25s\033[0m\n", "ID", "Name", "Destination", "Date of Trip", "Date of Booking");
            System.out.println("------------------------------------------------------------------------------------------------------------------------");

            for (int i = 0; i < searchedCustomer.size(); i++) {
                String formattedTripDate = Date.dateFormatter(searchedCustomer.get(i), 'T');
                String formattedBookingDate = Date.dateFormatter(searchedCustomer.get(i), 'B');
                System.out.printf("\n%-10d%-20s%-35s%-25s%-25s\n",
                        (i + 1),
                        searchedCustomer.get(i).getFirstName() + " " + searchedCustomer.get(i).getLastName(),
                        searchedCustomer.get(i).getBookedTrip().getDestination(),
                        formattedTripDate,
                        formattedBookingDate);
            }

            System.out.println("\nPlease choose trip by ID");
            System.out.print("Decision: ");
            decision = scanner.nextInt();
            decision--;

        } else if (searchedCustomer.size() == 0) {
            System.out.println("\nNo Trip found under. Please restart program");
            checkIfError = false;

        } else {
            System.out.println("\nError input.Please restart program");
            checkIfError = false;
        }

        if (!checkIfError) {
            System.out.println("\nError output.Please restart program");
            System.exit(1);
        }

        return decision;

    }

    public void bookTrip(List<Customer> customers) {

        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("\nFirst Name: ");
        String createFirstName = scanner.nextLine();

        System.out.print("Last Name: ");
        String createLastName = scanner.nextLine();

        Trip createTrip = Trip.createTrip();
        Date createToday = Date.createToday();

        // Set new Object only if dates are valid
        Customer createCustomer = new Customer(firstName, lastName, createTrip, createToday);

        if (Date.tripValidator(createCustomer)) {
            customers.add(createCustomer);
            System.out.println("\nTrip added successfully to recorded trips");
        } else {
            System.out.println("\nTip can not be added to recorded tips.\nPlease follow our policy: Days between trip and booking must be at least 5 days");
        }
    }

    public void editTrip(List<Customer> customers) {

        int decision = returnselectedCustomer(customers);

        System.out.println("\nPlease select property you want to edit");
        System.out.println("[Destination = 'D'/'d']\t\t[Date of Trip = 'T'/'t']");
        System.out.print("Property: ");

        char property = Character.toUpperCase(scanner.next().charAt(0));
        Customer testCustomer = new Customer();

        switch (property) {
            case 'D':
                String destinationBefore = customers.get(decision).getBookedTrip().getDestination();

                Trip editTrip = Trip.editTrip(customers.get(decision));
                testCustomer.setBookedTrip(editTrip);

                System.out.println("\n*** Date of booking will be modified and checked now ***");
                if (Date.tripValidator(testCustomer)) {
                    customers.get(decision).setBookedTrip(editTrip);
                    System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s\033[0m\n", "ID", "Name", "Destination - Before", "Destination - After");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("\n%-10d%-20s%-35s%-35s\033[0m\n",
                            decision, customers.get(decision).getFirstName() + " " + customers.get(decision).getLastName(),
                            destinationBefore, customers.get(decision).getBookedTrip().getDestination());
                    System.out.println("\nTrip modified successfully into database");
                } else {
                    System.out.println("\nTip can not be added to recorded tips.\nPlease follow our policy: Days between trip and booking must be at least 5 days");
                }
                break;

            case 'T':
                String dateOfTripBeforeFormatted = Date.dateFormatter(customers.get(decision), 'T');

                Trip editTripDate = customers.get(decision).getBookedTrip();
                editTripDate.setTripDate(Date.createDate());
                testCustomer.setBookedTrip(editTripDate);

                if (Date.tripValidator(testCustomer)) {
                    customers.get(decision).setBookedTrip(editTripDate);
                    String dateOfTripAfterFormatted = Date.dateFormatter(customers.get(decision), 'T');
                    System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s\033[0m\n", "ID", "Name", "Date of Trip - Before", "Date of Trip - After");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("\n%-10d%-20s%-35s%-35s\033[0m\n",
                            decision, customers.get(decision).getFirstName() + " " + customers.get(decision).getLastName(),
                            dateOfTripBeforeFormatted,
                            customers.get(decision).getBookedTrip().getDestination());
                    System.out.println("\nTrip modified successfully into database");
                } else {
                    System.out.println("\nTip can not be added to recorded tips.\nPlease follow our policy: Days between trip and booking must be at least 5 days");
                }
                break;

            default:
                System.out.println("\nError input. Please restart your program");
        }
    }

    public void cancelTrip(List<Customer> customers) {

        int decision = returnselectedCustomer(customers);
        int sizeBefore = customers.size();

        System.out.println("\nYou are sure? The whole record will be deleted from database");
        System.out.println("[Yes = 'Y'/'y']\t[No = 'N'/'n]");

        customers.remove(decision);
        int sizeAfer = customers.size();

        if (sizeBefore < sizeAfer) {
            System.out.println("\nRecord deleted from database");
        } else {
            System.out.println("\nError occurred while deleting. Please restart program");
        }

    }

    public void displayByProperty(List<Customer> customers) {

        System.out.println("\nPlease choose property below");
        System.out.println("[Name = 'N'/'n']\t[Destination = 'D/'d']\n[Month of Trip = 'T'/'t']\t[Month of booking = 'B'/'b']");
        System.out.print("Property: ");
        char property = Character.toUpperCase(scanner.next().charAt(0));

        int counter = 1;
        switch (property) {
            case 'N':

                System.out.print("\nFirst Name: ");
                String propFirstName = scanner.nextLine();
                System.out.print("Last Name: ");
                String propLastName = scanner.nextLine();

                String propFullName = propFirstName + " " + propLastName;

                System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");

                for (Customer c : customers) {
                    String FullName = c.getFirstName() + " " + c.getLastName();
                    if (propFullName.equals(propFirstName)) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10s%-20s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;

            case 'D':

                System.out.print("\nDestination: ");
                String propDestination = scanner.nextLine();

                System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");

                counter = 1;

                for (Customer c : customers) {
                    if (c.getBookedTrip().getDestination().contains(propDestination)) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10s%-20s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;

            case 'T':

                System.out.print("\nMonth value: ");
                int propMonthTrip = scanner.nextInt();

                System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s-10s\033[0m\n", "ID", "Name", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");

                counter = 1;

                for (Customer c : customers) {
                    if (c.getBookedTrip().getTripDate().getMonth() == propMonthTrip) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10s%-20s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;
            case 'B':
                System.out.print("\nMonth value: ");
                int propMonthBooking = scanner.nextInt();

                System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("------------------------------------------------------------------------------------------------------------------------");

                counter = 1;

                for (Customer c : customers) {
                    if (c.getBookedDate().getMonth() == propMonthBooking) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10s%-20s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;

            default:
                System.out.println("\nError input. Please restart program");
        }
    }

    public void displayAll(List<Customer> customers) {

        System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Date of Trip", "Date of Booking", "Valid Trip");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");

        int counter = 0;
        for (Customer c : customers) {
            String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
            String dateOfTripFormatted = Date.dateFormatter(c, 'T');
            String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
            System.out.printf("\n%-10s%-20s%-35s%-35s%-10s\n",
                    counter,
                    c.getFirstName() + " " + c.getLastName(),
                    dateOfTripFormatted,
                    dateOfBookedTripFormatted,
                    validator);
            counter++;
        }
    }
}



