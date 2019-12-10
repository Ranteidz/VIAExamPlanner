package planner;

import javafx.collections.FXCollections;
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
import model.LoadData;
import model.classes.*;
import model.classes.Date;
import model.DataModel;

public class PrimaryController {
    @FXML
    public CheckBox inputClassroomVGA;
    @FXML
    public CheckBox inputClassroomHDMI;

    public DataModel model;
    //    private ArrayList<Student> studentsTest = DataModel.getStudentAll();
    @FXML
    private TextField inputClassroomName;
    @FXML
    private TextField inputClassroomCapacity;
    @FXML
    public TableView<Classroom> classroomTable;
    @FXML
    public TableColumn<Classroom, String> name;
    @FXML
    public TableColumn<Classroom, Integer> capacity;
    @FXML
    public TableColumn<Classroom, Boolean> hdmi;
    @FXML
    public TableColumn<Classroom, Boolean> vga;
    @FXML
    private TextField classroomIdTextField;
    @FXML
    private TextField capacityTextField;
    @FXML
    private TextField hdmiTextField;
    @FXML
    private TextField vgaTextField;
    @FXML
    private CheckBox hdmiEditCheckBox;
    @FXML
    private CheckBox vgaEditCheckBox;
    @FXML
    private Button editSaveClassroom;
    @FXML
    private Button deleteClassroomButton;

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
    private Button editSaveStudent;
    @FXML
    private TextField studentIDTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private Button deleteStudentButton;


    @FXML
    public TableView<Examiner> examinerTable;
    @FXML
    public TableColumn<Examiner, String> examinerId;
    @FXML
    public TableColumn<Examiner, String> examinerName;
    @FXML
    public Label studentIdLable;
    @FXML
    Label studentFirstNameLabel;
    @FXML
    Label studentLastNameLabel;
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
    public Label courseIdLabel;
    @FXML
    public Label courseTypeLabel;
    @FXML
    public TableView<Student> courseStudentTable;
    @FXML
    public TableColumn<Student, Integer> courseStudentId;
    @FXML
    public TableColumn<Student, String> courseStudentName;

    @FXML
    public TableView<Exam> examTable;
    @FXML
    public TableColumn<Exam, String> examIdColumn;
    @FXML
    public TableColumn<Exam, String> examDateColumn;
    @FXML
    public TableColumn<Exam, String> examClassroomColumn;
    @FXML
    public TableColumn<Exam, String> examExaminerColumn;
    @FXML
    public Label examIdLabel;
    @FXML
    public Label examTypeLabel;
    @FXML
    public Label examExaminerIdLabel;
    @FXML
    public Label coexaminerLabel;
    @FXML
    public Label examClassroomIdLabel;

    public PrimaryController() {
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public void initialize() {
        studentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        studentFirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentFirstName"));
        studentLastName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentLastName"));
        name.setCellValueFactory(new PropertyValueFactory<Classroom, String>("name"));
        capacity.setCellValueFactory(new PropertyValueFactory<Classroom, Integer>("capacity"));
        hdmi.setCellValueFactory(new PropertyValueFactory<Classroom, Boolean>("hdmi"));
        vga.setCellValueFactory(new PropertyValueFactory<Classroom, Boolean>("vga"));
        examinerId.setCellValueFactory(new PropertyValueFactory<Examiner, String>("examinerId"));
        examinerName.setCellValueFactory(new PropertyValueFactory<Examiner, String>("examinerName"));
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseId"));
        courseTypeColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("courseType"));
        examinerDateColumn.setCellValueFactory(new PropertyValueFactory<Date, String>("formattedDate"));
        courseStudentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        courseStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));
        examIdColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("courseId"));
        examDateColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("examDate"));
        examClassroomColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("classroomId"));
        examExaminerColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("examinerId"));

//        loadAllData(); //TODO FIX causes null pointer exception, doesn't load naything, works after program starts
        LoadData waiter = new LoadData(this);
        waiter.start();
    }

    public void SwitchToDarkMode(ActionEvent event) {
        System.out.println("switch to dark mode");
    }

    public void updateData() throws NullPointerException {
        System.out.println("updating data");
        studentTable.getItems().clear();
        examinerTable.getItems().clear();
        courseTable.getItems().clear();
        classroomTable.getItems().clear();
        loadAllData();
    }

    private void loadAllData() throws NullPointerException {
        studentTable.getItems().addAll(model.getStudentAll());
        examinerTable.getItems().addAll(model.getExaminersALL());
        courseTable.getItems().addAll(model.getCoursesAll());
        classroomTable.getItems().addAll(model.getClassRoomsAll());
    }

    public void MethodTesting() {
        updateData();
    }

    public void openAddExamWindow() throws Exception {
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

    public void openEditExamWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editexam.fxml"));
        Parent root = (Parent) loader.load();
        EditExamController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Course");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void selectExamItem() {
        Exam exam = examTable.getSelectionModel().getSelectedItem();
        examIdLabel.setText(exam.courseIdProperty().get());
        examTypeLabel.setText("SELECT courseType FROM courses WHERE courseID = ?, exam.getId");
        examinerIdLabel.setText(exam.examinerIdProperty().get());
        coexaminerLabel.setText(exam.coexaminerTypeProperty().get().equalsIgnoreCase("internal") ? "Internal" : String.format("External: %s", exam.coexaminerNameProperty().get()));
        examClassroomIdLabel.setText(exam.classroomIdProperty().get());
    }

    public void cancelExam() {
        System.out.println("Exam cancelled");
    }

    public void cancelExam(String argument) {
        cancelExam();
        System.out.println("classroom not available tommorow");
    }

    public void addClassroom() {
        Classroom classroom = new Classroom(inputClassroomName.getText(),
                Integer.parseInt(inputClassroomCapacity.getText()), false, false);
        if (inputClassroomHDMI.isSelected())
            classroom.setHdmi(true);
        if (inputClassroomVGA.isSelected())
            classroom.setVga(true);
        model.addClassroom(classroom);
        inputClassroomName.clear();
        inputClassroomCapacity.clear();
        inputClassroomHDMI.setSelected(false);
        inputClassroomVGA.setSelected(false);
        updateData();
    }

    public void selectClassroomItem() {
        Classroom classroom = classroomTable.getSelectionModel().getSelectedItem();
        classroomIdTextField.setText(classroom.nameProperty().get());
        capacityTextField
                .setText(Integer.toString(classroom.capacityProperty().get()));
        hdmiTextField.setText(Boolean.toString(classroom.hdmiProperty().get()));
        vgaTextField.setText(Boolean.toString(classroom.vgaProperty().get()));
    }

    public void deleteClassroom() {
        Classroom classroom = classroomTable.getSelectionModel().getSelectedItem();
        model.deleteClassroom(classroom);
        classroomIdTextField.setText("");
        capacityTextField.setText("");
        hdmiTextField.setText("");
        vgaTextField.setText("");
        updateData();
    }

    public void addStudent() {
        Student student = new Student(Integer.parseInt(studentIDinput.getText()),
                studentFirstNameInput.getText(), studentLastNameInput.getText());
        model.addStudent(student);
        studentIDinput.clear();
        studentFirstNameInput.clear();
        studentLastNameInput.clear();
        updateData();
    }

    public void selectStudentItem() {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        studentIDTextField.setText(Integer.toString(student.studentIdProperty().get()));
        firstNameTextField.setText(student.studentFirstNameProperty().get());
        lastNameTextField.setText(student.studentLastNameProperty().get());
    }

    public void deleteStudent() {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        model.deleteStudent(student);
        examinerIdLabel.setText("");
        examinerLastNameLabel.setText("");
        examinerFirstNameLabel.setText("");
        updateData();
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
        examinerDateTable.getItems().addAll(model.getExaminerUnavailabilityDates(examiner.examinerIdProperty().get()));
    }

    public void deleteExaminer() {
        Examiner examiner = examinerTable.getSelectionModel().getSelectedItem();
        model.deleteExaminer(examiner);
        examinerIdLabel.setText("");
        examinerLastNameLabel.setText("");
        examinerFirstNameLabel.setText("");
        updateData();
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
        Course course = courseTable.getSelectionModel().getSelectedItem();
        courseIdLabel.setText(course.courseIdProperty().get());
        courseTypeLabel.setText(course.courseTypeProperty().get());
        courseStudentTable.getItems().addAll(model.getStudentsByCourse(course.courseIdProperty().get()));
        System.out.println(model.getStudentsByCourse(course.courseIdProperty().get()));
    }

    public void deleteCourse() {
        Course course = courseTable.getSelectionModel().getSelectedItem();
        model.deleteCourse(course);
        courseIdLabel.setText("");
        courseTypeLabel.setText("");
        courseStudentTable.getItems().clear();
        updateData();
    }

    public void classroomEdit() {
        if (editSaveClassroom.getText().equals("Edit")) {
            selectClassroomItem();
            classroomIdTextField.setStyle(null);
            capacityTextField.setStyle(null);
            hdmiTextField.setVisible(false);
            vgaTextField.setVisible(false);
            hdmiEditCheckBox.setVisible(true);
            vgaEditCheckBox.setVisible(true);

            if (hdmiTextField.getText().equals("true")) {
                hdmiEditCheckBox.setSelected(true);
            } else hdmiEditCheckBox.setSelected(false);
            if (vgaTextField.getText().equals("true")) {
                vgaEditCheckBox.setSelected(true);
            } else vgaEditCheckBox.setSelected(false);

            classroomIdTextField.setEditable(true);
            capacityTextField.setEditable(true);

            deleteClassroomButton.setDisable(true);

            editSaveClassroom.setText("Save");
        } else {
            hdmiEditCheckBox.setVisible(false);
            vgaEditCheckBox.setVisible(false);
            hdmiTextField.setVisible(true);
            vgaTextField.setVisible(true);
            classroomIdTextField.setEditable(false);
            capacityTextField.setEditable(false);
            hdmiTextField.setEditable(false);
            vgaTextField.setEditable(false);

            String styleTextField = "-fx-text-box-border: transparent; -fx-background-color:  -fx-control-inner-background; -fx-control-inner-background:  f4f4f4; -fx-cursor: none";
            classroomIdTextField.setStyle(styleTextField);
            capacityTextField.setStyle(styleTextField);
            hdmiTextField.setStyle(styleTextField);
            vgaTextField.setStyle(styleTextField);
//TODO classroom edit doesn't work
            Classroom classroom = new Classroom(classroomIdTextField.getText(),
                    Integer.parseInt(capacityTextField.getText()), hdmiEditCheckBox.isSelected(), vgaEditCheckBox.isSelected());
            model.editClassroom(classroom);
            classroomTable.getSelectionModel().clearSelection();
            deleteClassroomButton.setDisable(false);
            editSaveClassroom.setText("Edit");
            updateData();
        }
    }

    public void studentEdit() {
        if (editSaveStudent.getText().equals("Edit")) {
            selectStudentItem();
            studentIDTextField.setStyle(null);
            firstNameTextField.setStyle(null);
            lastNameTextField.setStyle(null);
            studentIDTextField.setEditable(true);
            firstNameTextField.setEditable(true);
            lastNameTextField.setEditable(true);
            deleteStudentButton.setDisable(true);
            editSaveStudent.setText("Save");
        } else {
            studentIDTextField.setEditable(false);
            firstNameTextField.setEditable(false);
            lastNameTextField.setEditable(false);

            String styleTextField = "-fx-text-box-border: transparent; -fx-background-color:  -fx-control-inner-background; -fx-control-inner-background:  f4f4f4; -fx-cursor: none";
            studentIDTextField.setStyle(styleTextField);
            firstNameTextField.setStyle(styleTextField);
            lastNameTextField.setStyle(styleTextField);

//TODO either fix deleteStudent or load table after save.Edit works.
            Student student = new Student(Integer.parseInt(studentIDTextField.getText()),
                    firstNameTextField.getText(), lastNameTextField.getText());
            model.editStudent(student);
            studentTable.getSelectionModel().clearSelection();
            deleteStudentButton.setDisable(false);
            editSaveStudent.setText("Edit");
            updateData();
        }
    }
}
