package planner;

import java.util.ArrayList;

import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.beans.Student;
import model.dao.StudentDao;
import model.DataModel;

public class PrimaryController {

    private DataModel model;
    private ArrayList<Student> studentsTest = DataModel.getStudentAll();
    @FXML
    private TableView studentTable;

    public void setModel(DataModel model) {
        this.model = model;
    }

    public void MethodTesting(ActionEvent actionEvent) {
        StudentDao dao = new StudentDao();
        ArrayList<Student> students = DataModel.getStudentAll();

        for (Student member : students) {
            System.out.println(member);
        }
    }

    final ObservableList<Student> dataStudents = FXCollections.observableArrayList(studentsTest);

    public void addClassroom() {
        System.out.println("Classroom added");
    }

    public void addStudent() {

    }
}
