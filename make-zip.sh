#!/bin/bash

tar -cvf - ./pom.xml ./src/main/java | gzip -c > source.tar.gz
