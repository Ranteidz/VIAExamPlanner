package model.lists;

import model.classes.Classroom;
import model.classes.Date;
import model.classes.Exam;

import java.util.ArrayList;
import java.util.Collection;
/**
 * Holds all exams and method to add, edit, cancel and get exams.
 */
public class ExamList {
    private ArrayList<Exam> exams;

    /**
     * Creates new ExamList object.
     */
    public ExamList() {
        exams = new ArrayList<Exam>();
    }

    /**
     * Returns all exams
     * @return list of all exams
     */
    public ArrayList<Exam> getExams(){
        return exams;
    }

    /**
     * Loads all exams into object, overwrites existing ones.
     * @param exams exams to be loaded
     */
    public void loadExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    /**
     * Adds an exam to object if it does not exist.
     * @param exam exam to be added
     * @return true if removed successfully
     */
    public boolean addExam(Exam exam) {
        if (!exams.contains(exam)) {
            exams.add(exam);
            return true;
        }
        return false;
    }

    /**
     * Removes and exam from object if it exists.
     * @param exam exam to be removed
     * @return true if removed successfully
     */
    public boolean removeExam(Exam exam) {
        if (exams.contains(exam)) {
            exams.remove(exam);
            return true;
        }
        return false;
    }

    /**
     * Returns a list of all exams booked on a specific date
     * @param date date
     * @return exams that meet condition
     */
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

    /**
     * Returns a list of IDs of the classrooms that are booked
     * @return list of classroom IDs (String)
     */
    public ArrayList<String> getReservedClassroomsIDs() {
        ArrayList<String> classrooms = new ArrayList<String>();
        for(Exam exam : exams)
            classrooms.add(exam.classroomIdProperty().get());
        return classrooms;
    }

    /**
     * Returns a list of IDs of the classrooms that are booked by the exams sent as parameter
     * @param exams list of exams to extract reserved classroom from
     * @return list of classroom IDs (String)
     */
    public ArrayList<String> getReservedClassroomsIDs(ArrayList<Exam> exams) {
        ArrayList<String> classrooms = new ArrayList<String>();
        for(Exam exam : exams)
            classrooms.add(exam.classroomIdProperty().get());
        return classrooms;
    }

    /**
     * Returns a list of exams whose ID contain the parameter ID
     * @param examId ID of exam
     * @return list of exams that meet condition
     */
    public Exam getExamById(String examId) {
        for(Exam exam : exams)
            if(exam.courseIdProperty().get().toLowerCase().equals(examId.toLowerCase()))
                return exam;
            return null;
    }

    /**
     * Edits exam
     * @param exam edited exam
     */
    public void editExam(Exam exam) {
        Exam localExam = getExamById(exam.courseIdProperty().get());
        if(exams.contains(localExam)) {
            localExam.setExaminerId(exam.examinerIdProperty().get());
            localExam.setClassroomId(exam.classroomIdProperty().get());
            localExam.setCoexaminerType(exam.coexaminerTypeProperty().get());
            localExam.setCoexaminerName(exam.coexaminerNameProperty().get());
        }
    }

    /**
     * Returns a list of all examiner IDs that are assigned to exams
     * @return list of examiner IDs (String)
     */
    public ArrayList<String> getExaminerIds() {
        ArrayList<String> result = new ArrayList<String>();
        for(Exam exam : exams) {
            result.add(exam.examinerIdProperty().get());
        }
        return result;
    }
}
