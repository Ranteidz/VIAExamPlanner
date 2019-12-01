package model.dao;

import model.DataModel;

import model.beans.ClassRoom;
import java.sql.*;
import java.util.ArrayList;

public class ClassRoomDao
{
  public ArrayList<ClassRoom> getClassRooms()
  {
    ArrayList<ClassRoom> ClassRooms = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
        Statement stmt = con.createStatement())
    {
      String SQL = "SELECT * FROM dbo.Classrooms";
      ResultSet rs = stmt.executeQuery(SQL);

      // Iterate through the data in the result set and display it.
      while (rs.next())
      {
        ClassRoom tmpClassRoom = new ClassRoom();
        process(rs, tmpClassRoom);
        ClassRooms.add(tmpClassRoom);
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return ClassRooms;
  }

  private void process(ResultSet rs, ClassRoom classRoom) throws SQLException
  {
    // Classroom
    classRoom.setName(rs.getString("ID"));
    classRoom.setCapacity(rs.getInt("Capacity"));
    classRoom.setHdmi(rs.getBoolean("HasHDMI"));
    classRoom.setVga(rs.getBoolean("HasVGA"));
  }


}