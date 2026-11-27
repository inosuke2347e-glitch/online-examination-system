# Presentation Demo Script — Online Examination System

One safe path through the whole system, about 3–4 minutes.
Start the program first (`run.bat` on Windows, or `sh run.sh`).
The top line will say Demo Mode (no MySQL) or Connected to MySQL —
both work exactly the same for this script.

## Part 1 — Student attends an exam (about 2 minutes)

1. Type `1` → Student Login.
2. ID: `S101`, Password: `student123` → "Login successful. Welcome, Shubam!"
3. Type `1` → shows both exams (Java OOPs Basics, Data Structures Basics).
4. Type `2` → Attend Exam. Exam ID: `1`.
5. Answer the 10 questions. Questions come in random order.
   Correct answers (by question):
   - Pillar of OOP → 1 (Encapsulation)
   - Inherit a class → 2 (extends)
   - Hides data → 3 (Encapsulation)
   - Objects from one class → 3 (As many as needed)
   - Starting point of a Java program → 2 (main())
   - Keyword to create an object → 2 (new)
   - Method overloading means → 1 (Same name, different parameters)
   - Visible only inside its own class → 2 (private)
   - Keyword to use an interface → 2 (implements)
   - NOT a pillar of OOP → 4 (Compilation)
6. Result appears: score, percentage, Pass. Type `3` → the same result
   is shown from storage (View My Results).
7. Type `4` → Logout.

## Part 2 — Admin manages the system (about 1–2 minutes)

1. Type `2` → Admin Login. ID: `admin`, Password: `admin123`.
2. Type `2` → all students are listed.
3. Type `5` → Add Question. Exam ID: `1`. Type any simple question,
   4 options, correct option, marks `1`. Note the new question ID shown.
4. Type `7` → Search Question by ID → enter the new ID → it is found.
5. Type `10` → all exams with question counts (Exam 1 now has 11).
6. Type `11` → all results — Shubam's result from Part 1 is listed.
7. Type `12` → Logout. Type `3` → Exit.

## If the examiner asks

- **Where is the database?** MySQL, 6 tables, file `database.sql`.
  If MySQL is off, the program runs in Demo Mode on the same data in
  memory (`DataStore`) — that is why it never fails in a demo.
- **Which OOP concepts?** Encapsulation (private fields), Inheritance
  (Student/Admin extend Person), Polymorphism (`displayRole()`),
  Constructor Overloading (Question), ArrayList/Collections, JDBC,
  Exception Handling (wrong input never crashes the program).
- **How does the timer work?** Start time is noted; after every
  question the time used is checked; if it crosses the exam duration,
  the exam submits automatically.
