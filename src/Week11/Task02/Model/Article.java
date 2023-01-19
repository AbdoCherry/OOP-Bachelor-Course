package Week11.Task02.Model;

import Week11.Task02.Crud;
import Week11.Task02.Shop;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.util.Set;

public class Article extends Shop<Article> implements Crud<Article> {

    Scanner scannerArticle = new Scanner(System.in);

    private double price;
    private int quantity;

    public Article() {
    }

    public Article(int id, String name, double price, int quantity) {
        super(id, name);
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void create(@NotNull Set<Article> articles) {

        int sizeBefore = articles.size();
        Article newArticle = getObj(articles);

        if (newArticle != null) {
            System.out.println("\nArticle " + newArticle.toString() + "already exists\n");
            System.exit(1);
        }

        System.out.print("\nEnter price for article: ");
        double price = scannerArticle.nextDouble();

        newArticle.setPrice(price);
        articles.add(newArticle);

        System.out.println(sizeBefore < articles.size() ?
                "\nArticle " + newArticle.toString() + " added successfully\n" :
                "\nArticle not added successfully\n");
    }

    @Override
    public void remove(@NotNull Set<Article> articles) {
        int sizeBefore = articles.size();

        Article remArticle = getObj(articles);

        if (remArticle == null) {
            System.out.println("\nArticle does not exist\n");
            System.exit(1);
        }

        articles.remove(remArticle);

        System.out.println(sizeBefore < articles.size() ?
                "\nArticle " + remArticle.toString() + " removed successfully\n" :
                "\nArticle not removed successfully");
    }
}
