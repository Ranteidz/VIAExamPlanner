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
    private ExamList examList; //TODO impelemtn exam-related stuff
    private Database db;

    public DataModel() {
        courseList = new CourseList();
        examinerList = new ExaminerList();
        classroomList = new ClassroomList();
        studentList = new StudentList();
        db = new Database();
    }

    public void loadAll() {
        courseList.addAll((ArrayList<Course>) db.load("Courses"));
        examinerList.addAll((ArrayList<Examiner>) db.load("Examiners"));
        classroomList.addAll((ArrayList<Classroom>) db.load("Classrooms"));
        studentList.addAll((ArrayList<Student>) db.load("Students"));
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
        return studentList.getStudentsByCourseID(courseId);
    }

    public ArrayList<Date> getExaminerUnavailabilityDates(String examinerID) {
        return examinerList.getExaminerByID(examinerID).getUnavailableDates();
    }

    public void addExaminer(Examiner examiner) {
        if (examinerList.addExam(examiner))
            db.save(examiner);
    }

    public void deleteExaminer(Examiner examiner) {
        if(examinerList.removeExaminer(examiner))
            db.removeExaminer(examiner);
    }

    public void addClassroom(Classroom classroom) {
        if(classroomList.addClassroom(classroom))
            db.save(classroom);
    }

    public void deleteClassroom(Classroom classroom) {
        if(classroomList.removeClassroom(classroom))
            db.removeClassroom(classroom);
    }

    public void addStudent(Student student) {
        if(studentList.addStudent(student))
            db.save(student);
    }

    public void deleteStudent(Student student) {
        if(studentList.removeStudent(student))
            db.removeStudent(student);
    }

    public void addCourse(Course course) {
        if(courseList.addCourse(course))
            db.save(course);
    }

    public void deleteCourse(Course course) {
        if(courseList.removeCourse(course))
            db.removeCourse(course);
    }

    public Student getStudent(String studentID) {
        return studentList.getStudentByID(Integer.parseInt(studentID));
    }


    //TODO change edit
/*    public void editExaminer(Examiner newExaminer) {
        examinerList.editExaminer(newExaminer);
    }

    public void editStudent(Student newStudent) {
        studentList.editStudent(newStudent);
    }

    public void addStudentToCourse(Course newCourse, Student newStudent) {
        courseList.insertStudentToCourse(newCourse, newStudent);
    }


    public void addUnavailabilityDateToExaminer(Examiner newExaminer, Date newDate) {
        examinerList.insertUnavailabilityToExaminer(newExaminer, newDate);
    }


    public void deleteUnavailabilityDateFromExaminer(Examiner newExaminer, Date newDate) {
        examinerList.removeUnavailabilityFromExaminer(newExaminer, newDate);
    }*/
}
