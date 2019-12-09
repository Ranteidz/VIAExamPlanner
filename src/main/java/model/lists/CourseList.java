package model.lists;

import model.classes.Course;

import java.util.ArrayList;

public class CourseList {
    private ArrayList<Course> courses;

    public CourseList() {
        courses = new ArrayList<Course>();
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void addAll(ArrayList<Course> courses) {
        for(Course course : courses) {
            this.courses.add(course);
        }
    }

    private void loadCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public boolean addCourse(Course course) {
        if(!courses.contains(course)) {
            courses.add(course);
            return true;
        }
        return false;
    }

  /*  public void insertStudentToCourse(Course course, Student student) {
        try {
            Connection con = DriverManager.getConnection(DataModel.getDatabaseConnectionString());
            PreparedStatement posted = con.prepareStatement("INSERT INTO Students_Courses (StudentID, CourseID)" + " values(?, ?)");
            posted.setString(1, String.valueOf(student.studentIdProperty().get()));
            posted.setString(2, course.courseIdProperty().get());
            posted.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }*/

    public boolean removeCourse(Course course) {
        if(courses.contains(course)){
            courses.remove(course);
            return true;
        }
        return false;
    }
}
