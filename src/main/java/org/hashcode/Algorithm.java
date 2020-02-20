package org.hashcode;

import org.hashcode.libs.Book;
import org.hashcode.libs.Library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm {
    private Simulator simulator = new Simulator();

    public SimulatorResult calculateResult(int deadLine, Map<Integer, Library> libraries, Map<Integer, Book> books) {
        ArrayList<Library> librariesSignupOrder = new ArrayList<>(libraries.values());

        HashMap<Library, List<Book>> orderOfBooksPerLibrary = new HashMap<>();
        for (Library library : libraries.values()) {
            orderOfBooksPerLibrary.put(library, new ArrayList<>(library.books));
        }

        return simulator.run(orderOfBooksPerLibrary, librariesSignupOrder, deadLine);
    }
}
