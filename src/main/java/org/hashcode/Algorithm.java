package org.hashcode;

import org.hashcode.libs.Book;
import org.hashcode.libs.Library;

import java.util.Map;

public interface Algorithm {
    SimulatorResult calculateResult(int deadLine, Map<Integer, Library> libraries, Map<Integer, Book> books);
}
