package Week05.Task01;

public class Shirt {

    private final int shirtID;
    private final String description;
    private final char colCode;
    private double price;
    private final int quantity;

    public Shirt(int shirtID, String description, char colCode, double price, int quantity) {
        this.shirtID = shirtID;
        this.description = description;
        this.colCode = colCode;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return double
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayInformation() {

        String colorName;

        switch (this.colCode) {
            case 'r':
                colorName = "Red";

                break;

            case 'b':
                colorName = "Blue";

                break;

            case 'g':
                colorName = "Grey";

                break;

            case 'B':

                colorName = "Black";

                break;

            case 'y':
                colorName = "Yellow";

                break;

            default:
                colorName = "Not recognized";

        }

        System.out.println("\nShirt ID: " + this.shirtID);
        System.out.println("Description: " + this.description);
        System.out.println("Color: " + colorName);
        System.out.println("Price: " + this.price + " $");
        System.out.println("Quantity in warehouse: " + this.quantity);

    }

}
