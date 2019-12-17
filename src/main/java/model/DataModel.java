package model;

import model.classes.*;
import model.classes.Date;
import model.lists.*;

import java.util.ArrayList;

/**
 * Connection between GUI, system and persistence. Contains all the information.
 */
public class DataModel {
    private CourseList courseList;
    private ClassroomList classroomList;
    private ExaminerList examinerList;
    private StudentList studentList;
    private ExamList examList;
    private Persistence db;

    /**
     * Creates a new DataModel object.
     */
    public DataModel() {
        courseList = new CourseList();
        examinerList = new ExaminerList();
        classroomList = new ClassroomList();
        studentList = new StudentList();
        examList = new ExamList();
        db = new Database();
        loadAll();
    }

    /**
     * Returns all courses which meet search criteria.
     * @param search search criteria
     * @return list of courses which meet conditions
     */
    public ArrayList<Course> getCoursesBySearch(String search) {
        return courseList.getCoursesBySearch(search);
    }

    /**
     * Returns all courses which meet search criteria and have not been booked for an exam.
     * @param search search criteria
     * @return list of courses which meet conditions
     */
    public ArrayList<Course> getAvailableCourses(String search) {
        ArrayList<Course> availableCourses = new ArrayList<Course>();
        for (Course course : getCoursesBySearch(search))
            if (!examList.getExams().contains(getExamById(course.courseIdProperty().get())))
                availableCourses.add(course);
        return availableCourses;
    }

    /**
     * Returns an exam with a specific ID.
     * @param examId ID of exam
     * @return exam with parameter ID
     */
    private Exam getExamById(String examId) {
        return examList.getExamById(examId);
    }

    /**
     * Returns all classrooms which meet search criteria.
     * @param search search criteria
     * @return list of classrooms which meet conditions
     */
    public ArrayList<Classroom> getClassroomsBySearch(String search) {
        return classroomList.getClassroomsBySearch(search);
    }

    /**
     * Returns all classrooms which meet search criteria and are available on a specific date.
     * @param search search criteria
     * @param date filtration date
     * @return list of classrooms which meet conditions
     */
    public ArrayList<Classroom> getClassroomsBySearch(String search, Date date) {
        ArrayList<Classroom> availableClassrooms = classroomList.getClassroomsById(examList.getReservedClassroomsIDs(examList.getExamsByDate(date)));
        return ClassroomList.getClassroomsBySearch(search, availableClassrooms);
    }

    /**
     * Returns true if classroom is not booked for any exams.
     * @param classroom classroom
     * @return whether a classroom can be deleted/modified
     */
    public boolean classroomDeletable(Classroom classroom) {
        return (classroomList.getClassroomsById(examList.getReservedClassroomsIDs())).contains(classroom);
    }

    /**
     * Returns true if course is not booked for any exams.
     * @param course course
     * @return whether a course can be deleted/modified
     */
    public boolean courseDeletable(Course course) {
        return !examList.getExams().contains(getExamById(course.courseIdProperty().get()));
    }

    /**
     * Returns true if examiner is not booked for any exams.
     * @param examiner examiner
     * @return whether a examiner can be deleted/modified
     */
    public boolean examinerDeletable(Examiner examiner) {
        return !examinerList.getExaminersById(examList.getExaminerIds()).contains(examiner);
    }

    /**
     * Returns all examiners which meet search criteria.
     * @param search search criteria
     * @return list of examiners which meet conditions
     */
    public ArrayList<Examiner> getExaminersBySearch(String search) {
        return examinerList.getExaminersBySearch(search);
    }

    /**
     * Returns all examiners which meet search criteria and are available on a specific date.
     * @param search search criteria
     * @param date filtration date
     * @return list of examiners which meet conditions
     */
    public ArrayList<Examiner> getAvailableExaminers(String search, Date date) {
        ArrayList<Examiner> availableExaminers = examinerList.getAvailableExaminers(search, date);
        for (Exam exam : examList.getExamsByDate(date))
            availableExaminers.remove(getExaminerById(exam.examinerIdProperty().get()));
        return availableExaminers;
    }

    /**
     * Returns all students which meet search criteria.
     * @param search search criteria
     * @return list of student which meet conditions
     */
    public ArrayList<Student> getStudentsBySearch(String search) {
        return studentList.getStudentsBySearch(search);
    }

    /**
     * Loads all data from persistence into list classes.
     */
    public void loadAll() {
        classroomList.loadClassrooms(db.loadClassrooms());
        studentList.loadStudents(db.loadStudents());
        examinerList.loadExaminers(db.loadExaminers());
        courseList.loadCourses(db.loadCourses());
        examList.loadExams(db.loadExams());
    }

    /**
     * Returns a list of all students.
     * @return all students
     */
    public ArrayList<Student> getStudentAll() {
        return studentList.getStudents();
    }

    /**
     * Returns a list of all exams.
     * @return all exams
     */
    public ArrayList<Exam> getExamAll() {
        return examList.getExams();
    }

    /**
     * Returns a list of all classrooms.
     * @return all classrooms
     */
    public ArrayList<Classroom> getClassRoomsAll() {
        return classroomList.getClassrooms();
    }

    /**
     * Returns a list of all examiners.
     * @return all examiners
     */
    public ArrayList<Examiner> getExaminersALL() {
        return examinerList.getExaminers();
    }

    /**
     * Returns a list of all courses.
     * @return all courses
     */
    public ArrayList<Course> getCoursesAll() {
        return courseList.getCourses();
    }

    /**
     * Returns all students which are assigned to a specific course.
     * @param courseId ID of course from which to return students
     * @return all students assigned to course
     */
    public ArrayList<Student> getStudentsByCourse(String courseId) {
        return courseList.getStudentsByCourse(courseId);
    }

    /**
     * Returns all dates on which a specific examiner is not available on.
     * @param examinerID ID of examiner
     * @return list of dates
     */
    public ArrayList<Date> getExaminerUnavailabilityDates(String examinerID) {
        return examinerList.getExaminerByID(examinerID).unavailableDatesProperty();
    }

    /**
     * Adds examiner to ExaminerList and to Persistence.
     * @param examiner examiner to be added
     */
    public void addExaminer(Examiner examiner) {
        if (examinerList.addExaminer(examiner)) {
            db.save(examiner);
            for (Date date : examiner.unavailableDatesProperty())
                db.insertUnavailabilityToExaminer(examiner, date);
        }
    }

    /**
     * Adds exam to ExamList and to Persistence.
     * @param exam exam to be added
     */
    public void addExam(Exam exam) {
        if (examList.addExam(exam)) {
            db.insertExam(exam, getCourseById(exam.courseIdProperty().get()), getClassroomById(exam.classroomIdProperty().get()), exam.getDate());
        }
    }

    /**
     * Returns an examiner by its ID.
     * @param examinerId ID of examiner
     * @return examiner with that ID
     */
    public Examiner getExaminerById(String examinerId) {
        return examinerList.getExaminerByID(examinerId);
    }

    /**
     * Returns a classroom by its ID.
     * @param classroomId ID of classroom
     * @return classroom with that ID
     */
    public Classroom getClassroomById(String classroomId) {
        return classroomList.getClassroomById(classroomId);
    }

    /**
     * Return a course by its ID.
     * @param courseId ID of course
     * @return course with that ID
     */
    public Course getCourseById(String courseId) {
        return courseList.getCourseById(courseId);
    }

    /**
     * Deletes an examiner from ExaminerList and Persistence.
     * @param examiner examiner to be deleted
     */
    public void deleteExaminer(Examiner examiner) {
        if (examinerList.removeExaminer(examiner))
            db.removeExaminer(examiner);
    }

    /**
     * Adds classroom to ClassroomList and to Persistence.
     * @param classroom classroom to be added
     */
    public void addClassroom(Classroom classroom) {
        if (classroomList.addClassroom(classroom))
            db.save(classroom);
    }

    /**
     * Deletes a classroom from ClassroomList and Persistence.
     * @param classroom examiner to be deleted
     */
    public void deleteClassroom(Classroom classroom) {
        if (classroomList.removeClassroom(classroom))
            db.removeClassroom(classroom);
    }

    /**
     * Adds student to StudentList and to Persistence.
     * @param student classroom to be added
     */
    public void addStudent(Student student) {
        if (studentList.addStudent(student))
            db.save(student);
    }

    /**
     * Deletes a student from StudentList and Persistence.
     * @param student student to be deleted
     */
    public void deleteStudent(Student student) {
        if (studentList.removeStudent(student)) {
            db.removeStudent(student);
            courseList.removeStudent(student);
        }
    }

    /**
     * Adds course to CourseList and to Persistence.
     * @param course course to be added
     */
    public void addCourse(Course course) {
        if (!course.courseIdProperty().get().isEmpty())
            if (courseList.addCourse(course))
                db.save(course);
    }

    /**
     * Deletes a course from CourseList and Persistence.
     * @param course course to be deleted
     */
    public void deleteCourse(Course course) {
        if (courseList.removeCourse(course))
            db.removeCourse(course);
    }

    /**
     * Return a student by its ID.
     * @param studentID ID of student as String
     * @return student with that ID
     */
    public Student getStudentById(String studentID) {
        return getStudentById(Integer.parseInt(studentID));
    }

    /**
     * Return a student by its ID.
     * @param studentId ID of student
     * @return student with that ID
     */
    public Student getStudentById(int studentId) {
        return studentList.getStudentByID(studentId);
    }

    /**
     * Updates the data of the examiner.
     * @param examiner updated examiner
     * @param deletedDates list of all deleted unavailability dates
     * @param addedDates list of all added unavailability dates
     */
    public void editExaminer(Examiner examiner, ArrayList<Date> deletedDates, ArrayList<Date> addedDates) {
        if (examinerList.editExaminer(examiner)) {
            db.editExaminer(examiner);
            for (Date date : addedDates)
                db.insertUnavailabilityToExaminer(examiner, date);
            for (Date date : deletedDates)
                db.removeUnavailabilityFromExaminer(examiner, date);
        }
    }

    /**
     * Updates the data of the exam.
     * @param exam updated exam
     */
    public void editExam(Exam exam) {
        examList.editExam(exam);
        db.editExam(exam);
    }

    /**
     * Deletes an exam from ExamList and Persistence.
     * @param exam exam to be deleted
     */
    public void removeExam(Exam exam) {
        if (examList.removeExam(exam))
            db.removeExam(exam);
    }

    /**
     * Returns a list of all exams that have been scheduled on a specific date.
     * @param date date
     * @return list of exams scheduled on date
     */
    public ArrayList<Exam> getExamsByDate(Date date) {
        return examList.getExamsByDate(date);
    }

    /**
     * Updates the data of a student.
     * @param student updated student
     */
    public void editStudent(Student student) {
        if (studentList.editStudent(student))
            db.editStudent(student);
    }

    /**
     * Adds a student to a specific course.
     * @param course target course
     * @param student student to be added to target course
     */
    private void addStudentToCourse(Course course, Student student) {
        Student localStudent = getStudentById(String.valueOf(student.studentIdProperty().get()));
        if (courseList.insertStudentToCourse(course, localStudent)) {
            db.insertStudentToCourse(course, localStudent);
        }
    }

    /**
     * Removes a student from a specific course.
     * @param course target course
     * @param student student to be removed from target course
     */
    private void removeStudentFromCourse(Course course, Student student) {
        if (courseList.removeStudentFromCourse(course, getStudentById(String.valueOf(student.studentIdProperty().get())))) {
            db.removeStudentFromCourse(course, getStudentById(String.valueOf(student.studentIdProperty().get())));
        }
    }

    /**
     * Updates the data of a classroom.
     * @param classroom updated classroom
     */
    public void editClassroom(Classroom classroom) {
        if (classroomList.editClassroom(classroom))
            db.editClassroom(classroom);
    }

    /**
     * Updates the data of a course.
     * @param course updated course
     */
    public void editCourse(Course course) {
        if (!course.courseIdProperty().get().isEmpty())
            if (courseList.editCourse(course))
                db.editCourse(course);
    }

    /**
     * Adds multiple students to a specific course.
     * @param course target course
     * @param addedStudents students to be added to target course
     */
    public void addStudentsToCourse(Course course, ArrayList<Student> addedStudents) {
        for (Student student : addedStudents) {
            addStudentToCourse(course, student);
        }
    }

    /**
     * Removes multiple students from a specific course.
     * @param course target course
     * @param deletedStudents students to be removed from target course
     */
    public void removeStudentsFromCourse(Course course, ArrayList<Student> deletedStudents) {
        for (Student student : deletedStudents) {
            removeStudentFromCourse(course, student);
        }
    }
}
