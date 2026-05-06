/**
 * Represents a multimedia item (DVD, CD, etc.) in the library collection.
 * INHERITANCE: Multimedia extends LibraryItem (inherits itemId, title, available).
 *
 * Adds multimedia-specific attributes: type and duration.
 * Overrides getInfo() and toString() from LibraryItem.
 *
 * Attributes (inherited from LibraryItem):
 *   - itemId    : unique multimedia ID (e.g. "MM001")
 *   - title     : title of the multimedia
 *   - available : borrow availability
 *
 * Attributes (added here):
 *   - type     : type of multimedia (e.g. "DVD", "CD", "Blu-ray")
 *   - duration : length of the content (e.g. "120 min", "45 min")
 */
public class Multimedia extends LibraryItem {

    // type is specific to Multimedia (e.g. "DVD", "CD")
    private String type;

    // duration is specific to Multimedia (e.g. "120 min")
    private String duration;

    // ── Constructors (OVERLOADING) ────────────────────────────────────────────
    // Two constructors with the same name but different parameters = OVERLOADING

    /**
     * Constructor 1: Creates a multimedia item with ID, title, and type only.
     * Duration defaults to "Unknown".
     * OVERLOADING: This is the shorter version.
     *
     * @param itemId unique multimedia ID
     * @param title  title of the multimedia
     * @param type   type (DVD, CD, Blu-ray, etc.)
     */
    public Multimedia(String itemId, String title, String type) {
        // Call the parent (LibraryItem) constructor to set itemId and title
        super(itemId, title);
        this.type     = type;
        this.duration = "Unknown";   // default when duration is not specified
    }

    /**
     * Constructor 2: Creates a multimedia item with ID, title, type, AND duration.
     * OVERLOADING: This is the longer version with an extra parameter.
     *
     * @param itemId   unique multimedia ID
     * @param title    title of the multimedia
     * @param type     type (DVD, CD, Blu-ray, etc.)
     * @param duration duration of the content (e.g. "120 min")
     */
    public Multimedia(String itemId, String title, String type, String duration) {
        // Call the parent (LibraryItem) constructor to set itemId and title
        super(itemId, title);
        this.type     = type;
        this.duration = duration;
    }

    /**
     * Returns the pre-defined initial multimedia data for the library.
     * Used by Librarian to pre-fill the multimedia list at startup.
     *
     * @return array of Multimedia pre-filled with default entries
     */
    public static Multimedia[] getInitialMultimedia() {
        Multimedia[] initial = new Multimedia[3];
        // Using the 4-parameter constructor (with duration) — OVERLOADING demonstrated
        initial[0] = new Multimedia("MM001", "Planet Earth II",    "DVD",    "60 min");
        initial[1] = new Multimedia("MM002", "The Beatles 1",      "CD",     "75 min");
        initial[2] = new Multimedia("MM003", "Inception",          "Blu-ray","148 min");
        return initial;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /** Returns the type of multimedia. */
    public String getType()     { return type; }

    /** Returns the duration of the content. */
    public String getDuration() { return duration; }

    // ── Setters ───────────────────────────────────────────────────────────────

    /** Updates the type. */
    public void setType(String type)         { this.type     = type; }

    /** Updates the duration. */
    public void setDuration(String duration) { this.duration = duration; }

    /**
     * OVERRIDING: Overrides getInfo() from LibraryItem.
     * Adds type and duration to the output.
     * Example: [MM001] "Planet Earth II" | Type: DVD | Duration: 60 min (Available)
     */
    @Override
    public String getInfo() {
        String status = available ? "Available" : "Borrowed";
        return "[" + itemId + "] \"" + title + "\""
                + " | Type: "     + type
                + " | Duration: " + duration
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
