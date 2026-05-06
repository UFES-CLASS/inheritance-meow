/**
 * Superclass representing a person in the library system.
 *
 * This is the parent class for both Member and Librarian.
 * It holds the shared attributes (id and name) and defines
 * common methods that subclasses will override.
 *
 * Attributes:
 *   - id   : unique identifier (e.g. "M001", "L001")
 *   - name : full name of the person
 */
public class Person {

    // protected allows subclasses (Member, Librarian) to access these directly
    protected String id;
    protected String name;

    /**
     * Creates a Person with the given ID and name.
     *
     * @param id   unique identifier
     * @param name full name
     */
    public Person(String id, String name) {
        this.id   = id;
        this.name = name;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    /** Returns the person's ID. */
    public String getId()   { return id; }

    /** Returns the person's name. */
    public String getName() { return name; }

    // ── Setters ───────────────────────────────────────────────────────────────

    /** Updates the person's ID. */
    public void setId(String id)     { this.id   = id; }

    /** Updates the person's name. */
    public void setName(String name) { this.name = name; }

    /**
     * Returns a one-line summary of this person.
     * Subclasses OVERRIDE this to add their own details.
     * This is an example of OVERRIDING in OOP.
     *
     * @return info string
     */
    public String getInfo() {
        return "Person[" + id + "] " + name;
    }

    /**
     * Returns a readable string of this object.
     * Overridden by Member and Librarian.
     */
    @Override
    public String toString() {
        return getInfo();
    }
}
