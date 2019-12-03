package planner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataModel;
import model.beans.Date;
import model.beans.Examiner;

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
        datesColumn.setCellValueFactory(new PropertyValueFactory<Date, String>("date"));
    }

    public void addExaminer() {
        //TODO add examiner to database. Partially done// need to add data for the unavailability
        examiner.setExaminerId(examinerIdInput.getText());
        examiner.setExaminerFirstName(examinerFirstNameInput.getText());
        examiner.setExaminerLastName(examinerLastNameInput.getText());
    DataModel.addExaminer(examiner);
        parentController.examinerTable.getItems().add(examiner);
//        parentController.updateData();
        closeWindow();
    }

    public void addUnavailableDate() {
        LocalDate localDate = datePicker.getValue();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        examiner.addUnavailableDate(date);
        unavailableDatesTable.getItems().add(date);
        datePicker.setValue(null);
    }

    public void closeWindow() {
        examiner = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}