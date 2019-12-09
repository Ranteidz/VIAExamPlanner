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

    public void loadClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public boolean addClassroom(Classroom classroom) {
        if(!classrooms.contains(classroom)) {
            classrooms.add(classroom);
            return true;
        }
        return false;
    }

    public Classroom getClassroomById(String classroomId) {
        for(Classroom classroom : classrooms)
            if(classroomId.equalsIgnoreCase(classroom.nameProperty().get()))
                return classroom;
            return null;
    }

    public boolean removeClassroom(Classroom classroom) {
        if(classrooms.contains(classroom)){
            classrooms.remove(classroom);
            return true;
        }
        return false;
    }

    public boolean editClassroom(Classroom classroom) {
        if(classrooms.contains(getClassroomById(classroom.nameProperty().get()))) {
            getClassroomById(classroom.nameProperty().get()).setCapacity(classroom.capacityProperty().get());
            getClassroomById(classroom.nameProperty().get()).setHdmi(classroom.hdmiProperty().get());
            getClassroomById(classroom.nameProperty().get()).setVga(classroom.vgaProperty().get());
            return true;
        }
        return false;
    }
}