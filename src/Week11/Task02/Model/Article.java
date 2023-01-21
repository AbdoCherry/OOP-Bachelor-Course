package Week11.Task02.Model;

import Week11.Task02.Abstract.Crud;
import Week11.Task02.Abstract.Shop;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import static java.util.Collections.*;

public class Article extends Shop<Article> implements Crud<Article>{

    transient Scanner scannerArticle = new Scanner(System.in);

    private double price;
    private int quantity;

    public Article() {
    }

    public Article(int id, String name, double price) {
        super(id, name);
        this.price = price;
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

    public Set<Article> parseToSet(Set<Order> orders) {
        return unmodifiableSet(super.parseToSet(orders));
    }


    public static @NotNull Set<Article> selectArticles(@NotNull Set<Article> articles) {
        Scanner selection = new Scanner(System.in);
        System.out.println("\nPlease select from articles below");

        List<Article> myArticles = new ArrayList<>(articles);
        Set<Article> basket = new HashSet<>();

        System.out.printf("\n%-5s%-10s%-35s%-10s%-5s\n", "|", "ID", "Article", "Price", "|");
        System.out.println("|===========================================================|");
        myArticles.forEach(a -> System.out.printf("%-5s%-10d%-35s%-10.2f%-5s\n", "|", a.getId(), a.getName(), a.getPrice(), "|"));

        char continueSelection;
        do {
            // Selection of article
            System.out.print("ID: ");
            int selectionId = selection.nextInt();
            Article selected = myArticles.stream()
                    .filter(a -> a.getId() == (selectionId))
                    .findFirst()
                    .orElse(null);
            System.out.print("\nAmount of selected article " + selected.getName() + ": ");
            int amount = selection.nextInt();
            selected.setQuantity(amount);
            System.out.printf("\nYour selection of article %s x %d equals %.2f $",
                    selected.getName(),
                    selected.getQuantity(),
                    (selected.getQuantity() * selected.getPrice()));

            // Add selected article to basket
            basket.add(selected);

            System.out.print("\nContinue selection of articles\n[Yes = 'Y'/'y']\t[No = 'N'/'n']: ");
            continueSelection = Character.toUpperCase(selection.next().charAt(0));
        } while (continueSelection == 'Y');


        return basket;
    }


    @Override
    public void create(@NotNull Set<Article> articles) {

        int sizeBefore = articles.size();
        String name = name();
        Article newArticle = getObj(articles, name);

        if (newArticle != null) {
            System.out.println("\nArticle " + newArticle.toString() + "already exists\n");
            System.exit(1);
        }

        newArticle = new Article();
        newArticle.setId(maxIdIncr(articles));
        newArticle.setName(name);

        System.out.print("\nEnter price for article: ");
        double price = scannerArticle.nextDouble();
        newArticle.setPrice(price);

        articles.add(newArticle);

        System.out.println(sizeBefore < articles.size() ?
                "\nArticle " + newArticle.toString() + " added successfully\n" :
                "\nArticle not added successfully\n");

        scannerArticle.nextLine();
    }

    @Override
    public void remove(@NotNull Set<Article> articles) {
        int sizeBefore = articles.size();

        Article remArticle = getObj(articles, name());

        if (remArticle == null) {
            System.out.println("\nArticle does not exist\n");
            System.exit(1);
        }

        articles.remove(remArticle);

        System.out.println(sizeBefore < articles.size() ?
                "\nArticle " + remArticle.toString() + " removed successfully\n" :
                "\nArticle not removed successfully");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", price = " + price +
                " } ";
    }
}
