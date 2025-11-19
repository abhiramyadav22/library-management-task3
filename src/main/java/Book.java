import java.util.UUID;

public class Book {
    private final String id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.id = UUID.randomUUID().toString().split("-")[0]; // short id
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issue() {
        isIssued = true;
    }

    public void returned() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s %s", id, title, author, (isIssued ? "(Issued)" : ""));
    }
}
