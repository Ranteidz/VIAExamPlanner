package planner;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.beans.ClassRoom;
import model.beans.Student;
import model.dao.StudentDao;
import model.DataModel;

public class PrimaryController {

    private DataModel model;
//    private ArrayList<Student> studentsTest = DataModel.getStudentAll();
    @FXML
    private TextField inputClassroomName;
    @FXML
    private TextField inputClassroomCapacity;
    @FXML
    private TableView<ClassRoom> tableClassroom = new TableView<>();
    @FXML
    private TextField studentIDinput;
    @FXML
    private TextField studentFirstNameInput;
    @FXML
    private TextField studentLastNameInput;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    public TableColumn<Student, Integer> studentIdColumn;
    @FXML
    public TableColumn<Student, String> studentFirstNameColumn;
    @FXML
    public TableColumn<Student, String> studentLastNameColumn;

    public void setModel(DataModel model) {
        this.model = model;
    }

    public void initialize(){
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        studentFirstNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentFirstName"));
        studentLastNameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("studentLastName"));
    }

    public void MethodTesting(ActionEvent actionEvent) {
        System.out.println("test");
        StudentDao dao = new StudentDao();
        ArrayList<Student> students = DataModel.getStudentAll();

        for (Student member : students) {
            System.out.println(member);
        }
    }



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

    public void addStudent() {
        Student student = new Student(Integer.parseInt(studentIDinput.getText()), studentFirstNameInput.getText(), studentFirstNameInput.getText());
        System.out.println("Student added");
        studentTable.getItems().add(student);
        studentIDinput.clear();
        studentFirstNameInput.clear();
        studentLastNameInput.clear();
    }
}
