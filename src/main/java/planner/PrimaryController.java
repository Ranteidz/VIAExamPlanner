package planner;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.beans.ClassRoom;
import model.beans.Student;
import model.dao.StudentDao;
import model.DataModel;

public class PrimaryController {

    private DataModel model;
    private ArrayList<Student> studentsTest = DataModel.getStudentAll();
    @FXML
    private TextField inputClassroomName;
    @FXML
    private TextField inputClassroomCapacity;
    @FXML
    private TableView<ClassRoom> tableClassroom = new TableView<>();

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


    public void addClassroom() throws NullPointerException {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName(inputClassroomName.getText());
        classRoom.setCapacity(Integer.parseInt(inputClassroomCapacity.getText()));
        classRoom.setHasHDMI(true);
        classRoom.setHasHDMI(false);
        System.out.println("Classroom added");
        tableClassroom.getItems().add(classRoom);
        inputClassroomName.clear();
        inputClassroomCapacity.clear();
    }


}
