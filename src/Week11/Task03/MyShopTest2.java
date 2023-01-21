package Week11.Task03;

import Week11.Task02.Model.Article;
import Week11.Task02.Model.Customer;
import Week11.Task02.Model.Order;

import java.text.DecimalFormat;
import java.util.Set;

public class MyShopTest2 {

    static DecimalFormat df = new DecimalFormat("#.##");

    public static void comparePrices(Set<Order> orders, Set<Article> articles2) {

        System.out.printf("\n%-5s%-30s%-20s%-20s%-20s%-20s%-30s%-5s\n", "|", "Article", "Price before", "Price after", "Differene", "Customer-ID", "Name", "|");
        System.out.println("|================================================================================================================================================|");
        for (Order o : orders)
            for (Article a : o.getMyBasket())
                for (Article a2 : articles2)
                    if ((a.getId() == a2.getId()) && (a.getPrice() != a2.getPrice())) {
                        String difference = df.format(a2.getPrice() - a.getPrice());
                        System.out.printf("%-5s%-30s%-20s%-20s%-20s%-20s%-30s%-5s\n", "|", a.getName(), a.getPrice(), a2.getPrice(), difference, o.getCustomer().getId(), o.getCustomer().getName(), "|");
                    }
    }

    public static void main(String[] args) {

        Order order = new Order();
        Article article = new Article();
        Customer customer = new Customer();

        // Order file
        String pathOrders = "src/Week11/Task02/Data/Orders.bin";
        String pathArticle = "src/Week11/Task03/Articles.bin";

        Set<Order> orders = IOStream.readOrders(pathOrders);
        Set<Customer> customers = customer.parseToSet(orders);
        Set<Article> articles1 = article.parseToSet(orders);
        Set<Article> articles2 = IOStream.readArticles(pathArticle);

        System.out.println("\nDisplay all Orders\n");
        order.displayAll(orders);
        article.displayAll(articles1);
        customer.displayAll(customers);

        System.out.println("\n|================================================================ CHANGED PRICES ================================================================|");


        comparePrices(orders, articles2);


    }

}

