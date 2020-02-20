package org.hashcode;

import org.hashcode.libs.Book;
import org.hashcode.libs.Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SmarterGreedyAlgorithm implements Algorithm {
    private Simulator simulator = new Simulator();

    public SimulatorResult calculateResult(int deadLine, Map<Integer, Library> libraries, Map<Integer, Book> books) {
        List<Library> librariesSignupOrder = new ArrayList<>(libraries.values());

        Map<Library, List<Book>> orderOfBooksPerLibrary = new HashMap<>();
        for (Library library : libraries.values()) {
            orderOfBooksPerLibrary.put(library, new ArrayList<>(library.books));
        }

        /*librariesSignupOrder.sort((f, s) -> {
            Integer firstLibraryTotalScore = f.books.stream().map(b -> b.score).reduce(0, Integer::sum);
            Integer secondLibraryTotalScore = s.books.stream().map(b -> b.score).reduce(0, Integer::sum);

            return secondLibraryTotalScore - firstLibraryTotalScore;
        });*/

        librariesSignupOrder.sort((f, s) -> rank(s, deadLine) - rank(f, deadLine));

        for (List<Book> value : orderOfBooksPerLibrary.values()) {
            value.sort((f, s) -> s.score - f.score);
        }

        /*Set<Book> seen = new HashSet<>();

        // Exclude duplicates
        for (Library library : librariesSignupOrder) {
            List<Book> value = orderOfBooksPerLibrary.get(library);
            for (int i = value.size() - 1; i >= 0; i--) {
                if (!seen.add(value.get(i))) {
                    value.remove(i);
                }
            }
        }*/

        return simulator.run(orderOfBooksPerLibrary, librariesSignupOrder, deadLine);
    }

    private int rank(Library library, int deadline) {
        return deadline - (library.signupProcessTime) * library.shipBooksPerDay;
    }
}
