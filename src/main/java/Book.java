import java.util.Comparator;
import java.util.Objects;

public class Book {

    final int id;
    final long score;

    public Book(int id, long score) {
        this.id = id;
        this.score = score;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Comparator<Book> scoreComparator = (o1, o2) -> Long.compare(o1.score, o2.score);

}
