package Week11.Task02;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Customer implements Auxiliary<Customer> {
    private int customerId;
    private String customerName;
    private String street;
    private int houseNo;
    private int zipCode;
    private String city;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String street, int houseNo, int zipCode, String city) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.street = street;
        this.houseNo = houseNo;
        this.zipCode = zipCode;
        this.city = city;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(int houseNo) {
        this.houseNo = houseNo;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static boolean customerExists(List<Customer> customers, String fullName) {
        return customers.stream()
                .filter(c -> c.getCustomerName().equalsIgnoreCase(fullName))
                .findFirst().isEmpty();
    }

    private int maxIncId(List<Customer> customers) {
        return customers.stream()
                .max(Comparator.comparing(Customer::getCustomerId))
                .orElseThrow(NoSuchElementException::new).getCustomerId() + 1;
    }

    public static Customer selectCustomer(List<Customer> customers) {
        Scanner getCustScanner = new Scanner(System.in);

        System.out.print("\nFirst Name: ");
        String firstName = getCustScanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = getCustScanner.nextLine();
        String fullName = firstName + " " + lastName;

        if (!customerExists(customers, fullName)) {
            return customers.stream()
                    .filter(c -> c.getCustomerName().equalsIgnoreCase(fullName)).findFirst().orElse(null);
        } else {
            return null;
        }
    }

    public void registerCustomer(List<Customer> customers) {
        Scanner registerScanner = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary informations in the fields below");
        System.out.print("First Name: ");
        String firstName = registerScanner.nextLine();
        System.out.print("Last Name: ");
        String lastName = registerScanner.nextLine();
        String fullName = firstName + " " + lastName;

        if (!customerExists(customers, fullName)) {
            System.out.println("\nCustomer already in database");
            System.exit(1);
        }

        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(maxIncId(customers));
        newCustomer.setCustomerName(fullName);

        System.out.println("\nPlease enter the address information below");
        System.out.print("Address: ");
        String address = registerScanner.nextLine();
        System.out.print("House No.: ");
        int houseNo = registerScanner.nextInt();
        System.out.print("Zip Code: ");
        int zipCode = registerScanner.nextInt();
        System.out.print("City: ");
        String city = registerScanner.nextLine();

        newCustomer.setStreet(address);
        newCustomer.setHouseNo(houseNo);
        newCustomer.setZipCode(zipCode);
        newCustomer.setCity(city);

        int sizeBefore = customers.size();
        customers.add(newCustomer);
        System.out.println(sizeBefore < customers.size() ? "\nCustomer successfully added: " + newCustomer.toString() + "\n"
                : "\nCustomer not added, check error " + Thread.currentThread().getStackTrace()[1].getLineNumber() + "\n");
    }

    public void removeCustomer(List<Orders> orders, List<Customer> customers) {
        System.out.println("\nPlease enter the necessary informations in the fields below\n");

        Customer rmCustomer = selectCustomer(customers);

        if (selectCustomer(customers) == null) {
            System.out.println("\nCustomer does not exist in database anyway\n");
            System.exit(1);
        }

        customers.remove(rmCustomer);
        orders.removeIf(o -> o.getCustomer().equals(rmCustomer));

        System.out.println("\nCustomer successfully removed - All articles in orderlist are also deleted\n");
    }

    @Override
    public void displayAll(List<Customer> customers) {
        System.out.println("\nAll customer in database:");
        System.out.printf("\033[1m%-5s%-10s%-35s%-10s%-10s%-35s%-25s%-5s\033[0m\n", "|", "ID", "Name", "Address", "HouseNo", "Zip", "City", "|");
        System.out.println("|==============================================================================|");
        for (Customer c : customers) {
            System.out.printf("%-5s%-10s%-35s%-10s%-10s%-35s%-25s%-5s\n", "|", c.getCustomerId(), c.getCustomerName(), c.getStreet(), c.getHouseNo(), c.getZipCode(), c.getCity(), "|");
            System.out.println("|------------------------------------------------------------------------------|");
        }
    }

    @Override
    public String toString() {
        return "Customer " +
                "customerId = " + customerId +
                ", customerName = '" + customerName + '\'' +
                ", street = '" + street + '\'' +
                ", houseNo = " + houseNo +
                ", zipCode = " + zipCode +
                ", city = '" + city + '\'' +
                '}';
    }
}
