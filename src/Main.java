// Online Examination System - Main Program (Complete Version)
// Students: Avinash, Shubam and MD Yasir, 3rd Semester, AI & DS
// VTU OOPs Concepts used here: Objects, ArrayList, Scanner, Exception Handling
//
// HOW THE BACKEND WORKS (simple):
//   Main (this file)     -> shows menus, takes input
//   DataStore            -> keeps all data in ArrayLists (memory)
//   Database + DBConnection -> same data in MySQL using JDBC
// At the start the program checks MySQL once:
//   - Connected    -> logins and questions are taken from MySQL first,
//                     and every result is also saved in MySQL.
//   - Not connected -> Demo Mode: everything runs on DataStore.
//                     Nothing crashes, the full demo still works.
//
// Demo logins:
//   Student: ID = S101, Password = student123  (also S102 / rahul123)
//   Admin:   ID = admin, Password = admin123

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Read an integer safely. Returns -1 if the input is not a number.
    public static int readInt(Scanner sc, String message) {
        System.out.print(message);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DataStore store = new DataStore();
        store.seedData();

        boolean dbConnected = DBConnection.isAvailable();

        System.out.println("======================================");
        System.out.println("  ONLINE EXAMINATION SYSTEM");
        System.out.println("  Avinash, Shubam and MD Yasir | 3rd Sem, AI & DS");
        System.out.println("======================================");
        if (dbConnected) {
            System.out.println("Database: Connected to MySQL.");
        } else {
            System.out.println("Database: MySQL not connected - running in Demo Mode.");
            System.out.println("(Same data, stored in memory. See README for MySQL setup.)");
        }

        while (true) {
            System.out.println("\n1. Student Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                studentLogin(sc, store, dbConnected);
            } else if (choice.equals("2")) {
                adminLogin(sc, store, dbConnected);
            } else if (choice.equals("3")) {
                System.out.println("Thank you. Exiting.");
                break;
            } else {
                System.out.println("Invalid choice. Enter 1, 2 or 3.");
            }
        }

        sc.close();
    }

    // -------------------- Student side --------------------

    public static void studentLogin(Scanner sc, DataStore store, boolean dbConnected) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Student student = null;
        if (dbConnected) {
            student = Database.checkStudentLogin(id, password); // MySQL first
        }
        if (student == null) {
            Student s = store.findStudent(id); // then memory
            if (s != null && s.checkPassword(password)) {
                student = s;
            }
        }

        if (student == null) {
            System.out.println("Wrong ID or password. Try again.");
            return;
        }

        System.out.println("\nLogin successful. Welcome, " + student.getName() + "!");
        student.displayRole();
        studentMenu(sc, store, dbConnected, student);
    }

    public static void studentMenu(Scanner sc, DataStore store, boolean dbConnected,
                                   Student student) {
        while (true) {
            System.out.println("\n--- Student Menu (" + student.getName() + ") ---");
            System.out.println("1. View Available Exams");
            System.out.println("2. Attend Exam");
            System.out.println("3. View My Results");
            System.out.println("4. Logout");
            System.out.print("Choose an option (1-4): ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.println("--- Available Exams ---");
                ArrayList<Exam> exams = store.getAllExams();
                for (int i = 0; i < exams.size(); i++) {
                    exams.get(i).displayExam();
                }

            } else if (choice.equals("2")) {
                int examId = readInt(sc, "Enter Exam ID to attend: ");
                Exam exam = store.findExamById(examId);
                if (exam == null) {
                    System.out.println("Exam not found.");
                    continue;
                }
                ArrayList<Question> questions = null;
                if (dbConnected) {
                    questions = Database.loadQuestionsForExam(examId); // MySQL first
                }
                if (questions == null || questions.size() == 0) {
                    questions = store.getQuestionsForExam(examId); // then memory
                }
                if (questions.size() == 0) {
                    System.out.println("No questions in this exam yet.");
                    continue;
                }
                Result result = exam.conductExam(sc, student, questions);
                result.displayResult();
                store.saveResult(result);
                if (dbConnected && Database.saveResult(result)) {
                    System.out.println("Result saved in MySQL database.");
                } else {
                    System.out.println("Result saved in memory.");
                }

            } else if (choice.equals("3")) {
                ArrayList<Result> myResults = store.getResultsForStudent(student.getId());
                if (myResults.size() == 0) {
                    System.out.println("No results yet. Attend an exam first.");
                } else {
                    for (int i = 0; i < myResults.size(); i++) {
                        myResults.get(i).displayResult();
                        System.out.println();
                    }
                }

            } else if (choice.equals("4")) {
                System.out.println("Logged out.");
                break;

            } else {
                System.out.println("Invalid choice. Enter 1, 2, 3 or 4.");
            }
        }
    }

    // -------------------- Admin side --------------------

    public static void adminLogin(Scanner sc, DataStore store, boolean dbConnected) {
        System.out.print("Enter Admin ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        Admin admin = null;
        if (dbConnected) {
            admin = Database.checkAdminLogin(id, password); // MySQL first
        }
        if (admin == null) {
            Admin a = store.findAdmin(id); // then memory
            if (a != null && a.checkPassword(password)) {
                admin = a;
            }
        }

        if (admin == null) {
            System.out.println("Wrong ID or password. Try again.");
            return;
        }

        System.out.println("\nLogin successful. Welcome, " + admin.getName() + "!");
        admin.displayRole();
        adminMenu(sc, store, dbConnected);
    }

    public static void adminMenu(Scanner sc, DataStore store, boolean dbConnected) {
        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Add Subject");
            System.out.println("4. View All Subjects");
            System.out.println("5. Add Question");
            System.out.println("6. View All Questions");
            System.out.println("7. Search Question by ID");
            System.out.println("8. Delete Question");
            System.out.println("9. Add Exam");
            System.out.println("10. View All Exams");
            System.out.println("11. View All Results");
            System.out.println("12. Logout");
            System.out.print("Choose an option (1-12): ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                System.out.print("Student ID: ");
                String id = sc.nextLine();
                if (store.findStudent(id) != null) {
                    System.out.println("A student with this ID already exists.");
                    continue;
                }
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();
                System.out.print("Department: ");
                String department = sc.nextLine();
                store.addStudent(new Student(name, id, password, department));
                System.out.println("Student added.");

            } else if (choice.equals("2")) {
                ArrayList<Student> students = store.getAllStudents();
                for (int i = 0; i < students.size(); i++) {
                    students.get(i).displayDetails();
                    System.out.println();
                }

            } else if (choice.equals("3")) {
                System.out.print("Subject ID (example SUB3): ");
                String id = sc.nextLine();
                System.out.print("Subject Name: ");
                String name = sc.nextLine();
                store.addSubject(new Subject(id, name));
                System.out.println("Subject added.");

            } else if (choice.equals("4")) {
                ArrayList<Subject> subjects = store.getAllSubjects();
                for (int i = 0; i < subjects.size(); i++) {
                    subjects.get(i).displaySubject();
                }

            } else if (choice.equals("5")) {
                int examId = readInt(sc, "Exam ID for this question: ");
                Exam exam = store.findExamById(examId);
                if (exam == null) {
                    System.out.println("Exam not found. Add the exam first.");
                    continue;
                }
                System.out.print("Question: ");
                String text = sc.nextLine();
                System.out.print("Option 1: ");
                String o1 = sc.nextLine();
                System.out.print("Option 2: ");
                String o2 = sc.nextLine();
                System.out.print("Option 3: ");
                String o3 = sc.nextLine();
                System.out.print("Option 4: ");
                String o4 = sc.nextLine();
                int correct = readInt(sc, "Correct option (1-4): ");
                if (correct < 1 || correct > 4) {
                    System.out.println("Correct option must be 1, 2, 3 or 4. Question not added.");
                    continue;
                }
                int marks = readInt(sc, "Marks: ");
                if (marks <= 0) {
                    marks = 1;
                }
                Question q = new Question(store.nextQuestionId(), examId, text,
                        o1, o2, o3, o4, correct, marks);
                store.addQuestion(q);
                if (dbConnected) {
                    Database.addQuestion(q, exam.getSubjectId());
                }
                System.out.println("Question added with ID " + q.getQuestionId() + ".");

            } else if (choice.equals("6")) {
                ArrayList<Question> questions = store.getAllQuestions();
                for (int i = 0; i < questions.size(); i++) {
                    questions.get(i).displayQuestionWithId();
                }

            } else if (choice.equals("7")) {
                int qid = readInt(sc, "Enter Question ID: ");
                Question q = store.findQuestionById(qid);
                if (q == null) {
                    System.out.println("Question not found.");
                } else {
                    q.displayQuestionWithId();
                }

            } else if (choice.equals("8")) {
                int qid = readInt(sc, "Enter Question ID to delete: ");
                if (store.deleteQuestionById(qid)) {
                    if (dbConnected) {
                        Database.deleteQuestion(qid);
                    }
                    System.out.println("Question deleted.");
                } else {
                    System.out.println("Question not found.");
                }

            } else if (choice.equals("9")) {
                System.out.print("Exam Title: ");
                String title = sc.nextLine();
                System.out.print("Subject ID: ");
                String subjectId = sc.nextLine();
                int duration = readInt(sc, "Duration in minutes: ");
                if (duration <= 0) {
                    duration = 10;
                }
                Exam e = new Exam(store.nextExamId(), title, subjectId, duration);
                store.addExam(e);
                System.out.println("Exam added with ID " + e.getExamId() + ".");

            } else if (choice.equals("10")) {
                ArrayList<Exam> exams = store.getAllExams();
                for (int i = 0; i < exams.size(); i++) {
                    Exam e = exams.get(i);
                    int count = store.getQuestionsForExam(e.getExamId()).size();
                    e.displayExam();
                    System.out.println("   Questions: " + count);
                }

            } else if (choice.equals("11")) {
                ArrayList<Result> results = store.getAllResults();
                if (results.size() == 0) {
                    System.out.println("No results yet.");
                } else {
                    for (int i = 0; i < results.size(); i++) {
                        Result r = results.get(i);
                        System.out.println(r.getStudentName() + " (" + r.getStudentId() + ") - "
                                + r.getExamTitle() + ": " + r.getScore() + "/" + r.getTotalMarks()
                                + " = " + r.calculatePercentage() + "%");
                    }
                }

            } else if (choice.equals("12")) {
                System.out.println("Logged out.");
                break;

            } else {
                System.out.println("Invalid choice. Enter a number from 1 to 12.");
            }
        }
    }
}
