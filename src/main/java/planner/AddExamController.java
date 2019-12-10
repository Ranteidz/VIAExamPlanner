package planner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.SearchThread;
import model.classes.*;

import java.time.LocalDate;

public class AddExamController extends Controller {

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
        examStudentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentInfo"));
        this.parentController = parentController;
        searchThread = new SearchThread(System.currentTimeMillis(), this, "null");
        searchThread.start();
    }

    public void showCourses() {
        infoTable.getItems().clear();
        infoLabel.setText("Courses");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("courseInfo"));
        infoTable.getItems().addAll(parentController.model.getCoursesAll());
    }

    public void showClassrooms() {
        infoTable.getItems().clear();
        infoLabel.setText("Classrooms");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("classroomInfo"));
        infoTable.getItems().addAll(parentController.model.getClassRoomsAll());
    }

    public void showExaminers() {
        infoTable.getItems().clear();
        infoLabel.setText("Examiners");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("examinerInfo"));
        infoTable.getItems().addAll(parentController.model.getExaminersALL());
    }

    public void selectTableItem() {
        switch (infoLabel.getText()) {
            case "Courses":
                Course course = (Course) infoTable.getSelectionModel().getSelectedItem();
                courseIdField.setText(course.courseIdProperty().get());
                fillStudents(courseIdField.getText());
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
            infoTable.getItems().clear();
            infoTable.getItems().addAll(parentController.model.getCoursesBySearch(courseIdField.getText()));
            if(infoTable.getItems().size() == 1) {
                try {
                    fillStudents(courseIdField.getText());
                }
                catch (NullPointerException e) {
                    System.out.println("No course found");
                }
            } else {
                examStudentsTable.getItems().clear();
            }
        }
    }

    private void fillStudents(String courseId) {
        examStudentsTable.getItems().clear();
        examStudentsTable.getItems().addAll(parentController.model.getStudentsByCourse(courseIdField.getText()));
    }

    public void getClassrooms() {
        if (classroomIdField.getText().isEmpty()) {
            showClassrooms();
        } else {
            infoTable.getItems().clear();
            infoTable.getItems().addAll(parentController.model.getClassroomsBySearch(classroomIdField.getText()));
        }
    }

    public void getExaminers() {
        if (examinerIdField.getText().isEmpty()) {
            showExaminers();
        } else {
            infoTable.getItems().clear();
            infoTable.getItems().addAll(parentController.model.getExaminersBySearch(examinerIdField.getText()));
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
