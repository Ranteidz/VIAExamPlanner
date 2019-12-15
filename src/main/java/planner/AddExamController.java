package planner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.classes.*;

import java.time.LocalDate;

public class AddExamController extends Controller {

    public PrimaryController parentController;
    private SearchThread searchThread;

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
        examStudentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentInfo"));
        this.parentController = parentController;
        searchThread = new SearchThread(System.currentTimeMillis(), this, "null");
        searchThread.start();
        examDatePicker.setValue(LocalDate.now());
    }

    private Date getDate() {
        LocalDate localDate = examDatePicker.getValue();
        return new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    public void showCourses() {
        infoTable.getItems().clear();
        infoLabel.setText("Courses");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("courseInfo"));
        infoTable.getItems().addAll(parentController.model.getAvailableCourses(""));
    }

    public void showClassrooms() {
        infoTable.getItems().clear();
        infoLabel.setText("Classrooms");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("classroomInfo"));
        try {
            infoTable.getItems().addAll(parentController.model.getClassroomsBySearch("", getDate()));
        } catch (NullPointerException e) {
            infoTable.getItems().addAll(parentController.model.getClassRoomsAll());
        }
    }

    public void showExaminers() {
        infoTable.getItems().clear();
        infoLabel.setText("Examiners");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("examinerInfo"));
        infoTable.getItems().addAll(parentController.model.getAvailableExaminers("", getDate()));
    }

    public void selectTableItem() {
        switch (infoLabel.getText()) {
            case "Courses":
                Course course = (Course) infoTable.getSelectionModel().getSelectedItem();
                courseIdField.setText(course.courseIdProperty().get());
                fillStudents();
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
        Exam exam;
        if (isInternal.isSelected())
            exam = new Exam(date.copy(), courseIdField.getText(), classroomIdField.getText(), examinerIdField.getText(), "Internal");
        else
            exam = new Exam(date.copy(), courseIdField.getText(), classroomIdField.getText(), examinerIdField.getText(), "External", coexaminerNameField.getText());
        parentController.model.addExam(exam);
        parentController.updateData();
        closeWindow();
    }

    public void searchData() {
        searchThread.keyPressed();
        searchThread = new SearchThread(System.currentTimeMillis(), this, infoLabel.getText());
        searchThread.start();
    }

    public void getCourses() {
        classroomIdField.clear();
        infoTable.getItems().clear();
        infoTable.getItems().addAll(parentController.model.getAvailableCourses(courseIdField.getText()));
        if (infoTable.getItems().size() == 1) {
            try {
                fillStudents();
            } catch (NullPointerException e) {
                System.out.println("No course found");
            }
        } else {
            examStudentsTable.getItems().clear();
        }
    }

    private void fillStudents() {
        examStudentsTable.getItems().clear();
        examStudentsTable.getItems().addAll(parentController.model.getStudentsByCourse(courseIdField.getText()));
    }

    public void getClassrooms() {
        infoTable.getItems().clear();
        try {
            infoTable.getItems().addAll(parentController.model.getClassroomsBySearch(classroomIdField.getText(), getDate()));
        } catch (NullPointerException e) {
            infoTable.getItems().addAll(parentController.model.getClassroomsBySearch(classroomIdField.getText()));
        }
    }

    public void getExaminers() {
        infoTable.getItems().clear();
        infoTable.getItems().addAll(parentController.model.getAvailableExaminers(examinerIdField.getText(), getDate()));
    }

    public void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
