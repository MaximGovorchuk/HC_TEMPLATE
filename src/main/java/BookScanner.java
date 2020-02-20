import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;

public class BookScanner {

    final List<Book> allBooks;
    final List<Library> allLibraries;
    final List<Library> librariesPending;
    final List<Library> librariesSignedUp = new ArrayList<>();

    public BookScanner(List<Book> books, List<Library> libraries) {
        this.allBooks = books;
        this.allLibraries = libraries;
        this.librariesPending = new ArrayList<>(allLibraries);
    }

    Result work(int dayCount) {
        Result result = new Result(allBooks);
        Library nextLibraryToSignUp = nextLibrary();
        int signUpNextLibraryAfter = nextLibraryToSignUp.signUpDuration;
        for (int i = 0; i < dayCount; i++) {
            // Finish with pending sign up
            if (signUpNextLibraryAfter == 0 && nextLibraryToSignUp != null) {
                result.signUp(nextLibraryToSignUp);
                librariesSignedUp.add(nextLibraryToSignUp);
                nextLibraryToSignUp = nextLibrary();
                if (nextLibraryToSignUp != null) {
                    signUpNextLibraryAfter = nextLibraryToSignUp.signUpDuration;
                }
            }
            // Add Books
            {
                scanPerDay(result);
            }
            // Prepare for Signup
            if (nextLibraryToSignUp != null) {
                signUpNextLibraryAfter--;
            }
            if (i%10 == 0) {
                System.err.print("Processed " + i + " days\r");
            }
        }
        System.err.println("Processed " + dayCount + " days\r");
        return result;
    }

    void scanPerDay(Result result) {
        List<Library> remainingLibraries = new ArrayList<>(librariesSignedUp);
        Map<Library, Integer> remainingBooksPerLibrary = new HashMap<>();
        librariesSignedUp.forEach((library -> {
            remainingBooksPerLibrary.put(library, library.booksPerDay);
        }));

        while (!remainingLibraries.isEmpty()) {
            int nextLibraryIndex = new Random().nextInt(remainingLibraries.size());
            Library nextLibrary = remainingLibraries.get(nextLibraryIndex);
            Book nextBook = nextLibrary.nextBook();
            if (nextBook == null) {
                remainingBooksPerLibrary.remove(nextLibrary);
                remainingLibraries.remove(nextLibrary);
                librariesSignedUp.remove(nextLibrary);
                continue;
            }
            result.sendBook(nextLibrary, nextBook);
            librariesSignedUp.parallelStream().forEach(library -> library.invalidateBook(nextBook));
            librariesPending.parallelStream().forEach(library -> library.invalidateBook(nextBook));
            int r = remainingBooksPerLibrary.get(nextLibrary);
            if (r == 1) {
                // System.err.println("Done processing library " + nextLibrary.id);
                remainingBooksPerLibrary.remove(nextLibrary);
                remainingLibraries.remove(nextLibrary);
            } else {
                remainingBooksPerLibrary.remove(nextLibrary);
                remainingBooksPerLibrary.put(nextLibrary, r-1);
            }
        }
    }


    Library nextLibrary() {
        while (!librariesPending.isEmpty()) {
            int nextLibraryIndex = new Random().nextInt(librariesPending.size());
            Library library = librariesPending.get(nextLibraryIndex);
            librariesPending.remove(nextLibraryIndex);
            if (library.pendingBooks.isEmpty()) {
                continue;
            }
            return library;
        }
        return null;
    }

}
