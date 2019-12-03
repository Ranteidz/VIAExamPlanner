package planner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.beans.Course;
import model.beans.Student;

public class AddExamController {

    public PrimaryController parentController;

    @FXML
    public DatePicker examDatePicker;
    @FXML
    public TextField courseIdField;
    @FXML
    public TextField classroomIdField;
    @FXML
    public TextField examinerIdField;
    @FXML
    public RadioButton isInternal;
    @FXML
    public RadioButton isExternal;
    @FXML
    public TextField coexaminerNameField;

    @FXML
    public TableView<Student> examStudentsTable;
    @FXML
    public TableColumn<Student, String> examStudentsColumn;

    @FXML
    public Label infoLabel;
    @FXML
    public TableView infoTable;
    @FXML
    public TableColumn infoColumn;

    public AddExamController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
    }

    public void showCourses() {
        infoTable.getItems().clear();
        infoLabel.setText("Courses");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseInfo"));
        infoTable.getItems().addAll(parentController.courseTable.getItems());
    }
}
