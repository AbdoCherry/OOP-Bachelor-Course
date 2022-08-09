package Week08.Task3.App;

import Week08.Task3.Model.Customer;
import Week08.Task3.Model.Supplier;

public class OrderApp {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Customer customer = new Customer("Mike Litoris", "Man-of-Steal Avn. 53-13", null, "DE55 1233 0000 5492 99",
                "Evil Bank");
        Supplier supplier = new Supplier("Sheikh Yerbouti", "Down S. Indrom Street 55", null, "UK51 9851 5930 5001 95",
                "Holy Shit Bank Corporation -HSBC-");

        supplier.placeShipment(customer, customer.placeOrder(supplier));
        System.out.println("\n************* CUSTOMER *************\n");
        System.out.println("Name: " + customer.getName());
        System.out.println("Street: " + customer.getAdress());
        System.out.println("Order Volume: " + customer.getOrderVolumeBusinessYear());

        System.out.println("\n");

        System.out.println("\n************* SUPPLIER *************\n");
        System.out.println("Name: " + supplier.getName());
        System.out.println("Street: " + supplier.getAdress());
        System.out.println("Order Volume: " + supplier.getOrderVolumeBusinessYear());

    }

}
