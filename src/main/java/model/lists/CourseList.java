package model.lists;

import model.classes.Course;
import model.classes.Student;

import java.util.ArrayList;

/**
 * Holds all courses and method to add, edit, remove and get courses.
 */
public class CourseList {
    private ArrayList<Course> courses;

    /**
     * Creates a new CourseList object in memory.
     */
    public CourseList() {
        courses = new ArrayList<Course>();
    }

    /**
     * Returns an ArrayList of all courses
     * @return list of courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Loads the parameter courses into array. Overwrites existing ones.
     * @param courses ArrayList of courses
     */
    public void loadCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Adds course to object if it does not exist already
     * @param course course to be added
     * @return true if added successfully
     */
    public boolean addCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

    /**
     * Returns a course by its ID
     * @param courseId course ID
     * @return course with that ID
     */
    public Course getCourseById(String courseId) {
        for (Course course : courses)
            if (courseId.equalsIgnoreCase(course.courseIdProperty().get())) {
                return course;
            }
        return null;
    }

    /**
     * Gets all students assigned to a specific course
     * @param courseId ID of course
     * @return students assigned to that course
     */
    public ArrayList<Student> getStudentsByCourse(String courseId) {
        return getCourseById(courseId).studentsProperty();
    }

    /**
     * Inserts a student to a specific course
     * @param course target course
     * @param student student to be added
     * @return true if add successfully
     */
    public boolean insertStudentToCourse(Course course, Student student) {
        Course localCourse = getCourseById(course.courseIdProperty().get());
        if(!localCourse.studentsProperty().contains(student)) {
            localCourse.addStudent(student);
            return true;
        }
        return false;
    }

    /**
     * Removes a student from all courses
     * @param student student to be reomved
     */
    public void removeStudent(Student student) {
        for(Course course : courses){
            if(course.studentsProperty().contains(student)){
                course.studentsProperty().remove(student);
            }
        }
    }

    /**
     * Returns a list of courses which meet the search condition
     * @param search search information
     * @return list of courses that meet condition
     */
    public ArrayList<Course> getCoursesBySearch(String search) {
        if(!search.isEmpty()) {
            ArrayList<Course> searchItems = new ArrayList<Course>();
            for (Course course : courses)
                if (course.courseInfoProperty().get().toLowerCase().contains(search.toLowerCase()))
                    searchItems.add(course);
            return searchItems;
        }
        return courses;
    }

    /**
     * Removes a student from a specific course
     * @param course target course
     * @param student student to be removed
     * @return true if removed successfully
     */
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

    /**
     * Removes a course from object.
     * @param course course to be deleted
     * @return true if removed successfully
     */
    public boolean removeCourse(Course course) {
        if (courses.contains(course)) {
            courses.remove(course);
            return true;
        }
        return false;
    }

    /**
     * Edits information of course
     * @param course edited course
     * @return true if edit successfully
     */
    public boolean editCourse(Course course){
        if(courses.contains(getCourseById(course.courseIdProperty().get()))){
            getCourseById(course.courseIdProperty().get()).setCourseType(course.courseTypeProperty().get());
            return true;
        }
        return false;
    }
}
