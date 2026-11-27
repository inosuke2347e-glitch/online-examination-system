-- Online Examination System - MySQL Database File
-- Students: Avinash, Shubam and MD Yasir, 3rd Semester, AI & DS
--
-- HOW TO USE:
--   1. Open MySQL (Command Line, Workbench or phpMyAdmin).
--   2. Run this whole file once. It creates the database, all 6 tables
--      and the same sample data that the program uses in Demo Mode.
--   3. In src/DBConnection.java, set PASSWORD to your MySQL password.
--   4. Run the program with the MySQL connector jar (see README).

CREATE DATABASE IF NOT EXISTS online_exam_db;
USE online_exam_db;

-- Table 1: Students (synopsis section 7: student_id, name, email, password, department)
CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(50),
    department VARCHAR(50)
);

-- Table 2: Admins (synopsis: admin_id, username, password)
CREATE TABLE IF NOT EXISTS admins (
    admin_id VARCHAR(20) PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);

-- Table 3: Subjects (synopsis: subject_id, subject_name)
CREATE TABLE IF NOT EXISTS subjects (
    subject_id VARCHAR(20) PRIMARY KEY,
    subject_name VARCHAR(100)
);

-- Table 4: Questions (synopsis: question_id, subject_id, question, options, correct_option, marks
--                     + exam_id, added so every question belongs to one exam)
CREATE TABLE IF NOT EXISTS questions (
    question_id INT PRIMARY KEY,
    exam_id INT,
    subject_id VARCHAR(20),
    question TEXT,
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    correct_option INT,
    marks INT
);

-- Table 5: Exams (synopsis: exam_id, subject_id, title, duration, total_questions)
CREATE TABLE IF NOT EXISTS exams (
    exam_id INT PRIMARY KEY,
    subject_id VARCHAR(20),
    title VARCHAR(100),
    duration INT,
    total_questions INT
);

-- Table 6: Results (synopsis: result_id, student_id, exam_id, score, percentage, submitted_at
--                   + total_marks, added so the score can be shown as score/total)
CREATE TABLE IF NOT EXISTS results (
    result_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id VARCHAR(20),
    exam_id INT,
    score INT,
    total_marks INT,
    percentage DOUBLE,
    submitted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ---------------- Sample data (same as DataStore seed data) ----------------

INSERT INTO admins (admin_id, username, password) VALUES
('admin', 'Avinash', 'admin123');

INSERT INTO students (student_id, name, email, password, department) VALUES
('S101', 'Shubam', 'shubam@example.com', 'student123', 'AI & DS'),
('S102', 'Rahul', 'rahul@example.com', 'rahul123', 'AI & DS');

INSERT INTO subjects (subject_id, subject_name) VALUES
('SUB1', 'Java OOPs'),
('SUB2', 'Data Structures');

INSERT INTO exams (exam_id, subject_id, title, duration, total_questions) VALUES
(1, 'SUB1', 'Java OOPs Basics', 10, 10),
(2, 'SUB2', 'Data Structures Basics', 10, 5);

INSERT INTO questions (question_id, exam_id, subject_id, question, option1, option2, option3, option4, correct_option, marks) VALUES
(1, 1, 'SUB1', 'Which of these is a pillar of OOP?', 'Encapsulation', 'Compilation', 'Debugging', 'Linking', 1, 1),
(2, 1, 'SUB1', 'Which keyword is used to inherit a class in Java?', 'implements', 'extends', 'inherits', 'super', 2, 1),
(3, 1, 'SUB1', 'Which of these hides data and shows only methods?', 'Polymorphism', 'Inheritance', 'Encapsulation', 'Abstraction', 3, 1),
(4, 1, 'SUB1', 'How many objects can be created from one class?', 'Only one', 'Only two', 'As many as needed', 'None', 3, 1),
(5, 1, 'SUB1', 'Which method is the starting point of a Java program?', 'start()', 'main()', 'run()', 'init()', 2, 1),
(6, 1, 'SUB1', 'Which keyword is used to create an object in Java?', 'create', 'new', 'make', 'object', 2, 1),
(7, 1, 'SUB1', 'Method overloading means:', 'Same method name with different parameters', 'Same method name in a child class', 'Two classes with the same name', 'None of these', 1, 1),
(8, 1, 'SUB1', 'Which access modifier makes a member visible only inside its own class?', 'public', 'private', 'protected', 'default', 2, 1),
(9, 1, 'SUB1', 'Which keyword is used by a class to use an interface?', 'extends', 'implements', 'interface', 'abstract', 2, 1),
(10, 1, 'SUB1', 'Which of these is NOT a pillar of OOP?', 'Encapsulation', 'Inheritance', 'Polymorphism', 'Compilation', 4, 1),
(11, 2, 'SUB2', 'Which data structure works on LIFO (Last In First Out)?', 'Queue', 'Stack', 'Linked List', 'Tree', 2, 1),
(12, 2, 'SUB2', 'Which data structure works on FIFO (First In First Out)?', 'Queue', 'Stack', 'Tree', 'Graph', 1, 1),
(13, 2, 'SUB2', 'In a stack, insertion of an element is called:', 'Pop', 'Delete', 'Push', 'Peek', 3, 1),
(14, 2, 'SUB2', 'Which of these is a linear data structure?', 'Tree', 'Graph', 'Array', 'Binary Tree', 3, 1),
(15, 2, 'SUB2', 'Time needed to access an array element by its index is:', 'O(n)', 'O(log n)', 'O(n log n)', 'O(1)', 4, 1);
