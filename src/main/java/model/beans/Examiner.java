package model.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Examiner {
    private StringProperty examinerId = new SimpleStringProperty();
    private StringProperty examinerFirstName = new SimpleStringProperty();
    private StringProperty examinerLastName = new SimpleStringProperty();
    private ArrayList<Date> unavailableDates = new ArrayList<Date>();

    public Examiner() {
    }

    public Examiner(String examinerId, String examinerFirstName, String examinerLastName) {
        this.examinerId.set(examinerId);
        this.examinerFirstName.set(examinerFirstName);
        this.examinerLastName.set(examinerLastName);
    }

    public ArrayList<Date> getUnavailableDates() {
        return unavailableDates;
    }

    public void setUnavailableDates(ArrayList<Date> unavailableDates) {
        this.unavailableDates = unavailableDates;
    }

    public void setExaminerId(String examinerId) {
        this.examinerId.set(examinerId);
    }

    public void setExaminerFirstName(String examinerFirstName) {
        this.examinerFirstName.set(examinerFirstName);
    }

    public void setExaminerLastName(String examinerLastName) {
        this.examinerLastName.set(examinerLastName);
    }

    public StringProperty examinerIdProperty() {
        return examinerId;
    }

    public StringProperty examinerFirstNameProperty() {
        return examinerFirstName;
    }

    public StringProperty examinerLastNameProperty() {
        return examinerLastName;
    }

    public StringProperty examinerNameProperty() {
        StringProperty name = new SimpleStringProperty();
        name.set(String.format("%s %s", examinerFirstName.get(), examinerLastName.get()));
        return name;
    }

    public ArrayList<Date> unavailableDatesProperty() {
        return unavailableDates;
    }

    public void addUnavailableDate(Date date) {
        if (!unavailableDates.contains(date)) ;
        unavailableDates.add(new Date(date.getDay(), date.getMonth(), date.getYear()));
    }


    @Override
    public String toString() {
        return examinerId + " " + examinerFirstName + " " + examinerLastName;
    }
}
