package model;

import javafx.scene.control.TableView;
import model.beans.Examiner;
import model.beans.Student;
import model.dao.ExaminerDao;
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
//This will probably be  unnecessary in the future, but just in case.
public static void createCourseTable() throws Exception
{
  try
  {
    Connection con = DriverManager.getConnection(getDatabaseConnectionString());
    PreparedStatement create = con.prepareStatement(
        "CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL,PRIMARY KEY(id))");
    create.executeUpdate();
  }
  catch (Exception e)
  {
    System.out.println(e);
  }
  finally
  {
    System.out.println("Worked");
  }
}
//var1,2,3 need to be changed to GUI Student add variables
    public static void post() throws Exception{
      final int var1 = 12;
      final String var2 = "Testinski";
      final String var3 = "Testovic";
      try{
        Connection con = DriverManager.getConnection(getDatabaseConnectionString());
        PreparedStatement posted = con.prepareStatement("INSERT INTO Students (ID, Name, Surname) VALUES ('"+var1+"', '"+var2+"', '"+var3+"')");
        posted.executeUpdate();
      }
      catch (Exception e){
        System.out.println(e);
      }

  }

  static public ArrayList<Student> getStudentAll()
  {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudents();
  }
  static public ArrayList<Examiner> getExaminersALL()
  {
    ExaminerDao examinerDao = new ExaminerDao();
    return examinerDao= new ExaminerDao();
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




}
