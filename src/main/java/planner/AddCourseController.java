package planner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.beans.Course;
import model.beans.Student;

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
    public TableView<Student> studentsTable;
    @FXML
    public TableColumn<Student, String> studentsColumn;
    @FXML
    public Button cancelButton;

    public AddCourseController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        studentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("date"));
    }

    public void addCourse() {
        //TODO add examiner to database
        course.setCourseId(courseIdInput.getText());
        course.setCourseType(isOral.isSelected() ? "Oral" : "Written");
        parentController.courseTable.getItems().add(course);
        parentController.updateData();
        closeWindow();
    }

    public void addStudent() {

    }


    public void closeWindow() {
        course = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
