/**
 * Represents a library member who can borrow, return, and search for items.
 * INHERITANCE: Member extends Person (inherits id and name).
 *
 * Adds member-specific attributes: borrowedItems array and borrowCount.
 * Overrides getInfo() and toString() from Person.
 *
 * Attributes (inherited from Person):
 *   - id   : unique member ID (e.g. "M001")
 *   - name : full name of the member
 *
 * Attributes (added here):
 *   - borrowedItems : array of LibraryItem currently borrowed
 *   - borrowCount   : number of items currently borrowed
 */
public class Member extends Person {

    // borrowedItems stores LibraryItem objects (can be Books OR Multimedia)
    // Using LibraryItem (superclass) allows polymorphism
    private LibraryItem[] borrowedItems;

    // borrowCount tracks how many items are currently borrowed
    private int borrowCount;

    // MAX_BORROW is the maximum number of items a member can borrow at once
    private static final int MAX_BORROW = 5;

    /**
     * Creates a new Member by calling the Person superclass constructor.
     * INHERITANCE: super(memberId, name) passes the values up to Person.
     *
     * @param memberId unique member ID
     * @param name     full name of the member
     */
    public Member(String memberId, String name) {
        // Call the parent (Person) constructor to set id and name
        super(memberId, name);

        // Initialize the borrowed items array with MAX_BORROW empty slots
        this.borrowedItems = new LibraryItem[MAX_BORROW];

        // No items borrowed yet
        this.borrowCount = 0;
    }

    /**
     * Returns the pre-defined initial member data for the library.
     *
     * @return array of Member pre-filled with default members
     */
    public static Member[] getInitialMembers() {
        Member[] initial = new Member[3];
        initial[0] = new Member("M001", "Alice");
        initial[1] = new Member("M002", "Bob");
        initial[2] = new Member("M003", "Charlie");
        return initial;
    }

    // ── Borrow & Return ───────────────────────────────────────────────────────

    /**
     * Borrows a LibraryItem (Book or Multimedia) for this member.
     * Uses LibraryItem (superclass) parameter = POLYMORPHISM in action.
     *
     * @param item the item to borrow (can be Books or Multimedia)
     * @return true if successful, false otherwise
     */
    public boolean borrowItem(LibraryItem item) {
        if (!item.isAvailable()) {
            System.out.println("  [FAILED] \"" + item.getTitle() + "\" is currently not available.");
            return false;
        }
        if (borrowCount >= MAX_BORROW) {
            System.out.println("  [FAILED] " + name + " has reached the borrow limit (" + MAX_BORROW + ").");
            return false;
        }
        borrowedItems[borrowCount++] = item;
        item.setAvailable(false);
        System.out.println("  [SUCCESS] " + name + " borrowed \"" + item.getTitle() + "\".");
        return true;
    }

    /**
     * Returns a borrowed LibraryItem back to the library.
     *
     * @param item the item to return
     * @return true if successful, false otherwise
     */
    public boolean returnItem(LibraryItem item) {
        for (int i = 0; i < borrowCount; i++) {
            if (borrowedItems[i] != null
                    && borrowedItems[i].getItemId().equals(item.getItemId())) {
                item.setAvailable(true);
                borrowedItems[i] = borrowedItems[--borrowCount];
                borrowedItems[borrowCount] = null;
                System.out.println("  [SUCCESS] " + name + " returned \"" + item.getTitle() + "\".");
                return true;
            }
        }
        System.out.println("  [FAILED] " + name + " does not have \"" + item.getTitle() + "\".");
        return false;
    }

    // ── searchItem (OVERLOADING) ──────────────────────────────────────────────
    // Two methods with the same name but different parameters = OVERLOADING

    /**
     * OVERLOADING Method 1: Search items by keyword in title.
     * Uses a keyword to filter matching items.
     *
     * @param catalog     full array of LibraryItem in the library
     * @param catalogSize number of valid entries in the catalog
     * @param keyword     search keyword to match against titles
     */
    public void searchItem(LibraryItem[] catalog, int catalogSize, String keyword) {
        System.out.println("  Search results for \"" + keyword + "\":");
        boolean found = false;
        for (int i = 0; i < catalogSize; i++) {
            if (catalog[i] != null
                    && catalog[i].getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("    -> " + catalog[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("    No items found matching \"" + keyword + "\".");
        }
    }

    /**
     * OVERLOADING Method 2: List ALL items in the catalog (no keyword filter).
     * Same method name, but takes no keyword parameter.
     *
     * @param catalog     full array of LibraryItem in the library
     * @param catalogSize number of valid entries in the catalog
     */
    public void searchItem(LibraryItem[] catalog, int catalogSize) {
        System.out.println("  All items in the library catalog:");
        if (catalogSize == 0) {
            System.out.println("    (empty)");
            return;
        }
        for (int i = 0; i < catalogSize; i++) {
            if (catalog[i] != null) {
                System.out.println("    -> " + catalog[i]);
            }
        }
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /** Returns the member's unique ID. */
    public String getMemberId()            { return id; }   // id is inherited from Person

    /** Returns the array of currently borrowed items. */
    public LibraryItem[] getBorrowedItems(){ return borrowedItems; }

    /** Returns the number of currently borrowed items. */
    public int getBorrowCount()            { return borrowCount; }

    /**
     * OVERRIDING: Overrides getInfo() from Person.
     * Adds borrow count to the output.
     * Example: Member[M001] Alice (borrowing: 1 item(s))
     */
    @Override
    public String getInfo() {
        return "Member[" + id + "] " + name
                + " (borrowing: " + borrowCount + " item(s))";
    }

    /**
     * OVERRIDING: Overrides toString() from Person.
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
