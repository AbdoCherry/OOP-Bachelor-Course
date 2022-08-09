package Week05.Task3;

public class CustomerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Customer customer1 = new Customer(1, "Sheikh Yerbouti", "sh.yerbouti@web.com");
        Customer customer2 = new Customer(2, "Dover Ben", "sh.yerbouti@web.com");

        customer2 = customer1; // Initiating customer2 with all information of customer1 => Replacing

        customer1.displayCustomer();
        customer2.displayCustomer();

        // Trying with isEqual method

        Customer customer3 = new Customer(1, "Sheikh Yerbouti", "sh.yerbouti@web.com");
        Customer customer4 = new Customer(2, "Dover Ben", "sh.yerbouti@web.com");

        boolean equalMailadress = customer3.isEqual(customer4);

        // == operator compares string pattern <=> equals method compares memory address
        // of attribute
        if (equalMailadress) {
            customer3.displayCustomer();
            customer4.displayCustomer();
            System.out.println(" ");
        } else {
            System.out.println("\nCustomers have same Mail-Adress\n");
        }
    }

}
