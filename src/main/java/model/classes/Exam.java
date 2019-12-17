package model.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a scheduled Exam
 */
public class Exam {
    private Date examDate;
    private StringProperty courseId = new SimpleStringProperty();
    private StringProperty classroomId = new SimpleStringProperty();
    private StringProperty examinerId = new SimpleStringProperty();
    private StringProperty coexaminerType = new SimpleStringProperty();
    private StringProperty coexaminerName = new SimpleStringProperty();

    /**
     * Creates a new Exam object in memory.
     * @param examDate Date of the exam, Date object
     * @param courseId ID of assigned course
     * @param classroomId ID of assigned classroom
     * @param examinerId ID of assigned examiner
     * @param coexaminerType Type of co-examiner (Internal/External)
     * @param coexaminerName Name of co-examiner, used when the examiner is external
     */
    public Exam(Date examDate, String courseId, String classroomId, String examinerId, String coexaminerType, String coexaminerName) {
        this.examDate = examDate;
        this.courseId.set(courseId);
        this.classroomId.set(classroomId);
        this.examinerId.set(examinerId);
        this.coexaminerType.set(coexaminerType);
        this.coexaminerName.set(coexaminerName);
    }

    /**
     * Creates a new Exam object in memory.
     * @param examDate Date of the exam, Date object
     * @param courseId ID of assigned course
     * @param classroomId ID of assigned classroom
     * @param examinerId ID of assigned examiner
     * @param coexaminerType Type of co-examiner (Internal/External)
     */
    public Exam(Date examDate, String courseId, String classroomId, String examinerId, String coexaminerType) {
        this.examDate = examDate;
        this.courseId.set(courseId);
        this.classroomId.set(classroomId);
        this.examinerId.set(examinerId);
        this.coexaminerType.set(coexaminerType);
        this.coexaminerName.set(null);
    }

    /**
     * Constructs new empty Exam object in memory.
     */
    public Exam(){}

    /**
     * Sets the exam date.
     * @param examDate date
     */
    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    /**
     * Sets the course ID.
     * @param courseId course ID
     */
    public void setCourseId(String courseId) {
        this.courseId.set(courseId);
    }

    /**
     * Sets the classroom ID.
     * @param classroomId classroom ID
     */
    public void setClassroomId(String classroomId) {
        this.classroomId.set(classroomId);
    }

    /**
     * Sets the examiner ID.
     * @param examienrId examiner ID
     */
    public void setExaminerId(String examienrId) {
        this.examinerId.set(examienrId);
    }

    /**
     * Sets the co-examiner type.
     * @param coexaminerType co-examiner type.
     */
    public void setCoexaminerType(String coexaminerType) {
        this.coexaminerType.set(coexaminerType);
    }

    /**
     * Sets the co-examiner name.
     * @param coexaminerName co-examiner name
     */
    public void setCoexaminerName(String coexaminerName) {
        this.coexaminerName.set(coexaminerName);
    }

    /**
     * Returns the date the exam is booked on.
     * @return date of exam
     */
    public Date getDate() {
        return new Date(examDate.getDay(), examDate.getMonth(), examDate.getYear());
    }

    /**
     * Returns date of exam as StringProperty.
     * @see Date
     * @return date as StringProperty
     */
    public StringProperty examDateProperty() {
        return examDate.dateProperty();
    }

    /**
     * Returns course ID  for exam as StringProperty.
     * @return coruse ID as StringProperty
     */
    public StringProperty courseIdProperty() {
        return courseId;
    }

    /**
     * Returns classroom ID for exam as StringProperty.
     * @return classroom ID as StringProperty
     */
    public StringProperty classroomIdProperty() {
        return classroomId;
    }

    /**
     * Returns examiner ID for exam as StringProperty.
     * @return examiner ID as StringProperty
     */
    public StringProperty examinerIdProperty() {
        return examinerId;
    }

    /**
     * Returns the type of co-examiner as StringProperty.
     * @return Internal/External co-examiner type
     */
    public StringProperty coexaminerTypeProperty() {
        return coexaminerType;
    }

    /**
     * Returns the name of co-examiner as StringProperty
     * @return null if type is Internal, name of co-examiner if type is External
     */
    public StringProperty coexaminerNameProperty() {
        return coexaminerName;
    }

    public String toString() {
        return String.format("%s %s %s", examDate, courseId.get(), classroomId.get());
    }
}
