package Week11.Task02.Model;

import Week11.Task02.Shop;

import java.util.Set;

public class Order extends Shop<Order> {

    private Customer customer;
    private Article basket;

    public Order() {
    }

    public Order(int id, Customer customer, Article basket) {
        super(id);
        this.customer = customer;
        this.basket = basket;
    }

    public void updateOrders() {

    }

    public void placeOrder(Set<Order> orders, Customer customer, Set<Article> articles) {
    }

}
