package planner;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.classes.*;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public void dateSelected() {
        classroomIdField.clear();
        examinerIdField.clear();
    }

    public void showCourses() {
        classroomIdField.clear();
        infoTable.getItems().clear();
        infoLabel.setText("Courses");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("courseInfo"));
        getCourses();
    }

    public void showClassrooms() {
        infoTable.getItems().clear();
        infoLabel.setText("Classrooms");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("classroomInfo"));
        getClassrooms();
    }

    public void showExaminers() {
        infoTable.getItems().clear();
        infoLabel.setText("Examiners");
        infoColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("examinerInfo"));
        getExaminers();
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
        if (parentController.model.getClassroomsBySearch(classroomIdField.getText(), date).size() == 1 && parentController.model.getAvailableCourses(courseIdField.getText()).size() == 1 && parentController.model.getAvailableExaminers(examinerIdField.getText(), date).size() == 1) {
            if(parentController.model.getClassroomsBySearch(classroomIdField.getText(), date).get(0).capacityProperty().get() >= parentController.model.getCourseById(courseIdField.getText()).numberOfStudentsProperty().get()) {
                if (isInternal.isSelected())
                    exam = new Exam(date.copy(), courseIdField.getText(), classroomIdField.getText(), examinerIdField.getText(), "Internal");
                else
                    exam = new Exam(date.copy(), courseIdField.getText(), classroomIdField.getText(), examinerIdField.getText(), "External", coexaminerNameField.getText());
                parentController.model.addExam(exam);
                parentController.updateData();
                closeWindow();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning: Invalid input");
                alert.setHeaderText(null);
                alert.setContentText("Classroom is not big enough!");
                alert.showAndWait();
            }
        } else {
            System.out.println("Invalid input data!");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning: Invalid input");
            alert.setHeaderText(null);
            if (parentController.model.getClassroomsBySearch(classroomIdField.getText(), date).size() != 1)
                alert.setContentText("The classroom you entered is booked on this date or does not exist!");
            else if(parentController.model.getAvailableCourses(courseIdField.getText()).size() != 1)
                alert.setContentText("The course you entered is booked on or does not exist!");
            else if(parentController.model.getAvailableExaminers(examinerIdField.getText(), date).size() != 1)
                alert.setContentText("The examiner you entered is booked on this date, not available or does not exist!");
            else
                alert.setContentText("An error has occurred!");
            alert.showAndWait();
        }
    }

    public void searchData() {
        searchThread.keyPressed();
        searchThread = new SearchThread(System.currentTimeMillis(), this, infoLabel.getText());
        searchThread.start();
    }

    public void getCourses() {
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
        ArrayList<Classroom> classrooms = null;
        Course course = parentController.model.getCourseById(courseIdField.getText());
        try {
            classrooms = parentController.model.getClassroomsBySearch(classroomIdField.getText(), getDate());
        } catch (NullPointerException e) {
            classrooms = parentController.model.getClassroomsBySearch(classroomIdField.getText());
        }
        if(course != null) {
            for (Classroom classroom : classrooms)
                if (classroom.capacityProperty().get() > course.numberOfStudentsProperty().get())
                    infoTable.getItems().add(classroom);
        } else {
            infoTable.getItems().addAll(classrooms);
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
