#!/bin/bash
# mvn compile
for x in a b c d e; do java -cp target/classes BooksApp < src/main/resources/$x.txt > $x.out; done;
