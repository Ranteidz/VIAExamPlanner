package planner;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.beans.*;
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
    private Label classroomIdLabel;
    @FXML
    private Label capacityLabel;
    @FXML
    private Label hdmiLabel;
    @FXML
    private Label vgaLabel;

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
    public Label examinerIdLabel;
    @FXML
    public Label examinerFirstNameLabel;
    @FXML
    public Label examinerLastNameLabel;
    @FXML
    public TableView<Date> examinerDateTable;
    @FXML
    public TableColumn<Date, String> examinerDateColumn;

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
        examinerDateColumn.setCellValueFactory(new PropertyValueFactory<Date, String>("date"));

        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        System.out.println("updating data");
//        studentTable.getItems().clear();
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

    public void selectExaminerItem(){
        examinerIdLabel.setText("");
        examinerLastNameLabel.setText("");
        examinerFirstNameLabel.setText("");
        examinerDateTable.getItems().clear();
        Examiner examiner = examinerTable.getSelectionModel().getSelectedItem();
        examinerIdLabel.setText(examiner.examinerIdProperty().get());
        examinerFirstNameLabel.setText(examiner.examinerFirstNameProperty().get());
        examinerLastNameLabel.setText(examiner.examinerLastNameProperty().get());
        ObservableList<Date> dates = FXCollections.<Date>observableArrayList(examiner.getUnavailableDates());
        examinerDateTable.getItems().addAll(dates);
    }

    public void deleteExaminer() {
        ObservableList<Examiner> allExaminers, selectedExaminer;
        allExaminers = examinerTable.getItems();
        selectedExaminer = examinerTable.getSelectionModel().getSelectedItems();
        allExaminers.removeAll(selectedExaminer);
        examinerIdLabel.setText("");
        examinerLastNameLabel.setText("");
        examinerFirstNameLabel.setText("");
        examinerDateTable.getItems().clear();
    }

    public void selectCourseItem() {

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

    public void selectClassroomItem() {
        ClassRoom classRoom = tableClassroom.getSelectionModel().getSelectedItem();
        classroomIdLabel.setText(classRoom.nameProperty().get());
        capacityLabel.setText(Integer.toString(classRoom.capacityProperty().get()));
        hdmiLabel.setText(Boolean.toString(classRoom.hdmiProperty().get()));
        vgaLabel.setText(Boolean.toString(classRoom.vgaProperty().get()));
    }

    public void deleteClassroom() {
        ObservableList<ClassRoom> allClassrooms, selectedClassroom;
        allClassrooms = tableClassroom.getItems();
        selectedClassroom = tableClassroom.getSelectionModel().getSelectedItems();
        allClassrooms.removeAll(selectedClassroom);
        classroomIdLabel.setText("");
        capacityLabel.setText("");
        hdmiLabel.setText("");
        vgaLabel.setText("");
    }
}
