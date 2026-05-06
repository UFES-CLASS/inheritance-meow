/**
 * Represents a single borrow transaction in the library system.
 *
 * Updated to use LibraryItem instead of Books, so it can record
 * the borrowing of both Books and Multimedia (POLYMORPHISM).
 *
 * Attributes:
 *   - recordId   : unique record ID (e.g. "REC001")
 *   - member     : the Member who borrowed the item
 *   - item       : the LibraryItem borrowed (Books or Multimedia)
 *   - borrowDate : date the item was borrowed
 *   - returnDate : date the item was returned ("-" if not yet)
 *   - returned   : true if the item has been returned
 */
public class BorrowRecord {

    private String      recordId;
    private Member      member;
    private LibraryItem item;       // changed from Books to LibraryItem (POLYMORPHISM)
    private String      borrowDate;
    private String      returnDate;
    private boolean     returned;

    /**
     * Creates a new BorrowRecord when a member borrows an item.
     *
     * @param recordId   unique record ID
     * @param member     the member borrowing the item
     * @param item       the LibraryItem being borrowed (Books or Multimedia)
     * @param borrowDate the date the item is borrowed
     */
    public BorrowRecord(String recordId, Member member, LibraryItem item, String borrowDate) {
        this.recordId   = recordId;
        this.member     = member;
        this.item       = item;
        this.borrowDate = borrowDate;
        this.returnDate = "-";      // not returned yet
        this.returned   = false;
    }

    // ── Getters ───────────────────────────────────────────────────────────────

    public String      getRecordId()   { return recordId;   }
    public Member      getMember()     { return member;     }
    public LibraryItem getItem()       { return item;       }
    public String      getBorrowDate() { return borrowDate; }
    public String      getReturnDate() { return returnDate; }
    public boolean     isReturned()    { return returned;   }

    // ── Setters ───────────────────────────────────────────────────────────────

    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public void setReturned(boolean returned)    { this.returned   = returned;   }

    /**
     * Returns a readable summary of this borrow record.
     * Example: Record[REC001] Member: Alice | Item: 1984 | Borrowed: 2026-04-28 | Returned: -
     */
    @Override
    public String toString() {
        return "Record[" + recordId + "]"
                + " Member: "    + member.getName()
                + " | Item: "    + item.getTitle()
                + " | Borrowed: " + borrowDate
                + " | Returned: " + returnDate;
    }
}
