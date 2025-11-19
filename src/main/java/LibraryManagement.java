import java.util.List;
import java.util.Scanner;

public class LibraryManagement {
    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {
        seedSampleData(); // optional demo data
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1": addBook(); break;
                case "2": listBooks(); break;
                case "3": addUser(); break;
                case "4": listUsers(); break;
                case "5": issueBook(); break;
                case "6": returnBook(); break;
                case "7": bookDetails(); break;
                case "8": userDetails(); break;
                case "0":
                    System.out.println("Exiting. Bye.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println(); // blank line for readability
        }
    }

    private static void printMenu() {
        System.out.println("=== Library Management ===");
        System.out.println("1. Add Book");
        System.out.println("2. List Books");
        System.out.println("3. Add User");
        System.out.println("4. List Users");
        System.out.println("5. Issue Book");
        System.out.println("6. Return Book");
        System.out.println("7. Show Book Details");
        System.out.println("8. Show User Details");
        System.out.println("0. Exit");
        System.out.print("Enter choice: ");
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine().trim();
        System.out.print("Enter author: ");
        String author = sc.nextLine().trim();
        if (title.isEmpty() || author.isEmpty()) {
            System.out.println("Title and author cannot be empty.");
            return;
        }
        Book b = new Book(title, author);
        library.addBook(b);
        System.out.println("Book added: " + b);
    }

    private static void listBooks() {
        List<Book> books = library.listBooks();
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        System.out.println("--- Books ---");
        books.forEach(System.out::println);
    }

    private static void addUser() {
        System.out.print("Enter user name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        User u = new User(name);
        library.addUser(u);
        System.out.println("User added: " + u);
    }

    private static void listUsers() {
        List<User> users = library.listUsers();
        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        System.out.println("--- Users ---");
        users.forEach(System.out::println);
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        String bookId = sc.nextLine().trim();
        System.out.print("Enter User ID to issue to: ");
        String userId = sc.nextLine().trim();
        String result = library.issueBook(bookId, userId);
        System.out.println(result);
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        String bookId = sc.nextLine().trim();
        System.out.print("Enter User ID returning: ");
        String userId = sc.nextLine().trim();
        String result = library.returnBook(bookId, userId);
        System.out.println(result);
    }

    private static void bookDetails() {
        System.out.print("Enter Book ID: ");
        String bookId = sc.nextLine().trim();
        library.findBookById(bookId).ifPresentOrElse(
                b -> System.out.println(b + (b.isIssued() ? " -- currently issued" : " -- available")),
                () -> System.out.println("Book not found.")
        );
    }

    private static void userDetails() {
        System.out.print("Enter User ID: ");
        String userId = sc.nextLine().trim();
        library.findUserById(userId).ifPresentOrElse(
                u -> {
                    System.out.println(u);
                    if (u.getIssuedBookIds().isEmpty()) {
                        System.out.println("No books issued.");
                    } else {
                        System.out.println("Issued Book IDs:");
                        u.getIssuedBookIds().forEach(id -> System.out.println(" - " + id));
                    }
                },
                () -> System.out.println("User not found.")
        );
    }

    private static void seedSampleData() {
        // optional: helpful for demo/testing
        library.addBook(new Book("Clean Code", "Robert C. Martin"));
        library.addBook(new Book("Introduction to Algorithms", "Cormen et al."));
        library.addUser(new User("Alice"));
        library.addUser(new User("Bob"));
    }
}
