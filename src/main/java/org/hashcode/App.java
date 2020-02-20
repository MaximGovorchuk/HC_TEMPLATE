package org.hashcode;

import org.apache.commons.io.IOUtils;
import org.hashcode.libs.Book;
import org.hashcode.libs.Library;
import org.hashcode.libs.Parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {


    public static void main(String[] args) throws IOException {
        for (String fileName : Arrays.asList("a", "b", "c", "d", "e", "f")) {
            Parser parser = new Parser();
            parser.parseInput("./src/main/resources/" + fileName + ".txt");

            Algorithm algorithm = new Algorithm();

            SimulatorResult result = algorithm.calculateResult(parser.getDeadLine(), parser.getLibraries(), parser.getBooks());

            List<SimulatorResult.LibraryOrder> libraryOrders = result.libraryOrders;

            ArrayList<String> lines = new ArrayList<>();
            lines.add(String.valueOf(libraryOrders.size()));

            for (SimulatorResult.LibraryOrder libraryOrder : libraryOrders) {
                lines.add(libraryOrder.libraryId + " " + libraryOrder.books.size());

                StringBuilder stringBuilder = new StringBuilder();
                for (Book book : libraryOrder.books) {
                    stringBuilder.append(book.id);
                    stringBuilder.append(" ");
                }
                stringBuilder.setLength(stringBuilder.length() - 1);
                lines.add(stringBuilder.toString());
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter("./src/main/resources/" + fileName + "_" + result.score + ".out"));

            IOUtils.writeLines(lines, null, writer);
            writer.close();
        }
    }
}
