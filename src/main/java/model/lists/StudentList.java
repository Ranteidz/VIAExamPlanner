package model.lists;

import model.DataModel;
import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        students = new ArrayList<Student>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    private void loadStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void addAll(ArrayList<Student> students) {
        for(Student student : students) {
            this.students.add(student);
        }
    }

    public ArrayList<Student> getStudentsBySearch(String search) {
        if (!search.isEmpty()) {
            ArrayList<Student> searchItems = new ArrayList<Student>();
            for (Student student : students) {
                if (student.studentInfoProperty().get().contains(search))
                    searchItems.add(student);
            }
            return searchItems;
        }
        return students;
    }

    public Student getStudentByID(int studentID) throws NullPointerException {
        for (Student student : students) {
            if (student.studentIdProperty().get() == studentID)
                return student;
        }
        return null;
    }

    public boolean addStudent(Student student) {
        if(!students.contains(student)){
            students.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            return true;
        }
        return false;
    }


    //TODO change these
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
//                process(rs, tmpStudent); TODO fix this
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
