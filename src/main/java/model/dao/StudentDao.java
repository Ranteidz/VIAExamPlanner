package model.dao;

import model.beans.Student;
import model.DataModel;

import java.sql.*;
import java.util.ArrayList;

public class StudentDao extends DataModel
{
  // Create a variable for the connection string.


  /**
   * Get All Students
   * @return Array list with Students
   */
  public ArrayList<Student> getStudents() {
    ArrayList<Student> Students = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString()); Statement stmt = con.createStatement()) {
      String SQL = "SELECT * FROM dbo.Students";
      ResultSet rs = stmt.executeQuery(SQL);

      // Iterate through the data in the result set and display it.
      while (rs.next()) {
        Student tmpStudent = new Student();
        process(rs, tmpStudent);
        Students.add(tmpStudent);
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e) {
      e.printStackTrace();
    }
    return Students;
  }

  /**
   * Get Students By Name
   * @param studentName String
   * @return Array List with Students
   */
  public ArrayList<Student> getStudentsByName(String studentName) {

    ArrayList<Student> Students = new ArrayList<>();


    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString())) {
      String SQL = "SELECT * FROM dbo.Students WHERE Name=? ";
      PreparedStatement preparedStatement
          = con.prepareStatement(SQL);

      preparedStatement.setString(1, studentName);
      ResultSet rs = preparedStatement.executeQuery();

      // Iterate through the data in the result set and display it.
      while (rs.next()) {
        Student tmpStudent = new Student();
        process(rs, tmpStudent);
        Students.add(tmpStudent);
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e) {
      e.printStackTrace();
    }
    return Students;
  }

  /**
   * Add New Student into the Database
   * @param newStudent
   */
  public void insertStudent(Student newStudent){

  }


  /**
   * Get Students By Name
   * @param studentSurname String
   * @return Array List with Students
   */
  public ArrayList<Student> getStudentsBySurname(String studentSurname) {

    ArrayList<Student> Students = new ArrayList<>();

    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString())) {
      String SQL = "SELECT * FROM dbo.Students WHERE Surname=? ";
      PreparedStatement preparedStatement
          = con.prepareStatement(SQL);

      preparedStatement.setString(1, studentSurname);
      ResultSet rs = preparedStatement.executeQuery();

      // Iterate through the data in the result set and display it.
      while (rs.next()) {
        Student tmpStudent = new Student();
        process(rs, tmpStudent);
        Students.add(tmpStudent);
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e) {
      e.printStackTrace();
    }
    return Students;
  }

  /**
   *
   * @param rs Recordset from SQL query
   * @param student Student Object
   * @throws SQLException Exception
   */
  private void process(ResultSet rs, Student student) throws SQLException {
    // Student
    student.setStudentID(rs.getInt("ID"));
    student.setStudentFirstName(rs.getString("Name"));
    student.setStudentLastName(rs.getString("Surname"));
  }
}

