package Week11.Task02;

import Week11.Task02.Model.Article;
import Week11.Task02.Model.Customer;
import Week11.Task02.Model.Order;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class MyShopTest {
    public static void main(String[] args) {

        Order o = new Order();
        Customer c = new Customer();
        Article a = new Article();

        Set<Customer> customers = new HashSet<>(Arrays.asList(
                new Customer(1, "Sheikh Yerbout", "GroundZ", 12, "New York"),
                new Customer(2, "Tess Tickles", "Singa-street", 4, "Berlin"),
                new Customer(3, "Mike Hunt", "Hurtaloot", 69, "Bangs TX"),
                new Customer(4, "Mike Litoris", "Shmeet street", 25, "San Francisco")
        ));

        Set<Article> articles = new HashSet<>(Arrays.asList(
                new Article(1, "Apples", .59),
                new Article(2, "Orange Juice", 1.10),
                new Article(3, "Drill Machine", 225.19),
                new Article(4, "LGTVHDBBQ+", 900),
                new Article(5, "Shampoo (All in One - Men)", .89),
                new Article(6, "Barber-Voucher", 49)
        ));

        Set<Order> orders = new HashSet<Order>();

        // Reading from object file

        System.out.println("\n=============================== WELCOME TO THE SHOP ===============================\n");


        char continueProgram;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please select from menu below\n");
            System.out.println("[Customer Menu : 'C'/'c']\t[Article Menu : 'A'/'a']");
            System.out.println("[Place Order : 'O'/'o']\t[Display Orders : 'D/'d']");
            System.out.println("\t\t\t[Exit Program : 'X'/'x']");

            System.out.print("\nMenu: ");
            char menu = Character.toUpperCase(scanner.next().charAt(0));

            switch (menu) {
                case 'C':
                    System.out.println("\n================== CUSTOMER MENU ==================\n");
                    System.out.println("Please select from submenu below\n");
                    System.out.println("[Add Customer  : 'A'/'a']\t[Remove Customer       : 'R'/'r']");
                    System.out.println("[Find Customer : 'F'/'f']\t[Display all Customers : 'D'/'d']");
                    System.out.println("\t\t\t[Exit Program : 'X'/'x']");

                    System.out.print("Customer Menu: ");
                    char menuCustomer = Character.toUpperCase(scanner.next().charAt(0));

                    switch (menuCustomer) {
                        case 'A': // Add customer
                            c.create(customers);
                            break;

                        case 'R': // Remove customer
                            c.remove(customers);
                            break;

                        case 'F': // Find customer
                            c = c.getObj(customers, c.name());
                            System.out.println(c != null ?
                                    "\nCustomer " + c.toString() + " is available\n"
                                    : "\nCustomer is not available\n");
                            c = null;
                            break;

                        case 'D':
                            c.displayAll(customers);
                            break;

                        case 'X': // Exit customers
                            System.out.println("\nThank you for using the program\n");
                            System.exit(0);

                            break;

                        default:
                            System.out.println("\nError input: '" + menuCustomer + "' not known");
                    }
                    break;

                case 'A':
                    System.out.println("\n================== ARTICLE MENU ==================\n");
                    System.out.println("Please select from submenu below\n");
                    System.out.println("[Add Article  : 'A'/'a']\t[Remove Article       : 'R'/'r']");
                    System.out.println("[Find Article : 'F'/'f']\t[Display all Articles : 'D'/'d']");
                    System.out.println("\t\t\t[Exit Program : 'X'/'x']");

                    System.out.print("Article Menu: ");
                    char menuArticle = Character.toUpperCase(scanner.next().charAt(0));

                    switch (menuArticle) {

                        case 'A': // Add customer
                            a.create(articles);
                            scanner.nextLine();
                            break;

                        case 'R': // Remove customer
                            a.remove(articles);
                            break;

                        case 'F': // Find customer
                            a = a.getObj(articles, a.name());
                            System.out.println(a != null ?
                                    "\n" + a.toString() + " is available\n" :
                                    "\nArticle is not available\n");
                            a = null;
                            break;

                        case 'D':
                            a.displayAll(articles);
                            break;

                        case 'X': // Exit customers
                            System.out.println("\nThank you for using the program\n");
                            System.exit(0);
                            break;

                        default:
                            System.out.println("\nError input: '" + menuArticle + "' not known");
                    }
                    break;

                case 'O':
                    o.placeOrder(orders, customers, articles);
                    scanner.nextLine();
                    break;

                case 'D':
                    o.displayAll(orders);
                    break;

                case 'X':
                    System.out.println("\nThank you for using the program\n");
                    System.exit(0);

                default:
                    System.out.println("\nError input: '" + menu + "' not known\n");
                    System.exit(1);
            }
            System.out.print("\nContinue Program?\n[Yes = 'Y'/'y']\t[No = 'N'/'n']");
            System.out.print("\nContinue: ");

            continueProgram = Character.toUpperCase(scanner.next().charAt(0));

        } while (continueProgram == 'Y');

        Order.writeObjects(orders);

    }
}
