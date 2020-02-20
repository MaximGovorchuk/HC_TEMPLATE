import java.io.PrintStream;
import java.util.*;
import java.util.stream.Collectors;

public class Result {

    final Map<Integer, Long> bookScore = new HashMap<>();
    LinkedHashMap<Library, List<Book>> libraryBooks = new LinkedHashMap<>();

    public Result(List<Book> books) {
        books.forEach(b -> {
            bookScore.put(b.id, b.score);
        });
    }

    public void signUp(Library library) {
        // System.err.println("Signing Up " + library.id);
        libraryBooks.put(library, new ArrayList<>());
    }

    public void sendBooks(Library library, List<Book> books) {
        libraryBooks.get(library).addAll(books);
    }

    public void sendBook(Library library, Book book) {
        // System.err.println("Sending Book " + book.id + " for library " + library.id);
        libraryBooks.get(library).add(book);
    }

    public long score() {
        return libraryBooks.values().stream().map(books -> books.stream().map(b -> b.score).reduce(0L, Long::sum)).reduce(0L, Long::sum);
    }

    public void write(PrintStream out, PrintStream err) {
        out.println(libraryBooks.size());
        libraryBooks.forEach((libray, books) -> {
            if (!books.isEmpty()) {
                out.println(libray.id + " " + books.size());
                out.println(books.stream().map(book -> Integer.toString(book.id)).collect(Collectors.joining(" ")));
            } else {
                out.println(libray.id + " " + 1);
                out.println(libray.allBooks.get(0));
            }
        });
        err.println("Score: " + score());
    }
}
