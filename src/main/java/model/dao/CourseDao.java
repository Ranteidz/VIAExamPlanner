package model.dao;

import model.DataModel;
import model.beans.Course;

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
  }


