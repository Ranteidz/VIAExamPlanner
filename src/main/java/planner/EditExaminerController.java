package planner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.classes.Date;
import model.classes.Examiner;

import java.time.LocalDate;
import java.util.ArrayList;

public class EditExaminerController {

    private PrimaryController parentController;
    @FXML
    public Label examinerIdInput;
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

    private ArrayList<Date> addedDates;
    private ArrayList<Date> deletedDates;

    private Examiner examiner = new Examiner();

    public EditExaminerController() {
        addedDates = new ArrayList<Date>();
        deletedDates = new ArrayList<Date>();
    }

    public void initialize(PrimaryController parentController) throws NullPointerException{
        this.parentController = parentController;
        datesColumn.setCellValueFactory(new PropertyValueFactory<Date, String>("formattedDate"));
        examiner = parentController.examinerTable.getSelectionModel().getSelectedItem();
        examinerIdInput.setText(examiner.examinerIdProperty().get());
        examinerFirstNameInput.setText(examiner.examinerFirstNameProperty().get());
        examinerLastNameInput.setText(examiner.examinerLastNameProperty().get());
        ObservableList<Date> dates = parentController.examinerDateTable.getItems();
        unavailableDatesTable.getItems().addAll(dates);
        closeWindow();
    }

    public void saveExaminer() {
        examiner.setExaminerId(examinerIdInput.getText());
        examiner.setExaminerFirstName(examinerFirstNameInput.getText());
        examiner.setExaminerLastName(examinerLastNameInput.getText());
        parentController.model.editExaminer(examiner, deletedDates, addedDates);
        parentController.updateData();
        closeWindow();
    }

    public void addUnavailableDate() {
        LocalDate localDate = datePicker.getValue();
        Date date = new Date(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
        if (!examiner.unavailableDatesProperty().contains(date)) {
            examiner.addUnavailableDate(date);
            unavailableDatesTable.getItems().add(date);
            if (deletedDates.contains(date))
                deletedDates.remove(date);
            addedDates.add(date);
            unavailableDatesTable.getItems().add(date);
        } else System.out.println("Date already exists!");
        datePicker.setValue(null);
    }

    public void deleteDate() {
        ObservableList<Date> allDates, selectedDate;
        allDates = unavailableDatesTable.getItems();
        selectedDate = unavailableDatesTable.getSelectionModel().getSelectedItems();
        Date date = unavailableDatesTable.getSelectionModel().getSelectedItem();
        addedDates.remove(date);
        deletedDates.add(date);
        examiner.unavailableDatesProperty().remove(selectedDate.get(0));
        allDates.removeAll(selectedDate);
    }

    public void closeWindow() {
        examiner = null;
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}