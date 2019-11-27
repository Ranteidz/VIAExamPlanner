package planner;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import model.beans.Student;
import model.dao.StudentDao;
import model.DataModel;

public class PrimaryController {

private DataModel model;

    public void setModel(DataModel model)
    {
        this.model = model;
    }

    public void MethodTesting(ActionEvent actionEvent)
    {
        System.out.println("test");
        StudentDao dao = new StudentDao();
        ArrayList<Student> students = DataModel.getStudentAll();

        for(Student member : students){
            System.out.println(member);
        }
    }
}
