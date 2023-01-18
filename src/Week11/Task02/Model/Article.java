package Week11.Task02.Model;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

public class Article {

    Scanner artScanner = new Scanner(System.in);
    private int artId;
    private String artName;
    private double artPrice;

    public Article() {
    }

    public Article(int artId, String artName, double artPrice) {
        this.artId = artId;
        this.artName = artName;
        this.artPrice = artPrice;
    }

    public int getArtId() {
        return artId;
    }

    public void setArtId(int artId) {
        this.artId = artId;
    }

    public String getArtName() {
        return artName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public double getArtPrice() {
        return artPrice;
    }

    public void setArtPrice(double artPrice) {
        this.artPrice = artPrice;
    }

    private boolean artExists(@NotNull TreeSet<Article> articles, String newArtName) {
        return articles.stream()
                .anyMatch(a -> a.getArtName().equalsIgnoreCase(newArtName));
    }

    private int maxIdIncr(@NotNull TreeSet<Article> articles) {
        return articles.last().getArtId() + 1;
    }

    private Article selectArticle(@NotNull TreeSet<Article> articles) {

        Article selectedArticle = new Article();

        System.out.printf("\n%-5s%-10s%-40s%-10s%-5s\n", "|", "ID", "Name", "Price $", "|");
        System.out.println("|=========================================================|");
        articles.forEach(a -> {
            System.out.printf("%-5s%-10d%-40s%-10.2f%-5s\n", "|", a.getArtId(), a.getArtName(), a.getArtPrice(), "|");
        });

        boolean validSelection = true;
        int id = -1;
        do {
            if (id == -1) {
                System.out.println("\nEntered ID not available in article list.\nPlease retry");
            } else {
                System.out.println("\nPlease select article by ID");
            }

            System.out.print("ID: ");
            id = artScanner.nextInt();

            for (Article a : articles) {
                if (a.getArtId() == id) {
                    selectedArticle = a;
                    validSelection = false;
                    break;
                }
            }

        } while (validSelection);

        return selectedArticle;
    }

    public void addArticle(@NotNull TreeSet<Article> articles) {

        System.out.println("\nPlease enter the necessary informations in the fields below");

        System.out.print("Article Name: ");
        String newArtName = artScanner.nextLine();

        int sizeBefore = articles.size();
        if (!artExists(articles, newArtName)) {
            System.out.println("\nArticle already exists in warehouse\n");
            System.exit(1);
        }

        System.out.print("Article Price: ");
        double newPrice = artScanner.nextDouble();

        Article newArticle = new Article();
        newArticle.setArtId(maxIdIncr(articles));
        newArticle.setArtName(newArtName);
        newArticle.setArtPrice(newPrice);

        articles.add(newArticle);

        System.out.println(sizeBefore < articles.size() ?
                "\nNew article " + newArticle.toString() + " added succusessfully\n" :
                "\nNew article was not added successfully\n");
    }

    public void removeArticle(@NotNull TreeSet<Article> articles) {
        System.out.println("\nPlease enter the necessary informations in the fields below\n");

        int sizeBefore = articles.size();
        Article remArticle = selectArticle(articles);
        articles.remove(remArticle);

        System.out.println(sizeBefore > articles.size() ?
                "Article " + remArticle.toString() + " removed successfully from warehouse\n" :
                "Article not removed successfully\n");
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Article { " +
                ", artId = " + artId +
                ", artName = '" + artName + '\'' +
                ", artPrice = " + df.format(artPrice) + " $" +
                '}';
    }
}
