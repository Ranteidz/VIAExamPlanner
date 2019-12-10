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
            if (courseId.equalsIgnoreCase(course.courseIdProperty().get())) {
                return course;
            }
        return null;
    }

    public ArrayList<Student> getStudentsByCourse(String courseId) {
        return getCourseById(courseId).studentsProperty();
    }

    public boolean insertStudentToCourse(Course course, Student student) {
        Course localCourse = getCourseById(course.courseIdProperty().get());
        if(!localCourse.studentsProperty().contains(student)) {
            localCourse.addStudent(student);
            return true;
        }
        return false;
    }

    public void removeStudent(Student student) {
        for(Course course : courses){
            if(course.studentsProperty().contains(student)){
                course.studentsProperty().remove(student);
            }
        }
    }

    public boolean removeStudentFromCourse(Course course, Student student) {
        System.out.println(student);
        Course localCourse = getCourseById(course.courseIdProperty().get());
        System.out.println(localCourse.studentsProperty());
        System.out.println(localCourse.studentsProperty().contains(student));
        if(localCourse.studentsProperty().contains(student)) {
            localCourse.removeStudent(student);
            System.out.println(localCourse.studentsProperty());
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
