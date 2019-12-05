package planner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.classes.Course;
import model.classes.Student;

public class EditCourseController {

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

    public EditCourseController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        studentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        course = parentController.courseTable.getSelectionModel().getSelectedItem();
        courseIdInput.setText(course.courseIdProperty().get());
        String courseType = course.courseTypeProperty().get();
        if(courseType.equals("Oral")) {
            isOral.setSelected(true);
        } else {
            isWritten.setSelected(true);
        }
        ObservableList<Student> students = FXCollections.<Student>observableArrayList(course.studentsProperty());
        studentsTable.getItems().addAll(students);
    }

    public void saveCourse() {
        //TODO add examiner to database
        course.setCourseId(courseIdInput.getText());
        course.setCourseType(isOral.isSelected() ? "Oral" : "Written");
        parentController.deleteCourse();
        parentController.courseTable.getItems().add(course);
        parentController.updateData();
        System.out.println("add");
        closeWindow();
    }

    public void addStudent() {
        Student student = new Student(Integer.parseInt(studentId.getText()), "", ""); //TODO get student names from database -- SELECT name, surname FROM TABLE students WHERE studentID = Integer.parseInt(studentId.getText())
        course.addStudent(student);
        studentsTable.getItems().add(student);
        studentId.clear();
    }

    public void deleteStudent() {
        ObservableList<Student> allStudents, selectedStudent;
        allStudents = studentsTable.getItems();
        selectedStudent = studentsTable.getSelectionModel().getSelectedItems();
        course.studentsProperty().remove(selectedStudent.get(0));
        allStudents.removeAll(selectedStudent);
    }


    public void closeWindow() {
        course = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
