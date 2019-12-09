package model.lists;

import model.classes.Course;
import model.classes.Student;

import java.util.ArrayList;

public class CourseList {
    private ArrayList<Course> courses;

    public CourseList() {
        courses = new ArrayList<Course>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void loadCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public boolean addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    public Course getCourseById(String courseId) {
        for (Course course : courses)
            if (courseId.equalsIgnoreCase(course.courseIdProperty().get()))
                return course;
        return null;
    }

    public boolean insertStudentToCourse(Course course, Student student) {
        if(!getCourseById(course.courseIdProperty().get()).studentsProperty().contains(student)) {
            getCourseById(course.courseIdProperty().get()).addStudent(student); //TODO revert changes if edit cancelled
            return true;
        }
        return false;
    }

    public boolean removeStudentFromCourse(Course course, Student student) {
        if(getCourseById(course.courseIdProperty().get()).studentsProperty().contains(student)) {
            getCourseById(course.courseIdProperty().get()).removeStudent(student);
            return true;
        }
        return false;
    }

    public boolean removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            return true;
        }
        return false;
    }

    //TODO edit course
    public boolean editCourse(Course course){
        if(courses.contains(getCourseById(course.courseIdProperty().get()))){
            getCourseById(course.courseIdProperty().get()).setCourseType(course.courseTypeProperty().get());
            return true;
        }
        return false;
    }
}
