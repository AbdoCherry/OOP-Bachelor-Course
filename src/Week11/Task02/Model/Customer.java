package Week11.Task02.Model;

import org.jetbrains.annotations.NotNull;
import Week11.Task02.Model.Residence;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Customer {
    Scanner scannerCustomer = new Scanner(System.in);

    private int customerId;
    private String customerName;
    private Residence residence;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String street, int houseNo, String city) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.residence = new Residence(street, houseNo, city);
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

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    private Customer getCustomer(@NotNull Set<Customer> customers) {
        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("Name: ");
        String name = scannerCustomer.nextLine();
        Set<Customer> containsName = customers.stream()
                .filter(c -> c.getCustomerName().contains(name))
                .collect(Collectors.toSet());

        System.out.printf("\n%-5s%-10s%-30s%-30s%-5s\n", "|", "ID", "Name", "Residence", "|");
        containsName.forEach((c -> {
            System.out.printf("%-5s%-10s%-30s%-30s%-5s\n", "|", c.getCustomerId(), c.getCustomerName(), c.getResidence(), "|");
        }));

        System.out.println("\nPlease select from Customer above by their ID");
        System.out.print("ID: ");
        int id = scannerCustomer.nextInt();
        Customer selectedCustomer = containsName.stream()
                .filter(c -> c.getCustomerId() == id).findFirst().orElse(null);

        boolean validSelection;

        do {
            char input;
            if (selectedCustomer == null) {
                System.out.println("\nError. Wrong ID\nPlease retry or cancel program");
                System.out.println("[Retry : 'R'/'r']\t[Cancel : 'C'/'c']");
                System.out.print("input");
                input = Character.toUpperCase(scannerCustomer.next().charAt(0));

                if (input == 'R') {
                    validSelection = true;
                } else if (input == 'X') {
                    System.out.println("\nThank you for using the program\n");
                    validSelection = false;
                    System.exit(0);
                } else {
                    System.out.println("\nEntry not recognized. Retry selecting customer\n");
                    validSelection = true;
                }
            } else {
                validSelection = false;
            }

        } while (validSelection);

        return selectedCustomer;
    }

    private int incEmpID(@NotNull Set<Customer> customers) {
        return customers.stream().mapToInt(Customer::getCustomerId).max().getAsInt() + 1;
    }

    public void addCustomer(@NotNull Set<Customer> customers) {

        int sizeBefore = customers.size();

        System.out.println("\nPlease enter the necessary informations in the fields below\n");

        System.out.print("Name: ");
        String name = scannerCustomer.nextLine();

        boolean exists = customers.stream()
                .anyMatch(c -> c.getCustomerName().equals(name));

        if (exists) {
            System.out.println("\nCustomer already exists\n");
            System.exit(1);
        }

        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(incEmpID(customers));
        newCustomer.setCustomerName(name);
        newCustomer.setResidence(residence.createResidence(customers));

        customers.add(newCustomer);

        System.out.println(sizeBefore < customers.size() ?
                "\nCustomer " + newCustomer.toString() + " added successfully\n" :
                "\nCustomer not added successfully\n");
    }

    public void remCustomer(@NotNull Set<Customer> customers) {

        int sizeBefore = customers.size();
        Customer remCustomer = getCustomer(customers);

        customers.remove(remCustomer);

        System.out.println(sizeBefore > customers.size() ?
                "\nCustomer " + remCustomer.toString() + " removed successfully\n" :
                "\nCustomer not removed successfully\n");
    }

    @Override
    public String toString() {
        return "Customer { " +
                "customerId = " + customerId +
                ", customerName = '" + customerName + '\'' +
                ", residence = " + residence.getStreet() + " " + residence.getHouseNo() + " " + residence.getCity() +
                '}';
    }
}
