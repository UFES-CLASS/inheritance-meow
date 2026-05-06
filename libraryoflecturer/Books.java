/**
 * Represents a book in the library collection.
 * INHERITANCE: Books extends LibraryItem (inherits itemId, title, available).
 *
 * Adds book-specific attributes: author and genre.
 * Overrides getInfo() and toString() from LibraryItem.
 *
 * Attributes (inherited from LibraryItem):
 *   - itemId    : unique book ID (e.g. "B001")
 *   - title     : title of the book
 *   - available : borrow availability
 *
 * Attributes (added here):
 *   - author : name of the book's author
 *   - genre  : genre/category of the book
 */
public class Books extends LibraryItem {

    // author is specific to Books — not in LibraryItem
    private String author;

    // genre is specific to Books — not in LibraryItem
    private String genre;

    // ── Constructors (OVERLOADING) ────────────────────────────────────────────
    // Two constructors with the same name but different parameters = OVERLOADING

    /**
     * Constructor 1: Creates a book with ID, title, and author only.
     * Genre defaults to "General".
     * OVERLOADING: This is the shorter version.
     *
     * @param bookId unique book ID
     * @param title  title of the book
     * @param author author of the book
     */
    public Books(String bookId, String title, String author) {
        // Call the parent (LibraryItem) constructor to set itemId and title
        super(bookId, title);
        this.author = author;
        this.genre  = "General";   // default genre when not specified
    }

    /**
     * Constructor 2: Creates a book with ID, title, author, AND genre.
     * OVERLOADING: This is the longer version with an extra parameter.
     *
     * @param bookId unique book ID
     * @param title  title of the book
     * @param author author of the book
     * @param genre  genre/category of the book
     */
    public Books(String bookId, String title, String author, String genre) {
        // Call the parent (LibraryItem) constructor to set itemId and title
        super(bookId, title);
        this.author = author;
        this.genre  = genre;
    }

    /**
     * Returns the pre-defined initial book data for the library.
     * Used by Librarian to pre-fill the catalog at startup.
     *
     * @return array of Books pre-filled with default catalog entries
     */
    public static Books[] getInitialBooks() {
        Books[] initial = new Books[5];
        // Using the 4-parameter constructor (with genre) — OVERLOADING demonstrated
        initial[0] = new Books("B001", "The Great Gatsby",       "F. Scott Fitzgerald", "Novel");
        initial[1] = new Books("B002", "To Kill a Mockingbird",  "Harper Lee",          "Drama");
        initial[2] = new Books("B003", "1984",                   "George Orwell",       "Dystopian");
        initial[3] = new Books("B004", "Brave New World",        "Aldous Huxley",       "Sci-Fi");
        initial[4] = new Books("B005", "The Catcher in the Rye", "J.D. Salinger",       "Novel");
        return initial;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /** Returns the book's unique ID (same as getItemId()). */
    public String getBookId() { return itemId; }   // itemId inherited from LibraryItem

    /** Returns the author of the book. */
    public String getAuthor() { return author; }

    /** Returns the genre of the book. */
    public String getGenre()  { return genre; }

    // ── Setters ───────────────────────────────────────────────────────────────

    /** Updates the author. */
    public void setAuthor(String author) { this.author = author; }

    /** Updates the genre. */
    public void setGenre(String genre)   { this.genre  = genre; }

    /**
     * OVERRIDING: Overrides getInfo() from LibraryItem.
     * Adds author and genre to the output.
     * Example: [B001] "The Great Gatsby" by F. Scott Fitzgerald | Genre: Novel (Available)
     */
    @Override
    public String getInfo() {
        String status = available ? "Available" : "Borrowed";
        return "[" + itemId + "] \"" + title + "\""
                + " by " + author
                + " | Genre: " + genre
                + " (" + status + ")";
    }

    /**
     * OVERRIDING: Overrides toString() from LibraryItem.
     * Delegates to getInfo() so the output is consistent.
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
