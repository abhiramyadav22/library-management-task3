import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private final String id;
    private String name;
    private List<String> issuedBookIds;

    public User(String name) {
        this.id = UUID.randomUUID().toString().split("-")[0];
        this.name = name;
        this.issuedBookIds = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getIssuedBookIds() {
        return issuedBookIds;
    }

    public void issueBook(String bookId) {
        issuedBookIds.add(bookId);
    }

    public boolean returnBook(String bookId) {
        return issuedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (Books issued: %d)", id, name, issuedBookIds.size());
    }
}
