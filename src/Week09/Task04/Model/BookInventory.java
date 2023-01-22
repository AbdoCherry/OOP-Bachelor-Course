package Week09.Task04.Model;

import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;

import Week09.Task04.BusinessRules.*;

public class BookInventory {

    private Scanner scanner = new Scanner(System.in);

    private Book book;
    private Date returnDate;

    public BookInventory() {

    }

    public BookInventory(String rackID, String title, String publisher, int day, String month) {
        this.book = new Book(rackID, title, publisher);
        this.returnDate = new Date(day, month);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public static boolean checkAvailabilty(BookInventory[] books, String title) {

        boolean available = false;

        int counter = 0;
        for (BookInventory b : books) {
            if (b != null) {
                if (b.getBook().getTitle().equals(title) && b.getReturnDate().getDay() == 0) {
                    counter++;
                }
            }
        }

        available = counter > 1;

        return available;

    }

    public void getBookByTitle(BookInventory[] books) {

        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Title: ");
        String title = scanner.nextLine();

        Book[] myBook = Book.countBooks(books, title);
        try {
            if (myBook.length == 0) {

                throw new NotAvailable(
                        "\nUnfortunately your searched book isn´t available in inventory of this library\n");
            } else {
                System.out.println("\nBook is available and can be found in rack: " + myBook[0].getRackID()
                        + "\nIn total there are " + myBook.length + " copies available");
            }

        } catch (NotAvailable e) {
            System.out.println(e.getMessage());
        }

    }

    public void addBookToInventory(BookInventory[] books) {

        System.out.println("\nPlease enter the necessary information in the fields below");
        int min = Book.minRacks(books);
        int max = Book.maxRacks(books);
        Random random = new Random();
        String rackID = String.valueOf(random.nextInt(max + 1) + min);
        System.out.print("Title: ");
        String title = scanner.nextLine();
        String publisher = null;

        Book[] myBook = Book.countBooks(books, title);

        int counterBefore = 0;
        int counterAfter = 0;

        try {
            if (myBook.length >= 2) {
                throw new MaxAdditionOfBook(
                        "\nUnfortunately we can only have up to three copies each book.\nWe have to regrettably decline the addition");

            } else {
                System.out.print("Publisher: ");
                publisher = scanner.nextLine();
                for (BookInventory b : books) {
                    if (b == null) {
                        b = new BookInventory(rackID, title, publisher, 0, null);
                        System.out.println("\nBook is successfully added to inventory\n");
                        break;
                    } else {
                        counterBefore++;
                    }
                }
            }

        } catch (MaxAdditionOfBook e) {
            System.out.println(e.getMessage());
        }

        for (BookInventory b : books) {
            if (b != null) {
                counterAfter++;
            }
        }

        if (counterAfter < counterBefore) {
            System.out.println(
                    "\nUnfortunately we don´t have any capacity in our library and can´t add any new book to inventory\n");
        }
    }

    public void borrowBook(BookInventory[] books) throws ParseException {

        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        boolean valid = false;

        for (BookInventory bookInventory : books) {
            if (bookInventory != null) {
                if (bookInventory.getBook().getTitle().equals(title) && bookInventory.getReturnDate().getDay() == 0) {
                    valid = BookInventory.checkAvailabilty(books, title);

                    if (valid) {
                        System.out.println("\nPlease select one lend period below");
                        System.out.println(
                                "[2 Weeks = 1]\t[2 Months = 2]\n[4 Weeks = 3]\t[3 Months = 4]\n[6 Weeks = 5]\t[4 Months = 6]");
                        System.out.print("Period of lend: ");
                        int input = scanner.nextInt();
                        Date returnDate = Date.returnDate(input);
                        bookInventory.setReturnDate(returnDate);
                        System.out.println("\nBook successfully lent\n");
                        System.out.println("Title: " + bookInventory.getBook().getTitle());
                        System.out.println("Publisher: " + bookInventory.getBook().getPublisher());
                        System.out.println("Return Date: " + bookInventory.getReturnDate().getDay() + " "
                                + bookInventory.getReturnDate().getMonth());
                        break;

                    }

                }
            }
        }
    }

    public void display(BookInventory[] books) {
        System.out.println("\nAll Books in Inventory");

        int id = 1;

        for (BookInventory b : books) {
            if (b != null) {
                System.out.println("\nID: " + id);
                System.out.println("Rack ID: " + b.getBook().getRackID());
                System.out.println("Title: " + b.getBook().getTitle());
                System.out.println("Publisher: " + b.getBook().getPublisher());
                if (b.getReturnDate().getDay() != 0) {
                    System.out
                            .println("Return date: " + b.getReturnDate().getDay() + " " + b.getReturnDate().getMonth());
                }
                id++;
            }
        }
    }
}
