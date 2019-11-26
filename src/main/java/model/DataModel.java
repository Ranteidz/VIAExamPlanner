package model;

import model.beans.Student;
import model.dao.StudentDao;
import java.util.ArrayList;

public class DataModel
{
  //public static  String connectionUrl = "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";

  static public String getDatabaseConnectionString(){
    return "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
  }

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
