import java.util.List;
import java.util.Scanner;

public class BooksApp {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int booksRequired = in.nextInt();
        int librariesNumber = in.nextInt();
        int daysDeadline = in.nextInt();

        int[] bookScores = new int[booksRequired];
        for(int i = 0; i < booksRequired; i++) {
            bookScores[i] = in.nextInt();
        }

        Library[] libraries = new Library[librariesNumber];

        for(int i = 0; i < librariesNumber; i++) {
            int booksInLibrary = in.nextInt();

            Library library = new Library();
            library.signupProcessTime = in.nextInt();
            library.shipBooksPerDay = in.nextInt();

            int[] libraryBookIds = new int[booksInLibrary];
            for(int j = 0; j < booksInLibrary; j++) {
                libraryBookIds[j] = in.nextInt();
            }

            library.bookIds = libraryBookIds;

            libraries[i] = library;
        }

    }

    public List<LibraryOrder> getSolution(int booksRequired, int daysDeadline, int[] bookScores, Library[] libraries) {
        return null;
    }
}
