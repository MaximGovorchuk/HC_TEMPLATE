package org.hashcode.libs;

import org.hashcode.Simulator;
import org.hashcode.libs.Library;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SimulatorTest {
    private Simulator simulator = new Simulator();

    @Test
    public void testSimulator() throws IOException {
        Parser parser = new Parser();
        parser.parseInput("input.txt");

        int deadLine = parser.getDeadLine();

        Map<Integer, Book> books = parser.getBooks();

        Map<Integer, Library> libraries = parser.getLibraries();

        HashMap<Library, List<Book>> orderOfBooksPerLibrary = new HashMap<>();
        Library first = libraries.get(0);

        List<Book> booksInOrderForFirstLibrary = new ArrayList<>();

        booksInOrderForFirstLibrary.add(books.get(0));
        booksInOrderForFirstLibrary.add(books.get(1));
        booksInOrderForFirstLibrary.add(books.get(2));
        booksInOrderForFirstLibrary.add(books.get(3));
        booksInOrderForFirstLibrary.add(books.get(4));

        orderOfBooksPerLibrary.put(first, booksInOrderForFirstLibrary);

        List<Book> booksInOrderForSecondLibrary = new ArrayList<>();

        Library second = libraries.get(1);
        booksInOrderForSecondLibrary.add(books.get(5));
        booksInOrderForSecondLibrary.add(books.get(2));
        booksInOrderForSecondLibrary.add(books.get(3));

        orderOfBooksPerLibrary.put(second, booksInOrderForSecondLibrary);

        ArrayList<Library> librariesToSignupOrder = new ArrayList<>();
        librariesToSignupOrder.add(first);
        librariesToSignupOrder.add(second);

        assertEquals(21, simulator.run(orderOfBooksPerLibrary, librariesToSignupOrder, deadLine));
    }
}
