package Week06.Task02;

import java.util.*;

public class Book {
    private String rackNo, title, publisher;

    public Book() {
    }

    public Book(String rackNo, String title, String publisher) {
        this.rackNo = rackNo;
        this.title = title;
        this.publisher = publisher;
    }

    public String getRackNo() {
        return rackNo;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public static Book searchBook(List<BookInventory> books) {
        Scanner selectBook = new Scanner(System.in);

        System.out.println("\nPlease enter the necessary information in the fields below");
        System.out.print("Title: ");
        String searchTitle = selectBook.nextLine().toLowerCase();

        List<Book> extractedBooksList = books.stream()
                .filter(b -> b.getReturnDate().getDay() == 0 && (b.getBook().getTitle().equalsIgnoreCase(searchTitle)
                        || b.getBook().getTitle().contains(searchTitle)))
                .map(BookInventory::getBook).distinct().toList();

        int bookDecision = 1;
        if (extractedBooksList.size() > 0) {
            System.out.printf("\n\033[1m%-10s%-10s%-65s%-25s\033[0m\n", "ID", "RackNo", "Title", "Publisher");
            System.out.println(
                    "------------------------------------------------------------------------------------------------");

            int counter = 1;
            for (Book b : extractedBooksList) {
                System.out.printf("%-10s%-10s%-65s%-25s\n", counter++, b.getRackNo(), b.getTitle(), b.getPublisher());
            }

            System.out.print("\nPlease select book by ID: ");
            bookDecision = selectBook.nextInt();
            return extractedBooksList.get(bookDecision - 1);
        } else {
            System.out.println("\nBook not available or in inventory");

            return null;
        }

    }

    public static String genres() {

        Scanner genreScanner = new Scanner(System.in);

        Map<Integer, String> genresMap = new HashMap<Integer, String>();
        genresMap.put(1, "Fantasy");
        genresMap.put(2, "Education");
        genresMap.put(3, "Adventure");
        genresMap.put(4, "Novel");
        genresMap.put(5, "History");
        genresMap.put(6, "Tech");
        genresMap.put(7, "Science");
        genresMap.put(8, "Nonfiction");
        genresMap.put(9, "Fiction");
        genresMap.put(10, "Philosophy");

        System.out.printf("\n\033[1m%-5s%-15s\033[0m\n", "Key", "Genre");
        System.out.println("--------------------------------");

        for (Map.Entry<Integer, String> entry : genresMap.entrySet()) {
            System.out.printf("%-5d%-15s\n", entry.getKey(), entry.getValue());
        }

        System.out.print("\nChoose genres: ");
        return genresMap.get(genreScanner.nextInt());
    }

    public static Book createBook() {
        Scanner createScanner = new Scanner(System.in);
        System.out.println("\nPlease enter the necessary information in the fields below");

        System.out.print("Title: ");
        String createTitle = createScanner.nextLine();

        System.out.print("Publisher: ");
        String createPublisher = createScanner.nextLine();

        String createRackNo = genres();

        return new Book(createRackNo, createTitle, createPublisher);
    }
}