package model.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Course extends Object{
    private StringProperty courseId = new SimpleStringProperty();
    private StringProperty courseType = new SimpleStringProperty();
    private ArrayList<Student> students = new ArrayList<Student>();

    public Course() {
    }

    /**
     * Creates new Course object in memory
     * @param name name/ID of the course
     */
    public Course(String name) {
        this.courseId.set(name);
    }

    /**
     * Sets the course name/ID to new value
     * @param name new value for course name/ID
     */
    public void setCourseId(String name) {
        this.courseId.set(name);
    }

    /**
     * Sets the course exam type (Oral/Written)
     * @param type new exam type (Oral/Writen)
     */
    public void setCourseType(String type) {
        this.courseType.set(type);
    }

    /**
     * Returns the course ID
     * @return course ID as StringProperty
     */
    public StringProperty courseIdProperty() {
        return courseId;
    }

    /**
     * Returns course exam type
     * @return Oral/Written as StringProperty
     */
    public StringProperty courseTypeProperty() {
        return courseType;
    }

    /**
     * Returns all the students assigned to the course
     * @return ArrayList of students
     */
    public ArrayList<Student> studentsProperty() {
        return students;
    }

    /**
     * Returns the number of students assigned to the course
     * @return number of students as IntegerProperty
     */
    public IntegerProperty numberOfStudentsProperty() {
        return new SimpleIntegerProperty(students.size());
    }

    /**
     * Returns all the information regarding the course
     * @return course information as StringProperty
     */
    public StringProperty courseInfoProperty() {
        StringProperty info = new SimpleStringProperty();
        info.set(String.format("%s, %s", courseId.get(), courseType.get()));
        return info;
    }

    /**
     * Adds a student to the course
     * @param student student to be added to the course
     */
    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
    }

    /**
     * Removes a student from the course
     * @param student student to be deleted from the course
     */
    public void removeStudent(Student student) {
        if(students.contains(student))
            students.remove(student);
    }
}
