// VTU OOPs Concept: ArrayList (Collections) + Object interaction
// Exam holds its details. The questions of the exam are taken from
// DataStore at the time of the exam, shown in random order, and the
// exam is submitted automatically when the time is over.

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Exam {
    private int examId;
    private String title;
    private String subjectId;
    private int durationMinutes;

    public Exam(int examId, String title, String subjectId, int durationMinutes) {
        this.examId = examId;
        this.title = title;
        this.subjectId = subjectId;
        this.durationMinutes = durationMinutes;
    }

    public int getExamId() {
        return examId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void displayExam() {
        System.out.println(examId + ". " + title + " (Subject: " + subjectId
                + ", Time: " + durationMinutes + " minutes)");
    }

    // Conduct the exam: ask questions one by one in random order,
    // check the time, calculate the score and return the Result.
    public Result conductExam(Scanner sc, Student student, ArrayList<Question> questions) {
        // Copy the list and shuffle it: random question order for every student
        ArrayList<Question> examQuestions = new ArrayList<Question>(questions);
        Collections.shuffle(examQuestions);

        int totalMarks = 0;
        for (int i = 0; i < examQuestions.size(); i++) {
            totalMarks = totalMarks + examQuestions.get(i).getMarks();
        }

        int score = 0;
        long startTime = System.currentTimeMillis();
        long timeLimit = durationMinutes * 60L * 1000L;

        System.out.println("\nExam: " + title + "  |  Time: " + durationMinutes + " minutes");
        System.out.println("Answer with 1, 2, 3 or 4.\n");

        for (int i = 0; i < examQuestions.size(); i++) {
            // Timer: if time is over, submit automatically
            long timeUsed = System.currentTimeMillis() - startTime;
            if (timeUsed > timeLimit) {
                System.out.println("Time over! Exam submitted automatically.");
                break;
            }

            Question q = examQuestions.get(i);
            System.out.println("Q" + (i + 1) + ".");
            q.displayQuestion();
            System.out.print("Your answer: ");

            int answer = 0;
            try {
                answer = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Counted as wrong.");
                answer = 0;
            }

            if (q.isCorrect(answer)) {
                score = score + q.getMarks();
                System.out.println("Correct!\n");
            } else {
                System.out.println("Wrong.\n");
            }
        }

        return new Result(student.getId(), student.getName(), examId, title,
                score, totalMarks);
    }
}
