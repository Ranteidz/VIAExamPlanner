package model.lists;

import model.classes.Classroom;
import model.classes.Date;
import model.classes.Exam;

import java.util.ArrayList;
import java.util.Collection;

public class ExamList {
    private ArrayList<Exam> exams;

    public ExamList() {
        exams = new ArrayList<Exam>();
    }
    public ArrayList<Exam> getExams(){
        return exams;
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

    public boolean removeExam(Exam exam) {
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
                return exams;
            }
            return this.exams;
        }
        return null;
    }

    public ArrayList<String> getReservedClassroomsIDs() {
        ArrayList<String> classrooms = new ArrayList<String>();
        for(Exam exam : exams)
            classrooms.add(exam.classroomIdProperty().get());
        return classrooms;
    }

    public ArrayList<String> getReservedClassroomsIDs(ArrayList<Exam> exams) {
        ArrayList<String> classrooms = new ArrayList<String>();
        for(Exam exam : exams)
            classrooms.add(exam.classroomIdProperty().get());
        return classrooms;
    }

    public Exam getExamById(String examId) {
        for(Exam exam : exams)
            if(exam.courseIdProperty().get().toLowerCase().equals(examId.toLowerCase()))
                return exam;
            return null;
    }

    public void editExam(Exam exam) {
        Exam localExam = getExamById(exam.courseIdProperty().get());
        if(exams.contains(localExam)) {
            localExam.setExaminerId(exam.examinerIdProperty().get());
            localExam.setClassroomId(exam.classroomIdProperty().get());
            localExam.setCoexaminerType(exam.coexaminerTypeProperty().get());
            localExam.setCoexaminerName(exam.coexaminerNameProperty().get());
        }
    }

    public ArrayList<String> getExaminerIds() {
        ArrayList<String> result = new ArrayList<String>();
        for(Exam exam : exams) {
            result.add(exam.examinerIdProperty().get());
        }
        return result;
    }
}
