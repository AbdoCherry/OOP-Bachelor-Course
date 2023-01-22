package Week09.Task04.Model;

public class Book {

    private String rackID, title, publisher;

    public Book() {

    }

    public Book(String rackID, String title, String publisher) {
        this.rackID = rackID;
        this.title = title;
        this.publisher = publisher;
    }

    public String getRackID() {
        return rackID;
    }

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public static Book[] countBooks(BookInventory[] books, String title) {

        int index = 0;
        for (BookInventory b : books) {
            if (b != null) {
                if (b.getBook().getTitle().equals(title)) {
                    index++;
                }
            }
        }

        Book[] extractedBook = new Book[index];

        int counter = 0;
        for (BookInventory b : books) {
            if (b != null) {
                if (b.getBook().getTitle().equals(title)) {
                    extractedBook[counter] = new Book(b.getBook().getRackID(), b.getBook().getTitle(),
                            b.getBook().getPublisher());
                    counter++;
                }
            }
        }

        return extractedBook;
    }

    public static int maxRacks(BookInventory[] books) {

        int max = 0;

        for (BookInventory b : books) {
            if (b != null) {
                if (max < Integer.parseInt(b.getBook().getRackID())) {
                    max = Integer.parseInt(b.getBook().getRackID());
                }
            }
        }
        return max;
    }

    public static int minRacks(BookInventory[] books) {

        int min = Integer.parseInt(books[0].getBook().getRackID());

        for (BookInventory b : books) {
            if (b != null) {
                if (min > Integer.parseInt(b.getBook().getRackID())) {
                    min = Integer.parseInt(b.getBook().getRackID());
                }
            }
        }
        return min;
    }

}
