package Week08.Task3.Model;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Customer {

    Scanner scanner = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("#0.00");

    private String name, adress, orderVolumeBusinessYear;
    private Bank account;

    public Customer() {

    }

    public Customer(String name, String adress, String orderVolumeBusinessYear, String iban, String bankName) {
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
     * @param supplier
     * @return double
     */
    public double placeOrder(Supplier supplier) {

        String[] items = { "Blanket", "Smartphone", "Laptop", "Tires - Winter", "Tires - Summer" };
        double[] prices = { 12.99, 1129.49, 2100.99, 34.49, 59.00 };

        System.out.println("\nPlease choose from items below");
        System.out.printf("\n\u001B[1m%-20s%-20s%-20s\033[0m\n", "Item No", "Item", "Price");

        for (int i = 0; i < items.length; i++) {
            System.out.printf("%-20d%-20s%.2f$\n", (i + 1), items[i], prices[i]);
        }
        System.out.println("\n");

        char addItem;
        double basket = 0;
        String[] myItems = new String[items.length];

        int counter = 0;
        do {
            System.out.print("Item: ");
            int itemID = scanner.nextInt();
            myItems[counter] = items[itemID - 1];

            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();
            basket += prices[itemID - 1] * quantity;

            System.out.print("\nAdding item?\t[Yes = \"Y\"/\"y\"] - [No = \"N\"/\"n\"]: ");
            addItem = scanner.next().charAt(0);
            counter++;
        } while ((addItem == 'Y' || addItem == 'y'));

        System.out.print("\nThanks for your order\nPlease send the total amount of " + df.format(basket)
                + " $ to this account "
                + supplier.getAccount().getIban() + " to this bank " + supplier.getAccount().getBankName() + "\n");

        String basketFinished = "";
        for (String element : myItems) {
            if (element != null) {
                basketFinished += element + "; ";

            }
        }

        this.orderVolumeBusinessYear = basketFinished;

        return basket;

    }
}
