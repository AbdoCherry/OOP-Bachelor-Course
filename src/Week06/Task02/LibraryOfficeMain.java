package Week06.Task02;


import java.util.List;
import java.util.Scanner;

public class LibraryOfficeMain {
    public static void main(String[] args) {

        BookInventory b = new BookInventory();
        List<BookInventory> books = BookInventory.readBooksFromCSV();

        System.out.println("\n---------------------------------------------------------------- \033[1mWELCOME TO LIBRARY OFFICE\033[0m ----------------------------------------------------------------");

        char continueProgram;

        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\nPlease choose menu below");
            System.out.println("[Add Book = 'A'/'a']\t[Borrow Book = 'B'/'b']");
            System.out.println("[Get Book = 'G'/'g']\t[Display Books = 'D'/'d']");
            System.out.println("\t\t[Exit Program = 'E'/'e']");

            System.out.print("\nMenu: ");

            char menu = Character.toUpperCase(scanner.next().charAt(0));

            switch (menu) {
                case 'A':
                    b.addBookToInventory(books);
                    break;
                case 'B':
                    b.borrowBook(books);
                    break;
                case 'G':
                    b.getBookByTitle(books);
                    break;
                case 'D':
                    b.display(books);
                    break;
                case 'E':
                    System.out.println("\nThanks for using the Library Program");
                    System.exit(0);
                default:
                    System.out.println("\nError input. Please restart program\n");
            }

            System.out.println("\nDo you want to restart the program?\n[Yes = 'Y'/'y'] - [No = 'N'/'n']");
            System.out.print("Restart: ");
            continueProgram = Character.toUpperCase(scanner.next().charAt(0));


        } while (continueProgram == 'Y');
    }

}
