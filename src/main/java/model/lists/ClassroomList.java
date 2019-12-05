package model.lists;

import model.DataModel;
import model.classes.Classroom;
import java.sql.*;
import java.util.ArrayList;

public class ClassroomList {

    private ArrayList<Classroom> classrooms;

    public ClassroomList() {
        loadClassrooms();
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    private void process(ResultSet rs, Classroom classroom) throws SQLException {
        classroom.setName(rs.getString("ID"));
        classroom.setCapacity(rs.getInt("Capacity"));
        classroom.setHdmi(rs.getBoolean("HasHDMI"));
        classroom.setVga(rs.getBoolean("HasVGA"));
    }

    private void loadClassrooms() {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
             Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.Classrooms";
            ResultSet rs = stmt.executeQuery(SQL);

            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Classroom tmpClassroom = new Classroom();
                process(rs, tmpClassroom);
                classrooms.add(tmpClassroom);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        this.classrooms = classrooms;
    }

    public void insertClassroom(Classroom newClassroom) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Classrooms (ID, Capacity, HasHDMI, HasVGA)" + " values(?, ?, ?, ?)");
            posted.setString(1, newClassroom.nameProperty().get());
            posted.setString(2, String.valueOf(newClassroom.capacityProperty().get()));
            posted.setString(3, String.valueOf(newClassroom.hdmiProperty().get() ? 1 : 0));
            posted.setString(4, String.valueOf(newClassroom.vgaProperty().get() ? 1 : 0));
            posted.execute();
            classrooms.add(newClassroom);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeClassroom(Classroom newClassroom) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Classrooms WHERE ID= ?");
            posted.setString(1, (newClassroom.nameProperty().get()));
            posted.executeUpdate();
            classrooms.remove(newClassroom);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}