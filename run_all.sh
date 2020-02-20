#!/bin/bash
# mvn compile
# java -cp target/classes BooksApp < src/main/resources/a.txt > a.out
# java -cp target/classes BooksApp < src/main/resources/b.txt > b.out
for x in a b c e; do java -cp target/classes BooksApp < src/main/resources/$x.txt > $x.out; done;
java -cp target/classes BooksApp < src/main/resources/d.txt > d.out
