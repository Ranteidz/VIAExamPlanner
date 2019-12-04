package model.dao;

import model.DataModel;
import model.beans.Course;
import model.beans.Student;

import java.sql.*;
import java.util.ArrayList;

public class CourseDao
{


    public ArrayList<Course> getCourses()
    {
      ArrayList<Course> courses = new ArrayList<>();
      try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
          Statement stmt = con.createStatement())
      {
        String SQL = "SELECT * FROM dbo.Courses";
        ResultSet rs = stmt.executeQuery(SQL);

        // Iterate through the data in the result set and display it.
        while (rs.next())
        {
          Course tmpCourse = new Course();
          process(rs, tmpCourse);
          courses.add(tmpCourse);
        }
      }
      // Handle any errors that may have occurred.
      catch (SQLException e)
      {
        e.printStackTrace();
      }
      return courses;
    }

    private void process(ResultSet rs, Course courses) throws SQLException
    {
      // Course
      courses.setCourseId(rs.getString("ID"));
      courses.setCourseType(rs.getString("Type"));

    }

  public void insertCourse(Course newCourse)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("INSERT INTO Courses (ID, Type)"+ " values(?, ?)");
      posted.setString(1,newCourse.courseIdProperty().get());
      posted.setString(2,newCourse.courseTypeProperty().get());

      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }
  public void insertStudentToCourse(Course newCourse, Student newStudent){
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("INSERT INTO Students_Courses (StudentID, CourseID)"+ " values(?, ?)");
      posted.setString(1, String.valueOf(newStudent.studentIdProperty().get()));
      posted.setString(2,newCourse.courseIdProperty().get());

      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public void removeCourse(Course newCourse)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("DELETE FROM Courses WHERE ID= ?");
      posted.setString(1,newCourse.courseIdProperty().get());

      posted.executeUpdate();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }
}


