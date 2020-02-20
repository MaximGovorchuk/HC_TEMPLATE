package org.hashcode;

import org.hashcode.libs.Book;

import java.util.ArrayList;
import java.util.List;

public class SimulatorResult {
    public int score;
    public List<LibraryOrder> libraryOrders = new ArrayList<>();

    static class LibraryOrder {
        int libraryId;
        List<Book> books;
    }
}
