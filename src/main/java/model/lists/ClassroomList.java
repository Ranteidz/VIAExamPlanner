package model.lists;

import model.classes.Classroom;

import java.util.ArrayList;

public class ClassroomList {

    private ArrayList<Classroom> classrooms;

    public ClassroomList() {
        classrooms = new ArrayList<Classroom>();
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    private void loadClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public void addAll(ArrayList<Classroom> classrooms) {
        for(Classroom classroom : classrooms) {
            this.classrooms.add(classroom);
        }
    }

    public boolean addClassroom(Classroom classroom) {
        if(!classrooms.contains(classroom)) {
            classrooms.add(classroom);
            return true;
        }
        return false;
    }

    public boolean removeClassroom(Classroom classroom) {
        if(classrooms.contains(classroom)){
            classrooms.remove(classroom);
            return true;
        }
        return false;
    }
}