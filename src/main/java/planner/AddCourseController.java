package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.classes.Course;
import model.classes.Student;

import java.util.ArrayList;

public class AddCourseController {

    private Course course = new Course();
    private PrimaryController parentController;
    private ArrayList<Student> addedStudents;
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
        addedStudents = new ArrayList<Student>();
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        studentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        isWritten.setSelected(true);
    }

    public void addCourse() {
        course.setCourseId(courseIdInput.getText());
        course.setCourseType(isOral.isSelected() ? "Oral" : "Written");
        if(parentController.model.getCourseById(course.courseIdProperty().get()) == null) {
            parentController.model.addCourse(course);
            parentController.model.addStudentsToCourse(course, addedStudents);
        } else
            System.out.println("ERROR: Course already exists!");
        parentController.updateData();
        closeWindow();
    }

    public void addStudent() {
        //TODO check if student exists
        Student student = parentController.model.getStudentById(studentId.getText());
        if(student != null) {
            addedStudents.add(student);
            studentsTable.getItems().add(student);
        } else
            System.out.println("ERROR: Student does not exist!");
        studentId.clear();
    }

    public void deleteStudent() {
        ObservableList<Student> allStudents, selectedStudent;
        allStudents = studentsTable.getItems();
        selectedStudent = studentsTable.getSelectionModel().getSelectedItems();
        addedStudents.remove(selectedStudent.get(0));
        allStudents.removeAll(selectedStudent);
    }

    public void closeWindow() {
        course = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
