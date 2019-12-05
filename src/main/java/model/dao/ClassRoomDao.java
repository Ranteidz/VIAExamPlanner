package model.dao;

import model.DataModel;

import model.classes.Classroom;
import java.sql.*;
import java.util.ArrayList;

public class ClassRoomDao
{
  public ArrayList<Classroom> getClassRooms()
  {
    ArrayList<Classroom> classrooms = new ArrayList<>();
    try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
        Statement stmt = con.createStatement())
    {
      String SQL = "SELECT * FROM dbo.Classrooms";
      ResultSet rs = stmt.executeQuery(SQL);

      // Iterate through the data in the result set and display it.
      while (rs.next())
      {
        Classroom tmpClassroom = new Classroom();
        process(rs, tmpClassroom);
        classrooms.add(tmpClassroom);
      }
    }
    // Handle any errors that may have occurred.
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return classrooms;
  }

  private void process(ResultSet rs, Classroom classRoom) throws SQLException
  {
    // Classroom
    classRoom.setName(rs.getString("ID"));
    classRoom.setCapacity(rs.getInt("Capacity"));
    classRoom.setHdmi(rs.getBoolean("HasHDMI"));
    classRoom.setVga(rs.getBoolean("HasVGA"));
  }

  public void insertClassRoom(Classroom newClassroom)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("INSERT INTO Classrooms (ID, Capacity, HasHDMI, HasVGA)"+ " values(?, ?, ?, ?)");
      posted.setString(1, newClassroom.nameProperty().get());
      posted.setString(2, String.valueOf(newClassroom.capacityProperty().get()));
      posted.setString(3, String.valueOf(newClassroom.hdmiProperty().get()?1:0));
      posted.setString(4,String.valueOf(newClassroom.vgaProperty().get()?1:0));
      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public void removeClassRoom(Classroom newClassroom)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("DELETE FROM Classrooms WHERE ID= ?");
      posted.setString(1,(newClassroom.nameProperty().get()));

      posted.executeUpdate();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }
}
