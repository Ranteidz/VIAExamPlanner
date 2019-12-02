package model.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Course
{
  private StringProperty courseId = new SimpleStringProperty();
  private StringProperty courseType = new SimpleStringProperty();
  private ArrayList<Student> students = new ArrayList<Student>();

  public Course(){}

  public Course(String name) {
    this.courseId.set(name);
  }

  public void setCourseId(String name) {
    this.courseId.set(name);
  }

  public void setCourseType(String type) {
    this.courseType.set(type);
  }

  public StringProperty courseIdProperty(){
    return courseId;
  }

  public StringProperty courseTypeProperty() {
    return courseType;
  }

  public ArrayList<Student> studentsProperty() {
    return students;
  }

  public IntegerProperty numberOfStudentsProperty() {
      return new SimpleIntegerProperty(students.size());
  }

  public void addStudent(Student student){
    if(!students.contains(student)){
      students.add(student);
    }
  }
}
