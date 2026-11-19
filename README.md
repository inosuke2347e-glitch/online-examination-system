# Online Examination System — Java Mini Project

**Students:** Avinash and Shubam
**Semester:** 3rd Semester, **Branch:** AI & DS (Artificial Intelligence & Data Science)
**Subject:** Object Oriented Programming with Java (VTU, 3rd Sem)

## What this version is (20–25% of the synopsis)

This is the first working part of the project from the synopsis —
simple Java only, no database, no GUI, no advanced code.

Built so far:
1. **Login** — Student login and Admin login (demo accounts)
2. **Question bank** — 5 sample MCQs on Java OOPs basics
3. **Timed exam** — 10-minute timer, automatic submission when time is over
4. **Automatic result** — score, percentage, Pass/Fail, stored in a list

Not built yet (remaining 75–80%):
MySQL database + JDBC, full Admin add/edit/delete questions,
Swing GUI, saving results to a file/database, negative marking,
question shuffling.

## Files

| File | What it does | VTU OOPs concept |
|---|---|---|
| `src/Person.java` | Base class for all users | Inheritance, Encapsulation |
| `src/Student.java` | Student user | Inheritance, Polymorphism (overriding) |
| `src/Admin.java` | Admin user | Inheritance, Polymorphism (overriding) |
| `src/Question.java` | One MCQ | Class & Object, Encapsulation, Constructor Overloading |
| `src/Exam.java` | Holds questions, conducts exam | ArrayList, Object interaction |
| `src/Result.java` | Score and percentage | Encapsulation |
| `src/Main.java` | Menu, login, runs the exam | Objects, Scanner, Exception Handling |

## How to run

Needs JDK 17 or above.

```bash
javac src/*.java -d out
java -cp out Main
```

## Demo logins (for testing only)

- Student: ID `S101`, Password `student123`
- Admin: ID `admin`, Password `admin123`

Try this: choose `1`, log in as the student, answer the 5 questions
(answers: 1, 2, 3, 3, 2), and see your score. Then choose `2`,
log in as admin, and see the stored result.

## Report

See `Report-Online-Examination-System.pdf` — 2-page report with
the concepts used, what is built, and what comes next.
