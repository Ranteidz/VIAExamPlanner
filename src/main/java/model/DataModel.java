package model;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.beans.ClassRoom;
import model.beans.Course;
import model.beans.Examiner;
import model.beans.Student;
import model.dao.ClassRoomDao;
import model.dao.CourseDao;
import model.dao.ExaminerDao;
import model.dao.StudentDao;
import model.beans.Date;
import planner.PrimaryController;

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
    public static void postStudent() throws Exception{
      final int var1 =2 ;
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

  static public ArrayList<Student> getStudentsByCourse(String courseId){
    StudentDao studentsDAO = new StudentDao();
    return studentsDAO.getStudentsByCourseID(courseId);
  }

  static public void addExaminer(Examiner newExaminer){
    ExaminerDao examinerDao = new ExaminerDao();
    examinerDao.insertExaminer(newExaminer);
}
static public void deleteExaminer(Examiner newExaminer){
    ExaminerDao examinerDao = new ExaminerDao();
    examinerDao.removeExaminer(newExaminer);
}
static public void addClassRoom(ClassRoom newClassRoom){
  ClassRoomDao classRoomDao= new ClassRoomDao();
  classRoomDao.insertClassRoom(newClassRoom);
}
static public void deleteClassRoom(ClassRoom newClassRoom){
    ClassRoomDao classRoomDao = new ClassRoomDao();
    classRoomDao.removeClassRoom(newClassRoom);
}
  static public void addStudent(Student newStudent){
    StudentDao studentsDAO = new StudentDao();
       studentsDAO.insertStudent(newStudent);
  }
  static public void editStudent(Student newStudent){
    StudentDao studentDao = new StudentDao();
    studentDao.changeStudent(newStudent);
  }
  static public void editExaminer(Examiner newExaminer){
    ExaminerDao examinerDao = new ExaminerDao();
    examinerDao.changeExaminer(newExaminer);
  }
  static public Student getStudent(String studentID){
    StudentDao studentDao = new StudentDao();

    return studentDao.getStudentByID(studentID);
  }
  static public void deleteStudent(Student newStudent){
    StudentDao studentDao = new StudentDao();
    studentDao.removeStudent(newStudent);
  }
static public void addCourse(Course newCourse){
  CourseDao courseDao = new CourseDao();
  courseDao.insertCourse(newCourse);
}
static public void addStudentToCourse(Course newCourse,Student newStudent){
    CourseDao courseDao = new CourseDao();
    courseDao.insertStudentToCourse(newCourse,newStudent);
}
static public void addUnavailabilityDateToExaminer(Examiner newExaminer,Date newDate){
    ExaminerDao examinerDao= new ExaminerDao();
    examinerDao.insertUnavailabilityToExaminer(newExaminer,newDate);
}
static public void deleteCourse(Course newCourse){
    CourseDao courseDao = new CourseDao();
    courseDao.removeCourse(newCourse);
}
static public ArrayList<ClassRoom> getClassRoomsAll(){
    ClassRoomDao classRoomDao = new ClassRoomDao();
    return classRoomDao.getClassRooms();
}
  static public ArrayList<Examiner> getExaminersALL()
  {
    ExaminerDao examinerDao = new ExaminerDao();
    return examinerDao.getExaminers();
  }
  static public ArrayList<Course> getCoursesAll(){
    CourseDao courseDao = new CourseDao();
    return courseDao.getCourses();
  }
  static public void addStudentToArray(Student student){
    ArrayList<Student> students = new ArrayList<>();
    students.add(student);

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
