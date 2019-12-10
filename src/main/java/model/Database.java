package model;

import model.classes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Database implements Persistence {
    public Database() {
    }

    private String getDatabaseConnectionString() {
        return "jdbc:sqlserver://planner.database.windows.net:1433;database=ExaminationPlanner;user=databaseadmin@planner;password=Pass-2019;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30";
    }

    private void process(ResultSet rs, Examiner examiner) throws SQLException {
        examiner.setExaminerId(rs.getString("ID"));
        examiner.setExaminerFirstName(rs.getString("Name"));
        examiner.setExaminerLastName(rs.getString("Surname"));
    }

    private void process(ResultSet rs, Classroom classroom) throws SQLException {
        classroom.setName(rs.getString("ID"));
        classroom.setCapacity(rs.getInt("Capacity"));
        classroom.setHdmi(rs.getBoolean("HasHDMI"));
        classroom.setVga(rs.getBoolean("HasVGA"));
    }

    private void process(ResultSet rs, Student student) throws SQLException {
        student.setStudentID(rs.getInt("ID"));
        student.setStudentFirstName(rs.getString("Name"));
        student.setStudentLastName(rs.getString("Surname"));
    }

    private void process(ResultSet rs, Course courses) throws SQLException {
        courses.setCourseId(rs.getString("ID"));
        courses.setCourseType(rs.getString("Type"));
    }

    public void save() {
        //this can be used to save to files when using local files
    }

    public void save(Course course) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Courses (ID, Type)" + " values(?, ?)");
            posted.setString(1, course.courseIdProperty().get());
            posted.setString(2, course.courseTypeProperty().get());
            posted.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save(Exam exam) {
        //implement insert exam
    }

    public void save(Examiner examiner) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Examiners (ID, Name, Surname)" + " values(?, ?, ?)");
            posted.setString(1, examiner.examinerIdProperty().get());
            posted.setString(2, examiner.examinerFirstNameProperty().get());
            posted.setString(3, examiner.examinerLastNameProperty().get());
            posted.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save(Student student) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Students (ID, Name, Surname)" + " values(?, ?, ?)");
            posted.setString(1, Integer.toString(student.studentIdProperty().get()));
            posted.setString(2, student.studentFirstNameProperty().get());
            posted.setString(3, student.studentLastNameProperty().get());
            posted.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void save(Classroom classroom) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Classrooms (ID, Capacity, HasHDMI, HasVGA)" + " values(?, ?, ?, ?)");
            posted.setString(1, classroom.nameProperty().get());
            posted.setString(2, String.valueOf(classroom.capacityProperty().get()));
            posted.setString(3, String.valueOf(classroom.hdmiProperty().get() ? 1 : 0));
            posted.setString(4, String.valueOf(classroom.vgaProperty().get() ? 1 : 0));
            posted.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Object load(){
        //loads from file in case of use local files
        return null;
    }

    public Object load(String argument) {
        switch (argument) {
            case "Courses":
                return loadCourses();
            case "Exams":
                return loadExams();
            case "Examiners":
                return loadExaminers();
            case "Students":
                return loadStudents();
            case "Classrooms":
                return loadClassrooms();
            default:
                throw new IllegalArgumentException();
        }
    }

    public ArrayList<Course> loadCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(getDatabaseConnectionString());
             Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.Courses";
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Course tmpCourse = new Course();
                process(rs, tmpCourse);
                tmpCourse.studentsProperty().addAll(getStudentsByCourseID(tmpCourse.courseIdProperty().get()));
                courses.add(tmpCourse);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    private ArrayList<Exam> loadExams() {
        return null; //implement select from exams
    }

    public ArrayList<Examiner> loadExaminers() {
        ArrayList<Examiner> examiners = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(getDatabaseConnectionString());
             Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.examiners";
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Examiner tmpExaminer = new Examiner();
                process(rs, tmpExaminer);
                for (model.classes.Date date : getExaminerDates(tmpExaminer.examinerIdProperty().get()))
                    tmpExaminer.addUnavailableDate(date);
                examiners.add(tmpExaminer);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        return examiners;
    }

    private ArrayList<model.classes.Date> getExaminerDates(String examinerID) {
        ArrayList<model.classes.Date> dates = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(getDatabaseConnectionString())) {
            String SQL = "SELECT Date FROM dbo.ExaminersUnavailabilityDates WHERE ExaminerID=? ";
            PreparedStatement preparedStatement = con.prepareStatement(SQL);
            preparedStatement.setString(1, examinerID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                java.sql.Date date = rs.getDate("Date");
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                dates.add(new model.classes.Date(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dates;
    }

    public ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(getDatabaseConnectionString()); Statement stmt = con.createStatement()) {
            String SQL = "SELECT * FROM dbo.students";
            ResultSet rs = stmt.executeQuery(SQL);
            // Iterate through the data in the result set and display it.
            while (rs.next()) {
                Student tmpStudent = new Student();
                process(rs, tmpStudent);
                students.add(tmpStudent);
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public ArrayList<Classroom> loadClassrooms() {
        ArrayList<Classroom> classrooms = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(getDatabaseConnectionString());
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
        return classrooms;
    }

    public void removeExaminer(Examiner examiner) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Examiners WHERE ID= ?");
            posted.setString(1, (examiner.examinerIdProperty().get()));
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeClassroom(Classroom classroom) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Classrooms WHERE ID= ?");
            posted.setString(1, (classroom.nameProperty().get()));
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeStudent(Student student) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Students WHERE ID= ?");
            posted.setString(1, Integer.toString(student.studentIdProperty().get()));
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE  FROM Students_Courses WHERE StudentID= ?");
            posted.setString(1, Integer.toString(student.studentIdProperty().get()));
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeCourse(Course course) {
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Courses WHERE ID= ?");
            posted.setString(1, course.courseIdProperty().get());
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            Connection con = DriverManager.getConnection(getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE  FROM Students_Courses WHERE CourseID= ?");
            posted.setString(1, course.courseIdProperty().get());
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editExaminer(Examiner examiner) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("UPDATE Examiners SET Name = ?, Surname = ? WHERE id = ?");
            posted.setString(1, (examiner.examinerFirstNameProperty().get()));
            posted.setString(2, examiner.examinerLastNameProperty().get());
            posted.setString(3, (examiner.examinerIdProperty().get()));
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editStudent(Student student) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("UPDATE Students SET Name = ?, Surname = ? WHERE id = ?");
            posted.setString(1, student.studentFirstNameProperty().get());
            posted.setString(2, student.studentLastNameProperty().get());
            posted.setString(3, String.valueOf(student.studentIdProperty().get()));
            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editClassroom(Classroom classroom) {
        removeClassroom(classroom);
        save(classroom);
        //TODO change this to UPDATE query
    }

    public void editCourse(Course course) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("UPDATE Courses SET Type= ?");
            posted.setString(1, course.courseTypeProperty().get());

            posted.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insertStudentToCourse(Course course, Student student) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Students_Courses (StudentID, CourseID)" + " values(?, ?)");
            posted.setString(1, String.valueOf(student.studentIdProperty().get()));
            posted.setString(2, course.courseIdProperty().get());
            posted.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeStudentFromCourse(Course course, Student student){
        try{
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("DELETE FROM Students_Courses WHERE StudentID=? AND CourseID=?");
            posted.setString(1, course.courseIdProperty().get());
            posted.setString(2,String.valueOf(student.studentIdProperty().get()));

            posted.execute();

        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<Student> getStudentsByCourseID(String courseId) {
        ArrayList<Student> students = new ArrayList<Student>();
        try (Connection con = DriverManager.getConnection(getDatabaseConnectionString())) {
            String SQL = "SELECT * FROM dbo.Students WHERE ID IN (SELECT StudentID FROM dbo.Students_Courses WHERE CourseID = ?) ";
            PreparedStatement preparedStatement
                    = con.prepareStatement(SQL);

            preparedStatement.setString(1, courseId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Student tmpStudent = new Student();
                process(rs, tmpStudent);
                students.add(tmpStudent); //TODO maybe make this work locally, needs connection to model
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}
