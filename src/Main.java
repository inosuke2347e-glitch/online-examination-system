// Online Examination System - Main Program
// Students: Avinash and Shubam, 3rd Semester, AI & DS
// VTU OOPs Concepts used here: Objects, ArrayList, Scanner, Exception Handling
//
// WHAT IS BUILT IN THIS VERSION (about 20-25% of the full synopsis):
//   1. Student login and Admin login (demo accounts, no database yet)
//   2. Question bank with 5 sample MCQs (Java OOPs basics)
//   3. Timed exam with automatic submission when time is over
//   4. Automatic checking, score and percentage, result stored in a list
// NOT BUILT YET (remaining 75-80%): MySQL database + JDBC, full Admin
//   add/edit/delete questions, Swing GUI, file storage, negative marking.
//
// Demo logins (for testing only, database login comes later):
//   Student: ID = S101, Password = student123
//   Admin:   ID = admin, Password = admin123

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Two sample users. In the full project these will come from MySQL.
        Student demoStudent = new Student("Shubam", "S101", "student123", "AI & DS");
        Admin demoAdmin = new Admin("Avinash", "admin", "admin123");

        // One sample exam with 5 questions
        Exam javaExam = new Exam("Java OOPs Basics", 10);
        javaExam.addQuestion(new Question(
                "Which of these is a pillar of OOP?",
                "Encapsulation", "Compilation", "Debugging", "Linking", 1));
        javaExam.addQuestion(new Question(
                "Which keyword is used to inherit a class in Java?",
                "implements", "extends", "inherits", "super", 2));
        javaExam.addQuestion(new Question(
                "Which of these hides data and shows only methods?",
                "Polymorphism", "Inheritance", "Encapsulation", "Abstraction", 3));
        javaExam.addQuestion(new Question(
                "How many objects can be created from one class?",
                "Only one", "Only two", "As many as needed", "None", 3));
        javaExam.addQuestion(new Question(
                "Which method is the starting point of a Java program?",
                "start()", "main()", "run()", "init()", 2));

        // All results of this run are kept in a list (in memory only)
        ArrayList<Result> allResults = new ArrayList<Result>();

        System.out.println("======================================");
        System.out.println("  ONLINE EXAMINATION SYSTEM");
        System.out.println("  Avinash and Shubam | 3rd Sem, AI & DS");
        System.out.println("======================================");

        while (true) {
            System.out.println("\n1. Student Login");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                // --- Student login ---
                System.out.print("Enter Student ID: ");
                String id = sc.nextLine();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                if (id.equals(demoStudent.getId()) && demoStudent.checkPassword(password)) {
                    System.out.println("\nLogin successful. Welcome, " + demoStudent.getName() + "!");
                    demoStudent.displayRole();
                    Result result = javaExam.conductExam(sc, demoStudent);
                    result.displayResult();
                    allResults.add(result);
                } else {
                    System.out.println("Wrong ID or password. Try again.");
                }

            } else if (choice.equals("2")) {
                // --- Admin login: view questions count and results so far ---
                System.out.print("Enter Admin ID: ");
                String id = sc.nextLine();
                System.out.print("Enter Password: ");
                String password = sc.nextLine();

                if (id.equals(demoAdmin.getId()) && demoAdmin.checkPassword(password)) {
                    System.out.println("\nLogin successful. Welcome, " + demoAdmin.getName() + "!");
                    demoAdmin.displayRole();
                    System.out.println("Exam: " + javaExam.getTitle());
                    System.out.println("Total Marks: " + javaExam.getTotalMarks());
                    System.out.println("Results stored in this run: " + allResults.size());
                    for (int i = 0; i < allResults.size(); i++) {
                        Result r = allResults.get(i);
                        System.out.println("- " + r.getStudentName() + ": "
                                + r.getScore() + "/" + r.getTotalMarks());
                    }
                } else {
                    System.out.println("Wrong ID or password. Try again.");
                }

            } else if (choice.equals("3")) {
                System.out.println("Thank you. Exiting.");
                break;

            } else {
                System.out.println("Invalid choice. Enter 1, 2 or 3.");
            }
        }

        sc.close();
    }
}
