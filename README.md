# Online Examination System — Java Mini Project (Complete)

**Students:** Avinash, Shubam and MD Yasir
**Semester:** 3rd Semester, **Branch:** AI & DS (Artificial Intelligence & Data Science)
**Subject:** Object Oriented Programming with Java (VTU, 3rd Sem)
**Technologies:** Java, MySQL, JDBC

## What the system does (100% of the synopsis)

- **Admin:** login, add/view students, add/view subjects, add/view/search/delete
  questions, add/view exams, view all results
- **Student:** login, view available exams, attend a timed MCQ exam
  (questions in random order, automatic submission when time is over),
  view own results
- **Exam:** MCQ with 4 options, timer, automatic answer checking
- **Result:** score, percentage, Pass/Fail (40%), stored and shown later
- **Database:** MySQL with 6 tables — Students, Admins, Subjects,
  Questions, Exams, Results (`database.sql`)

## Two ways to run — read this before the presentation

The program checks MySQL once at the start:

- **MySQL connected** → logins and questions come from MySQL first, and
  every result is also saved in the MySQL `results` table.
- **MySQL not connected → Demo Mode** → everything runs on the same data
  stored in memory (`DataStore`). **The full demo still works with only
  a JDK installed — no MySQL needed.** This is the safe way to present.

## Quick run (Demo Mode, JDK only)

Needs JDK 17 or above. Easiest: double-click `run.bat` (Windows) or
run `sh run.sh` (Linux/Mac). Or manually:

```bash
javac src/*.java -d out
java -cp out Main
```

## Full run with MySQL

1. Install MySQL and start it.
2. Run `database.sql` once in MySQL — it creates the database
   `online_exam_db`, all 6 tables and the sample data.
3. In `src/DBConnection.java`, set `PASSWORD` to your MySQL password
   (the connector jar is already in the `lib` folder).
4. Compile and run with the jar:

```bash
javac -cp lib/mysql-connector-j.jar src/*.java -d out
java -cp out:lib/mysql-connector-j.jar Main        (Linux/Mac)
java -cp out;lib/mysql-connector-j.jar Main        (Windows)
```

On startup the program prints `Database: Connected to MySQL.` when it
is using the database.

## Demo logins

- Student: ID `S101`, Password `student123` (also `S102` / `rahul123`)
- Admin: ID `admin`, Password `admin123`

## Files

| File | What it does | Concept |
|---|---|---|
| `src/Person.java` | Base class for all users | Inheritance, Encapsulation |
| `src/Student.java` | Student user | Inheritance, Polymorphism |
| `src/Admin.java` | Admin user | Inheritance, Polymorphism |
| `src/Subject.java` | One subject | Encapsulation |
| `src/Question.java` | One MCQ | Encapsulation, Constructor Overloading |
| `src/Exam.java` | Conducts the timed exam, random order | ArrayList, Collections |
| `src/Result.java` | Score and percentage | Encapsulation |
| `src/DataStore.java` | All data in ArrayLists + sample data, CRUD | Collections, CRUD |
| `src/DBConnection.java` | JDBC connection to MySQL | JDBC steps 1–2 |
| `src/Database.java` | SQL work: login check, load questions, save result, add/delete question | JDBC, PreparedStatement |
| `src/Main.java` | Menus for student and admin | Objects, Scanner, Exception Handling |
| `database.sql` | Creates the MySQL database, 6 tables, sample data | SQL, DDL + DML |
| `DEMO-SCRIPT.md` | Step-by-step script for the live presentation | — |

## Presentation

Follow `DEMO-SCRIPT.md` — it lists exactly what to type, in order,
and what the examiner will see. One safe path through the whole system
takes about 3–4 minutes.

## Report

`Report-Online-Examination-System.pdf` — the final working report,
including the backend code explanation.
