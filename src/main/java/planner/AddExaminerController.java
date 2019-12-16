package planner;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataModel;
import model.classes.Date;
import model.classes.Examiner;

public class AddExaminerController {

    private PrimaryController parentController;
    @FXML
    public TextField examinerIdInput;
    @FXML
    public TextField examinerFirstNameInput;
    @FXML
    public TextField examinerLastNameInput;
    @FXML
    public DatePicker datePicker;
    @FXML
    public Button cancelButton;
    @FXML
    public TableView<Date> unavailableDatesTable;
    @FXML
    private TableColumn<Date, String> datesColumn;

    private Examiner examiner = new Examiner();

    public AddExaminerController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        datesColumn.setCellValueFactory(new PropertyValueFactory<Date, String>("formattedDate"));
    }

    public void addExaminer() {
        //TODO add examiner to database. Partially done// need to add data for the unavailability
        examiner.setExaminerId(examinerIdInput.getText());
        examiner.setExaminerFirstName(examinerFirstNameInput.getText());
        examiner.setExaminerLastName(examinerLastNameInput.getText());
        if(parentController.model.getExaminerById(examiner.examinerIdProperty().get()) == null) {
            parentController.model.addExaminer(examiner);
            parentController.updateData();
        } else
            System.out.println("ERROR: Examiner already exists!");
        closeWindow();
    }

    public void addUnavailableDate() {
        LocalDate localDate = datePicker.getValue();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        if(!examiner.getUnavailableDates().contains(date)) {
            examiner.addUnavailableDate(date);
            unavailableDatesTable.getItems().add(date);
        } else System.out.println("Date already exists!");
        datePicker.setValue(null);
    }

    public void closeWindow() {
        examiner = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}