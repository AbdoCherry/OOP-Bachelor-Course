package Week11.Task02.Model;

import Week11.Task02.Abstract.Shop;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Set;

public class Order extends Shop<Order> implements Serializable {

    private Customer customer;
    private Set<Article> myBasket;

    public Order() {
    }

    public Order(int id, Customer customer, Set<Article> myBasket) {
        super(id);
        this.customer = customer;
        this.myBasket = myBasket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Article> getMyBasket() {
        return myBasket;
    }

    public void placeOrder(@NotNull Set<Order> orders, Set<Customer> customers, Set<Article> articles) {

        int sizeBefore = orders.size();
        Customer selectedCustomer = new Customer();
        selectedCustomer = selectedCustomer.getObj(customers, name());

        if (selectedCustomer == null) {
            System.out.println("\nCustomer not found in registered List\n");
            System.exit(1);
        }

        Set<Article> basket = Article.selectArticles(articles);

        double sumTotalCosts = 0;
        for (Article a : basket) sumTotalCosts += a.getPrice() * a.getQuantity();
        System.out.printf("\nThe total costs are %.2f $: \n", sumTotalCosts);

        Order newOrder = new Order(maxIdIncr(orders), selectedCustomer, basket);
        orders.add(newOrder);

        System.out.println(sizeBefore < orders.size() ?
                "\nOrder " + newOrder.toString() + " added successfully" :
                "\nOrder not added successfully");
    }

    @Override
    public String toString() {
        return super.toString() +
                " Customer = " + customer.getName() +
                ", Basket Size = " + myBasket.size() +
                " } ";
    }
}
