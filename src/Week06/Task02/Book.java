package Week06.Task2;

public class Book {

    private String rackID, title, publisher;

    public Book() {

    }

    public Book(String rackID, String title, String publisher) {
        this.rackID = rackID;
        this.title = title;
        this.publisher = publisher;
    }

    /**
     * @return String
     */
    public String getRackID() {
        return rackID;
    }

    /**
     * @param rackID
     */
    public void setRackID(String rackID) {
        this.rackID = rackID;
    }

    /**
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * @param books
     * @param title
     * @return Book[]
     */
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

    /**
     * @param books
     * @return int
     */
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

    /**
     * @param books
     * @return int
     */
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
