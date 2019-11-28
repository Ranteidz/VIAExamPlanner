package model.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Examiner
{
  private IntegerProperty examinerId = new SimpleIntegerProperty();
  private StringProperty examinerFirstName = new SimpleStringProperty();
  private StringProperty examinerLastName = new SimpleStringProperty();

  public Examiner()
  { }

  public Examiner(int examinerId, String examinerFirstName, String examinerLastName) {
    this.examinerId.set(examinerId);
    this.examinerFirstName.set(examinerFirstName);
    this.examinerLastName.set(examinerLastName);
  }

  public IntegerProperty studentIdProperty() {
    return examinerId;
  }

  public void setStudentID(int studentId) {
    this.examinerId.setValue(studentId);
  }

  public StringProperty studentFirstNameProperty() {
    return examinerFirstName;
  }

  public void setStudentFirstName(String studentFirstName) {
    this.examinerFirstName.set(studentFirstName);
  }

  public StringProperty studentLastNameProperty() {
    return examinerLastName;
  }

  public void setStudentLastName(String studentLastName) {
    this.examinerLastName.set(studentLastName);
  }

  @Override public String toString()
  {
    return examinerId + " " + examinerFirstName +" "+ examinerLastName ;
  }
}
