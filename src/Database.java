// Backend Part 3: Database
// VTU Concept: JDBC - Step 3 (run SQL) and Step 4 (close)
//
// Every method here follows the same simple pattern:
//   1. Get a connection from DBConnection.
//   2. Make a PreparedStatement with ? marks (values are filled safely).
//   3. Run it: executeQuery() for SELECT, executeUpdate() for
//      INSERT / DELETE.
//   4. Close the statement and the connection.
//
// If MySQL is not connected, every method safely returns null, an
// empty list or false, and the program continues in Demo Mode
// using DataStore. So these methods can never crash the program.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {

    // Check a student login in the students table.
    // Returns the Student if ID and password match, otherwise null.
    public static Student checkStudentLogin(String studentId, String password) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }
        try {
            String sql = "SELECT * FROM students WHERE student_id = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, studentId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            Student s = null;
            if (rs.next()) {
                s = new Student(rs.getString("name"), rs.getString("student_id"),
                        rs.getString("password"), rs.getString("department"));
            }
            rs.close();
            ps.close();
            con.close();
            return s;
        } catch (Exception e) {
            return null;
        }
    }

    // Check an admin login in the admins table.
    public static Admin checkAdminLogin(String adminId, String password) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return null;
        }
        try {
            String sql = "SELECT * FROM admins WHERE admin_id = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, adminId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            Admin a = null;
            if (rs.next()) {
                // In the admins table, the username column holds the admin name
                a = new Admin(rs.getString("username"), rs.getString("admin_id"),
                        rs.getString("password"));
            }
            rs.close();
            ps.close();
            con.close();
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    // Load all questions of one exam from the questions table.
    public static ArrayList<Question> loadQuestionsForExam(int examId) {
        ArrayList<Question> list = new ArrayList<Question>();
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return list;
        }
        try {
            String sql = "SELECT * FROM questions WHERE exam_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, examId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Question q = new Question(rs.getInt("question_id"), rs.getInt("exam_id"),
                        rs.getString("question"), rs.getString("option1"),
                        rs.getString("option2"), rs.getString("option3"),
                        rs.getString("option4"), rs.getInt("correct_option"),
                        rs.getInt("marks"));
                list.add(q);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (Exception e) {
            // return whatever was loaded (possibly an empty list)
        }
        return list;
    }

    // Save one result in the results table. Returns true if saved.
    public static boolean saveResult(Result r) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return false;
        }
        try {
            String sql = "INSERT INTO results (student_id, exam_id, score, total_marks, percentage) "
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, r.getStudentId());
            ps.setInt(2, r.getExamId());
            ps.setInt(3, r.getScore());
            ps.setInt(4, r.getTotalMarks());
            ps.setDouble(5, r.calculatePercentage());
            int rows = ps.executeUpdate();
            ps.close();
            con.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Add one question to the questions table. Returns true if added.
    public static boolean addQuestion(Question q, String subjectId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return false;
        }
        try {
            String sql = "INSERT INTO questions (question_id, exam_id, subject_id, question, "
                    + "option1, option2, option3, option4, correct_option, marks) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, q.getQuestionId());
            ps.setInt(2, q.getExamId());
            ps.setString(3, subjectId);
            ps.setString(4, q.getQuestionText());
            ps.setString(5, q.getOption1());
            ps.setString(6, q.getOption2());
            ps.setString(7, q.getOption3());
            ps.setString(8, q.getOption4());
            ps.setInt(9, q.getCorrectOption());
            ps.setInt(10, q.getMarks());
            int rows = ps.executeUpdate();
            ps.close();
            con.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Delete one question from the questions table. Returns true if deleted.
    public static boolean deleteQuestion(int questionId) {
        Connection con = DBConnection.getConnection();
        if (con == null) {
            return false;
        }
        try {
            String sql = "DELETE FROM questions WHERE question_id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, questionId);
            int rows = ps.executeUpdate();
            ps.close();
            con.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
