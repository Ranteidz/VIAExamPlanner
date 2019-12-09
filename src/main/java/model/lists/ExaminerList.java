package model.lists;

import model.classes.Examiner;

import java.util.ArrayList;

public class ExaminerList {
    private ArrayList<Examiner> examiners;

    public ExaminerList() {
        examiners = new ArrayList<Examiner>();
    }

    public ArrayList<Examiner> getExaminers() {
        return examiners;
    }

    private void loadExaminers(ArrayList<Examiner> examiners) {
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
        if(!examiners.contains(examiner)) {
            examiners.add(examiner);
            return true;
        }
        return false;
    }

    public void addAll(ArrayList<Examiner> examiners) {
        for(Examiner examiner : examiners) {
            this.examiners.add(examiner);
        }
    }

    public boolean removeExaminer(Examiner examiner) {
        if(examiners.contains(examiner)) {
            examiners.remove(examiner);
            return true;
        }
        return false;
    }

    //TODO fix this
    /*public void insertUnavailabilityToExaminer(Examiner newExaminer, model.classes.Date newDate) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO ExaminersUnavailabilityDates (ExaminerID, Date)" + " values(?, ?)");
            posted.setString(1, newExaminer.examinerIdProperty().get());
            posted.setString(2, newDate.dateProperty().get());
            posted.execute();
            //TODO edit locally
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeUnavailabilityFromExaminer(Examiner newExaminer, model.classes.Date newDate) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM ExaminersUnavailabilityDates WHERE ExaminerID=? AND Date=?");
            posted.setString(1, newExaminer.examinerIdProperty().get());
            posted.setString(2, newDate.dateProperty().get());
            posted.execute();
            //TODO edit locally
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private ArrayList<model.classes.Date> getExaminerDates(String examinerID) {
        ArrayList<model.classes.Date> dates = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString())) {
            String SQL = "SELECT Date FROM dbo.ExaminersUnavailabilityDates WHERE ExaminerID=? ";
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, examinerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                java.sql.Date date = rs.getDate("Date");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                dates.add(new model.classes.Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public void editExaminer(Examiner newExaminer) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("UPDATE Examiners SET Name = ?, Surname = ? WHERE id = ?");
            posted.setString(1, (newExaminer.examinerFirstNameProperty().get()));
            posted.setString(2, newExaminer.examinerLastNameProperty().get());
            posted.setString(3, (newExaminer.examinerIdProperty().get()));
            examiners.remove(getExaminerByID(newExaminer.examinerIdProperty().get()));
            examiners.add(newExaminer);
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/
}

