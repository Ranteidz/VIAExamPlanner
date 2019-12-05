package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataModel;
import model.classes.Course;
import model.classes.Student;

public class AddCourseController {

    private Course course = new Course();
    private PrimaryController parentController;
    @FXML
    public TextField courseIdInput;
    @FXML
    public RadioButton isOral;
    @FXML
    public RadioButton isWritten;
    @FXML
    public TextField studentId;
    @FXML
    public TableView<Student> studentsTable;
    @FXML
    public TableColumn<Student, String> studentsColumn;
    @FXML
    public Button cancelButton;

    public AddCourseController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        studentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
    }

    public void addCourse() {
        course.setCourseId(courseIdInput.getText());
        course.setCourseType(isOral.isSelected() ? "Oral" : "Written");
        DataModel.addCourse(course);
        ObservableList<Student> students = studentsTable.getItems();
        for (Student member: students)
            DataModel.addStudentToCourse(course,member);
        parentController.courseTable.getItems().add(course);
        parentController.updateData();
        System.out.println("add");
        closeWindow();
    }

    public void addStudent() {
     /*   Student student = new Student(Integer.parseInt(studentId.getText()), "", ""); //TODO get student names from database -- SELECT name, surname FROM TABLE students WHERE studentID = Integer.parseInt(studentId.getText())
        course.addStudent(student);
        studentsTable.getItems().add(student);
        studentId.clear();*/
        //TODO under construction
        System.out.println("test");
        System.out.println(studentId.getText());
        System.out.println(DataModel.getStudent(studentId.getText()));

        Student newStudent=  DataModel.getStudent(studentId.getText());
//        DataModel.addStudentToCourse(course,newStudent);
        System.out.println(newStudent.studentFirstNameProperty());
        Student student = new Student(Integer.parseInt(studentId.getText()),newStudent.studentFirstNameProperty().get(),newStudent.studentLastNameProperty().get()); //TODO get student names from database -- SELECT name, surname FROM TABLE students WHERE studentID = Integer.parseInt(studentId.getText())

        course.addStudent(newStudent);
        studentsTable.getItems().add(newStudent);
        studentId.clear();
    }

    public void closeWindow() {
        course = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
