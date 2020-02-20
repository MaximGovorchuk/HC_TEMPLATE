package org.hashcode;

import org.hashcode.libs.Book;
import org.hashcode.libs.Library;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*

1 -> 2 3 4
  st = 1
  bps = 1

1 = sig, 2, 3 => sert of books (2,3)



2 -> 1 2 3 4
sgt = 1
bps = 2

2 = signup, (1,2), (3,4) => (1,2,3,4)

2, 3, 4, 1 =  2 + 1 + 3 + 1 = 7

book scores:
1 -> 1 points
2 -> 2 points
4 -> 3 points
3  -> 1 poin

1 2 4




*/
public class Simulator {
    public int run(Map<Library, List<Book>> orderOfBooksPerLibrary, List<Library> librariesToSignupOrder, int daysDeadline) {
        Map<Library, Integer> timeAvailableForLibrary = new HashMap<>();

        int time = 0;
        for (Library library : librariesToSignupOrder) {
            time += library.signupProcessTime;
            int timeLeft = daysDeadline - time;
            if (timeLeft <= 0) {
                break;
            }
            timeAvailableForLibrary.put(library, timeLeft);
        }

        Set<Book> scanned = new HashSet<>();

        int totalScore = 0;

        for (Map.Entry<Library, List<Book>> e : orderOfBooksPerLibrary.entrySet()) {
            Library library = e.getKey();
            Iterator<Book> bookIterator = e.getValue().iterator();

            int currentTime = timeAvailableForLibrary.get(library);
            int booksPerDay = library.shipBooksPerDay;

            while (currentTime < daysDeadline && bookIterator.hasNext()) {
                Book book = bookIterator.next();
                if (scanned.add(book)) {
                    totalScore += book.score;
                    booksPerDay--;
                    if (booksPerDay == 0) {
                        currentTime++;
                    }
                }
            }
        }

        return totalScore;
    }
}
