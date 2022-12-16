package Week06.Task01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer {

    private final Scanner customerScanner = new Scanner(System.in);

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

        List<Customer> customers = new ArrayList<>();

        String pathMacOs = "src/Week06/Task01/TripAgencyDB.csv", pathWindows = "src\\Week06\\Task01\\TripAgencyDB.csv", path;
        String myOS = System.getProperty("os.name");
        String line;

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

    /**
     * @param customers Gets as input parameter a list from where it selects the filtered customers
     * @return filtered list of customers by first- and lastname
     */
    public List<Customer> selectCustomers(List<Customer> customers) {

        Scanner selectCustomerScanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("First Name: ");
        String searchFirstName = selectCustomerScanner.nextLine();

        System.out.print("Last Name: ");
        String searchLastName = selectCustomerScanner.nextLine();

        return customers.stream()
                .filter(c -> c.getFirstName().equalsIgnoreCase(searchFirstName) && c.getLastName().equalsIgnoreCase(searchLastName))
                .collect(Collectors.toList());
    }

    public Customer returnSelectedCustomer(List<Customer> customers) {
        List<Customer> searchedCustomer = selectCustomers(customers);
        Customer selectedRecord;

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
            decision = customerScanner.nextInt();
            decision--;

        } else if (searchedCustomer.size() == 0) {
            System.out.println("\nNo Trip found in database. Please restart program");
            checkIfError = false;

        } else {
            System.out.println("\nError input.Please restart program");
            checkIfError = false;
        }

        if (!checkIfError) {
            System.out.println("\nError output.Please restart program");
            System.exit(1);
        }

        selectedRecord = searchedCustomer.get(decision);
        return selectedRecord;

    }

    public void bookTrip(List<Customer> customers) {

        Scanner createCustomerScanner = new Scanner(System.in);

        Customer createCustomer = new Customer();
        System.out.println("\nPlease enter the necessary information in the fields below\n");

        System.out.print("First Name: ");
        String createFirstName = createCustomerScanner.nextLine();

        System.out.print("Last Name: ");
        String createLastName = createCustomerScanner.nextLine();

        Trip createTrip = Trip.createTrip();
        Date createToday = Date.createToday();

        // Add attributes to object
        createCustomer.setFirstName(createFirstName);
        createCustomer.setLastName(createLastName);
        createCustomer.setBookedTrip(createTrip);
        createCustomer.setBookedDate(createToday);

        if (Date.tripValidator(createCustomer)) {

            customers.add(createCustomer);
            System.out.println("\nTrip added successfully to recorded trips");
        } else {
            System.out.println("\nTip can not be added to recorded tips.\nPlease follow our policy: Days between trip and booking must be at least 5 days");
        }
    }

    public void editTrip(List<Customer> customers) {

        Customer selectedRecord = returnSelectedCustomer(customers);

        System.out.println("\nPlease select property you want to edit");
        System.out.println("[Destination = 'D'/'d']\t\t[Date of Trip = 'T'/'t']");
        System.out.print("Property: ");

        char property = Character.toUpperCase(customerScanner.next().charAt(0));
        Customer testCustomer = new Customer();

        switch (property) {
            case 'D':
                String destinationBefore = selectedRecord.getBookedTrip().getDestination();

                Trip editTrip = Trip.editTrip(selectedRecord);
                testCustomer.setBookedDate(selectedRecord.getBookedDate());
                testCustomer.setBookedTrip(editTrip);

                System.out.println("\n*** Date of booking will be modified and checked now ***");
                if (Date.tripValidator(testCustomer)) {
                    selectedRecord.setBookedTrip(editTrip);
                    System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s\033[0m\n", "ID", "Name", "Destination - Before", "Destination - After");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("\n%-10d%-20s%-35s%-35s\033[0m\n",
                            1, selectedRecord.getFirstName() + " " + selectedRecord.getLastName(),
                            destinationBefore, selectedRecord.getBookedTrip().getDestination());
                    System.out.println("\nTrip modified successfully into database");
                } else {
                    System.out.println("\nTip can not be added to recorded tips.\nPlease follow our policy: Days between trip and booking must be at least 5 days");
                }
                break;

            case 'T':
                String dateOfTripBeforeFormatted = Date.dateFormatter(selectedRecord, 'T');

                Trip editTripDate = selectedRecord.getBookedTrip();
                editTripDate.setTripDate(Date.createDate());
                testCustomer.setBookedTrip(editTripDate);

                if (Date.tripValidator(testCustomer)) {
                    selectedRecord.setBookedTrip(editTripDate);
                    String dateOfTripAfterFormatted = Date.dateFormatter(selectedRecord, 'T');
                    System.out.printf("\n\033[1m%-10s%-20s%-35s%-35s\033[0m\n", "ID", "Name", "Date of Trip - Before", "Date of Trip - After");
                    System.out.println("------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("\n%-10d%-20s%-35s%-35s\033[0m\n",
                            1, selectedRecord.getFirstName() + " " + selectedRecord.getLastName(),
                            dateOfTripBeforeFormatted,
                            dateOfTripAfterFormatted);
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

        Scanner cancel = new Scanner(System.in);

        Customer selectedCustomer = returnSelectedCustomer(customers);
        int sizeBefore = customers.size();
        int sizeAfter = 0;

        System.out.println("\nYou are sure? The whole record will be deleted from database");
        System.out.println("[Yes = 'Y'/'y']\t[No = 'N'/'n]");
        System.out.print("Are you sure?: ");
        char cancelConfirmation = Character.toUpperCase(cancel.next().charAt(0));

        if (cancelConfirmation == 'Y') {
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).equals(selectedCustomer)) {
                    customers.remove(i);
                    sizeAfter = customers.size();
                    break;
                }
            }
        }

        if (sizeAfter < sizeBefore) {
            System.out.println("\nRecord deleted from database");
        } else {
            System.out.println("\nError occurred while deleting. Please restart program");
        }
    }

    public void displayByProperty(List<Customer> customers) {

        Scanner newScanner = new Scanner(System.in);

        System.out.println("\nPlease choose property below");
        System.out.printf("\n%-25s%-25s", "[Name = 'N'/'n']", "[Destination = 'D/'d']");
        System.out.printf("\n%-25s%-25s", "[Month of Trip = 'T'/'t']", "[Month of booking = 'B'/'b']\n");
        System.out.print("Property: ");
        char property = Character.toUpperCase(customerScanner.next().charAt(0));

        int counter = 1;
        switch (property) {
            case 'N':

                System.out.print("\nFirst Name: ");
                String propFirstName = newScanner.nextLine();
                System.out.print("Last Name: ");
                String propLastName = newScanner.nextLine();

                System.out.printf("\n\033[1m%-10s%-20s%-25s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Destination", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

                for (Customer c : customers) {
                    if (c.getFirstName().equals(propFirstName) && c.getLastName().equals(propLastName)) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10d%-20s%-25s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                c.getBookedTrip().getDestination(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;

            case 'D':

                System.out.print("Destination: ");
                String propDestination = newScanner.nextLine();

                System.out.printf("\n\033[1m%-10s%-20s%-25s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Destination", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

                counter = 1;

                for (Customer c : customers) {
                    if (c.getBookedTrip().getDestination().contains(propDestination)) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10d%-20s%-25s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                c.getBookedTrip().getDestination(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;

            case 'T':

                System.out.print("\nMonth value: ");
                int propMonthTrip = newScanner.nextInt();

                System.out.printf("\n\033[1m%-10s%-20s%-25s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Destination", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

                counter = 1;

                for (Customer c : customers) {
                    if (c.getBookedTrip().getTripDate().getMonth() == propMonthTrip) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10d%-20s%-25s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                c.getBookedTrip().getDestination(),
                                dateOfTripFormatted,
                                dateOfBookedTripFormatted,
                                validator);
                        counter++;
                    }
                }
                break;
            case 'B':
                System.out.print("\nMonth value: ");
                int propMonthBooking = newScanner.nextInt();

                System.out.printf("\n\033[1m%-10s%-20s%-25s%-35s%-35s%-10s\033[0m\n", "ID", "Name", "Destination", "Date of Trip", "Date of Booking", "Valid Trip");
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

                counter = 1;

                for (Customer c : customers) {
                    if (c.getBookedDate().getMonth() == propMonthBooking) {
                        String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
                        String dateOfTripFormatted = Date.dateFormatter(c, 'T');
                        String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
                        System.out.printf("\n%-10d%-20s%-25s%-35s%-35s%-10s\n",
                                counter,
                                c.getFirstName() + " " + c.getLastName(),
                                c.getBookedTrip().getDestination(),
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

        System.out.printf("\n\033[1m%-10s%-20s%-25s%-35s%-15s%-15s%-10s\033[0m\n", "ID", "Name", "Destination", "Date of Trip", "Date of Booking", " | ", "Valid Trip");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");

        int counter = 0;
        for (Customer c : customers) {
            String validator = Date.tripValidator(c) ? "*** VALID TRIP ***" : "*** NOT VALID TRIP ***";
            String dateOfTripFormatted = Date.dateFormatter(c, 'T');
            String dateOfBookedTripFormatted = Date.dateFormatter(c, 'B');
            System.out.printf("%-10d%-20s%-25s%-35s%-15s%-15s%-10s\n",
                    counter,
                    c.getFirstName() + " " + c.getLastName(),
                    c.getBookedTrip().getDestination(),
                    dateOfTripFormatted,
                    dateOfBookedTripFormatted,
                    " | ",
                    validator);
            counter++;
        }
        customerScanner.close();
    }

}



