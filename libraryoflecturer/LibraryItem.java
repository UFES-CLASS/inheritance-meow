/**
 * Superclass representing an item in the library collection.
 *
 * This is the parent class for both Books and Multimedia.
 * It holds shared attributes (itemId, title, available) and defines
 * common methods that subclasses will override.
 *
 * Attributes:
 *   - itemId    : unique identifier (e.g. "B001", "MM001")
 *   - title     : title of the item
 *   - available : true if the item can be borrowed
 */
public class LibraryItem {

    // protected allows subclasses (Books, Multimedia) to access these directly
    protected String  itemId;
    protected String  title;
    protected boolean available;

    /**
     * Creates a LibraryItem with the given ID and title.
     * The item is available by default.
     *
     * @param itemId unique identifier
     * @param title  title of the item
     */
    public LibraryItem(String itemId, String title) {
        this.itemId    = itemId;
        this.title     = title;
        this.available = true;   // every new item starts as available
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /** Returns the item's unique ID. */
    public String getItemId()      { return itemId; }

    /** Returns the item's title. */
    public String getTitle()       { return title; }

    /** Returns true if the item is available to borrow. */
    public boolean isAvailable()   { return available; }

    // ── Setters ───────────────────────────────────────────────────────────────

    /** Updates the item ID. */
    public void setItemId(String itemId)       { this.itemId    = itemId; }

    /** Updates the title. */
    public void setTitle(String title)         { this.title     = title; }

    /** Sets whether the item is available. */
    public void setAvailable(boolean available){ this.available = available; }

    /**
     * Returns a one-line summary of this item.
     * Subclasses (Books, Multimedia) OVERRIDE this method.
     * This is an example of OVERRIDING in OOP.
     *
     * @return info string
     */
    public String getInfo() {
        String status = available ? "Available" : "Borrowed";
        return "[" + itemId + "] \"" + title + "\" (" + status + ")";
    }

    /**
     * Returns a readable string of this object.
     * Overridden by Books and Multimedia.
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
