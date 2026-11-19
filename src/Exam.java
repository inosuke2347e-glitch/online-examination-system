// VTU OOPs Concept: ArrayList (Collections) + Object interaction
// Exam holds many Question objects in an ArrayList and conducts the test.

import java.util.ArrayList;
import java.util.Scanner;

public class Exam {
    private String title;
    private int durationMinutes;
    private ArrayList<Question> questions;

    public Exam(String title, int durationMinutes) {
        this.title = title;
        this.durationMinutes = durationMinutes;
        this.questions = new ArrayList<Question>();
    }

    public String getTitle() {
        return title;
    }

    // Add one question to the exam
    public void addQuestion(Question q) {
        questions.add(q);
    }

    public int getTotalMarks() {
        int total = 0;
        for (int i = 0; i < questions.size(); i++) {
            total = total + questions.get(i).getMarks();
        }
        return total;
    }

    // Conduct the exam: ask questions one by one, check time, return Result.
    public Result conductExam(Scanner sc, Student student) {
        int score = 0;
        long startTime = System.currentTimeMillis();
        long timeLimit = durationMinutes * 60L * 1000L;

        System.out.println("\nExam: " + title + "  |  Time: " + durationMinutes + " minutes");
        System.out.println("Answer with 1, 2, 3 or 4.\n");

        for (int i = 0; i < questions.size(); i++) {
            // Timer: if time is over, submit automatically
            long timeUsed = System.currentTimeMillis() - startTime;
            if (timeUsed > timeLimit) {
                System.out.println("Time over! Exam submitted automatically.");
                break;
            }

            Question q = questions.get(i);
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

        return new Result(student.getName(), title, score, getTotalMarks());
    }
}
