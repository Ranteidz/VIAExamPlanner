package planner;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.beans.ClassRoom;
import model.beans.Student;
import model.dao.StudentDao;
import model.DataModel;

public class PrimaryController {
    @FXML
    public CheckBox inputClassroomVGA;
    @FXML
    public CheckBox inputClassroomHDMI;

    private DataModel model;
    //    private ArrayList<Student> studentsTest = DataModel.getStudentAll();
    @FXML
    private TextField inputClassroomName;
    @FXML
    private TextField inputClassroomCapacity;
    @FXML
    private TableView<ClassRoom> tableClassroom;
    @FXML
    public TableColumn<ClassRoom, String> name;
    @FXML
    public TableColumn<ClassRoom, Integer> capacity;
    @FXML
    public TableColumn<ClassRoom, Boolean> hdmi;
    @FXML
    public TableColumn<ClassRoom, Boolean> vga;
    @FXML
    private TextField studentIDinput;
    @FXML
    private TextField studentFirstNameInput;
    @FXML
    private TextField studentLastNameInput;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    public TableColumn<Student, Integer> studentId;
    @FXML
    public TableColumn<Student, String> studentFirstName;
    @FXML
    public TableColumn<Student, String> studentLastName;

    public void setModel(DataModel model) {
        this.model = model;
    }

    public void initialize() {


        studentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        studentFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentFirstName"));
        studentLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentLastName"));
        name.setCellValueFactory(new PropertyValueFactory<ClassRoom, String>("name"));
        capacity.setCellValueFactory(new PropertyValueFactory<ClassRoom, Integer>("capacity"));
        hdmi.setCellValueFactory(new PropertyValueFactory<ClassRoom, Boolean>("hdmi"));
        vga.setCellValueFactory(new PropertyValueFactory<ClassRoom, Boolean>("vga"));

    }


    public void MethodTesting(ActionEvent actionEvent) {
        System.out.println("test");
        StudentDao dao = new StudentDao();
        ArrayList<Student> students = DataModel.getStudentAll();

        for (Student member : students) {
            System.out.println(member);
        }
    }


    public void addClassroom() {
        ClassRoom classRoom = new ClassRoom(inputClassroomName.getText(), Integer.parseInt(inputClassroomCapacity.getText()), false, false);
        if (inputClassroomHDMI.isSelected())
            classRoom.setHdmi(true);
        if (inputClassroomVGA.isSelected())
            classRoom.setVga(true);
        System.out.println("Classroom added");
        tableClassroom.getItems().add(classRoom);
        inputClassroomName.clear();
        inputClassroomCapacity.clear();
        inputClassroomHDMI.setSelected(false);
        inputClassroomVGA.setSelected(false);
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
