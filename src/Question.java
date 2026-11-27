// VTU OOPs Concept: Class & Object + Encapsulation + Constructor Overloading
// One Question object = one MCQ. All data is private.
// Each question belongs to one exam (examId).

public class Question {
    private int questionId;
    private int examId;
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctOption; // 1 to 4
    private int marks;

    // Constructor 1: marks are given
    public Question(int questionId, int examId, String questionText, String option1,
                    String option2, String option3, String option4,
                    int correctOption, int marks) {
        this.questionId = questionId;
        this.examId = examId;
        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.marks = marks;
    }

    // Constructor 2 (overloading): same name, fewer inputs, marks = 1 by default
    public Question(int questionId, int examId, String questionText, String option1,
                    String option2, String option3, String option4, int correctOption) {
        this(questionId, examId, questionText, option1, option2, option3, option4,
                correctOption, 1);
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getExamId() {
        return examId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public int getMarks() {
        return marks;
    }

    // Show the question on screen
    public void displayQuestion() {
        System.out.println(questionText);
        System.out.println("1. " + option1);
        System.out.println("2. " + option2);
        System.out.println("3. " + option3);
        System.out.println("4. " + option4);
    }

    // Show the question with its ID (used by the admin)
    public void displayQuestionWithId() {
        System.out.println("ID " + questionId + " (Exam " + examId + "): " + questionText);
        System.out.println("   1. " + option1 + "  2. " + option2
                + "  3. " + option3 + "  4. " + option4);
        System.out.println("   Correct option: " + correctOption + ", Marks: " + marks);
    }

    // Check the student's answer. Returns true if correct.
    public boolean isCorrect(int answer) {
        return answer == correctOption;
    }
}
