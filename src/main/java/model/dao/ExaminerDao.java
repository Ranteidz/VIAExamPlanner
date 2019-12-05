package model.dao;

import model.DataModel;
import model.beans.Examiner;
import model.beans.Date;

import java.sql.*;
import java.util.ArrayList;

public class ExaminerDao
{
  public ArrayList<Examiner> getExaminers()
  {
    ArrayList<Examiner> Examiners = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
        Statement stmt = con.createStatement())
    {
      String SQL = "SELECT * FROM dbo.Examiners";
      ResultSet rs = stmt.executeQuery(SQL);

      // Iterate through the data in the result set and display it.
      while (rs.next())
      {
        Examiner tmpExaminer = new Examiner();
        process(rs, tmpExaminer);
        Examiners.add(tmpExaminer);
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return Examiners;
  }

  private void process(ResultSet rs, Examiner examiner) throws SQLException
  {
    // Examiner
    examiner.setExaminerId(rs.getString("ID"));
    examiner.setExaminerFirstName(rs.getString("Name"));
    examiner.setExaminerLastName(rs.getString("Surname"));
  }

  public void insertExaminer(Examiner newExaminer)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("INSERT INTO Examiners (ID, Name, Surname)"+ " values(?, ?, ?)");
      posted.setString(1,newExaminer.examinerIdProperty().get());
      posted.setString(2,newExaminer.examinerFirstNameProperty().get());
      posted.setString(3,newExaminer.examinerLastNameProperty().get());
      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public void removeExaminer(Examiner newExaminer)
  {
    try{
    Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
    PreparedStatement posted = con.prepareStatement("DELETE FROM Examiners WHERE ID= ?");
    posted.setString(1,(newExaminer.examinerIdProperty().get()));

    posted.executeUpdate();

  }
  catch (Exception e){
    System.out.println(e);
  }
  }

  public void insertUnavailabilityToExaminer(Examiner newExaminer, Date newDate)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("INSERT INTO ExaminersUnavailabilityDates (ExaminerID, Date)"+ " values(?, ?)");
      posted.setString(1, newExaminer.examinerIdProperty().get());
      posted.setString(2, newDate.dateProperty().get());

      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }
//TODO this
  public void changeExaminer(Examiner newExaminer)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("UPDATE Examiners SET Name = ?, Surname = ? WHERE id = ?");
      posted.setString(1,(newExaminer.examinerFirstNameProperty().get()));
      posted.setString(2,newExaminer.examinerLastNameProperty().get());
      posted.setString(3,(newExaminer.examinerIdProperty().get()));

      posted.executeUpdate();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public void removeUnavailabilityFromExaminer(Examiner newExaminer, Date newDate)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("DELETE FROM ExaminersUnavailabilityDates WHERE ExaminerID=? AND Date=?");
      posted.setString(1, newExaminer.examinerIdProperty().get());
      posted.setString(2, newDate.dateProperty().get());

      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public ArrayList<Date> getExaminerDates(String examinerID)
  {
    ArrayList<Date> dates = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString())) {
      String SQL = "SELECT * FROM dbo.ExaminersUnavailabilityDates WHERE ID IN (SELECT Date FROM dbo.ExaminersUnavailabilityDates WHERE ExaminerID = ?) ";
      PreparedStatement preparedStatement
          = con.prepareStatement(SQL);

      preparedStatement.setString(1, examinerID);
      ResultSet rs = preparedStatement.executeQuery();

      while (rs.next()) {
        Date tmpDate = new Date();
        processDate(rs, tmpDate);
        dates.add(tmpDate);
      }

    }

    catch (SQLException e) {
      e.printStackTrace();
    }
    return dates;
  }
  private void processDate(ResultSet rs, Date date) throws SQLException
  {
    // Date
    /*date.setExaminerId(rs.getString("ID"));*/
date.getDate(rs.getDate("Date"));
  }
}
