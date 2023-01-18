package Week11.Task02.Model;

import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Order implements Serializable {
    Scanner scannerOrder = new Scanner(System.in);
    private int orderId;
    private Customer customer;
    private Set<Article> basket;

    public Order() {
    }

    public Order(int orderId, Customer customer, Set<Article> basket) {
        this.orderId = orderId;
        this.customer = customer;
        this.basket = basket;
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

    public Set<Article> getBasket() {
        return basket;
    }

    public void setBasket(Set<Article> basket) {
        this.basket = basket;
    }

    public void createOrder(TreeSet<Order> orders, Customer customer, TreeSet<Article> articles){



    }
}
