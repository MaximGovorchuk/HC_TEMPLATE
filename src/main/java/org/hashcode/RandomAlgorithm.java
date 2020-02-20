package org.hashcode;

import org.hashcode.libs.Book;
import org.hashcode.libs.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomAlgorithm implements Algorithm {
    private Simulator simulator = new Simulator();

    public SimulatorResult calculateResult(int deadLine, Map<Integer, Library> libraries, Map<Integer, Book> books) {
        List<Library> librariesSignupOrder = new ArrayList<>(libraries.values());

        Map<Library, List<Book>> orderOfBooksPerLibrary = new HashMap<>();
        for (Library library : libraries.values()) {
            orderOfBooksPerLibrary.put(library, new ArrayList<>(library.books));
        }

        SimulatorResult bestResult = new SimulatorResult();

        for(int times = 0; times < 10000; times++) {
            shuffleOrderOfBooksPerLibrary(orderOfBooksPerLibrary);
            shuffleLibrariesSignupOrder(librariesSignupOrder);

            SimulatorResult attempt = attempt(deadLine, orderOfBooksPerLibrary, librariesSignupOrder);

            if (attempt.score > bestResult.score) {
                bestResult = attempt;
            }
        }

        return bestResult;
    }

    private SimulatorResult attempt(int deadLine, Map<Library, List<Book>> orderOfBooksPerLibrary, List<Library> librariesSignupOrder) {
        return simulator.run(orderOfBooksPerLibrary, librariesSignupOrder, deadLine);
    }

    private void shuffleOrderOfBooksPerLibrary(Map<Library, List<Book>> orderOfBooksPerLibrary) {
        for (List<Book> books : orderOfBooksPerLibrary.values()) {
            shuffleList(books);
        }
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
