package model.lists;

import model.classes.Exam;

import java.util.ArrayList;

public class ExamList {
    private ArrayList<Exam> exams;

    public ExamList() {
        exams=new ArrayList<Exam>();
    }

    public void loadExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public boolean addExam(Exam exam) {
        if(!exams.contains(exam)){
            exams.add(exam);
            return true;
        }
        return false;
    }

    public boolean removeCourse(Exam exam) {
        if(exams.contains(exam)) {
            exams.remove(exam);
            return true;
        }
        return false;
    }
}
