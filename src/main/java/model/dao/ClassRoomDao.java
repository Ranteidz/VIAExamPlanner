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

  public void insertClassRoom(ClassRoom newClassRoom)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("INSERT INTO Classrooms (ID, Capacity, HasHDMI, HasVGA)"+ " values(?, ?, ?, ?)");
      posted.setString(1,newClassRoom.nameProperty().get());
      posted.setString(2, String.valueOf(newClassRoom.capacityProperty().get()));
      posted.setString(3, String.valueOf(newClassRoom.hdmiProperty().get()?1:0));
      posted.setString(4,String.valueOf(newClassRoom.vgaProperty().get()?1:0));
      posted.execute();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }

  public void removeClassRoom(ClassRoom newClassRoom)
  {
    try{
      Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
      PreparedStatement posted = con.prepareStatement("DELETE FROM Classrooms WHERE ID= ?");
      posted.setString(1,(newClassRoom.nameProperty().get()));

      posted.executeUpdate();

    }
    catch (Exception e){
      System.out.println(e);
    }
  }
}
