package model;

import model.classes.*;
import model.classes.Date;
import model.dao.ExaminerDao;
import model.dao.StudentDao;
import model.lists.*;

import java.sql.*;
import java.util.ArrayList;

public class DataModel {
    private CourseList courseList;
    private ClassroomList classroomList;
    private ExaminerList examinerList;
    private StudentList studentList;
    private ExamList examList;
    private Persistence db;

    public DataModel() {
        courseList = new CourseList();
        examinerList = new ExaminerList();
        classroomList = new ClassroomList();
        studentList = new StudentList();
        examList = new ExamList();
        db = new Database();
        classroomList.loadClassrooms(db.loadClassrooms());
        studentList.loadStudents(db.loadStudents());
        examinerList.loadExaminers(db.loadExaminers());
        courseList.loadCourses(db.loadCourses());
        examList.loadExams(db.loadExams());
    }

    //TODO remove this after getting done with separation of database
    public static String getDatabaseConnectionString() {
        return "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    }

    public ArrayList<Course> getCoursesBySearch(String search) {
        return courseList.getCoursesBySearch(search);
    }

    public ArrayList<Course> getAvailableCourses(String search) {
        ArrayList<Course> availableCourses = new ArrayList<Course>();
        for (Course course : getCoursesBySearch(search))
            if (!examList.getExams().contains(getExamById(course.courseIdProperty().get())))
                availableCourses.add(course);
        return availableCourses;
    }

    public Exam getExamById(String examId) {
        return examList.getExamById(examId);
    }

    public ArrayList<Classroom> getClassroomsBySearch(String search) {
        return classroomList.getClassroomsBySearch(search);
    }

    public ArrayList<Classroom> getClassroomsBySearch(String search, String courseId, Date date) {
        return classroomList.getClassroomsById(examList.getReservedClassroomsIDs(examList.getExamsByDate(date)));
    }

    public boolean classroomDeletable(Classroom classroom) {
        return !(classroomList.getClassroomsById(examList.getReservedClassroomsIDs())).contains(classroom);
    }

    public ArrayList<Examiner> getExaminersBySearch(String search) {
        return examinerList.getExaminersBySearch(search);
    }

    public ArrayList<Examiner> getAvailableExaminers(String search, Date date) {
        ArrayList<Examiner> availableExaminers = examinerList.getAvailableExaminers(search, date);
        for (Exam exam : examList.getExamsByDate(date))
            availableExaminers.remove(getExaminerById(exam.examinerIdProperty().get()));
        return availableExaminers;
    }

    public ArrayList<Student> getStudentsBySearch(String search) {
        return studentList.getStudentsBySearch(search);
    }


    public void loadAll() {
        courseList.loadCourses(db.loadCourses());
        examinerList.loadExaminers(db.loadExaminers());
        classroomList.loadClassrooms(db.loadClassrooms());
        studentList.loadStudents(db.loadStudents());
        examList.loadExams(db.loadExams());
    }

    public ArrayList<Student> getStudentAll() {
        return studentList.getStudents();
    }

    public ArrayList<Exam> getExamAll() {
        return examList.getExams();
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

    public ArrayList<Student> getStudentsByCourse(String courseId) {
        return courseList.getStudentsByCourse(courseId);
    }

    public ArrayList<Date> getExaminerUnavailabilityDates(String examinerID) {
        return examinerList.getExaminerByID(examinerID).getUnavailableDates();
    }

    public void addExaminer(Examiner examiner) {
        if (examinerList.addExam(examiner)) {
            db.save(examiner);
            for (Date date : examiner.unavailableDatesProperty())
                db.insertUnavailabilityToExaminer(examiner, date);
        }
    }

    public void addExam(Exam exam) {
        if (examList.addExam(exam)) {
            db.insertExam(exam, getCourseById(exam.courseIdProperty().get()), getClassroomById(exam.classroomIdProperty().get()), exam.getDate());
        }
    }

    private Examiner getExaminerById(String examinerId) {
        return examinerList.getExaminerByID(examinerId);
    }


    public Classroom getClassroomById(String classroomId) {
        return classroomList.getClassroomById(classroomId);
    }


    public Course getCourseById(String courseId) {
        return courseList.getCourseById(courseId);
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
        if (studentList.removeStudent(student)) {
            db.removeStudent(student);
            courseList.removeStudent(student);
        }
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

    public void editExaminer(Examiner examiner, ArrayList<Date> deletedDates, ArrayList<Date> addedDates) {
        if (examinerList.editExaminer(examiner)) {
            db.editExaminer(examiner);
            for (Date date : addedDates)
                db.insertUnavailabilityToExaminer(examiner, date);
            for (Date date : deletedDates)
                db.removeUnavailabilityFromExaminer(examiner, date);
        }
    }

    public void editExam(Exam exam, String oldExaminerId) {
        examList.editExam(exam);
        db.editExam(exam);


    }
    public void removeExam(Exam exam){
        if(examList.removeExam(exam))
            db.removeExam(exam);
    }

    public ArrayList<Exam> getExamsByDate(Date date) {
        return examList.getExamsByDate(date);
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

    public void addStudentsToCourse(Course course, ArrayList<Student> addedStudents) {
        for (Student student : addedStudents) {
            addStudentToCourse(course, student);
        }
    }

    public void removeStudentsFromCourse(Course course, ArrayList<Student> deletedStudents) {
        for (Student student : deletedStudents) {
            removeStudentsFromCourse(course, deletedStudents);
        }
    }

    public void addUnavailabilityDatesToExaminer(Examiner examiner, ArrayList<Date> examinerDates) {
        for (Date date : examinerDates) {
            addUnavailabilityDateToExaminer(examiner, date);
        }
    }

    //TODO

    public void addUnavailabilityDateToExaminer(Examiner newExaminer, Date newDate) {
//        db.insertUnavailabilityToExaminer(newExaminer, newDate);
    }


/*

    public void deleteUnavailabilityDateFromExaminer(Examiner newExaminer, Date newDate) {
        examinerList.removeUnavailabilityFromExaminer(newExaminer, newDate);
    }*/
}
