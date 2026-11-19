// VTU OOPs Concept: Encapsulation + simple calculation
// One Result object stores one student's score for one exam.

public class Result {
    private String studentName;
    private String examTitle;
    private int score;
    private int totalMarks;

    public Result(String studentName, String examTitle, int score, int totalMarks) {
        this.studentName = studentName;
        this.examTitle = examTitle;
        this.score = score;
        this.totalMarks = totalMarks;
    }

    public String getStudentName() {
        return studentName;
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
        System.out.println("Student: " + studentName);
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
