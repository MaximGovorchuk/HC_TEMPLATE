package org.hashcode;

import org.hashcode.libs.Book;
import org.hashcode.libs.Library;

import java.util.ArrayList;
import java.util.Collections;
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

        librariesSignupOrder.sort((f, s) -> {
            Integer firstLibraryTotalScore = f.books.stream().map(b -> b.score).reduce(0, Integer::sum);
            Integer secondLibraryTotalScore = s.books.stream().map(b -> b.score).reduce(0, Integer::sum);

            return secondLibraryTotalScore - firstLibraryTotalScore;
        });

        for (List<Book> value : orderOfBooksPerLibrary.values()) {
            value.sort((f, s) -> s.score - f.score);
        }

        return simulator.run(orderOfBooksPerLibrary, librariesSignupOrder, deadLine);
    }

    private void shuffleLibrariesSignupOrder(List<Library> librariesSignupOrder) {
        shuffleList(librariesSignupOrder);
    }

    private static <T> void shuffleList(List<T> list) {
        int index;
        T temp;
        Random random = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = list.get(index);
            list.set(index, list.get(i));
            list.set(i, temp);
        }
    }
}
