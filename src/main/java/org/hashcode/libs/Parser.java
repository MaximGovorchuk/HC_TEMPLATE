package org.hashcode.libs;

import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static void main(String[] args) throws IOException {
        String path = args[0];
        List<String> lines = IOUtils.readLines(new FileReader(new File(path)));

        int deadline = parseDeadline(lines.get(0));
        int countLibs = parseNumberLibs(lines.get(0));
        Map<Integer, Book> books = parseBooks(lines.get(1));

        int start = 2;
        for (int i = 0; i < countLibs; i++) {

        }


    }

    static int parseDeadline(String line) {
        return Integer.parseInt(line.split(" ")[2]);
    }

    static int parseNumberLibs(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }


    static Map<Integer, Book> parseBooks(String line) {
        Map<Integer, Book> map = new HashMap<>();
        String[] booksScore = line.split(" ");

        for (int i = 0; i < booksScore.length; i++) {
            map.put(i, new Book(i, Integer.parseInt(booksScore[i])));
        }

        return map;
    }
}
