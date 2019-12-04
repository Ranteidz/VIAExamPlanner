package planner;

import java.sql.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.beans.*;
import model.beans.Date;
import model.dao.StudentDao;
import model.DataModel;

public class PrimaryController
{
  @FXML public CheckBox inputClassroomVGA;
  @FXML public CheckBox inputClassroomHDMI;

  private DataModel model;
  //    private ArrayList<Student> studentsTest = DataModel.getStudentAll();
  @FXML private TextField inputClassroomName;
  @FXML private TextField inputClassroomCapacity;
  @FXML public TableView<ClassRoom> tableClassroom;
  @FXML public TableColumn<ClassRoom, String> name;
  @FXML public TableColumn<ClassRoom, Integer> capacity;
  @FXML public TableColumn<ClassRoom, Boolean> hdmi;
  @FXML public TableColumn<ClassRoom, Boolean> vga;
  @FXML private TextField classroomIdTextField;
  @FXML private TextField capacityTextField;
  @FXML private TextField hdmiTextField;
  @FXML private TextField vgaTextField;
  @FXML private Button editSaveClassroom;

  @FXML private TextField studentIDinput;
  @FXML private TextField studentFirstNameInput;
  @FXML private TextField studentLastNameInput;
  @FXML private TableView<Student> studentTable;
  @FXML public TableColumn<Student, Integer> studentId;
  @FXML public TableColumn<Student, String> studentFirstName;
  @FXML public TableColumn<Student, String> studentLastName;

  @FXML public TableView<Examiner> examinerTable;
  @FXML public TableColumn<Examiner, String> examinerId;
  @FXML public TableColumn<Examiner, String> examinerName;
  @FXML public Label studentIdLable;
  @FXML Label studentFirstNameLabel;
  @FXML Label studentLastNameLabel;
  @FXML public Label examinerIdLabel;
  @FXML public Label examinerFirstNameLabel;
  @FXML public Label examinerLastNameLabel;
  @FXML public TableView<Date> examinerDateTable;
  @FXML public TableColumn<Date, String> examinerDateColumn;

  @FXML public TableView<Course> courseTable;
  @FXML public TableColumn<Course, String> courseIdColumn;
  @FXML public TableColumn<Course, String> courseTypeColumn;
  @FXML public TableColumn<Course, Integer> courseNumberOfStudentsColumn;
  @FXML public Label courseIdLabel;
  @FXML public Label courseTypeLabel;
  @FXML public TableView<Student> courseStudentTable;
  @FXML public TableColumn<Student, Integer> courseStudentId;
  @FXML public TableColumn<Student, String> courseStudentName;

  public PrimaryController()
  {
  }

  public void setModel(DataModel model)
  {
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
        courseStudentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        courseStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));

        try {
            loadAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        System.out.println("updating data");
        studentTable.getItems().clear();
        examinerTable.getItems().clear();
        try {
            loadAllData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  private void loadAllData() throws Exception
  {
    //TODO Load the rest of data
    ArrayList<Student> students = DataModel.getStudentAll();
    ArrayList<Examiner> examiners = DataModel.getExaminersALL();
    ArrayList<Course> courses = DataModel.getCoursesAll();
    ArrayList<ClassRoom> classRooms = DataModel.getClassRoomsAll();
    studentTable.getItems().addAll(students);
    examinerTable.getItems().addAll(examiners);
    courseTable.getItems().addAll(courses);
    tableClassroom.getItems().addAll(classRooms);
  }

  public void MethodTesting(ActionEvent actionEvent) throws Exception
  {
    System.out.println("test");
    StudentDao dao = new StudentDao();
    ArrayList<Student> students = DataModel.getStudentAll();
    DataModel.postStudent();
    for (Student member : students)
    {
      System.out.println(member);
      studentTable.getItems().add(member);
    }

  }

    public void openAddExamWindow() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addexam.fxml"));
        Parent root = (Parent) loader.load();
        AddExamController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Course");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void addClassroom() {
        ClassRoom classRoom = new ClassRoom(inputClassroomName.getText(),
                Integer.parseInt(inputClassroomCapacity.getText()), false, false);
        if (inputClassroomHDMI.isSelected())
            classRoom.setHdmi(true);
        if (inputClassroomVGA.isSelected())
            classRoom.setVga(true);
        System.out.println("Classroom added");
        DataModel.addClassRoom(classRoom);
        tableClassroom.getItems().add(classRoom);
        inputClassroomName.clear();
        inputClassroomCapacity.clear();
        inputClassroomHDMI.setSelected(false);
        inputClassroomVGA.setSelected(false);
    }

  public void selectClassroomItem()
  {
    ClassRoom classRoom = tableClassroom.getSelectionModel().getSelectedItem();
    classroomIdTextField.setText(classRoom.nameProperty().get());
    capacityTextField
        .setText(Integer.toString(classRoom.capacityProperty().get()));
    hdmiTextField.setText(Boolean.toString(classRoom.hdmiProperty().get()));
    vgaTextField.setText(Boolean.toString(classRoom.vgaProperty().get()));
  }

  public void deleteClassroom()
  {
    ObservableList<ClassRoom> allClassrooms, selectedClassroom;
    ClassRoom classRoom=tableClassroom.getSelectionModel().getSelectedItem();
    allClassrooms = tableClassroom.getItems();
    selectedClassroom = tableClassroom.getSelectionModel().getSelectedItems();
    DataModel.deleteClassRoom(classRoom);
    allClassrooms.removeAll(selectedClassroom);
    classroomIdTextField.setText("");
    capacityTextField.setText("");
    hdmiTextField.setText("");
    vgaTextField.setText("");
  }

  public void addStudent()
  {
    Student student = new Student(Integer.parseInt(studentIDinput.getText()),
        studentFirstNameInput.getText(), studentLastNameInput.getText());
    DataModel.addStudent(student);
    studentTable.getItems().add(student);
    studentIDinput.clear();
    studentFirstNameInput.clear();
    studentLastNameInput.clear();
  }

  public void selectStudentItem()
  {
    studentIdLable.setText("");
    studentFirstNameLabel.setText("");
    studentLastNameLabel.setText("");
    Student student = studentTable.getSelectionModel().getSelectedItem();
    studentIdLable.setText(Integer.toString(student.studentIdProperty().get()));
    studentFirstNameLabel.setText(student.studentFirstNameProperty().get());
    studentLastNameLabel.setText(student.studentLastNameProperty().get());
    }

    public void deleteStudent() {
        ObservableList<Student> allStudents, selectedStudent;
        Student student= studentTable.getSelectionModel().getSelectedItem();
        allStudents = studentTable.getItems();
        selectedStudent = studentTable.getSelectionModel().getSelectedItems();
        DataModel.deleteStudent(student);
        allStudents.removeAll(selectedStudent);
        examinerIdLabel.setText("");
        examinerLastNameLabel.setText("");
        examinerFirstNameLabel.setText("");
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

    public void openEditExaminerWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editexaminer.fxml"));
        Parent root = (Parent) loader.load();
        EditExaminerController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Edit Examiner");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void selectExaminerItem() {
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

  public void deleteExaminer()
  {

    ObservableList<Examiner> allExaminers, selectedExaminer;
    Examiner examiner = examinerTable.getSelectionModel().getSelectedItem();
    allExaminers = examinerTable.getItems();
    selectedExaminer = examinerTable.getSelectionModel().getSelectedItems();
    DataModel.deleteExaminer(examiner);
    allExaminers.removeAll(selectedExaminer);
    examinerIdLabel.setText("");
    examinerLastNameLabel.setText("");
    examinerFirstNameLabel.setText("");
    examinerDateTable.getItems().clear();
  }

    public void openAddCourseWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addcourse.fxml"));
        Parent root = (Parent) loader.load();
        AddCourseController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Course");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openEditCourseWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editcourse.fxml"));
        Parent root = (Parent) loader.load();
        EditCourseController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Course");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void selectCourseItem() {
        courseIdLabel.setText("");
        courseTypeLabel.setText("");
        courseStudentTable.getItems().clear();
        try {
            Course course = courseTable.getSelectionModel().getSelectedItem();
            courseIdLabel.setText(course.courseIdProperty().get());
            courseTypeLabel.setText(course.courseTypeProperty().get());
            ObservableList<Student> students = FXCollections.<Student>observableArrayList(course.studentsProperty());
            courseStudentTable.getItems().addAll(students);
        } catch (Exception e) {}
    }

    public void deleteCourse() {
        ObservableList<Course> allCourses, selectedCourse;
        Course course= courseTable.getSelectionModel().getSelectedItem();
        allCourses = courseTable.getItems();
        selectedCourse = courseTable.getSelectionModel().getSelectedItems();
        DataModel.deleteCourse(course);
        allCourses.removeAll(selectedCourse);
        courseIdLabel.setText("");
        courseTypeLabel.setText("");
        courseStudentTable.getItems().clear();
    }

    public void classroomEdit() {
        if (editSaveClassroom.getText().equals("Edit")) {
            selectClassroomItem();
            classroomIdTextField.setStyle(null);
            capacityTextField.setStyle(null);
            hdmiTextField.setStyle(null);
            vgaTextField.setStyle(null);
            classroomIdTextField.setEditable(true);
            capacityTextField.setEditable(true);
            hdmiTextField.setEditable(true);
            vgaTextField.setEditable(true);
            editSaveClassroom.setText("Save");
        } else {
            classroomIdTextField.setEditable(false);
            capacityTextField.setEditable(false);
            hdmiTextField.setEditable(false);
            vgaTextField.setEditable(false);

            String styleTextField = "-fx-text-box-border: transparent; -fx-background-color:  -fx-control-inner-background; -fx-control-inner-background:  f4f4f4; -fx-cursor: none";
            classroomIdTextField.setStyle(styleTextField);
            capacityTextField.setStyle(styleTextField);
            hdmiTextField.setStyle(styleTextField);
            vgaTextField.setStyle(styleTextField);

            ClassRoom classRoom = new ClassRoom(classroomIdTextField.getText(),
                    Integer.parseInt(capacityTextField.getText()), Boolean.parseBoolean(hdmiTextField.getText()), Boolean.parseBoolean(vgaTextField.getText()));
            deleteClassroom();
            tableClassroom.getItems().add(classRoom);
            tableClassroom.getSelectionModel().clearSelection();
            editSaveClassroom.setText("Edit");
        }
    }
}
