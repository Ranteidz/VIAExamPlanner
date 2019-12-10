package model.lists;

import model.DataModel;
import model.classes.Student;

import java.sql.*;
import java.util.ArrayList;

public class StudentList {
    private ArrayList<Student> students;

    public StudentList() {
        students = new ArrayList<Student>();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void loadStudents(ArrayList<Student> students) {
        this.students = students;
    }

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

    public Student getStudentByID(int studentID) throws NullPointerException {
        for (Student student : students) {
            if (student.studentIdProperty().get() == studentID)
                return student;
        }
        return null;
    }

    public boolean addStudent(Student student) {
        if(!students.contains(student)){
            students.add(student);
            return true;
        }
        return false;
    }

    public boolean removeStudent(Student student) {
        if(students.contains(student)){
            students.remove(student);
            return true;
        }
        return false;
    }

    public boolean editStudent(Student student) {
        if(students.contains(getStudentByID(student.studentIdProperty().get()))){
            getStudentByID(student.studentIdProperty().get()).setStudentFirstName(student.studentFirstNameProperty().get());
            getStudentByID(student.studentIdProperty().get()).setStudentLastName(student.studentLastNameProperty().get());
            return true;
        }
        return false;
    }
}
