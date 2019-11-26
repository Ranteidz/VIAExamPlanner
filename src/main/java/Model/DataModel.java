package model;

import model.Beans.Student;
import model.Dao.StudentDao;
import java.util.ArrayList;

public class DataModel
{
  public String connectionUrl = "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";

  static public ArrayList<Student> getStudentAll() {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudents();
  }

  static public ArrayList<Student> getStudentByName(String studentName) {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudentsByName(studentName);

  }

  static public ArrayList<Student> getStudentBySurname(String surname) {
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudentsBySurname(surname);


  }


}
