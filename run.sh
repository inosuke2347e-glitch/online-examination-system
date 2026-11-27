#!/bin/sh
# Online Examination System - run script for Linux/Mac
# If the MySQL connector jar is in lib, it is used automatically.
# Without MySQL the program simply runs in Demo Mode.

if [ -f lib/mysql-connector-j.jar ]; then
    javac -cp lib/mysql-connector-j.jar -d out src/*.java
    java -cp out:lib/mysql-connector-j.jar Main
else
    javac -d out src/*.java
    java -cp out Main
fi
