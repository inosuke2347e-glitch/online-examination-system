// Backend Part 1: DataStore
// VTU OOPs Concept: ArrayList (Collections) + CRUD operations
//
// This class is the storage of the program. It keeps students, admins,
// subjects, questions, exams and results in ArrayLists (in memory).
// It also loads the sample (seed) data at the start.
//
// When MySQL is connected, the Database class works on the same data
// in the database tables. When MySQL is not connected, the program
// runs fully on this DataStore - this is called Demo Mode, so the
// project can be demonstrated on any computer with only a JDK.

import java.util.ArrayList;

public class DataStore {
    private ArrayList<Student> students;
    private ArrayList<Admin> admins;
    private ArrayList<Subject> subjects;
    private ArrayList<Question> questions;
    private ArrayList<Exam> exams;
    private ArrayList<Result> results;

    public DataStore() {
        students = new ArrayList<Student>();
        admins = new ArrayList<Admin>();
        subjects = new ArrayList<Subject>();
        questions = new ArrayList<Question>();
        exams = new ArrayList<Exam>();
        results = new ArrayList<Result>();
    }

    // ---------------- Seed (sample) data ----------------
    public void seedData() {
        admins.add(new Admin("Avinash", "admin", "admin123"));

        students.add(new Student("Shubam", "S101", "student123", "AI & DS"));
        students.add(new Student("Rahul", "S102", "rahul123", "AI & DS"));

        subjects.add(new Subject("SUB1", "Java OOPs"));
        subjects.add(new Subject("SUB2", "Data Structures"));

        exams.add(new Exam(1, "Java OOPs Basics", "SUB1", 10));
        exams.add(new Exam(2, "Data Structures Basics", "SUB2", 10));

        // Exam 1: Java OOPs Basics - 10 questions, 1 mark each
        questions.add(new Question(1, 1, "Which of these is a pillar of OOP?",
                "Encapsulation", "Compilation", "Debugging", "Linking", 1));
        questions.add(new Question(2, 1, "Which keyword is used to inherit a class in Java?",
                "implements", "extends", "inherits", "super", 2));
        questions.add(new Question(3, 1, "Which of these hides data and shows only methods?",
                "Polymorphism", "Inheritance", "Encapsulation", "Abstraction", 3));
        questions.add(new Question(4, 1, "How many objects can be created from one class?",
                "Only one", "Only two", "As many as needed", "None", 3));
        questions.add(new Question(5, 1, "Which method is the starting point of a Java program?",
                "start()", "main()", "run()", "init()", 2));
        questions.add(new Question(6, 1, "Which keyword is used to create an object in Java?",
                "create", "new", "make", "object", 2));
        questions.add(new Question(7, 1, "Method overloading means:",
                "Same method name with different parameters",
                "Same method name in a child class",
                "Two classes with the same name", "None of these", 1));
        questions.add(new Question(8, 1, "Which access modifier makes a member visible only inside its own class?",
                "public", "private", "protected", "default", 2));
        questions.add(new Question(9, 1, "Which keyword is used by a class to use an interface?",
                "extends", "implements", "interface", "abstract", 2));
        questions.add(new Question(10, 1, "Which of these is NOT a pillar of OOP?",
                "Encapsulation", "Inheritance", "Polymorphism", "Compilation", 4));

        // Exam 2: Data Structures Basics - 5 questions, 1 mark each
        questions.add(new Question(11, 2, "Which data structure works on LIFO (Last In First Out)?",
                "Queue", "Stack", "Linked List", "Tree", 2));
        questions.add(new Question(12, 2, "Which data structure works on FIFO (First In First Out)?",
                "Queue", "Stack", "Tree", "Graph", 1));
        questions.add(new Question(13, 2, "In a stack, insertion of an element is called:",
                "Pop", "Delete", "Push", "Peek", 3));
        questions.add(new Question(14, 2, "Which of these is a linear data structure?",
                "Tree", "Graph", "Array", "Binary Tree", 3));
        questions.add(new Question(15, 2, "Time needed to access an array element by its index is:",
                "O(n)", "O(log n)", "O(n log n)", "O(1)", 4));
    }

    // ---------------- Students ----------------
    public Student findStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(studentId)) {
                return students.get(i);
            }
        }
        return null;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    // ---------------- Admins ----------------
    public Admin findAdmin(String adminId) {
        for (int i = 0; i < admins.size(); i++) {
            if (admins.get(i).getId().equals(adminId)) {
                return admins.get(i);
            }
        }
        return null;
    }

    // ---------------- Subjects ----------------
    public void addSubject(Subject s) {
        subjects.add(s);
    }

    public ArrayList<Subject> getAllSubjects() {
        return subjects;
    }

    // ---------------- Questions ----------------
    public void addQuestion(Question q) {
        questions.add(q);
    }

    public ArrayList<Question> getAllQuestions() {
        return questions;
    }

    // All questions of one exam
    public ArrayList<Question> getQuestionsForExam(int examId) {
        ArrayList<Question> list = new ArrayList<Question>();
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getExamId() == examId) {
                list.add(questions.get(i));
            }
        }
        return list;
    }

    // Search one question by its ID
    public Question findQuestionById(int questionId) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionId() == questionId) {
                return questions.get(i);
            }
        }
        return null;
    }

    // Delete one question by its ID. Returns true if deleted.
    public boolean deleteQuestionById(int questionId) {
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionId() == questionId) {
                questions.remove(i);
                return true;
            }
        }
        return false;
    }

    // Next free question ID (largest ID + 1)
    public int nextQuestionId() {
        int max = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getQuestionId() > max) {
                max = questions.get(i).getQuestionId();
            }
        }
        return max + 1;
    }

    // ---------------- Exams ----------------
    public void addExam(Exam e) {
        exams.add(e);
    }

    public ArrayList<Exam> getAllExams() {
        return exams;
    }

    public Exam findExamById(int examId) {
        for (int i = 0; i < exams.size(); i++) {
            if (exams.get(i).getExamId() == examId) {
                return exams.get(i);
            }
        }
        return null;
    }

    public int nextExamId() {
        int max = 0;
        for (int i = 0; i < exams.size(); i++) {
            if (exams.get(i).getExamId() > max) {
                max = exams.get(i).getExamId();
            }
        }
        return max + 1;
    }

    // ---------------- Results ----------------
    public void saveResult(Result r) {
        results.add(r);
    }

    public ArrayList<Result> getAllResults() {
        return results;
    }

    // All results of one student
    public ArrayList<Result> getResultsForStudent(String studentId) {
        ArrayList<Result> list = new ArrayList<Result>();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getStudentId().equals(studentId)) {
                list.add(results.get(i));
            }
        }
        return list;
    }
}
