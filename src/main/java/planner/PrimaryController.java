package planner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.classes.*;
import model.classes.Date;
import model.DataModel;

import java.time.LocalDate;

public class PrimaryController extends Controller {
    @FXML
    public CheckBox inputClassroomVGA;
    @FXML
    public CheckBox inputClassroomHDMI;

    private SearchThread searchThread;

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
    public TableColumn<Student, Integer> courseStudentNumber;

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
    @FXML
    public TextField studentSearch;
    @FXML
    public TextField classroomSearch;
    @FXML
    public TextField examinerSearch;
    @FXML
    public TextField courseSearch;
    @FXML
    private TabPane tabPane;

    @FXML
    public TableView<Student> examStudentsTable;
    @FXML
    public TableColumn<Student, Integer> examStudentId;
    @FXML
    public TableColumn<Student, String> examStudentName;
    @FXML
    public RadioButton showAllExamsRadio;
    @FXML
    public RadioButton showExamsByDateRadio;
    @FXML
    public DatePicker examDatePicker;

    @FXML
    public Label classroomErrorLabel;
    @FXML
    public Label examinerErrorLabel;
    @FXML
    public Label courseErrorLabel;
    @FXML
    public Label studentErrorLabel;

    public PrimaryController() {
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    public void initialize() {
        showAllExamsRadio.setSelected(true);
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
        courseStudentNumber.setCellValueFactory(new PropertyValueFactory<Student, Integer>("numberOfStudents"));
        examIdColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("courseId"));
        examDateColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("examDate"));
        examClassroomColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("classroomId"));
        examExaminerColumn.setCellValueFactory(new PropertyValueFactory<Exam, String>("examinerId"));
        examStudentId.setCellValueFactory(new PropertyValueFactory<Student, Integer>("studentId"));
        examStudentName.setCellValueFactory(new PropertyValueFactory<Student, String>("studentName"));

        LoadData waiter = new LoadData(this);
        waiter.start();

        searchThread = new SearchThread(System.currentTimeMillis(), this, "null");
        searchThread.start();
    }

    public void examDatePicked() {
        showExamsByDateRadio.setSelected(true);
        showExamsByDate();
    }

    public void showAllExams() {
        examTable.getItems().clear();
        examTable.getItems().addAll(model.getExamAll());
    }

    public void showExamsByDate() {
        examTable.getItems().clear();
        LocalDate localDate = examDatePicker.getValue();
        try {
            examTable.getItems().addAll(model.getExamsByDate(new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear())));
        } catch (NullPointerException e) {
            showAllExams();
        }
    }

    public void updateData() throws NullPointerException {
        studentTable.getItems().clear();
        examinerTable.getItems().clear();
        courseTable.getItems().clear();
        classroomTable.getItems().clear();
        examTable.getItems().clear();
        loadAllData();
    }

    private void loadAllData() throws NullPointerException {
        studentTable.getItems().addAll(model.getStudentAll());
        examinerTable.getItems().addAll(model.getExaminersALL());
        courseTable.getItems().addAll(model.getCoursesAll());
        classroomTable.getItems().addAll(model.getClassRoomsAll());
        examTable.getItems().addAll(model.getExamAll());
    }

    public void searchData() {
        searchThread.keyPressed();
        searchThread = new SearchThread(System.currentTimeMillis(), this, tabPane.getSelectionModel().getSelectedItem().getText());
        searchThread.start();
    }

    public void openAddExamWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addexam.fxml"));
        Parent root = (Parent) loader.load();
        AddExamController controller = loader.getController();
        controller.initialize(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Add Exam");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void openEditExamWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editexam.fxml"));
        Parent root = (Parent) loader.load();
        EditExamController controller = loader.getController();
        try {
            controller.initialize(this);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Edit Exam");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (NullPointerException e) {
            System.out.println("No exam selected!");
        }
    }

    public void selectExamItem() {
        Exam exam = examTable.getSelectionModel().getSelectedItem();
        examStudentsTable.getItems().clear();
        try {
            examStudentsTable.getItems().addAll(model.getStudentsByCourse(exam.courseIdProperty().get()));
            examIdLabel.setText(exam.courseIdProperty().get());
            examTypeLabel.setText(model.getCourseById(exam.courseIdProperty().get()).courseTypeProperty().get());
            examExaminerIdLabel.setText(exam.examinerIdProperty().get());
            coexaminerLabel.setText(exam.coexaminerTypeProperty().get().equalsIgnoreCase("internal") ? "Internal" : String.format("External: %s", exam.coexaminerNameProperty().get()));
            examClassroomIdLabel.setText(exam.classroomIdProperty().get());
        } catch (NullPointerException e) {
            System.out.println("No exam object selected.");
        }
    }

    public void cancelExam() {
        examStudentsTable.getItems().clear();
        examIdLabel.setText("");
        examTypeLabel.setText("");
        examExaminerIdLabel.setText("");
        coexaminerLabel.setText("");
        examClassroomIdLabel.setText("");
        try {
            model.removeExam(examTable.getSelectionModel().getSelectedItem());
        } catch (NullPointerException e) {
            System.out.println("No exam object selected.");
        }
        updateData();
    }

    public void addClassroom() {
        classroomErrorLabel.setText("");
        Classroom classroom = new Classroom(inputClassroomName.getText(),
                Integer.parseInt(inputClassroomCapacity.getText()), false, false);
        if (inputClassroomHDMI.isSelected())
            classroom.setHdmi(true);
        if (inputClassroomVGA.isSelected())
            classroom.setVga(true);
        if (model.getClassroomById(classroom.nameProperty().get()) == null) {
            model.addClassroom(classroom);
        } else {
            System.out.println("ERROR: Classroom already exists!");
            classroomErrorLabel.setText("Invalid input");
        }
        inputClassroomName.clear();
        inputClassroomCapacity.clear();
        inputClassroomHDMI.setSelected(false);
        inputClassroomVGA.setSelected(false);
        updateData();
    }

    public void selectClassroomItem() {
        classroomErrorLabel.setText("");
        Classroom classroom = classroomTable.getSelectionModel().getSelectedItem();
        try {
            classroomIdTextField.setText(classroom.nameProperty().get());
            capacityTextField
                    .setText(Integer.toString(classroom.capacityProperty().get()));
            hdmiTextField.setText(Boolean.toString(classroom.hdmiProperty().get()));
            vgaTextField.setText(Boolean.toString(classroom.vgaProperty().get()));
        } catch (NullPointerException e) {
            System.out.println("No classroom object selected.");
        }
    }

    public void deleteClassroom() {
        Classroom classroom = classroomTable.getSelectionModel().getSelectedItem();
        try {
            if (model.classroomDeletable(classroom)) {
                model.deleteClassroom(classroom);
                classroomIdTextField.setText("");
                capacityTextField.setText("");
                hdmiTextField.setText("");
                vgaTextField.setText("");
                updateData();
            } else {
                classroomErrorLabel.setText("Classroom reserved!");
            }
        } catch (NullPointerException e) {
            System.out.println("No classroom object selected.");
        }
    }

    public void addStudent() {
        studentErrorLabel.setText("");
        Student student = new Student(Integer.parseInt(studentIDinput.getText()),
                studentFirstNameInput.getText(), studentLastNameInput.getText());
        if (student.studentIdProperty().get() >= 0 && model.getStudentById(student.studentIdProperty().get()) == null) {
            model.addStudent(student);
        } else {
            System.out.println("ERROR: Student already exists or input is incorrect!");
            studentErrorLabel.setText("Invalid student input");
        }
        studentIDinput.clear();
        studentFirstNameInput.clear();
        studentLastNameInput.clear();
        updateData();
    }

    public void selectStudentItem() {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        studentErrorLabel.setText("");
        try {
            studentIDTextField.setText(Integer.toString(student.studentIdProperty().get()));
            firstNameTextField.setText(student.studentFirstNameProperty().get());
            lastNameTextField.setText(student.studentLastNameProperty().get());
        } catch (NullPointerException e) {
            System.out.println("No studen object selected.");
        }
    }

    public void deleteStudent() {
        Student student = studentTable.getSelectionModel().getSelectedItem();
        studentErrorLabel.setText("");
        try {
            model.deleteStudent(student);
        } catch (NullPointerException e) {
            System.out.println("No student object selected.");
        }
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
        if (model.examinerDeletable(examinerTable.getSelectionModel().getSelectedItem())) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("editexaminer.fxml"));
            Parent root = (Parent) loader.load();
            EditExaminerController controller = loader.getController();
            try {
                controller.initialize(this);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Edit Examiner");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (NullPointerException e) {
                examinerErrorLabel.setText("No examiner selected!");
                e.printStackTrace();
            }
        } else {
            examinerErrorLabel.setText("Examiner booked!");
        }
    }

    public void selectExaminerItem() {
        examinerErrorLabel.setText("");
        examinerIdLabel.setText("");
        examinerLastNameLabel.setText("");
        examinerFirstNameLabel.setText("");
        examinerDateTable.getItems().clear();
        Examiner examiner = examinerTable.getSelectionModel().getSelectedItem();
        try {
            examinerIdLabel.setText(examiner.examinerIdProperty().get());
            examinerFirstNameLabel.setText(examiner.examinerFirstNameProperty().get());
            examinerLastNameLabel.setText(examiner.examinerLastNameProperty().get());
            ObservableList<Date> dates = FXCollections.<Date>observableArrayList(examiner.unavailableDatesProperty());
            examinerDateTable.getItems().addAll(model.getExaminerUnavailabilityDates(examiner.examinerIdProperty().get()));
        } catch (NullPointerException e) {
            System.out.println("No examiner object selected!");
        }
    }

    public void deleteExaminer() {
        Examiner examiner = examinerTable.getSelectionModel().getSelectedItem();
        try {
            if (model.examinerDeletable(examiner)) {
                model.deleteExaminer(examiner); //this and the line above causes the exception
                examinerIdLabel.setText("");
                examinerLastNameLabel.setText("");
                examinerFirstNameLabel.setText("");
                updateData();
            } else {
                examinerErrorLabel.setText("Examiner booked!");
            }
        } catch (NullPointerException e) {
            System.out.println("No examiner object selected.");
        }
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
        try {
            if (model.courseDeletable(courseTable.getSelectionModel().getSelectedItem())) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("editcourse.fxml"));
                Parent root = (Parent) loader.load();
                EditCourseController controller = loader.getController();
                controller.initialize(this);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Edit Course");
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                courseErrorLabel.setText("Course booked!");
            }
        } catch (NullPointerException e) {
            courseErrorLabel.setText("No course selected!");
        }
    }

    public void selectCourseItem() {
        courseErrorLabel.setText("");
        courseIdLabel.setText("");
        courseTypeLabel.setText("");
        courseStudentTable.getItems().clear();
        Course course = courseTable.getSelectionModel().getSelectedItem();
        try {
            courseIdLabel.setText(course.courseIdProperty().get());
            courseTypeLabel.setText(course.courseTypeProperty().get());
            courseStudentTable.getItems().addAll(model.getStudentsByCourse(course.courseIdProperty().get()));
        } catch (NullPointerException e) {
            System.out.println("No course object selected.");
        }
    }

    public void deleteCourse() {
        Course course = courseTable.getSelectionModel().getSelectedItem();
        try {
            if (model.courseDeletable(course)) {
                model.deleteCourse(course);
                courseIdLabel.setText("");
                courseTypeLabel.setText("");
                courseStudentTable.getItems().clear();
                updateData();
            } else {
                courseErrorLabel.setText("Course booked!");
            }
        } catch (NullPointerException e) {
            System.out.println("No course object selected.");
        }
    }

    public void classroomEdit() {
        String styleTextField = "-fx-text-box-border: transparent; -fx-background-color:  -fx-control-inner-background; -fx-control-inner-background:  f4f4f4; -fx-cursor: none";
        if (model.classroomDeletable(classroomTable.getSelectionModel().getSelectedItem())) {
            if (editSaveClassroom.getText().equals("Edit")) {
                selectClassroomItem();
                classroomIdTextField.setStyle(styleTextField);
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

                classroomIdTextField.setEditable(false);
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
        } else {
            classroomErrorLabel.setText("Classroom reserved!");
        }

    }

    public void studentEdit() {
        String styleTextField = "-fx-text-box-border: transparent; -fx-background-color:  -fx-control-inner-background; -fx-control-inner-background:  f4f4f4; -fx-cursor: none";
        if(!studentTable.getSelectionModel().isEmpty()) {
            if (editSaveStudent.getText().equals("Edit")) {
                selectStudentItem();
                studentIDTextField.setStyle(styleTextField);
                firstNameTextField.setStyle(null);
                lastNameTextField.setStyle(null);
                studentIDTextField.setEditable(false);
                firstNameTextField.setEditable(true);
                lastNameTextField.setEditable(true);
                deleteStudentButton.setDisable(true);
                editSaveStudent.setText("Save");
            } else {
                studentIDTextField.setEditable(false);
                firstNameTextField.setEditable(false);
                lastNameTextField.setEditable(false);

                studentIDTextField.setStyle(styleTextField);
                firstNameTextField.setStyle(styleTextField);
                lastNameTextField.setStyle(styleTextField);

//TODO either fix deleteStudent or load table after save.Edit works. causes error number format exception when saving empty
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

    public void getCourses() {
        if (courseSearch.getText().isEmpty()) {
            courseTable.getItems().clear();
            courseTable.getItems().addAll(model.getCoursesAll());
        } else {
            courseTable.getItems().clear();
            courseTable.getItems().addAll(model.getCoursesBySearch(courseSearch.getText()));
        }
    }

    public void getClassrooms() {
        if (classroomSearch.getText().isEmpty()) {
            classroomTable.getItems().clear();
            classroomTable.getItems().addAll(model.getClassRoomsAll());
        } else {
            classroomTable.getItems().clear();
            classroomTable.getItems().addAll(model.getClassroomsBySearch(classroomSearch.getText()));
        }
    }

    public void getExaminers() {
        if (examinerSearch.getText().isEmpty()) {
            examinerTable.getItems().clear();
            examinerTable.getItems().addAll(model.getExaminersALL());
        } else {
            examinerTable.getItems().clear();
            examinerTable.getItems().addAll(model.getExaminersBySearch(examinerSearch.getText()));
        }
    }

    public void getStudents() {
        if (studentSearch.getText().isEmpty()) {
            studentTable.getItems().clear();
            studentTable.getItems().addAll(model.getStudentAll());
        } else {
            studentTable.getItems().clear();
            studentTable.getItems().addAll(model.getStudentsBySearch(studentSearch.getText()));
        }
    }
}
