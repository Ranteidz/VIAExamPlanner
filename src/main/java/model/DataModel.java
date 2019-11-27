package model;

import javafx.scene.control.TableView;
import model.beans.Student;
import model.dao.StudentDao;

import java.sql.*;
import java.util.ArrayList;

public class DataModel
{
  //public static  String connectionUrl = "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";

  static public String getDatabaseConnectionString()
  {
    return "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
  }

  static public ArrayList<Student> getStudentAll()
  {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudents();
  }

  static public ArrayList<Student> getStudentByName(String studentName)
  {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudentsByName(studentName);

  }

  static public ArrayList<Student> getStudentBySurname(String surname)
  {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudentsBySurname(surname);

  }

  public ArrayList<Student> studentTable()
  {
    ArrayList<Student> studentList = new ArrayList<>();
    try
    {
      Connection con = DriverManager
          .getConnection(getDatabaseConnectionString());
      String SQL = "Select * FROM Students";
      Statement st = con.createStatement();
      ResultSet rs = st.executeQuery(SQL);
      Student tmpStudent;
      while (rs.next())
      {
        tmpStudent = new Student(rs.getInt("ID"), rs.getString("Name"),
            rs.getString("Surname"));
        studentList.add(tmpStudent);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return studentList;
  }
public void showStudents(){
    ArrayList<Student> studentTable = studentTable();
  TableView tableView = new TableView();
}
}
