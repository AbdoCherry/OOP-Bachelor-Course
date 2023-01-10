package Week11.Task02;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ShopMain {
    public static void main(String[] args) throws IOException {

        Customer c = new Customer();
        Article a = new Article();
        Orders o = new Orders();

        // Creating sample data for our test
        List<Customer> customers = Arrays.asList(
                new Customer(1, "Philipp v. Simpeling", "Simpstreet", 12, 60312, "Simpcity"),
                new Customer(2, "Andrew Hate", "Alphastreet", 69, 60316, "Sigmacity"),
                new Customer(3, "Greta Thunfish", "Aspergerstreet", 5, 60123, "Plasticity"),
                new Customer(4, "Stepan Senz", "Gehway", 49, 95338, "Pastor-Sempa-City")
        );

        // Just got inspired by weird products offered by wish
        List<Article> articles = Arrays.asList(
                new Article(1, "John Travolta Fake Beard", 42.99),
                new Article(2, "Wolf-Claws from plastic", 12.99),
                new Article(3, "Outdated Food", .99),
                new Article(4, "Master of Beta", 6.59),
                new Article(5, "Nicolas Cage pillow", 52.99),
                new Article(6, "Trump tupet", 1.19)
        );

        List<Orders> orders = Orders.randomShuffle(customers, articles);

        //---------------------------------------------------------------- The Program ----------------------------------------------------------------

        System.out.println("----------------------------------------------- WELCOME TO OUR SHOP -----------------------------------------------");

        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease select from the menu");

        char restart;

        do {
            System.out.println("[Add Customer = 'A'/'a']\t[Remove Customer = 'R'/'r']");
            System.out.println("[Place Order  = 'P'/'p']\t[Cancel Program  = 'C'/'c']");
            System.out.print("Menu:");

            char menu = Character.toUpperCase(scanner.next().charAt(0));

            switch (menu) {
                case 'A':
                    c.registerCustomer(customers);
                    break;
                case 'P':
                    o.placeOrder(orders, articles, customers);
                    break;
                case 'R':
                    c.removeCustomer(orders, customers);
                    break;
                case 'C':
                    System.out.println("\nThanks for using the program\n");
                    System.exit(0);

                default:
                    System.out.println("\nWrong entry\n");
            }

            System.out.println("\nRestart program?\n[Yes = 'Y'/'y']\t[No = 'N'/'n']");
            System.out.print("Restart: ");
            restart = Character.toUpperCase(scanner.next().charAt(0));
        } while (restart == 'Y');

        //---------------------------------------------------------------- The export of Objects ----------------------------------------------------------------

        File file = new File("src/Week11/Task02/Orders.bin");
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file));

        for (Orders order : orders) {
            ous.writeObject(order);
        }

        ous.close();
    }
}
