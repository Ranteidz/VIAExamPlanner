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

    private ArrayList<Date> examinerDates;
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
        examinerDates = new ArrayList<Date>();
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
        parentController.model.addExaminer(examiner);
        /*for (Date member : date) { TODO fix this
            parentController.model.addUnavailabilityDateToExaminer(examiner, member);
        }*/
//        parentController.model.addUnavailabilityDatesToExaminer(examiner, examinerDates);
        parentController.updateData();
        closeWindow();
    }

    public void addUnavailableDate() {
        LocalDate localDate = datePicker.getValue();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        examinerDates.add(date);
        unavailableDatesTable.getItems().add(date);
        datePicker.setValue(null);
    }

    public void closeWindow() {
        examiner = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}