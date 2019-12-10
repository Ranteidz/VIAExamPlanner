package model.classes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student
{
  private IntegerProperty studentId = new SimpleIntegerProperty();
  private StringProperty studentFirstName = new SimpleStringProperty();
  private StringProperty studentLastName = new SimpleStringProperty();

  public Student()
  { }

  public Student(int studentId, String studentFirstName, String studentLastName) {
    this.studentId.set(studentId);
    this.studentFirstName.set(studentFirstName);
    this.studentLastName.set(studentLastName);
  }

  public IntegerProperty studentIdProperty() {
    return studentId;
  }

  public void setStudentID(int studentId) {
    this.studentId.setValue(studentId);
  }

  public StringProperty studentFirstNameProperty() {
    return studentFirstName;
  }

  public void setStudentFirstName(String studentFirstName) {
    this.studentFirstName.set(studentFirstName);
  }

  public StringProperty studentLastNameProperty() {
    return studentLastName;
  }

  public void setStudentLastName(String studentLastName) {
    this.studentLastName.set(studentLastName);
  }

  public StringProperty studentNameProperty() {
    StringProperty name = new SimpleStringProperty();
    name.set(String.format("%s %s", studentFirstName.get(), studentLastName.get()));
    return name;
  }

  public StringProperty studentInfoProperty () {
    StringProperty info = new SimpleStringProperty();
    info.set(String.format("%d: %s %s", studentId.get(), studentFirstName.get(), studentLastName.get()));
    return info;
  }

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
