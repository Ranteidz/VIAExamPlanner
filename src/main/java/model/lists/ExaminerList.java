package model.lists;

import model.classes.Date;
import model.classes.Examiner;

import java.util.ArrayList;
import java.util.Collection;

public class ExaminerList {
    private ArrayList<Examiner> examiners;

    public ExaminerList() {
        examiners = new ArrayList<Examiner>();
    }

    public ArrayList<Examiner> getExaminers() {
        return examiners;
    }

    public void loadExaminers(ArrayList<Examiner> examiners) {
        this.examiners = examiners;
    }

    public Examiner getExaminerByID(String examinerID) {
        for (Examiner examiner : examiners) {
            if (examiner.examinerIdProperty().get().equalsIgnoreCase(examinerID))
                return examiner;
        }
        return null;
    }

    public boolean addExam(Examiner examiner) {
        if (!examiners.contains(examiner)) {
            examiners.add(examiner);
            return true;
        }
        return false;
    }

    public boolean removeExaminer(Examiner examiner) {
        if (examiners.contains(examiner)) {
            examiners.remove(examiner);
            return true;
        }
        return false;
    }

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

    public boolean editExaminer(Examiner examiner) {
        if (examiners.contains(getExaminerByID(examiner.examinerIdProperty().get()))) {
            getExaminerByID(examiner.examinerIdProperty().get()).setExaminerFirstName(examiner.examinerFirstNameProperty().get());
            getExaminerByID(examiner.examinerIdProperty().get()).setExaminerLastName(examiner.examinerLastNameProperty().get());
            getExaminerByID(examiner.examinerIdProperty().get()).setUnavailableDates(examiner.unavailableDatesProperty());
            return true;
        }
        return false;
    }

    public ArrayList<Examiner> getAvailableExaminers(String search, Date date) {
        ArrayList<Examiner> result = new ArrayList<Examiner>();
        for (Examiner examiner : getExaminersBySearch(search))
            if (!examiner.unavailableDatesProperty().contains(date))
                result.add(examiner);
        return result;
    }

    public ArrayList<Examiner> getExaminersById(ArrayList<String> examinerIds) {
        ArrayList<Examiner> result = new ArrayList<Examiner>();
        for (Examiner examiner : examiners)
            if (examinerIds.contains(examiner.examinerIdProperty().get()))
                result.add(examiner);
        return result;
    }
}