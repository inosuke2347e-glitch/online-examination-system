@echo off
REM Online Examination System - one-click run for Windows
REM If the MySQL connector jar is in lib, it is used automatically.
REM Without MySQL the program simply runs in Demo Mode.

IF EXIST lib\mysql-connector-j.jar (
    javac -cp lib\mysql-connector-j.jar -d out src\*.java
    java -cp out;lib\mysql-connector-j.jar Main
) ELSE (
    javac -d out src\*.java
    java -cp out Main
)
pause
