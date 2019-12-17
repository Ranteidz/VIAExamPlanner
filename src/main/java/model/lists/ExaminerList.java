package model.lists;

import model.classes.Date;
import model.classes.Examiner;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Holds all examiners and method to add, edit, remove and get examiners.
 */
public class ExaminerList {
    private ArrayList<Examiner> examiners;

    /**
     * Creates a new ExaminerList object.
     */
    public ExaminerList() {
        examiners = new ArrayList<Examiner>();
    }

    /**
     * Returns all examiners.
     * @return list of all examiners
     */
    public ArrayList<Examiner> getExaminers() {
        return examiners;
    }

    /**
     * Loads all examiners into object, overwrites existing ones.
     * @param examiners list of examiners
     */
    public void loadExaminers(ArrayList<Examiner> examiners) {
        this.examiners = examiners;
    }

    /**
     * Returns an examiner by its ID
     * @param examinerID examiner ID
     * @return examiner with that ID
     */
    public Examiner getExaminerByID(String examinerID) {
        for (Examiner examiner : examiners) {
            if (examiner.examinerIdProperty().get().equalsIgnoreCase(examinerID))
                return examiner;
        }
        return null;
    }

    /**
     * Adds an examiner to object if it does not exist already.
     * @param examiner examiner to be added
     * @return true if added successfully
     */
    public boolean addExaminer(Examiner examiner) {
        if (!examiners.contains(examiner)) {
            examiners.add(examiner);
            return true;
        }
        return false;
    }

    /**
     * Removes an examiner from object if it exists.
     * @param examiner examiner to be removed
     * @return true if removed successfully
     */
    public boolean removeExaminer(Examiner examiner) {
        if (examiners.contains(examiner)) {
            examiners.remove(examiner);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of all examiners who fulfill search condition.
     * @param search search information
     * @return list of examiners that meet condition
     */
    public ArrayList<Examiner> getExaminersBySearch(String search) {
        if (!search.isEmpty()) {
            ArrayList<Examiner> searchItems = new ArrayList<Examiner>();
            for (Examiner examiner : examiners)
                if (examiner.examinerInfoProperty().get().toLowerCase().contains(search.toLowerCase()))
                    searchItems.add(examiner);
            return searchItems;
        }
        return examiners;
    }

    /**
     * Edits an examiner
     * @param examiner edited examiner
     * @return true if edit successfully
     */
    public boolean editExaminer(Examiner examiner) {
        if (examiners.contains(getExaminerByID(examiner.examinerIdProperty().get()))) {
            getExaminerByID(examiner.examinerIdProperty().get()).setExaminerFirstName(examiner.examinerFirstNameProperty().get());
            getExaminerByID(examiner.examinerIdProperty().get()).setExaminerLastName(examiner.examinerLastNameProperty().get());
            getExaminerByID(examiner.examinerIdProperty().get()).setUnavailableDates(examiner.unavailableDatesProperty());
            return true;
        }
        return false;
    }

    /**
     * Returns all available examiners on a specific date that meet search condition.
     * @param search search information
     * @param date date
     * @return list of examiners that meet condition
     */
    public ArrayList<Examiner> getAvailableExaminers(String search, Date date) {
        ArrayList<Examiner> result = new ArrayList<Examiner>();
        for (Examiner examiner : getExaminersBySearch(search))
            if (!examiner.unavailableDatesProperty().contains(date))
                result.add(examiner);
        return result;
    }

    /**
     * Returns all examiners by their partial IDs (ex: MA results in MASA, MARI etc.)
     * @param examinerIds examiner ID
     * @return list of examiners that meet condition
     */
    public ArrayList<Examiner> getExaminersById(ArrayList<String> examinerIds) {
        ArrayList<Examiner> result = new ArrayList<Examiner>();
        for (Examiner examiner : examiners)
            if (examinerIds.contains(examiner.examinerIdProperty().get()))
                result.add(examiner);
        return result;
    }
}