package model;

import model.classes.*;

import java.util.ArrayList;

/**
 * Persistence interface, enables saving and loading of information inside DataModel
 */
public interface Persistence {
    /**
     * Saves all the data.
     */
    public void save();

    /**
     * Loads all the data.
     * @return object with all data
     */
    public Object load();

    /**
     * Loads all the courses.
     * @return list of courses
     */
    ArrayList<Course> loadCourses();

    /**
     * Loads all the examiners.
     * @return list of examiners
     */
    ArrayList<Examiner> loadExaminers();

    /**
     * Loads all the classrooms.
     * @return list of classrooms
     */
    ArrayList<Classroom> loadClassrooms();

    /**
     * Loads all the students.
     * @return list of students
     */
    ArrayList<Student> loadStudents();

    /**
     * Saves an examiner.
     * @param examiner examiner to be saved
     */
    void save(Examiner examiner);

    /**
     * Removes an examiner.
     * @param examiner examiner to be removed
     */
    void removeExaminer(Examiner examiner);

    /**
     * Saves a classroom.
     * @param classroom classroom to be saved
     */
    void save(Classroom classroom);

    /**
     * Removes a classroom.
     * @param classroom classroom to be removed
     */
    void removeClassroom(Classroom classroom);

    /**
     * Saves a student.
     * @param student student to be saved
     */
    void save(Student student);

    /**
     * Removes a student.
     * @param student student to be removed
     */
    void removeStudent(Student student);

    /**
     * Removes a course.
     * @param course course to be removed
     */
    void removeCourse(Course course);

    /**
     * Saves a course.
     * @param course course to be saved
     */
    void save(Course course);

    /**
     * Updates an examiner.
     * @param examiner updated student
     */
    void editExaminer(Examiner examiner);

    /**
     * Updates a student.
     * @param student updated student
     */
    void editStudent(Student student);

    /**
     * Inserts a student to a specific course.
     * @param course target course
     * @param localStudent student to be added to target course
     */
    void insertStudentToCourse(Course course, Student localStudent);

    /**
     * Removes a student from a specific course.
     * @param course target course
     * @param student student to be removed form target course
     */
    void removeStudentFromCourse(Course course, Student student);

    /**
     * Updates a classroom.
     * @param classroom updated classroom
     */
    void editClassroom(Classroom classroom);

    /**
     * Updates a course.
     * @param course updated course
     */
    void editCourse(Course course);

    /**
     * Adds a unavailability date to a specific examiner.
     * @param examiner target examiner
     * @param date unavailability date
     */
    void insertUnavailabilityToExaminer(Examiner examiner, Date date);

    /**
     * Removes a unavailability date from a specific examiner.
     * @param examiner target examiner
     * @param date unavailability date
     */
    void removeUnavailabilityFromExaminer(Examiner examiner, Date date);

    /**
     * Saves an exam.
     * @param exam exam to be saved
     * @param course assigned course
     * @param classroom assigned classroom
     * @param date exam date
     */
    void insertExam(Exam exam, Course course, Classroom classroom, Date date);

    /**
     * Loads all the exams.
     * @return lsit of exams
     */
    ArrayList<Exam> loadExams();

    /**
     * Updates an exam.
     * @param exam updated exam
     */
    void editExam(Exam exam);

    /**
     * Removes an exam.
     * @param exam exam to be removed
     */
    void removeExam(Exam exam);
}
