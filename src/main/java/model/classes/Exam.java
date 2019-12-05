package model.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Exam {
    private Date examDate;
    private StringProperty courseId = new SimpleStringProperty();
    private StringProperty classroomId = new SimpleStringProperty();
    private StringProperty examinerId = new SimpleStringProperty();
    private StringProperty coexaminerType = new SimpleStringProperty();
    private StringProperty coexaminerName = new SimpleStringProperty();

    public Exam(Date examDate, String courseId, String classroomId, String examinerId, String coexaminerType, String coexaminerName) {
        this.examDate = examDate;
        this.courseId.set(courseId);
        this.classroomId.set(classroomId);
        this.examinerId.set(examinerId);
        this.coexaminerType.set(coexaminerType);
        this.coexaminerName.set(coexaminerName);
    }

    public Exam(Date examDate, String courseId, String classroomId, String examinerId, String coexaminerType) {
        this.examDate = examDate;
        this.courseId.set(courseId);
        this.classroomId.set(classroomId);
        this.examinerId.set(examinerId);
        this.coexaminerType.set(coexaminerType);
        this.coexaminerName.set(null);
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setCourseId(String courseId) {
        this.courseId.set(courseId);
    }

    public void setClassroomId(String classroomId) {
        this.classroomId.set(classroomId);
    }

    public void setExaminerId(String examienrId) {
        this.examinerId.set(examienrId);
    }

    public void setCoexaminerType(String coexaminerType) {
        this.coexaminerType.set(coexaminerType);
    }

    public void setCoexaminerName(String coexaminerName) {
        this.coexaminerName.set(coexaminerName);
    }

    public Date getDate() {
        return new Date(examDate.getDay(), examDate.getMonth(), examDate.getYear());
    }

    public StringProperty examDateProperty() {
        return examDate.dateProperty();
    }

    public StringProperty courseIdProperty() {
        return courseId;
    }

    public StringProperty classroomIdProperty() {
        return classroomId;
    }

    public StringProperty examinerIdProperty() {
        return examinerId;
    }

    public StringProperty coexaminerTypeProperty() {
        return coexaminerType;
    }

    public StringProperty coexaminerNameProperty() {
        return coexaminerName;
    }
}
