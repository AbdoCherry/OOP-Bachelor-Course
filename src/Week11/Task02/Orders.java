package Week11.Task02;

import java.io.Serializable;
import java.util.*;

public class Orders implements Auxiliary<Orders>, Serializable {

    private int orderId;
    private Customer customer;
    private List<Article> articles;

    public Orders() {
    }

    public Orders(int orderId, Customer customer, List<Article> articles) {
        this.orderId = orderId;
        this.customer = customer;
        this.articles = articles;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    private int maxIncOrder(List<Orders> orders) {
        return orders.stream()
                .max(Comparator.comparing(Orders::getOrderId))
                .orElseThrow(NoSuchElementException::new).getOrderId() + 1;
    }

    public static List<Orders> randomShuffle(List<Customer> customers, List<Article> articles) {

        List<Orders> orders = new ArrayList<>();

        Random random = new Random();
        int randomSize = random.nextInt(11);
        int counter = 0;

        while (counter < randomSize) {
            int randomIndexCustomer = random.nextInt((customers.size()));
            int randomIndexArticles = random.nextInt((articles.size()));

            Customer customer = customers.get(randomIndexCustomer);
            List<Article> articleList = new ArrayList<>();

            int counterArticles = 0;

            while (counterArticles < 5) {
                articleList.add(articles.get(randomIndexArticles));
                counterArticles++;
            }
            orders.add(new Orders((counter + 1), customer, articleList));
            counter++;
        }
        return orders;
    }

    public void placeOrder(List<Orders> orders, List<Article> articles, List<Customer> customers) {
        Scanner placeOrderScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary informations in the fields below");
        Customer customer = Customer.selectCustomer(customers);
        if (customer == null) {
            System.out.println("\nCustomer not found\n");
            System.exit(1);
        }

        List<Article> articleSelection = new ArrayList<>();

        System.out.println("\nPlease select Article by ID. You are allowed to pick multiple articles");
        System.out.printf("%-5s%-10s%-25s%-20s%-5s\n", "|", "ID", "Name", "Price", "|");
        System.out.println("|---------------------------------------------------------------|");
        articles.forEach(a -> {
            System.out.printf("%-5s%-10d%-25s%-20.2f%-5s\n", "|", a.getArtId(), a.getArtName(), a.getUnitPrice(), "|");
        });

        char input;

        do {
            System.out.println("\nYour selection or enter 'N'/'n' for cancel");
            System.out.print("Input: ");
            input = placeOrderScanner.next().charAt(0);

            if (Character.isDigit(input)) {
                int selection = input - '0' - 1;
                Article selectedArticle = articles.get(selection);
                System.out.println("Article added:");
                System.out.println("--------------------------------");
                System.out.println("ID: " + articles.get(selection).getArtId());
                System.out.println("Name: " + articles.get(selection).getArtName());
                System.out.println("Price: " + articles.get(selection).getUnitPrice() + "$");
                articleSelection.add(selectedArticle);
            }
        } while (Character.toUpperCase(input) != 'N');

        Orders newOrder = new Orders();
        newOrder.setOrderId(maxIncOrder(orders));
        newOrder.setCustomer(customer);
        newOrder.setArticles(articleSelection);

        orders.add(newOrder);
    }

    @Override
    public void displayAll(List<Orders> orders) {
        System.out.printf("\nAll Orders in Database:\n");

        orders.forEach(o -> {
            System.out.println("|=======================================================================================================================|");
            System.out.printf("\033[1m%-5s%-10s%-10s%-45s%-50s%-5s\033[0m\n", "|", "Order-ID", "Custo-ID", "Name", "Full Address", "|");
            System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
            String fullAddres = o.getCustomer().getStreet() + " " + String.valueOf(o.getCustomer().getHouseNo()) + ", " + String.valueOf(o.getCustomer().getZipCode()) + ", " + o.getCustomer().getCity();
            System.out.printf("%-5s%-10d%-10s%-45s%-50s%-5s\n", "|", o.getOrderId(), o.getCustomer().getCustomerId(), o.getCustomer().getCustomerName(), fullAddres, "|");
            System.out.println("|=======================================================================================================================|");
            System.out.printf("\033[1m%-15s%-25s%-35s%-45s%-15s\033[0m\n", "|", "Art-ID", "Art-Name", "Art-Price", "|");
            System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
            o.getArticles().forEach(a -> {
                System.out.printf("%-15s%-25d%-35s%-45.2f%-15s\n", "|", a.getArtId(), a.getArtName(), a.getUnitPrice(), "|");
            });
            System.out.println("|-----------------------------------------------------------------------------------------------------------------------|\n");

        });
    }

}
