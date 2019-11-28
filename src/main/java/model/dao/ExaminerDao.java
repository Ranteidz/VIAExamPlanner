package model.dao;

import model.DataModel;
import model.beans.Examiner;


import java.sql.*;
import java.util.ArrayList;

public class ExaminerDao
{
  public ArrayList<Examiner> getStudents()
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
    examiner.setStudentID(rs.getInt("ID"));
    examiner.setStudentFirstName(rs.getString("Name"));
    examiner.setStudentLastName(rs.getString("Surname"));
  }
}