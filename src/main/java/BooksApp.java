import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class BooksApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int bookCount = in.nextInt();
        int libraryCount = in.nextInt();
        int daysDeadline = in.nextInt();

        List<Book> books = new ArrayList<>(bookCount);
        for(int i = 0; i < bookCount; i++) {
            books.add(new Book(i, in.nextLong()));
        }

        List<Library> libraries = new ArrayList<>();
        for(int libraryId = 0; libraryId < libraryCount; libraryId++) {
            int booksInLibrary = in.nextInt();
            int signUpDuration = in.nextInt();
            int booksPerDay = in.nextInt();
            List<Book> libraryBooks = new ArrayList<>(booksInLibrary);
            for(int j = 0; j < booksInLibrary; j++) {
                int bookId = in.nextInt();
                libraryBooks.add(books.get(bookId));
            }
            Library library = new Library(libraryId, libraryBooks, signUpDuration, booksPerDay);
            libraries.add(library);
        }

        BookScanner bookScanner = new BookScanner(books, libraries);
        Result result = bookScanner.work(daysDeadline);
        result.write(System.out, System.err);
    }

}
