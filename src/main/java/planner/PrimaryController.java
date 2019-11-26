package planner;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Beans.Student;
import model.Dao.StudentDao;
import model.DataModel;

public class PrimaryController {

private DataModel model;

    public void setModel(DataModel model)
    {
        this.model = model;
    }

    public void MethodTesting(ActionEvent actionEvent)
    {
        StudentDao dao = new StudentDao();
        ArrayList<Student> students = DataModel.getStudentAll();

        for(Student member : students){
            System.out.println(member);
        }
    }
}
