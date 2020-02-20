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

        Map<Integer, Library> libraries = parser.getLibraries();


        HashMap<Library, List<Book>> orderOfBooksPerLibrary = new HashMap<>();
        Library first = libraries.get(0);
        first.books.

        orderOfBooksPerLibrary.put(first, Arrays.asList())

        ArrayList<Library> librariesToSignupOrder = new ArrayList<>();


        simulator.run(orderOfBooksPerLibrary, librariesToSignupOrder, deadLine)

        //assertEquals();
    }
}
