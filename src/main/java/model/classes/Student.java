package model.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a student.
 * A student can be assigned to a course.
 */
public class Student
{
  private IntegerProperty studentId = new SimpleIntegerProperty();
  private StringProperty studentFirstName = new SimpleStringProperty();
  private StringProperty studentLastName = new SimpleStringProperty();

  /**
   * Creates new empty Student object in memory.
   */
  public Student()
  { }

  /**
   * Creates new Student object in memory.
   * @param studentId ID of student (positive int)
   * @param studentFirstName first name of student
   * @param studentLastName last name of student
   */
  public Student(int studentId, String studentFirstName, String studentLastName) {
    this.studentId.set(studentId);
    this.studentFirstName.set(studentFirstName);
    this.studentLastName.set(studentLastName);
  }

  /**
   * Returns the id of student as IntegerProperty
   * @return id of student
   */
  public IntegerProperty studentIdProperty() {
    return studentId;
  }

  /**
   * Sets the id of student
   * @param studentId id of student
   */
  public void setStudentID(int studentId) {
    this.studentId.setValue(studentId);
  }

  /**
   * Returns the first name of the student as StringProperty
   * @return first name of student
   */
  public StringProperty studentFirstNameProperty() {
    return studentFirstName;
  }

  /**
   * Sets the first name of the student.
   * @param studentFirstName first name of student
   */
  public void setStudentFirstName(String studentFirstName) {
    this.studentFirstName.set(studentFirstName);
  }

  /**
   * Returns the last name of student as StringProperty.
   * @return last name of student
   */
  public StringProperty studentLastNameProperty() {
    return studentLastName;
  }

  /**
   * Sets the last name of student.
   * @param studentLastName last name of student
   */
  public void setStudentLastName(String studentLastName) {
    this.studentLastName.set(studentLastName);
  }

  /**
   * Returns the full name of student as StringProperty.
   * @return full name of student
   */
  public StringProperty studentNameProperty() {
    StringProperty name = new SimpleStringProperty();
    name.set(String.format("%s %s", studentFirstName.get(), studentLastName.get()));
    return name;
  }

  /**
   * Return the full details of student
   * @return ID and full name of students
   */
  public StringProperty studentInfoProperty () {
    StringProperty info = new SimpleStringProperty();
    info.set(String.format("%d: %s %s", studentId.get(), studentFirstName.get(), studentLastName.get()));
    return info;
  }

  /**
   * Compares student to parameter object
   * @param obj object to be compared to
   * @return true if objects are the same
   */
  public boolean equals(Object obj) {
    if (!(obj instanceof Student))
      return false;
    Student otherStudent = (Student) obj;
    return this.studentInfoProperty().get().equals(otherStudent.studentInfoProperty().get());
  }

  @Override public String toString()
  {
    return studentId + " " + studentFirstName +" "+ studentLastName ;
  }
}
