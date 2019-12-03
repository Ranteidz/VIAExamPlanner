package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.beans.ClassRoom;
import model.beans.Course;
import model.beans.Examiner;
import model.beans.Student;

public class AddExamController {

    public PrimaryController parentController;
    //private Exam exam;
    private long keyTime;

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
    public TableView<Object> infoTable;
    @FXML
    public TableColumn<Object, String> infoColumn;
    @FXML
    public Button cancelButton;

    public AddExamController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
    }

    public void showCourses() {
        infoTable.getItems().clear();
        infoLabel.setText("Courses");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("courseInfo"));
        ObservableList<Course> data = parentController.courseTable.getItems();
        infoTable.getItems().addAll(data);
    }

    public void showClassrooms() {
        infoTable.getItems().clear();
        infoLabel.setText("Classrooms");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("classroomInfo"));
        ObservableList<ClassRoom> data = parentController.tableClassroom.getItems();
        infoTable.getItems().addAll(data);
    }

    public void showExaminers() {
        infoTable.getItems().clear();
        infoLabel.setText("Examiners");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("examinerInfo"));
        ObservableList<Examiner> data = parentController.examinerTable.getItems();
        infoTable.getItems().addAll(data);
    }

    public void selectTableItem() {
        switch (infoLabel.getText()) {
            case "Courses":
                Course course = (Course) infoTable.getSelectionModel().getSelectedItem();
                courseIdField.setText(course.courseIdProperty().get());
                //update students table
                break;
            case "Classrooms":
                ClassRoom classroom = (ClassRoom) infoTable.getSelectionModel().getSelectedItem();
                ;
                classroomIdField.setText(classroom.nameProperty().get());
                break;
            case "Examiners":
                Examiner examiner = (Examiner) infoTable.getSelectionModel().getSelectedItem();
                examinerIdField.setText(examiner.examinerIdProperty().get());
                break;
        }
    }

    public void addExam() {
    }

    public void searchData() {
            keyTime = System.currentTimeMillis();
            this.notify();
        synchronized (this) {
            System.out.println(keyTime);
            try {
                this.wait(200);
                if (System.currentTimeMillis() - keyTime > 600)
                    System.out.println("Search");
            } catch (InterruptedException e) {
                System.out.println("search override");
            }
        }
    }

    public void getSelectedItem() {
    }

    public void closeWindow() {
        // exam = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
