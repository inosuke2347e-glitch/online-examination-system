// VTU OOPs Concept: Encapsulation + simple calculation
// One Result object stores one student's score for one exam.

public class Result {
    private String studentId;
    private String studentName;
    private int examId;
    private String examTitle;
    private int score;
    private int totalMarks;

    public Result(String studentId, String studentName, int examId,
                  String examTitle, int score, int totalMarks) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.examId = examId;
        this.examTitle = examTitle;
        this.score = score;
        this.totalMarks = totalMarks;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public int getExamId() {
        return examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public int getScore() {
        return score;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    // Percentage = (score / total) * 100
    public double calculatePercentage() {
        if (totalMarks == 0) {
            return 0;
        }
        return (score * 100.0) / totalMarks;
    }

    public void displayResult() {
        System.out.println("----- Result -----");
        System.out.println("Student: " + studentName + " (" + studentId + ")");
        System.out.println("Exam: " + examTitle);
        System.out.println("Score: " + score + " / " + totalMarks);
        System.out.println("Percentage: " + calculatePercentage() + "%");
        if (calculatePercentage() >= 40) {
            System.out.println("Status: Pass");
        } else {
            System.out.println("Status: Fail");
        }
    }
}
