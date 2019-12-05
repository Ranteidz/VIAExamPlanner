package model;

import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    public Database() {
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
}
