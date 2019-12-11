package model.lists;

import model.classes.Classroom;
import model.classes.Date;
import model.classes.Exam;

import java.util.ArrayList;

public class ExamList {
    private ArrayList<Exam> exams;

    public ExamList() {
        exams = new ArrayList<Exam>();
    }

    public void loadExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public boolean addExam(Exam exam) {
        if (!exams.contains(exam)) {
            exams.add(exam);
            return true;
        }
        return false;
    }

    public boolean removeCourse(Exam exam) {
        if (exams.contains(exam)) {
            exams.remove(exam);
            return true;
        }
        return false;
    }

    public ArrayList<Exam> getExamsByDate(Date date) {
        if(!exams.isEmpty()) {
            if (date != null) {
                ArrayList<Exam> exams = new ArrayList<Exam>();
                for (Exam exam : this.exams) {
                    if (exam.getDate().equals(date))
                        exams.add(exam);
                }
                System.out.println("A few exams");
                return exams;
            }
            System.out.println("All exams");
            return this.exams;
        }
        System.out.println("null");
        return null;
    }

    public ArrayList<String> getReservedClassroomsIDs(ArrayList<Exam> exams) {
        ArrayList<String> classrooms = new ArrayList<String>();
        for(Exam exam : exams)
            classrooms.add(exam.classroomIdProperty().get());
        return classrooms;
    }
}
