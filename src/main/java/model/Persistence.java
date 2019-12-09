package model;

import model.classes.*;

public interface Persistence {
    public void save();
    public void save(Course course);
    public void save(Exam exam);
    public void save(Examiner examiner);
    public void save(Student student);
    public void save(Classroom classroom);

    public Object load();
    public Object load(String argument);
}
