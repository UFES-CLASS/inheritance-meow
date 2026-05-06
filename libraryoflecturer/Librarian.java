/**
 * Represents the librarian who manages the library system.
 * INHERITANCE: Librarian extends Person (inherits id and name).
 *
 * The Librarian manages:
 *   - catalog[]      : array of Books
 *   - multimedia[]   : array of Multimedia
 *   - members[]      : array of Member
 *   - borrowRecords[]: array of BorrowRecord
 *
 * Overrides getInfo() and toString() from Person.
 * Demonstrates OVERLOADING via addBook() and addMultimedia() variants.
 */
public class Librarian extends Person {

    // catalog holds all Books in the library
    private Books[]       catalog;
    private int           catalogCount;

    // multimedia holds all Multimedia items in the library
    private Multimedia[]  multimedia;
    private int           multimediaCount;

    // members holds all registered Members
    private Member[]      members;
    private int           memberCount;

    // borrowRecords holds all borrow/return transactions
    private BorrowRecord[] borrowRecords;
    private int            recordCount;

    private static final int MAX_BOOKS      = 100;
    private static final int MAX_MULTIMEDIA = 50;
    private static final int MAX_MEMBERS    = 50;
    private static final int MAX_RECORDS    = 200;

    /**
     * Creates a Librarian and auto-loads initial data.
     * INHERITANCE: super(librarianId, name) calls Person's constructor.
     *
     * @param librarianId unique librarian ID
     * @param name        full name of the librarian
     */
    public Librarian(String librarianId, String name) {
        // Pass id and name up to the Person superclass
        super(librarianId, name);

        this.catalog        = new Books[MAX_BOOKS];
        this.catalogCount   = 0;

        this.multimedia     = new Multimedia[MAX_MULTIMEDIA];
        this.multimediaCount = 0;

        this.members        = new Member[MAX_MEMBERS];
        this.memberCount    = 0;

        this.borrowRecords  = new BorrowRecord[MAX_RECORDS];
        this.recordCount    = 0;

        loadInitialData();
    }

    /**
     * Loads initial Books, Multimedia, and Members from their respective classes.
     */
    private void loadInitialData() {
        // Load initial books
        Books[] initialBooks = Books.getInitialBooks();
        for (int i = 0; i < initialBooks.length; i++) {
            catalog[catalogCount++] = initialBooks[i];
        }

        // Load initial multimedia
        Multimedia[] initialMultimedia = Multimedia.getInitialMultimedia();
        for (int i = 0; i < initialMultimedia.length; i++) {
            multimedia[multimediaCount++] = initialMultimedia[i];
        }

        // Load initial members
        Member[] initialMembers = Member.getInitialMembers();
        for (int i = 0; i < initialMembers.length; i++) {
            members[memberCount++] = initialMembers[i];
        }
    }

    // ── addBook (OVERLOADING) ─────────────────────────────────────────────────
    // Two addBook methods with the same name, different parameters = OVERLOADING

    /**
     * OVERLOADING Method 1: Adds a book with ID, title, and author only.
     * Genre defaults to "General" inside the Books constructor.
     *
     * @param bookId unique book ID
     * @param title  title of the book
     * @param author author of the book
     * @return the created Books object, or null if failed
     */
    public Books addBook(String bookId, String title, String author) {
        if (catalogCount >= MAX_BOOKS) {
            System.out.println("  [FAILED] Catalog is full.");
            return null;
        }
        if (findBookById(bookId) != null) {
            System.out.println("  [FAILED] Book ID \"" + bookId + "\" already exists.");
            return null;
        }
        Books book = new Books(bookId, title, author);   // calls 3-param constructor
        catalog[catalogCount++] = book;
        System.out.println("  [ADDED] " + book);
        return book;
    }

    /**
     * OVERLOADING Method 2: Adds a book with ID, title, author, AND genre.
     *
     * @param bookId unique book ID
     * @param title  title of the book
     * @param author author of the book
     * @param genre  genre/category of the book
     * @return the created Books object, or null if failed
     */
    public Books addBook(String bookId, String title, String author, String genre) {
        if (catalogCount >= MAX_BOOKS) {
            System.out.println("  [FAILED] Catalog is full.");
            return null;
        }
        if (findBookById(bookId) != null) {
            System.out.println("  [FAILED] Book ID \"" + bookId + "\" already exists.");
            return null;
        }
        Books book = new Books(bookId, title, author, genre);   // calls 4-param constructor
        catalog[catalogCount++] = book;
        System.out.println("  [ADDED] " + book);
        return book;
    }

    /**
     * Removes a book from the catalog by its ID.
     */
    public boolean removeBook(String bookId) {
        for (int i = 0; i < catalogCount; i++) {
            if (catalog[i].getBookId().equals(bookId)) {
                if (!catalog[i].isAvailable()) {
                    System.out.println("  [FAILED] Cannot remove a book that is currently borrowed.");
                    return false;
                }
                for (int j = i; j < catalogCount - 1; j++) {
                    catalog[j] = catalog[j + 1];
                }
                catalog[--catalogCount] = null;
                System.out.println("  [REMOVED] Book ID \"" + bookId + "\" removed.");
                return true;
            }
        }
        System.out.println("  [FAILED] Book ID \"" + bookId + "\" not found.");
        return false;
    }

    /**
     * Updates the title and/or author of an existing book.
     */
    public boolean updateBook(String bookId, String newTitle, String newAuthor) {
        Books book = findBookById(bookId);
        if (book == null) {
            System.out.println("  [FAILED] Book ID \"" + bookId + "\" not found.");
            return false;
        }
        if (!newTitle.trim().isEmpty())  book.setTitle(newTitle.trim());
        if (!newAuthor.trim().isEmpty()) book.setAuthor(newAuthor.trim());
        System.out.println("  [UPDATED] " + book);
        return true;
    }

    /** Finds a book by its ID. Returns null if not found. */
    public Books findBookById(String bookId) {
        for (int i = 0; i < catalogCount; i++) {
            if (catalog[i].getBookId().equals(bookId)) return catalog[i];
        }
        return null;
    }

    // ── addMultimedia (OVERLOADING) ───────────────────────────────────────────

    /**
     * OVERLOADING Method 1: Adds multimedia with ID, title, and type only.
     * Duration defaults to "Unknown" inside the Multimedia constructor.
     *
     * @param itemId unique multimedia ID
     * @param title  title of the multimedia
     * @param type   type (DVD, CD, Blu-ray, etc.)
     * @return the created Multimedia object, or null if failed
     */
    public Multimedia addMultimedia(String itemId, String title, String type) {
        if (multimediaCount >= MAX_MULTIMEDIA) {
            System.out.println("  [FAILED] Multimedia list is full.");
            return null;
        }
        if (findMultimediaById(itemId) != null) {
            System.out.println("  [FAILED] Multimedia ID \"" + itemId + "\" already exists.");
            return null;
        }
        Multimedia mm = new Multimedia(itemId, title, type);   // calls 3-param constructor
        multimedia[multimediaCount++] = mm;
        System.out.println("  [ADDED] " + mm);
        return mm;
    }

    /**
     * OVERLOADING Method 2: Adds multimedia with ID, title, type, AND duration.
     *
     * @param itemId   unique multimedia ID
     * @param title    title of the multimedia
     * @param type     type (DVD, CD, Blu-ray, etc.)
     * @param duration duration of the content (e.g. "90 min")
     * @return the created Multimedia object, or null if failed
     */
    public Multimedia addMultimedia(String itemId, String title, String type, String duration) {
        if (multimediaCount >= MAX_MULTIMEDIA) {
            System.out.println("  [FAILED] Multimedia list is full.");
            return null;
        }
        if (findMultimediaById(itemId) != null) {
            System.out.println("  [FAILED] Multimedia ID \"" + itemId + "\" already exists.");
            return null;
        }
        Multimedia mm = new Multimedia(itemId, title, type, duration);   // calls 4-param constructor
        multimedia[multimediaCount++] = mm;
        System.out.println("  [ADDED] " + mm);
        return mm;
    }

    /** Finds a Multimedia item by its ID. Returns null if not found. */
    public Multimedia findMultimediaById(String itemId) {
        for (int i = 0; i < multimediaCount; i++) {
            if (multimedia[i].getItemId().equals(itemId)) return multimedia[i];
        }
        return null;
    }

    /**
     * Finds any LibraryItem (Books or Multimedia) by its ID.
     * Demonstrates POLYMORPHISM — returns the shared superclass type.
     *
     * @param itemId the ID to search for
     * @return the matching LibraryItem, or null if not found
     */
    public LibraryItem findItemById(String itemId) {
        Books b = findBookById(itemId);
        if (b != null) return b;
        Multimedia m = findMultimediaById(itemId);
        if (m != null) return m;
        return null;
    }

    // ── Member Management ─────────────────────────────────────────────────────

    /** Registers a new Member into the system. */
    public Member registerMember(String memberId, String name) {
        if (memberCount >= MAX_MEMBERS) {
            System.out.println("  [FAILED] Member limit reached.");
            return null;
        }
        if (findMemberById(memberId) != null) {
            System.out.println("  [FAILED] Member ID \"" + memberId + "\" already exists.");
            return null;
        }
        Member member = new Member(memberId, name);
        members[memberCount++] = member;
        System.out.println("  [REGISTERED] " + member);
        return member;
    }

    /** Removes a member by their ID. */
    public boolean removeMember(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equals(memberId)) {
                if (members[i].getBorrowCount() > 0) {
                    System.out.println("  [FAILED] Cannot remove a member who still has borrowed items.");
                    return false;
                }
                for (int j = i; j < memberCount - 1; j++) {
                    members[j] = members[j + 1];
                }
                members[--memberCount] = null;
                System.out.println("  [REMOVED] Member ID \"" + memberId + "\" removed.");
                return true;
            }
        }
        System.out.println("  [FAILED] Member ID \"" + memberId + "\" not found.");
        return false;
    }

    /** Updates a member's name. */
    public boolean updateMember(String memberId, String newName) {
        Member member = findMemberById(memberId);
        if (member == null) {
            System.out.println("  [FAILED] Member ID \"" + memberId + "\" not found.");
            return false;
        }
        if (!newName.trim().isEmpty()) {
            member.setName(newName.trim());
            System.out.println("  [UPDATED] " + member);
            return true;
        }
        System.out.println("  [FAILED] New name cannot be empty.");
        return false;
    }

    /** Finds a member by their ID. Returns null if not found. */
    public Member findMemberById(String memberId) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getMemberId().equals(memberId)) return members[i];
        }
        return null;
    }

    // ── Borrow Record Management ──────────────────────────────────────────────

    /** Creates and stores a new BorrowRecord using LibraryItem (POLYMORPHISM). */
    public void recordBorrow(Member member, LibraryItem item) {
        if (recordCount >= MAX_RECORDS) return;
        String recordId = "REC" + String.format("%03d", recordCount + 1);
        borrowRecords[recordCount++] = new BorrowRecord(recordId, member, item, "2026-04-28");
    }

    /** Marks an open borrow record as returned. */
    public void recordReturn(Member member, LibraryItem item) {
        for (int i = 0; i < recordCount; i++) {
            BorrowRecord r = borrowRecords[i];
            if (!r.isReturned()
                    && r.getMember().getMemberId().equals(member.getMemberId())
                    && r.getItem().getItemId().equals(item.getItemId())) {
                r.setReturnDate("2026-04-28");
                r.setReturned(true);
                return;
            }
        }
    }

    // ── Display Helpers ───────────────────────────────────────────────────────

    /** Prints all books in the catalog. */
    public void displayCatalog() {
        System.out.println("  ---- Book Catalog (" + catalogCount + " book(s)) ----");
        if (catalogCount == 0) { System.out.println("  (empty)"); return; }
        for (int i = 0; i < catalogCount; i++) System.out.println("  " + catalog[i]);
    }

    /** Prints all multimedia items. */
    public void displayMultimedia() {
        System.out.println("  ---- Multimedia (" + multimediaCount + " item(s)) ----");
        if (multimediaCount == 0) { System.out.println("  (empty)"); return; }
        for (int i = 0; i < multimediaCount; i++) System.out.println("  " + multimedia[i]);
    }

    /** Prints all registered members. */
    public void displayMembers() {
        System.out.println("  ---- Registered Members (" + memberCount + " member(s)) ----");
        if (memberCount == 0) { System.out.println("  (empty)"); return; }
        for (int i = 0; i < memberCount; i++) System.out.println("  " + members[i]);
    }

    /** Prints all borrow records. */
    public void displayAllRecords() {
        System.out.println("  ---- Borrow Records (" + recordCount + " total) ----");
        if (recordCount == 0) { System.out.println("  (none yet)"); return; }
        for (int i = 0; i < recordCount; i++) System.out.println("  " + borrowRecords[i]);
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public String        getLibrarianId()      { return id; }    // id inherited from Person
    public Books[]       getCatalog()           { return catalog; }
    public int           getCatalogCount()      { return catalogCount; }
    public Multimedia[]  getMultimedia()        { return multimedia; }
    public int           getMultimediaCount()   { return multimediaCount; }
    public Member[]      getMembers()           { return members; }
    public int           getMemberCount()       { return memberCount; }
    public BorrowRecord[]getBorrowRecords()     { return borrowRecords; }
    public int           getRecordCount()       { return recordCount; }

    /**
     * OVERRIDING: Overrides getInfo() from Person.
     * Example: Librarian[L001] Mrs. Smith
     */
    @Override
    public String getInfo() {
        return "Librarian[" + id + "] " + name;
    }

    /**
     * OVERRIDING: Overrides toString() from Person.
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
