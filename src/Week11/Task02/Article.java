package Week11.Task02;

import java.util.List;

public class Article implements Auxiliary<Article> {
    private int artId;
    private String artName;
    private double unitPrice;

    public Article() {
    }

    public Article(int artId, String artName, double unitPrice) {
        this.artId = artId;
        this.artName = artName;
        this.unitPrice = unitPrice;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Override
    public void displayAll(List<Article> article) {
        System.out.println("\nAll articles in warehouse:");
        System.out.printf("\033[1m%-5s%-10s%-35s%-25s%-5s\033[0m\n", "|", "ID", "Name", "Price", "|");
        System.out.println("|==============================================================================|");
        for (Article a : article) {
            System.out.printf("%-5s%-10d%-35s%-25.2f%-5s\n", "|", a.getArtId(), a.getArtName(), a.getUnitPrice(), "|");
            System.out.println("|------------------------------------------------------------------------------|");
        }

    }
}
