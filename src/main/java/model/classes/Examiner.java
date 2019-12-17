package model.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

/**
 * Represents an examiner.
 * An examiner can be assigned to an exam.
 */
public class Examiner {
    private StringProperty examinerId = new SimpleStringProperty();
    private StringProperty examinerFirstName = new SimpleStringProperty();
    private StringProperty examinerLastName = new SimpleStringProperty();
    private ArrayList<Date> unavailableDates = new ArrayList<Date>();

    /**
     * Construct new empty Examiner object.
     */
    public Examiner() {
    }

    /**
     * Creates a new Examiner in memory.
     * @param examinerId ID of examiner
     * @param examinerFirstName First name of examiner
     * @param examinerLastName Last name of examiner
     */
    public Examiner(String examinerId, String examinerFirstName, String examinerLastName) {
        this.examinerId.set(examinerId);
        this.examinerFirstName.set(examinerFirstName);
        this.examinerLastName.set(examinerLastName);
    }

    /**
     * Sets the unavailable dates to parameter.
     * @param unavailableDates new set of unavailable dates, overwrites old dates
     */
    public void setUnavailableDates(ArrayList<Date> unavailableDates) {
        this.unavailableDates = unavailableDates;
    }

    /**
     * Sets the examiner ID
     * @param examinerId ID of examiner
     */
    public void setExaminerId(String examinerId) {
        this.examinerId.set(examinerId);
    }

    /**
     * Sets the first name of the examiner.
     * @param examinerFirstName first name of examiner
     */
    public void setExaminerFirstName(String examinerFirstName) {
        this.examinerFirstName.set(examinerFirstName);
    }

    /**
     * Sets the last name of the examiner.
     * @param examinerLastName last name of examiner
     */
    public void setExaminerLastName(String examinerLastName) {
        this.examinerLastName.set(examinerLastName);
    }

    /**
     * Returns the ID of examiner as StringProperty
     * @return ID of examiner
     */
    public StringProperty examinerIdProperty() {
        return examinerId;
    }

    /**
     * Returns the first name of examiner as StringProperty
     * @return first name of examiner
     */
    public StringProperty examinerFirstNameProperty() {
        return examinerFirstName;
    }

    /**
     * Returns the last name of examiner as StringProperty
     * @return last name of examiner
     */
    public StringProperty examinerLastNameProperty() {
        return examinerLastName;
    }

    /**
     * Returns the full name of examiner as StringProperty
     * @return full name of examiner
     */
    public StringProperty examinerNameProperty() {
        StringProperty name = new SimpleStringProperty();
        name.set(String.format("%s %s", examinerFirstName.get(), examinerLastName.get()));
        return name;
    }

    /**
     * Returns the information of examiner as StringProperty
     * @return ID and full name of examiner
     */
    public StringProperty examinerInfoProperty() {
        StringProperty name = new SimpleStringProperty();
        name.set(String.format("%s, %s", examinerId.get(), examinerNameProperty().get()));
        return name;
    }

    /**
     * Returns the unavailable dates of examiner
     * @return ArrayList of unavailable dates of examiner
     */
    public ArrayList<Date> unavailableDatesProperty() {
        return unavailableDates;
    }

    /**
     * Adds an unavailable date to examiner.
     * @param date date to be added to examiner unavailable dates
     */
    public void addUnavailableDate(Date date) {
        if (!unavailableDates.contains(date))
            unavailableDates.add(new Date(date.getDay(), date.getMonth(), date.getYear()));
    }

    /**
     * Removes an unavailable date from examiner.
     * @param date date to be removed from examiner unavailable dates
     */
    public void removeUnavailabilityDate(Date date) {
        if(unavailableDates.contains(date))
            unavailableDates.remove(date);
    }

    @Override
    public String toString() {
        return examinerId + " " + examinerFirstName + " " + examinerLastName;
    }
}
