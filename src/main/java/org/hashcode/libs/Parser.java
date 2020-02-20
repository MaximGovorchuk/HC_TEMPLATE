package org.hashcode.libs;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static void main(String[] args) throws IOException {
        new Parser().parseInput(args[0]);
    }

    public Map<Integer, Library> parseInput(String filePath) throws IOException {
        List<String> lines = IOUtils.readLines(new FileReader(new File(filePath)));

        int deadline = parseDeadline(lines.get(0));
        int countLibs = parseNumberLibs(lines.get(0));
        Map<Integer, Book> books = parseBooks(lines.get(1));

        Map<Integer, Library> result = new HashMap<>();
        int start = 2;
        for (int i = 0; i < countLibs; i++) {
            Library lib = parseLibrary(lines.get(start), i);
            parseLibraryBooks(books, lines.get(start + 1), lib);
            start += 2;

            result.put(i, lib);
        }

        return result;
    }

    private void parseLibraryBooks(Map<Integer, Book> books, String line, Library lib) {
        String[] bookIdz = line.split(" ");
        for (String id : bookIdz) {
            lib.books.add(books.get(Integer.parseInt(id)));
        }
    }

    private Library parseLibrary(String line, int id) {
        String[] libValues = line.split(" ");

        Library library = new Library();
        library.id = id;
        library.signupProcessTime = Integer.parseInt(libValues[1]);
        library.shipBooksPerDay = Integer.parseInt(libValues[2]);

        return library;
    }

    private int parseDeadline(String line) {
        return Integer.parseInt(line.split(" ")[2]);
    }

    private int parseNumberLibs(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }


    private Map<Integer, Book> parseBooks(String line) {
        Map<Integer, Book> map = new HashMap<>();
        String[] booksScore = line.split(" ");

        for (int i = 0; i < booksScore.length; i++) {
            map.put(i, new Book(i, Integer.parseInt(booksScore[i])));
        }

        return map;
    }
}
