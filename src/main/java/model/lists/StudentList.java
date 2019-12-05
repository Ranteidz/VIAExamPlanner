package model.lists;

import javafx.collections.ObservableList;
import model.DataModel;
import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        loadStudents();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    private void process(ResultSet rs, Student student) throws SQLException {
        student.setStudentID(rs.getInt("ID"));
        student.setStudentFirstName(rs.getString("Name"));
        student.setStudentLastName(rs.getString("Surname"));
    }

    private void loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString()); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.students";
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Student tmpStudent = new Student();
                process(rs, tmpStudent);
                students.add(tmpStudent);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        this.students = students;
    }

    public Student getStudentByID(int studentID) throws NullPointerException {
        for (Student student : students) {
            if (student.studentIdProperty().get() == studentID)
                return student;
        }
        return null;
    }

    public void insertStudent(Student newStudent) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Students (ID, Name, Surname)" + " values(?, ?, ?)");
            posted.setString(1, Integer.toString(newStudent.studentIdProperty().get()));
            posted.setString(2, newStudent.studentFirstNameProperty().get());
            posted.setString(3, newStudent.studentLastNameProperty().get());
            posted.execute();
            students.add(newStudent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeStudent(Student newStudent) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Students WHERE ID= ?");
            posted.setString(1, Integer.toString(newStudent.studentIdProperty().get()));
            posted.executeUpdate();
            students.remove(newStudent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ArrayList<Student> getStudentsByCourseID(String courseId) {
        ArrayList<Student> Students = new ArrayList<Student>();
        //TODO get students by course -- SELECT * FROM students where id IN (SELECT studentId FROM student-course-table WHERE courseid = courseid)

        try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString())) {
            String SQL = "SELECT * FROM dbo.Students WHERE ID IN (SELECT StudentID FROM dbo.Students_Courses WHERE CourseID = ?) ";
            PreparedStatement preparedStatement
                    = con.prepareStatement(SQL);

            preparedStatement.setString(1, courseId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Student tmpStudent = new Student();
                process(rs, tmpStudent);
                Students.add(tmpStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Students;
    }

    public void editStudent(Student newStudent) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("UPDATE Students SET Name = ?, Surname = ? WHERE id = ?");
            posted.setString(1, newStudent.studentFirstNameProperty().get());
            posted.setString(2, newStudent.studentLastNameProperty().get());
            posted.setString(3, String.valueOf(newStudent.studentIdProperty().get()));
            posted.executeUpdate();
            students.remove(getStudentByID(newStudent.studentIdProperty().get()));
            students.add(newStudent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
