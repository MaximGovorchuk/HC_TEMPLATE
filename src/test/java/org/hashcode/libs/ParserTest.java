package org.hashcode.libs;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class ParserTest {

    @Test
    public void testParser() throws IOException {
        Map<Integer, Library> libraryMap = new Parser().parseInput("input.txt");

        Library lib = libraryMap.get(0);
        Assert.assertEquals(0, lib.id);
        Assert.assertEquals(2, lib.shipBooksPerDay);
        Assert.assertEquals(2, lib.signupProcessTime);

        Book[] books = lib.books.toArray(new Book[0]);
        Assert.assertEquals(0, books[0].id);
        Assert.assertEquals(1, books[1].id);
        Assert.assertEquals(2, books[2].id);
        Assert.assertEquals(4, books[3].id);
        Assert.assertEquals(3, books[4].id);

        Assert.assertEquals(1, books[0].score);
        Assert.assertEquals(2, books[1].score);
        Assert.assertEquals(3, books[2].score);
        Assert.assertEquals(5, books[3].score);
        Assert.assertEquals(6, books[4].score);


        lib = libraryMap.get(1);
        Assert.assertEquals(1, lib.id);
        Assert.assertEquals(1, lib.shipBooksPerDay);
        Assert.assertEquals(3, lib.signupProcessTime);

        books = lib.books.toArray(new Book[0]);
        Assert.assertEquals(0, books[0].id);
        Assert.assertEquals(2, books[1].id);
        Assert.assertEquals(5, books[2].id);
        Assert.assertEquals(3, books[3].id);

        Assert.assertEquals(1, books[0].score);
        Assert.assertEquals(3, books[1].score);
        Assert.assertEquals(4, books[2].score);
        Assert.assertEquals(6, books[3].score);


    }
}
