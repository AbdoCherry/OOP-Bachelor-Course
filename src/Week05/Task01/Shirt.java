package Week05.Task1;

public class Shirt {

    private int shirtID;
    private String description;
    private char colCode;
    private double price;
    private int quantity;

    public Shirt() {

    }

    public Shirt(int shirtID, String description, char colCode, double price, int quantity) {
        this.shirtID = shirtID;
        this.description = description;
        this.colCode = colCode;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return int
     */
    public int getShirtID() {
        return shirtID;
    }

    /**
     * @param shirtID
     */
    public void setShirtID(int shirtID) {
        this.shirtID = shirtID;
    }

    /**
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return char
     */
    public char getColCode() {
        return colCode;
    }

    /**
     * @param colCode
     */
    public void setColCode(char colCode) {
        this.colCode = colCode;
    }

    /**
     * @return double
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return int
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void displayInformation() {

        String colorName = "";

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
