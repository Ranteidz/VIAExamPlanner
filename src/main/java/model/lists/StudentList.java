package model.lists;

import model.DataModel;
import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;
/**
 * Holds all students and method to add, edit, remove and get students.
 */
public class StudentList {
    private ArrayList<Student> students;

    /**
     * Creates a new StudentList object.
     */
    public StudentList() {
        students = new ArrayList<Student>();
    }

    /**
     * Returns a list of all students.
     * @return list of students.
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Loads students into object, overwrites existing ones.
     * @param students list of students
     */
    public void loadStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Returns all students that meet search criteria
     * @param search search information
     * @return list of students that meet conditions
     */
    public ArrayList<Student> getStudentsBySearch(String search) {
        if (!search.isEmpty()) {
            ArrayList<Student> searchItems = new ArrayList<Student>();
            for (Student student : students) {
                if (student.studentInfoProperty().get().toLowerCase().contains(search.toLowerCase()))
                    searchItems.add(student);
            }
            return searchItems;
        }
        return students;
    }

    /**
     * Returns a student by its ID
     * @param studentID ID of student
     * @return student with ID
     */
    public Student getStudentByID(int studentID) {
        for (Student student : students) {
            if (student.studentIdProperty().get() == studentID)
                return student;
        }
        return null;
    }

    /**
     * Adds a student to object if it doesn't already exist.
     * @param student student to be added
     * @return true if added successfully
     */
    public boolean addStudent(Student student) {
        if(!students.contains(getStudentByID(student.studentIdProperty().get()))){
            students.add(student);
            return true;
        }
        return false;
    }

    /**
     * Removes student form object if it exists.
     * @param student student to be removed
     * @return true if removed successfully
     */
    public boolean removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            return true;
        }
        return false;
    }

    /**
     * Edits student
     * @param student edited student
     * @return true if edits successfully
     */
    public boolean editStudent(Student student) {
        if(students.contains(getStudentByID(student.studentIdProperty().get()))){
            getStudentByID(student.studentIdProperty().get()).setStudentFirstName(student.studentFirstNameProperty().get());
            getStudentByID(student.studentIdProperty().get()).setStudentLastName(student.studentLastNameProperty().get());
            return true;
        }
        return false;
    }
}
