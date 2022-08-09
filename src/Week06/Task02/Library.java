package Week06.Task02;

import java.text.ParseException;
import java.util.Scanner;

public class Library {

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {

        Scanner scanner = new Scanner(System.in);

        BookInventory book = new BookInventory();
        BookInventory[] books = new BookInventory[12];

        books[0] = new BookInventory("1", "Java Course - Beginners", "AbdoCherry", 0, null);
        books[1] = new BookInventory("1", "Java Course - Beginners", "AbdoCherry", 14, "Aug");
        books[2] = new BookInventory("1", "Pyhton Course - Beginners", "AbdoChery", 0, null);
        books[3] = new BookInventory("1", "Flutter Course - Beginner", "AbdoCherry", 0, null);
        books[4] = new BookInventory("2", "Nineteen Eighty Four - 1984", "George Orwell", 0, null);
        books[5] = new BookInventory("2", "New Brave World", "Aldous Huxley", 12, "May");
        books[6] = new BookInventory("2", "New Brave World", "Aldous Huxley", 04, "Feb");
        books[7] = new BookInventory("2", "New Brave World", "Aldous Huxley", 0, null);
        books[8] = new BookInventory("3", "The Hitchhiker's Guide To The Galaxy", "Adams, Douglas et. al", 0, null);
        books[9] = new BookInventory("3", "The Hitchhiker's Guide To The Galaxy", "Adams, Douglas et. al", 0, null);
        books[10] = new BookInventory("3", "The Hitchhiker's Guide To The Galaxy", "Adams, Douglas et. al", 0, null);

        System.out.println(
                "\n---------------------------------------------------------------- Library Menu ----------------------------------------------------------------\n");

        char inputContinue;

        do {
            System.out.println("Please choose one option below");
            System.out.print("[Search Book = 1]\t[Add Book to Inventory = 2]\t[Borrow Book = 3]: ");
            int inputUser = scanner.nextInt();

            switch (inputUser) {

                case 0:
                    System.out.println("\nMenu cancelled\n");

                    break;

                case 1:
                    System.out.println(
                            "\n----------------------------------------------------------------- Search Book -----------------------------------------------------------------\n");
                    book.getBookByTitle(books);

                    break;

                case 2:
                    System.out.println(
                            "\n------------------------------------------------------------- Add Book to Inventory -------------------------------------------------------------\n");
                    book.addBookToInventory(books);
                    break;

                case 3:
                    book.borrowBook(books);

                    break;

                default:

                    System.out.println("\nWrong entry. Please close program and try later\n");
            }

            System.out.print("You want to restart the menu?\t[Yes = \"Y\"/\"y\"] - No = [\"N\"/\"n\"]: ");
            inputContinue = scanner.next().charAt(0);

        } while (inputContinue == 'Y' || inputContinue == 'y');

        System.out
                .println(
                        "\n----------------------------------------------------------- List all books in Inventory -----------------------------------------------------------\n");
        book.display(books);

        scanner.close();
    }

}
