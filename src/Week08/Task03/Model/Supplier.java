package Week08.Task03.Model;

import java.util.Scanner;

public class Supplier {

    Scanner scanner = new Scanner(System.in);
    private String name, adress, orderVolumeBusinessYear;
    private Bank account;

    public Supplier() {

    }

    public Supplier(String name, String adress, String orderVolumeBusinessYear, String iban, String bankName) {
        this.name = name;
        this.adress = adress;
        this.orderVolumeBusinessYear = orderVolumeBusinessYear;
        this.account = new Bank(iban, bankName);

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
    public String getAdress() {
        return adress;
    }

    /**
     * @param adress
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    /**
     * @return String
     */
    public String getOrderVolumeBusinessYear() {
        return orderVolumeBusinessYear;
    }

    /**
     * @param orderVolumeBusinessYear
     */
    public void setOrderVolumeBusinessYear(String orderVolumeBusinessYear) {
        this.orderVolumeBusinessYear = orderVolumeBusinessYear;
    }

    /**
     * @return Bank
     */
    public Bank getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(Bank account) {
        this.account = account;
    }

    /**
     * @param customer
     * @param basket
     */
    public void placeShipment(Customer customer, double basket) {

        System.out.println("\nYou got an order from " + customer.getName() + " with those items "
                + customer.getOrderVolumeBusinessYear() + "\n");

        System.out.println(
                "Do you accept the order or do you want to decline?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
        char shipment = scanner.next().charAt(0);

        if (shipment == 'Y' || shipment == 'y') {

            System.out.println("\nPlease send the ordered items to this adress " + customer.getAdress() + " soon\n");
            this.orderVolumeBusinessYear = customer.getOrderVolumeBusinessYear();
        } else if (shipment == 'N' || shipment == 'n') {
            System.out.println("\nPlease send the payed in amount of " + basket + " $ back to customer to this account "
                    + customer.getAccount().getIban() + " at this bank " + customer.getAccount().getBankName() + "\n");

        } else {
            System.out.println("\nWront entry. Please close and restart program\n");
        }

    }

}
