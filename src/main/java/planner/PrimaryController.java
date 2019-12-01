package planner;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.beans.ClassRoom;
import model.beans.Course;
import model.beans.Examiner;
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

    @FXML
    public TableView<Examiner> examinerTable;
    @FXML
    public TableColumn<Examiner, String> examinerId;
    @FXML
    public TableColumn<Examiner, String> examinerName;

    @FXML
    public TableView<Course> courseTable;
    @FXML
    public TableColumn<Course, String> courseIdColumn;
    @FXML
    public TableColumn<Course, String> courseTypeColumn;
    @FXML
    public TableColumn<Course, Integer> courseNumberOfStudentsColumn;

    public PrimaryController() {
    }

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
        examinerId.setCellValueFactory(new PropertyValueFactory<Examiner, String>("examinerId"));
        examinerName.setCellValueFactory(new PropertyValueFactory<Examiner, String>("examinerName"));
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseId"));
        courseTypeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseType"));
        courseNumberOfStudentsColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numberOfStudents"));

        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        System.out.println("updating data");
//        studentTable.setItems(null);
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData() throws Exception {
//        StudentDao dao = new StudentDao();
//        ArrayList<Student> students = DataModel.getStudentAll();
//        DataModel.post();
//        for (Student member : students) {
//            System.out.println(member);
//            studentTable.getItems().add(member);
//        }
    }

    public void MethodTesting(ActionEvent actionEvent) throws Exception {
        System.out.println("test");
        StudentDao dao = new StudentDao();
        ArrayList<Student> students = DataModel.getStudentAll();
        DataModel.post();
        for (Student member : students) {
            System.out.println(member);
            studentTable.getItems().add(member);
        }
    }

    public void addClassroom() {
        ClassRoom classRoom = new ClassRoom(inputClassroomName.getText(),
                Integer.parseInt(inputClassroomCapacity.getText()), false, false);
        if (inputClassroomHDMI.isSelected())
            classRoom.setHdmi(true);
        if (inputClassroomVGA.isSelected())
            classRoom.setVga(true);
        System.out.println("Classroom added");
        //TODO add classroom to database
        tableClassroom.getItems().add(classRoom);
        inputClassroomName.clear();
        inputClassroomCapacity.clear();
        inputClassroomHDMI.setSelected(false);
        inputClassroomVGA.setSelected(false);
    }

    public void addStudent() {
        Student student = new Student(Integer.parseInt(studentIDinput.getText()),
                studentFirstNameInput.getText(), studentFirstNameInput.getText());
        System.out.println("Student added");
        //TODO add student to database
        studentTable.getItems().add(student);
        studentIDinput.clear();
        studentFirstNameInput.clear();
        studentLastNameInput.clear();
    }

    public void openAddExaminerWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addexaminer.fxml"));
        Parent root = (Parent) loader.load();
        AddExaminerController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Examiner");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openAddCourseWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addcourse.fxml"));
        Parent root = (Parent) loader.load();
        AddCourseController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Examiner");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
