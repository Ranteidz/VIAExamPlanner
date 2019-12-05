package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.beans.*;

import java.time.LocalDate;

public class EditExamController extends ExamController{

    public PrimaryController parentController;
    //private Exam exam;
    private Counter counter;
    private Exam exam;
    private Date initialDate;

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

    public EditExamController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        exam = parentController.examTable.getSelectionModel().getSelectedItem();
        examDatePicker.setValue(LocalDate.of(exam.getDate().getYear(), exam.getDate().getMonth(), exam.getDate().getDay()));
        initialDate = exam.getDate();
        courseIdField.setText(exam.courseIdProperty().get());
        classroomIdField.setText(exam.classroomIdProperty().get());
        examinerIdField.setText(exam.examinerIdProperty().get());
        if(exam.coexaminerTypeProperty().get().equalsIgnoreCase("internal"))
            isInternal.setSelected(true);
        else {
            isExternal.setSelected(true);
            coexaminerNameField.setText(exam.coexaminerNameProperty().get());
        }
        counter = new Counter(System.currentTimeMillis(), this, "null");
        counter.start();
    }

    public void getCourses() {
        System.out.println("search courses");
    }

    public void getClassrooms() {
        System.out.println("Search classrooms");
    }

    public void getExaminers() {
        System.out.println("Search examiners");
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
        if(date.equals(initialDate))
            parentController.cancelExam();
        else
            parentController.cancelExam("cancel");
        parentController.examTable.getItems().add(exam);
        closeWindow();
    }

    public void searchData() {
        counter.keyPressed();
        counter = new Counter(System.currentTimeMillis(), this, infoLabel.getText());
        counter.start();
    }

    public void getSelectedItem() {
    }

    public void closeWindow() {
        // exam = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
