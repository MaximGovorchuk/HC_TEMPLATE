import java.util.*;

public class Library {
    final int id;
    final List<Book> allBooks;
    final int signUpDuration;
    final int booksPerDay;
    final Map<Integer, Book> pendingBooks = new HashMap<>();
    final SortedMap<Long, Map<Integer, Book>> pendingBooksByScore = new TreeMap<>(Comparator.reverseOrder());

    public Library(int id, List<Book> books, int signUpDuration, int booksPerDay) {
        this.id = id;
        this.allBooks = books;
        this.signUpDuration = signUpDuration;
        this.booksPerDay = booksPerDay;
        books.forEach((b) -> {
            pendingBooks.put(b.id, b);
            pendingBooksByScore.putIfAbsent(b.score, new HashMap<>());
            pendingBooksByScore.get(b.score).put(b.id, b);
        });
    }

    public void invalidateBooks(Collection<Book> books) {
        books.forEach(this::invalidateBook);
    }

    public void invalidateBook(Book book) {
        pendingBooks.remove(book.id);
        Map<Integer, Book> bookWithSameScore = pendingBooksByScore.get(book.score);
        if (bookWithSameScore != null) {
            bookWithSameScore.remove(book.id);
            if (bookWithSameScore.isEmpty()) {
                pendingBooksByScore.remove(book.score);
            }
        }
    }

    public Book nextBook() {
        if (pendingBooks.isEmpty()) {
            return null;
        }
        long highScore = pendingBooksByScore.firstKey();
        Map<Integer, Book> booksWithHighScore = pendingBooksByScore.get(highScore);
        return booksWithHighScore.entrySet().iterator().next().getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return id == library.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
