package Week06.Task02;

import java.io.*;
import java.util.*;

public class BookInventory {
    private Book book;
    private Date returnDate;

    public BookInventory() {
    }

    public BookInventory(Book book, Date returnDate) {
        this.book = book;
        this.returnDate = returnDate;
    }

    public BookInventory(String rackNo, String title, String publisher, int day, String month) {
        this.book = new Book(rackNo, title, publisher);
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

    public static List<BookInventory> readBooksFromCSV() {

        List<BookInventory> books = new ArrayList<>();

        String pathMacOs = "src/Week06/Task02/BooksList.csv";
        String pathWindows = "src\\Week06\\Task02\\BooksList.csv";
        String path, line;
        String[][] genres = {{"1", "Fantasy"},
                {"2", "Education"},
                {"3", "Adventure"},
                {"4", "Novel"},
                {"5", "History"},
                {"6", "tech"},
                {"7", "Science"},
                {"8", "nonfiction"},
                {"9", "fiction"},
                {"10", "philosophy"}};


        String myOS = System.getProperty("os.name");

        if (myOS.startsWith("Windows")) {
            path = pathWindows;
        } else if (myOS.startsWith("Mac")) {
            path = pathMacOs;
        } else {
            path = null;
            System.out.println("\nError. OS cannot be determined\n");
            System.exit(1);
        }

        boolean readSuccessfully = false;

        try {
            BufferedReader readCSV = new BufferedReader(new FileReader(path));
            readCSV.readLine();

            while ((line = readCSV.readLine()) != null) {
                String[] values = line.split(";");
                System.out.println(values.length);

                for (String[] genre : genres) {
                    for (String s : genre) {
                        if (values[3].equals(s)) {
                            values[0] = genre[0];
                            break;
                        }
                    }
                }

                books.add(new BookInventory(
                        values[0], // RackNo
                        values[1], // Title
                        values[2], // Publisher
                        Integer.parseInt(values[4]), // Return Day
                        values[5])); // Return Month
            }
            readSuccessfully = books.size() > 0;
            readCSV.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(
                readSuccessfully ? "\n================================ IMPORT SUCCESSFUL ================================\n"
                        : "\n================================ IMPORT FAILED ================================\n");

        return books;

    }


    public void getBookByTitle(List<BookInventory> books) {
        Book selectedBook = Book.searchBook(books);

        if (selectedBook != null) {
            System.out.println("The Book is available at rack-no: " + selectedBook.getRackNo() + "\n");
        } else {
            System.out.println("The Book is not currently availabe at our inventory");
        }
    }

    public void addBookToInventory(List<BookInventory> books) {

        Book newBook = Book.createBook();

        long availability = books.stream()
                .filter(b -> b.getBook() == newBook)
                .count();

        if (availability < 4) {
            Date newDate = Date.createEmptyDate();
            BookInventory newBookInInventory = new BookInventory(newBook, newDate);
            books.add(newBookInInventory);
            System.out.println("\nBook is added successfully in inventory");
            System.out.println("Rack No: " + newBookInInventory.getBook().getRackNo());
            System.out.println("Title: " + newBookInInventory.getBook().getTitle());
            System.out.println("Publisher: " + newBookInInventory.getBook().getPublisher());
        } else {
            System.out.println("\nApparently it is not possible to this book to inventory\n3 Books with this title " + newBook.getTitle() + " at max");
        }
    }

    public void borrowBook(List<BookInventory> books) {

        Book borrowBook = Book.searchBook(books);
        Date borrowDate = new Date();


        long availability = books.stream()
                .filter(b -> b.getBook().equals(borrowBook) && b.getReturnDate().getDay() == 0)
                .count();

        if (availability > 0) {
            borrowDate = Date.createReturnDate();
        } else {
            System.out.println("\nApparently it is not possible to borrow this book " + borrowBook.getTitle() + "\nAt least one sample needs to stay at the inventory\n");
        }

        BookInventory newBookInInventory = new BookInventory(borrowBook, borrowDate);
        for (BookInventory b : books) {
            if (b.getBook().equals(borrowBook)) {
                b = newBookInInventory;
                System.out.println("\nBook successfully borrowed from inventory");
                String returnDateFormatted = Date.formatDate(b.getReturnDate());
                System.out.printf("\n\033[1m%-15s%-65s%-25s%-10s%-15s\033[0m\n", "Rack-No", "Title", "Publisher", " | ", "Return Date");
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%-15s%-65s%-25s%-10s%-15s\n",
                        b.getBook().getRackNo(),
                        b.getBook().getTitle(),
                        b.getBook().getPublisher(),
                        " | ",
                        returnDateFormatted);
                break;
            }
        }
    }

    public void display(List<BookInventory> books) {
        System.out.printf("\n\033[1m%-5s%-65s%-25s%-5s%-15s\033[0m\n", "Rack-No", "Title", "Publisher", " | ", "Return Date");
        System.out.println("------------------------------------------------------------------------------------------------");

        books.forEach(b -> {
            String returnDateFormatted = Date.formatDate(b.getReturnDate());
            System.out.printf("%-5s%-65s%-25s%-5s%-15s\n",
                    b.getBook().getRackNo(),
                    b.getBook().getTitle(),
                    b.getBook().getPublisher(),
                    " | ",
                    returnDateFormatted
            );
        });
        System.out.println("\nIn total we do have: " + books.size() + " in our inventroy\n");
    }

}
