package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.SearchThread;
import model.classes.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddExamController extends ExamController {

    public PrimaryController parentController;
    //private Exam exam;
    private SearchThread searchThread;
    private Exam exam;

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
        searchThread = new SearchThread(System.currentTimeMillis(), this, "null");
        searchThread.start();
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
        ObservableList<Classroom> data = parentController.classroomTable.getItems();
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
                Classroom classroom = (Classroom) infoTable.getSelectionModel().getSelectedItem();
                classroomIdField.setText(classroom.nameProperty().get());
                break;
            case "Examiners":
                Examiner examiner = (Examiner) infoTable.getSelectionModel().getSelectedItem();
                examinerIdField.setText(examiner.examinerIdProperty().get());
                break;
        }
    }

    public void addExam() {
        LocalDate selectedDate = examDatePicker.getValue();
        Date date = new Date(selectedDate.getDayOfMonth(), selectedDate.getMonthValue(), selectedDate.getYear());
        if (isInternal.isSelected())
            exam = new Exam(date.copy(), courseIdField.getText(), classroomIdField.getText(), examinerIdField.getText(), "Internal");
        else
            exam = new Exam(date.copy(), courseIdField.getText(), classroomIdField.getText(), examinerIdField.getText(), "External", coexaminerNameField.getText());
        parentController.examTable.getItems().add(exam);
        closeWindow();
    }

    public void searchData() {
        searchThread.keyPressed();
        searchThread = new SearchThread(System.currentTimeMillis(), this, infoLabel.getText());
        searchThread.start();
    }

    public void getCourses() {
        if (courseIdField.getText().isEmpty()) {
            showCourses();
        } else {
            ObservableList<Course> courses = parentController.courseTable.getItems();
            ArrayList<Course> searchItems = new ArrayList<Course>();
            for (Course course : courses) {
                if (course.courseIdProperty().get().contains(courseIdField.getText()))
                    searchItems.add(course);
            }
            infoTable.getItems().clear();
            infoTable.getItems().addAll(searchItems);
        }
    }

    public void getClassrooms() {
        if (classroomIdField.getText().isEmpty()) {
            showClassrooms();
        } else {
            ObservableList<Classroom> classrooms = parentController.classroomTable.getItems();
            ArrayList<Classroom> searchItems = new ArrayList<Classroom>();
            for (Classroom classRoom : classrooms) {
                if (classRoom.nameProperty().get().contains(classroomIdField.getText()))
                    searchItems.add(classRoom);
            }
            infoTable.getItems().clear();
            infoTable.getItems().addAll(searchItems);
        }
    }

    public void getExaminers() {
        if (examinerIdField.getText().isEmpty()) {
            showExaminers();
        } else {
            ObservableList<Examiner> examiners = parentController.examinerTable.getItems();
            ArrayList<Examiner> searchItems = new ArrayList<Examiner>();
            for (Examiner examiner : examiners) {
                if (examiner.examinerIdProperty().get().contains(examinerIdField.getText()))
                    searchItems.add(examiner);
            }
            infoTable.getItems().clear();
            infoTable.getItems().addAll(searchItems);
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
