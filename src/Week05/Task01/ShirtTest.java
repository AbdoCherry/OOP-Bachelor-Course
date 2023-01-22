package Week05.Task01;

public class ShirtTest {

    public static void main(String[] args) {

        Shirt[] myShirts = new Shirt[10];

        myShirts[0] = new Shirt(1, "Ralph Lauren", 'b', 59.99, 125);
        myShirts[1] = new Shirt(2, "Craftmen - Shirt", 'y', 30.99, 30);
        myShirts[2] = new Shirt(3, "Lacoste", 'y', 12.49, 125);
        myShirts[3] = new Shirt(4, "Allrounder", 'r', 9.99, 22);
        myShirts[4] = new Shirt(5, "Bowling - Shirt", 'B', 11.99, 5);
        myShirts[5] = new Shirt(6, "Adidas - Shirt", 'l', 15.99, 125);
        myShirts[6] = new Shirt(7, "Jogging - Shirt", 'm', 1.99, 15);
        myShirts[7] = new Shirt(8, "Business - Shirt", 'w', 59.19, 45);
        myShirts[8] = new Shirt(9, "Casual - Sundowner", 'r', 9.99, 33);
        myShirts[9] = new Shirt(10, "Troy - Style Shirt", 'w', 55.99, 400);

        for (Shirt shirt : myShirts) {
            shirt.displayInformation();
        }
        System.out.println(" ");

    }

}
