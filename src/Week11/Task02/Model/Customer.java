package Week11.Task02.Model;

import Week11.Task02.Crud;
import Week11.Task02.Shop;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class Customer extends Shop<Customer> implements Crud<Customer> {

    private Residence residence;

    public Customer() {
    }

    public Customer(int id, String name, String street, int houseNo, String city) {
        super(id, name);
        this.residence = new Residence(street, houseNo, city);
    }

    public Residence getResidence() {
        return residence;
    }

    public void setResidence(Residence residence) {
        this.residence = residence;
    }

    @Override
    public void create(@NotNull Set<Customer> customers) {
        int sizeBefore = customers.size();
        Customer newCustomer = getObj(customers);

        if (newCustomer != null) {
            System.out.println("Customer already exists: " + newCustomer.toString() + "\n");
            System.exit(0);
        }
        newCustomer.setId(maxIdIncr((Set<Customer>) customers));
        newCustomer.setResidence(residence.createResidence(customers));
        customers.add(newCustomer);

        System.out.println(sizeBefore < customers.size() ?
                "New customer " + newCustomer.toString() + " addedd successfull" :
                "New customer not added successfully\n");
    }

    @Override
    public void remove(@NotNull Set<Customer> customers) {

        int sizeBefore = customers.size();
        Customer remCustomer = getObj(customers);

        if (remCustomer == null) {
            System.out.println("\nCustomer not in database\n");
            System.exit(1);
        }

        customers.remove(remCustomer);

        System.out.println(sizeBefore > customers.size() ?
                "\nCustomer " + remCustomer.toString() + " removed successfully\n" :
                "\nCustomer not removed successfully\n");

    }

    @Override
    public String toString() {
        return "residence = " + residence.getStreet() + " " + residence.getHouseNo() + " " + residence.getCity() +
                " } " + super.toString();
    }
}
