package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.classes.Course;
import model.classes.Student;

import java.util.ArrayList;

public class EditCourseController {

    private Course course = new Course();
    private ArrayList<Student> addedStudents;
    private ArrayList<Student> deletedStudents;
    private PrimaryController parentController;
    @FXML
    public Label courseIdInput;
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
        addedStudents = new ArrayList<Student>();
        deletedStudents = new ArrayList<Student>();
    }

    public void initialize(PrimaryController parentController) throws NullPointerException {
        this.parentController = parentController;
        studentsColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentId"));
        course = parentController.courseTable.getSelectionModel().getSelectedItem();
        courseIdInput.setText(course.courseIdProperty().get());
        String courseType = course.courseTypeProperty().get();
        if (courseType.equals("Oral")) {
            isOral.setSelected(true);
        } else {
            isWritten.setSelected(true);
        }
        studentsTable.getItems().addAll(parentController.model.getStudentsByCourse(course.courseIdProperty().get()));
    }

    public void saveCourse() {
        course.setCourseId(courseIdInput.getText());
        course.setCourseType(isOral.isSelected() ? "Oral" : "Written");
        parentController.model.editCourse(course);
        //TODO bug here, replicate by removing then adding same students
        parentController.model.removeStudentsFromCourse(course, deletedStudents);
        parentController.model.addStudentsToCourse(course, addedStudents);
        parentController.updateData();
        closeWindow();
    }

    public void addStudent() {
        //TODO check if student exists
        Student student = parentController.model.getStudentById(studentId.getText());
        if (student != null) {
            addedStudents.add(student);
            studentsTable.getItems().add(student);
            if (deletedStudents.contains(student))
                deletedStudents.remove(student);
        } else
            System.out.println("ERROR: Student does not exist!");
        studentId.clear();
    }

    public void deleteStudent() {
        ObservableList<Student> allStudents, selectedStudent;
        allStudents = studentsTable.getItems();
        selectedStudent = studentsTable.getSelectionModel().getSelectedItems();
        deletedStudents.add(selectedStudent.get(0));
        if (addedStudents.contains(selectedStudent.get(0)))
            addedStudents.remove(selectedStudent.get(0));
        allStudents.removeAll(selectedStudent);
    }


    public void closeWindow() {
        course = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
