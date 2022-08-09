package Week05.Task03;

public class Customer {

    private int id;
    private String name, mailaddress;

    public Customer() {

    }

    public Customer(int id, String name, String mailaddress) {
        this.id = id;
        this.name = name;
        this.mailaddress = mailaddress;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String
     */
    public String getMailaddress() {
        return mailaddress;
    }

    /**
     * @param mailaddress
     */
    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }

    public void displayCustomer() {
        System.out.println("\nCustomer ID: " + this.id);
        System.out.println("Customer name: " + this.name);
        System.out.println("Customer mail: " + this.mailaddress);
    }

    /**
     * @param customer
     * @return boolean
     */
    public boolean isEqual(Customer customer) {
        boolean isEqual = false;

        isEqual = this.mailaddress.equals(customer.getMailaddress());

        return isEqual;

    }

}
