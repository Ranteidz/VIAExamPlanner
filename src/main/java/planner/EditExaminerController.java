package planner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataModel;
import model.beans.Date;
import model.beans.Examiner;

import java.time.LocalDate;

public class EditExaminerController {

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

    public EditExaminerController() {
    }

    public void initialize(PrimaryController parentController) {
        this.parentController = parentController;
        datesColumn.setCellValueFactory(new PropertyValueFactory<Date, String>("formattedDate"));
        examiner = parentController.examinerTable.getSelectionModel().getSelectedItem();
        examinerIdInput.setText(examiner.examinerIdProperty().get());
        examinerFirstNameInput.setText(examiner.examinerFirstNameProperty().get());
        examinerLastNameInput.setText(examiner.examinerLastNameProperty().get());
        ObservableList<Date> dates = parentController.examinerDateTable.getItems();
        unavailableDatesTable.getItems().addAll(dates);
    }

    public void saveExaminer() {
        examiner.setExaminerId(examinerIdInput.getText());
        examiner.setExaminerFirstName(examinerFirstNameInput.getText());
        examiner.setExaminerLastName(examinerLastNameInput.getText());
        DataModel.editExaminer(examiner);
        parentController.examinerTable.getItems().add(examiner);
        parentController.updateData();
        closeWindow();
    }

    public void addUnavailableDate() {
        LocalDate localDate = datePicker.getValue();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        examiner.addUnavailableDate(date);
        unavailableDatesTable.getItems().add(date);
        datePicker.setValue(null);
    }

    public void deleteDate() {
        ObservableList<Date> allDates, selectedDate;
        allDates = unavailableDatesTable.getItems();
        selectedDate = unavailableDatesTable.getSelectionModel().getSelectedItems();
        Date date= unavailableDatesTable.getSelectionModel().getSelectedItem();
        DataModel.deleteUnavailabilityDateFromExaminer(examiner,date);
        examiner.unavailableDatesProperty().remove(selectedDate.get(0));
        allDates.removeAll(selectedDate);
    }

    public void closeWindow() {
        examiner = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}