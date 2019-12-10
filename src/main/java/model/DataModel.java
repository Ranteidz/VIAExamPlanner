package model;

import model.classes.Classroom;
import model.classes.Course;
import model.classes.Examiner;
import model.classes.Student;
import model.dao.ExaminerDao;
import model.dao.StudentDao;
import model.classes.Date;
import model.lists.*;

import java.sql.*;
import java.util.ArrayList;

public class DataModel {
    private CourseList courseList;
    private ClassroomList classroomList;
    private ExaminerList examinerList;
    private StudentList studentList;
    private ExamList examList; //TODO implement exam-related stuff
    private Database db;
    public volatile boolean loading;

    public DataModel() {
        courseList = new CourseList();
        examinerList = new ExaminerList();
        classroomList = new ClassroomList();
        studentList = new StudentList();
        db = new Database();
    }

    //TODO remove this after getting done with separation of database
    public static String getDatabaseConnectionString() {
        return "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    }

    public void loadAll() {
        loading = true;
        courseList.loadCourses(db.loadCourses());
        examinerList.loadExaminers(db.loadExaminers());
        classroomList.loadClassrooms(db.loadClassrooms());
        studentList.loadStudents(db.loadStudents());
        loading = false;
    }

    public ArrayList<Student> getStudentAll() {
        return studentList.getStudents();
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

    public ArrayList<Student> getStudentsBySearch(String search) {
        return studentList.getStudentsBySearch(search);
    }

    public ArrayList<Student> getStudentsByCourse(String courseId) {
        return courseList.getStudentsByCourse(courseId);
    }

    public ArrayList<Date> getExaminerUnavailabilityDates(String examinerID) {
        return examinerList.getExaminerByID(examinerID).getUnavailableDates();
    }

    public void addExaminer(Examiner examiner) {
        if (examinerList.addExam(examiner))
            db.save(examiner);
    }

    public void deleteExaminer(Examiner examiner) {
        if (examinerList.removeExaminer(examiner))
            db.removeExaminer(examiner);
    }

    public void addClassroom(Classroom classroom) {
        if (classroomList.addClassroom(classroom))
            db.save(classroom);
    }

    public void deleteClassroom(Classroom classroom) {
        if (classroomList.removeClassroom(classroom))
            db.removeClassroom(classroom);
    }

    public void addStudent(Student student) {
        if (studentList.addStudent(student))
            db.save(student);
    }

    public void deleteStudent(Student student) {
        if (studentList.removeStudent(student))
            db.removeStudent(student);
    }

    public void addCourse(Course course) {
        if (!course.courseIdProperty().get().isEmpty())
            if (courseList.addCourse(course))
                db.save(course);
    }

    public void deleteCourse(Course course) {
        if (courseList.removeCourse(course))
            db.removeCourse(course);
    }

    public Student getStudent(String studentID) {
        return studentList.getStudentByID(Integer.parseInt(studentID));
    }

    public void editExaminer(Examiner examiner) {
        if (examinerList.editExaminer(examiner))
            db.editExaminer(examiner);
    }

    public void editStudent(Student student) {
        if (studentList.editStudent(student))
            db.editStudent(student);
    }

    public void addStudentToCourse(Course course, Student student) {
        Student localStudent = getStudent(String.valueOf(student.studentIdProperty().get()));
        if (courseList.insertStudentToCourse(course, localStudent)) {
            db.insertStudentToCourse(course, localStudent);
        }
    }

    public void removeStudentFromCourse(Course course, Student student) {
        if (courseList.removeStudentFromCourse(course, getStudent(String.valueOf(student.studentIdProperty().get())))) {
            db.removeStudentFromCourse(course, getStudent(String.valueOf(student.studentIdProperty().get())));
        }
    }

    public void editClassroom(Classroom classroom) {
        if (classroomList.editClassroom(classroom))
            db.editClassroom(classroom);
    }

    public void editCourse(Course course) {
        if (!course.courseIdProperty().get().isEmpty())
            if (courseList.editCourse(course))
                db.editCourse(course);
    }

    //TODO
/*
    public void addUnavailabilityDateToExaminer(Examiner newExaminer, Date newDate) {
        examinerList.insertUnavailabilityToExaminer(newExaminer, newDate);
    }


    public void deleteUnavailabilityDateFromExaminer(Examiner newExaminer, Date newDate) {
        examinerList.removeUnavailabilityFromExaminer(newExaminer, newDate);
    }*/
}
