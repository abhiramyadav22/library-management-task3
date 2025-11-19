import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Library {
    private final List<Book> books;
    private final List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Book operations
    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> listBooks() {
        return books;
    }

    public Optional<Book> findBookById(String id) {
        return books.stream().filter(b -> b.getId().equalsIgnoreCase(id)).findFirst();
    }

    // User operations
    public void addUser(User user) {
        users.add(user);
    }

    public List<User> listUsers() {
        return users;
    }

    public Optional<User> findUserById(String id) {
        return users.stream().filter(u -> u.getId().equalsIgnoreCase(id)).findFirst();
    }

    // Issue book
    public String issueBook(String bookId, String userId) {
        Optional<Book> ob = findBookById(bookId);
        if (!ob.isPresent()) return "Book not found.";

        Book book = ob.get();
        if (book.isIssued()) return "Book already issued.";

        Optional<User> ou = findUserById(userId);
        if (!ou.isPresent()) return "User not found.";

        User user = ou.get();
        book.issue();
        user.issueBook(bookId);
        return "Book issued successfully to " + user.getName() + ".";
    }

    // Return book
    public String returnBook(String bookId, String userId) {
        Optional<Book> ob = findBookById(bookId);
        if (!ob.isPresent()) return "Book not found.";

        Book book = ob.get();
        Optional<User> ou = findUserById(userId);
        if (!ou.isPresent()) return "User not found.";

        User user = ou.get();
        boolean removed = user.returnBook(bookId);
        if (!removed) return "This user did not issue that book.";

        book.returned();
        return "Book returned successfully.";
    }
}
