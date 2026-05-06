import java.util.Scanner;

/**
 * Entry point for the Library Borrowing System (Updated with Inheritance).
 *
 * Changes from previous version:
 *   - Borrow/Return now supports both Books AND Multimedia (POLYMORPHISM)
 *   - Added menu options for Multimedia management
 *   - addBook and addMultimedia demonstrate OVERLOADING (with/without optional fields)
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("  Library Borrowing System");
        System.out.println("  (Inheritance + Overloading + Overriding)");
        System.out.println("============================================\n");

        Scanner sc = new Scanner(System.in);

        // Librarian now extends Person — still created the same way
        Librarian librarian = new Librarian("L001", "Mrs. Smith");

        System.out.println("=== Welcome, " + librarian.getName() + "! ===\n");

        int choice = -1;

        while (choice != 0) {

            System.out.println("\n============================================");
            System.out.println("        LIBRARY BORROWING SYSTEM          ");
            System.out.println("============================================");
            System.out.println(" --- Book Management ---");
            System.out.println("  1. Add Book");
            System.out.println("  2. Remove Book");
            System.out.println("  3. Update Book");
            System.out.println("  4. View All Books");
            System.out.println(" --- Multimedia Management ---");
            System.out.println("  5. Add Multimedia");
            System.out.println("  6. View All Multimedia");
            System.out.println(" --- Member Management ---");
            System.out.println("  7. Register Member");
            System.out.println("  8. Remove Member");
            System.out.println("  9. Update Member Name");
            System.out.println(" 10. View All Members");
            System.out.println(" --- Borrow & Return ---");
            System.out.println(" 11. Borrow Item (Book or Multimedia)");
            System.out.println(" 12. Return Item");
            System.out.println(" 13. Search Item by Title");
            System.out.println(" 14. View All Borrow Records");
            System.out.println("--------------------------------------------");
            System.out.println("  0. Exit");
            System.out.println("============================================");
            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
            } else {
                sc.nextLine();
                System.out.println("  Please enter a number from the menu.");
                continue;
            }

            switch (choice) {

                // ── 1. Add Book ───────────────────────────────────────────────
                case 1:
                    System.out.print("  Book ID   : ");
                    String newId = sc.nextLine().trim();
                    System.out.print("  Title     : ");
                    String newTitle = sc.nextLine().trim();
                    System.out.print("  Author    : ");
                    String newAuthor = sc.nextLine().trim();
                    System.out.print("  Genre (press Enter to skip): ");
                    String newGenre = sc.nextLine().trim();

                    if (newId.isEmpty() || newTitle.isEmpty() || newAuthor.isEmpty()) {
                        System.out.println("  [FAILED] ID, Title, and Author are required.");
                    } else if (newGenre.isEmpty()) {
                        // OVERLOADING: call addBook with 3 params (no genre)
                        librarian.addBook(newId, newTitle, newAuthor);
                    } else {
                        // OVERLOADING: call addBook with 4 params (with genre)
                        librarian.addBook(newId, newTitle, newAuthor, newGenre);
                    }
                    break;

                // ── 2. Remove Book ────────────────────────────────────────────
                case 2:
                    librarian.displayCatalog();
                    System.out.print("  Enter Book ID to remove: ");
                    librarian.removeBook(sc.nextLine().trim());
                    break;

                // ── 3. Update Book ────────────────────────────────────────────
                case 3:
                    librarian.displayCatalog();
                    System.out.print("  Enter Book ID to update: ");
                    String updateBookId = sc.nextLine().trim();
                    System.out.print("  New Title  (Enter to keep): ");
                    String updTitle = sc.nextLine();
                    System.out.print("  New Author (Enter to keep): ");
                    String updAuthor = sc.nextLine();
                    librarian.updateBook(updateBookId, updTitle, updAuthor);
                    break;

                // ── 4. View All Books ─────────────────────────────────────────
                case 4:
                    librarian.displayCatalog();
                    break;

                // ── 5. Add Multimedia ─────────────────────────────────────────
                case 5:
                    System.out.print("  Multimedia ID  : ");
                    String mmId = sc.nextLine().trim();
                    System.out.print("  Title          : ");
                    String mmTitle = sc.nextLine().trim();
                    System.out.print("  Type (DVD/CD/Blu-ray): ");
                    String mmType = sc.nextLine().trim();
                    System.out.print("  Duration (press Enter to skip): ");
                    String mmDur = sc.nextLine().trim();

                    if (mmId.isEmpty() || mmTitle.isEmpty() || mmType.isEmpty()) {
                        System.out.println("  [FAILED] ID, Title, and Type are required.");
                    } else if (mmDur.isEmpty()) {
                        // OVERLOADING: call addMultimedia with 3 params (no duration)
                        librarian.addMultimedia(mmId, mmTitle, mmType);
                    } else {
                        // OVERLOADING: call addMultimedia with 4 params (with duration)
                        librarian.addMultimedia(mmId, mmTitle, mmType, mmDur);
                    }
                    break;

                // ── 6. View All Multimedia ────────────────────────────────────
                case 6:
                    librarian.displayMultimedia();
                    break;

                // ── 7. Register Member ────────────────────────────────────────
                case 7:
                    System.out.print("  Member ID : ");
                    String newMemberId = sc.nextLine().trim();
                    System.out.print("  Name      : ");
                    String newMemberName = sc.nextLine().trim();
                    if (newMemberId.isEmpty() || newMemberName.isEmpty()) {
                        System.out.println("  [FAILED] All fields are required.");
                    } else {
                        librarian.registerMember(newMemberId, newMemberName);
                    }
                    break;

                // ── 8. Remove Member ──────────────────────────────────────────
                case 8:
                    librarian.displayMembers();
                    System.out.print("  Enter Member ID to remove: ");
                    librarian.removeMember(sc.nextLine().trim());
                    break;

                // ── 9. Update Member Name ─────────────────────────────────────
                case 9:
                    librarian.displayMembers();
                    System.out.print("  Enter Member ID to update: ");
                    String updateMemberId = sc.nextLine().trim();
                    System.out.print("  New Name  : ");
                    String updatedName = sc.nextLine().trim();
                    librarian.updateMember(updateMemberId, updatedName);
                    break;

                // ── 10. View All Members ──────────────────────────────────────
                case 10:
                    librarian.displayMembers();
                    break;

                // ── 11. Borrow Item ───────────────────────────────────────────
                case 11:
                    librarian.displayMembers();
                    System.out.print("  Enter Member ID : ");
                    String borrowMemberId = sc.nextLine().trim();
                    Member borrower = librarian.findMemberById(borrowMemberId);

                    if (borrower == null) {
                        System.out.println("  [FAILED] Member not found.");
                    } else {
                        // Show both Books and Multimedia
                        librarian.displayCatalog();
                        librarian.displayMultimedia();

                        System.out.print("  Enter Item ID (Book ID or Multimedia ID): ");
                        String borrowItemId = sc.nextLine().trim();

                        // findItemById uses POLYMORPHISM — returns LibraryItem
                        LibraryItem itemToBorrow = librarian.findItemById(borrowItemId);

                        if (itemToBorrow == null) {
                            System.out.println("  [FAILED] Item not found.");
                        } else {
                            // borrowItem uses LibraryItem param — POLYMORPHISM
                            if (borrower.borrowItem(itemToBorrow)) {
                                librarian.recordBorrow(borrower, itemToBorrow);
                            }
                        }
                    }
                    break;

                // ── 12. Return Item ───────────────────────────────────────────
                case 12:
                    librarian.displayMembers();
                    System.out.print("  Enter Member ID : ");
                    String returnMemberId = sc.nextLine().trim();
                    Member returner = librarian.findMemberById(returnMemberId);

                    if (returner == null) {
                        System.out.println("  [FAILED] Member not found.");
                    } else if (returner.getBorrowCount() == 0) {
                        System.out.println("  " + returner.getName() + " has no borrowed items.");
                    } else {
                        System.out.println("  Items borrowed by " + returner.getName() + ":");
                        LibraryItem[] borrowed = returner.getBorrowedItems();
                        for (int i = 0; i < returner.getBorrowCount(); i++) {
                            System.out.println("    " + borrowed[i]);
                        }
                        System.out.print("  Enter Item ID to return: ");
                        String returnItemId = sc.nextLine().trim();

                        // findItemById returns LibraryItem (POLYMORPHISM)
                        LibraryItem itemToReturn = librarian.findItemById(returnItemId);

                        if (itemToReturn == null) {
                            System.out.println("  [FAILED] Item not found.");
                        } else {
                            if (returner.returnItem(itemToReturn)) {
                                librarian.recordReturn(returner, itemToReturn);
                            }
                        }
                    }
                    break;

                // ── 13. Search Item by Title ──────────────────────────────────
                case 13:
                    librarian.displayMembers();
                    System.out.print("  Enter Member ID : ");
                    String searchMemberId = sc.nextLine().trim();
                    Member searcher = librarian.findMemberById(searchMemberId);

                    if (searcher == null) {
                        System.out.println("  [FAILED] Member not found.");
                    } else {
                        // Build combined catalog (Books + Multimedia) as LibraryItem[]
                        int totalSize = librarian.getCatalogCount() + librarian.getMultimediaCount();
                        LibraryItem[] allItems = new LibraryItem[totalSize];
                        int idx = 0;
                        for (int i = 0; i < librarian.getCatalogCount(); i++) {
                            allItems[idx++] = librarian.getCatalog()[i];
                        }
                        for (int i = 0; i < librarian.getMultimediaCount(); i++) {
                            allItems[idx++] = librarian.getMultimedia()[i];
                        }

                        System.out.print("  Enter keyword (or Enter to list all): ");
                        String keyword = sc.nextLine().trim();

                        if (keyword.isEmpty()) {
                            // OVERLOADING: call searchItem with 2 params (list all)
                            searcher.searchItem(allItems, totalSize);
                        } else {
                            // OVERLOADING: call searchItem with 3 params (filter by keyword)
                            searcher.searchItem(allItems, totalSize, keyword);
                        }
                    }
                    break;

                // ── 14. View All Borrow Records ───────────────────────────────
                case 14:
                    librarian.displayAllRecords();
                    break;

                // ── 0. Exit ───────────────────────────────────────────────────
                case 0:
                    System.out.println("\n  Goodbye! Library system closed.");
                    break;

                default:
                    System.out.println("  Invalid choice. Please enter a number from the menu.");
            }
        }

        sc.close();
    }
}
