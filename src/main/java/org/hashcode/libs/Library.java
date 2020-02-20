package org.hashcode.libs;

import java.util.Set;
import java.util.TreeSet;

public class Library {

    int id;

    Set<Book> books = new TreeSet<>((b1, b2) ->
            b1.score > b2.score ? 1 : b1.score == b2.score ? 0 : -1
    );

    int signupProcessTime;
    int shipBooksPerDay;

}

