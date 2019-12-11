package model;

import model.classes.*;

import java.util.ArrayList;

public interface Persistence {
    public void save();

    public Object load();

    ArrayList<Course> loadCourses();

    ArrayList<Examiner> loadExaminers();

    ArrayList<Classroom> loadClassrooms();

    ArrayList<Student> loadStudents();

    void save(Examiner examiner);

    void removeExaminer(Examiner examiner);

    void save(Classroom classroom);

    void removeClassroom(Classroom classroom);

    void save(Student student);

    void removeStudent(Student student);

    void removeCourse(Course course);

    void save(Course course);

    void editExaminer(Examiner examiner);

    void editStudent(Student student);

    void insertStudentToCourse(Course course, Student localStudent);

    void removeStudentFromCourse(Course course, Student student);

    void editClassroom(Classroom classroom);

    void editCourse(Course course);

    void insertUnavailabilityToExaminer(Examiner examiner, Date date);

    void removeUnavailabilityFromExaminer(Examiner examiner, Date date);

    ArrayList<Classroom> getAvailableClassrooms(Date date);

    void insertExam(Exam exam, Course course, Classroom classroom, Date date);

    ArrayList<Exam> loadExams();

    void insertExaminerToExamExaminers(Exam exam, Examiner examiner);

    void editExam(Exam exam);

    void removeExaminerFomExamsExaminers(Exam exam, Examiner examiner);
}
