package org.hashcode;

import org.apache.commons.io.IOUtils;
import org.hashcode.libs.Library;
import org.hashcode.libs.Parser;

import java.io.IOException;
import java.util.Map;

public class App {


    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        parser.parseInput("/Users/mhovorchuk/hashcode/HashCode/src/main/resources/a.txt");

        Algorithm algorithm = new Algorithm();

        SimulatorResult result = algorithm.calculateResult(parser.getDeadLine(), parser.getLibraries(), parser.getBooks());


        IOUtils.writeLines();
    }
}
