package model;

import model.classes.Classroom;
import model.classes.Course;
import model.classes.Examiner;
import model.classes.Student;
import model.dao.ClassRoomDao;
import model.dao.CourseDao;
import model.dao.ExaminerDao;
import model.dao.StudentDao;
import model.classes.Date;
import model.lists.ClassroomList;
import model.lists.CourseList;
import model.lists.ExaminerList;
import model.lists.StudentList;

import java.sql.*;
import java.util.ArrayList;

public class DataModel {
    private CourseList courseList;
    private ClassroomList classroomList;
    private ExaminerList examinerList;
    private StudentList studentList;

    static public String getDatabaseConnectionString() {
        return "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    }

    public ArrayList<Student> getStudentAll() {
        return studentList.getStudents();
    }

    public ArrayList<Student> getStudentsBySearch(String search) {
        if (!search.isEmpty()) {
            ArrayList<Student> searchItems = new ArrayList<Student>();
            for (Student student : studentList.getStudents()) {
                if (student.studentInfoProperty().get().contains(search))
                    searchItems.add(student);
            }
            return searchItems;
        }
        return studentList.getStudents();
    }

    public ArrayList<Student> getStudentsByCourse(String courseId) {
        return studentList.getStudentsByCourseID(courseId);
    }

    //TODO change
    static public ArrayList<Date> getExaminerUnavailabilityDates(String examinerID) {
        ExaminerDao examinerDao = new ExaminerDao();
        return examinerDao.getExaminerDates(examinerID);
    }

    public void addExaminer(Examiner newExaminer) {
        examinerList.insertExaminer(newExaminer);
    }

    public void deleteExaminer(Examiner newExaminer) {
        examinerList.removeExaminer(newExaminer);
    }

    public void addClassroom(Classroom newClassroom) {
        classroomList.insertClassroom(newClassroom);
    }

    public void deleteClassroom(Classroom newClassroom) {
        classroomList.removeClassroom(newClassroom);
    }

    public void addStudent(Student newStudent) {
        studentList.insertStudent(newStudent);
    }

    public void deleteStudent(Student newStudent) {
        studentList.removeStudent(newStudent);
    }

    public void editStudent(Student newStudent) {
        studentList.editStudent(newStudent);
    }

    public Student getStudent(String studentID) {
        return studentList.getStudentByID(Integer.parseInt(studentID));
    }

    public void editExaminer(Examiner newExaminer) {
        examinerList.editExaminer(newExaminer);
    }


    public void addCourse(Course newCourse) {
        courseList.insertCourse(newCourse);
    }

    public void addStudentToCourse(Course newCourse, Student newStudent) {
        courseList.insertStudentToCourse(newCourse, newStudent);
    }


    public void addUnavailabilityDateToExaminer(Examiner newExaminer, Date newDate) {
        examinerList.insertUnavailabilityToExaminer(newExaminer, newDate);
    }


    public void deleteUnavailabilityDateFromExaminer(Examiner newExaminer, Date newDate) {
        examinerList.removeUnavailabilityFromExaminer(newExaminer, newDate);
    }

    public void deleteCourse(Course newCourse) {
        courseList.removeCourse(newCourse);
    }

    public ArrayList<Classroom> getClassRoomsAll() {
        return classroomList.getClassrooms();
    }

    public ArrayList<Examiner> getExaminersALL() {
        return examinerList.getExaminers();
    }

    public ArrayList<Course> getCoursesAll() {
        return courseList.getCourses();
    }

    //TODO can remove from here to end
    static public void addStudentToArray(Student student) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(student);

    }

    static public ArrayList<Student> getStudentByName(String studentName) {
        StudentDao studentsDAO = new StudentDao();
        return studentsDAO.getStudentsByName(studentName);

    }

    static public ArrayList<Student> getStudentBySurname(String surname) {
        StudentDao studentsDAO = new StudentDao();
        return studentsDAO.getStudentsBySurname(surname);

    }

    public ArrayList<Student> studentTable() {
        ArrayList<Student> studentList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            String SQL = "Select * FROM Students";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            Student tmpStudent;
            while (rs.next()) {
                tmpStudent = new Student(rs.getInt("ID"), rs.getString("Name"),
                        rs.getString("Surname"));
                studentList.add(tmpStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }
}
