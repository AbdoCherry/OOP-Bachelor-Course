package Week11.Task02.Abstract;

import Week11.Task02.Model.Article;
import Week11.Task02.Model.Customer;
import Week11.Task02.Model.Order;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public abstract class Shop<T> implements Serializable {

    transient Scanner scannerShop = new Scanner(System.in);

    private int id;
    private String name;

    public Shop() {
    }

    public Shop(int id) {
        this.id = id;
    }

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Increment maxId by 1
    public int maxIdIncr(@NotNull Set<? extends Shop<T>> t) {
        return t.stream().mapToInt(Shop::getId).max().orElse(0) + 1;
    }

    public String name() {
        System.out.println("\nPlease enter the necessary information in the fields below\n");
        System.out.print("Name: ");
        return scannerShop.nextLine();
    }

    // Get my Object -> Useful for creation and finding methods
    public T getObj(@NotNull Set<? extends Shop<T>> t, String name) {

        return (T) t.stream()
                .filter(o -> o.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void displayAll(Set<T> t) {

        if (this instanceof Order) {
            DecimalFormat df = new DecimalFormat("#.##");
            Set<Order> orders = (Set<Order>) t;
            orders.forEach(o -> {
                System.out.println("\n| Orders --------------- Orders --------------- Orders -------------- Orders -------------- Orders --------------- Orders --------------- Orders |");
                System.out.printf("%-5s%-15s%-15s%-30s%-30s%-20s%-30s%-5s\n", "|", "Order ID", "Customer ID", "Name", "Street", "House No", "City", "|");
                System.out.println("|================================================================================================================================================|");
                System.out.printf("%-5s%-15d%-15d%-30s%-30s%-20d%-30s%-5s\n", "|",
                        o.getId(),
                        o.getCustomer().getId(),
                        o.getCustomer().getName(),
                        o.getCustomer().getResidence().getStreet(),
                        o.getCustomer().getResidence().getHouseNo(),
                        o.getCustomer().getResidence().getCity(), "|");

                System.out.println("\n| OrderedArticles ------- OrderedArticles ------- OrderedArticles ------- OrderedArticles |");
                System.out.printf("%-5s%-10s%-10s%-30s%-10s%-15s%-10s%-5s\n", "|", " ", "ID", "Name", "Price", "Quantity", "Total", "|");
                System.out.println("|=========================================================================================|");
                o.getMyBasket().forEach(a -> {
                    String totalPrice = df.format(a.getPrice() * a.getQuantity()) + " $";
                    String price = df.format(a.getPrice()) + " $";
                    System.out.printf("%-5s%-10s%-10d%-30s%-10s%-15d%-10s%-5s\n",
                            "|",
                            " ",
                            a.getId(),
                            a.getName(),
                            price,
                            a.getQuantity(),
                            totalPrice,
                            "|");
                });

            });
        } else if (this instanceof Customer) {
            Set<Customer> customers = (Set<Customer>) t;
            System.out.println("\n| Customers ----------- Customers ------------ Customers ------------ Customers ----------- Customers ------------ Customers |");
            System.out.printf("%-5s%-10s%-30s%-30s%-20s%-30s%-5s\n", "|", "ID", "Name", "Street", "HouseNo", "City", "|");
            System.out.println("|============================================================================================================================|");
            customers.forEach(c -> System.out.printf("%-5s%-10d%-30s%-30s%-20d%-30s%-5s\n", "|",
                    c.getId(),
                    c.getName(),
                    c.getResidence().getStreet(),
                    c.getResidence().getHouseNo(),
                    c.getResidence().getCity(),
                    "|"));
            System.out.println("|============================================================================================================================|");
        } else if (this instanceof Article) {
            Set<Article> articles = (Set<Article>) t;
            System.out.println("\n| Articles ------------ Articles ------------ Articles |");
            System.out.printf("%-5s%-10s%-30s%-10s%-5s\n", "|", "ID", "Name", "Price", "|");
            System.out.println("|======================================================|");
            articles.forEach(a -> System.out.printf("%-5s%-10d%-30s%-10.2f%-5s\n", "|", a.getId(), a.getName(), a.getPrice(), "|"));
            System.out.println("|======================================================|");
        } else {
            System.out.println("\nObject not assigned to known type\n");
            System.exit(1);
        }
    }

    @Override
    public String toString() {

        if (this instanceof Customer) {
            return "Customer { id = " + id +
                    ", name = '" + name + '\'';
        } else if (this instanceof Order) {
            return "Order { id = '" + id + '\'';
        } else if (this instanceof Article) {
            return "Article { id = " + id +
                    ", name = '" + name + '\'' +
                    '}';
        } else {
            return null;
        }
    }

    public static void writeObjects(Set<Order> orders) {

        String filePath = "src/Week11/Task02/Data/Orders.bin";
        File fileOrder = new File(filePath);

        try {
            if (!fileOrder.exists()) fileOrder.createNewFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(filePath));
            ous.writeObject(orders);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<T> parseToSet(Set<Order> orders) {


        if (this instanceof Article) {
            Set<Article> articles = new HashSet<>();
            for (Order o : orders) {
                if (o.getMyBasket() != null) {
                    articles.addAll(o.getMyBasket());
                }
            }
            return (Set<T>) articles;

        } else if (this instanceof Customer) {
            Set<Customer> customers = new HashSet<>();
            for (Order o : orders) {
                if (o.getCustomer() != null) {
                    customers.add(o.getCustomer());
                }
            }
            return (Set<T>) customers;
        } else {
            System.out.println("\nType not known\n");
            System.exit(1);
        }
        return null;
    }

}
