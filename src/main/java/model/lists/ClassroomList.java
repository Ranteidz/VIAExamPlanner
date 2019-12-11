package model.lists;

import model.classes.Classroom;
import model.classes.Course;

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
        if (!classrooms.contains(classroom)) {
            classrooms.add(classroom);
            return true;
        }
        return false;
    }

    public Classroom getClassroomById(String classroomId) {
        for (Classroom classroom : classrooms)
            if (classroomId.equalsIgnoreCase(classroom.nameProperty().get()))
                return classroom;
        return null;
    }

    public boolean removeClassroom(Classroom classroom) {
        if (classrooms.contains(classroom)) {
            classrooms.remove(classroom);
            return true;
        }
        return false;
    }

    public ArrayList<Classroom> getClassroomsBySearch(String search) {
        ArrayList<Classroom> searchItems = new ArrayList<Classroom>();
        for (Classroom classroom : classrooms)
            if (classroom.classroomInfoProperty().get().toLowerCase().contains(search.toLowerCase()))
                searchItems.add(classroom);
        return searchItems;
    }


    public boolean editClassroom(Classroom classroom) {
        if (classrooms.contains(getClassroomById(classroom.nameProperty().get()))) {
            getClassroomById(classroom.nameProperty().get()).setCapacity(classroom.capacityProperty().get());
            getClassroomById(classroom.nameProperty().get()).setHdmi(classroom.hdmiProperty().get());
            getClassroomById(classroom.nameProperty().get()).setVga(classroom.vgaProperty().get());
            return true;
        }
        return false;
    }

    public ArrayList<Classroom> getValidClassrooms(String search, Course course) {
        ArrayList<Classroom> validClassrooms = new ArrayList<Classroom>();
        for (Classroom classroom : getClassroomsBySearch(search))
            if (classroom.capacityProperty().get() > (course != null ? course.numberOfStudentsProperty().get() : 0))
                validClassrooms.add(classroom);
        return validClassrooms;
    }

    public ArrayList<Classroom> getClassroomsById(ArrayList<String> unavailableClasses) {
        ArrayList<Classroom> items = new ArrayList<Classroom>();
        for(Classroom classroom : classrooms) {
            if(!unavailableClasses.contains(classroom.nameProperty().get()))
                items.add(classroom);
        }
        return items;
    }
}