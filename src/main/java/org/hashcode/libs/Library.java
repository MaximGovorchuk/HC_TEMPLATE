package org.hashcode.libs;

import java.util.Set;
import java.util.TreeSet;

public class Library {
    public int id;

    public Set<Book> books = new TreeSet<>((b1, b2) ->
            b1.score > b2.score ? 1 : b1.score == b2.score ? 0 : -1
    );

    public int signupProcessTime;
    public int shipBooksPerDay;

    public Library(int id, Set<Book> books, int signupProcessTime, int shipBooksPerDay) {
        this.id = id;
        this.books = books;
        this.signupProcessTime = signupProcessTime;
        this.shipBooksPerDay = shipBooksPerDay;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", books=" + books +
                ", signupProcessTime=" + signupProcessTime +
                ", shipBooksPerDay=" + shipBooksPerDay +
                '}';
    }
}

