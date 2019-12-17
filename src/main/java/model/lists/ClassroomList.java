package model.lists;

import model.classes.Classroom;
import model.classes.Course;

import java.util.ArrayList;

/**
 * Holds all classrooms and method to add, edit, remove and get classrooms.
 */
public class ClassroomList {

    private ArrayList<Classroom> classrooms;

    /**
     * Creates a new ClassroomList object.
     */
    public ClassroomList() {
        classrooms = new ArrayList<Classroom>();
    }

    /**
     * Returns all the classrooms stored in the object.
     * @return Arraylist of all classrooms
     */
    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    /**
     * Loads the parameter classrooms into array. Overwrites existing ones.
     * @param classrooms ArrayList of classrooms
     */
    public void loadClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    /**
     * Adds a classroom to the object if it does not exist already.
     * @param classroom classroom to be added
     * @return true if classroom added successfully
     */
    public boolean addClassroom(Classroom classroom) {
        if (!classrooms.contains(getClassroomById(classroom.nameProperty().get()))) {
            classrooms.add(classroom);
            return true;
        }
        return false;
    }

    /**
     * Returns a classroom by its ID
     * @param classroomId classroom ID
     * @return one classroom with parameter ID
     */
    public Classroom getClassroomById(String classroomId) {
        for (Classroom classroom : classrooms)
            if (classroomId.equalsIgnoreCase(classroom.nameProperty().get()))
                return classroom;
        return null;
    }

    /**
     * Removes a classroom from the a object if it exists
     * @param classroom classroom to be removed
     * @return true if removed successfully
     */
    public boolean removeClassroom(Classroom classroom) {
        if (classrooms.contains(classroom)) {
            classrooms.remove(classroom);
            return true;
        }
        return false;
    }

    /**
     * Returns all classroom which contain search information
     * @param search search information
     * @return classrooms which fulfill search
     */
    public ArrayList<Classroom> getClassroomsBySearch(String search) {
        ArrayList<Classroom> searchItems = new ArrayList<Classroom>();
        for (Classroom classroom : classrooms)
            if (classroom.classroomInfoProperty().get().toLowerCase().contains(search.toLowerCase()))
                searchItems.add(classroom);
        return searchItems;
    }

    /**
     * Returns all classroom which containt search information from ArrayList of classrooms sent as parameter
     * @param search search information
     * @param classrooms ArrayList to select information from
     * @return classrooms which fullfill search
     */
    public static ArrayList<Classroom> getClassroomsBySearch(String search, ArrayList<Classroom> classrooms) {
        ArrayList<Classroom> searchItems = new ArrayList<Classroom>();
        for (Classroom classroom : classrooms)
            if (classroom.classroomInfoProperty().get().toLowerCase().contains(search.toLowerCase()))
                searchItems.add(classroom);
        return searchItems;
    }

    /**
     * Edits a classroom.
     * @param classroom edited classroom object
     * @return true if edit successfully
     */
    public boolean editClassroom(Classroom classroom) {
        if (classrooms.contains(getClassroomById(classroom.nameProperty().get()))) {
            getClassroomById(classroom.nameProperty().get()).setCapacity(classroom.capacityProperty().get());
            getClassroomById(classroom.nameProperty().get()).setHdmi(classroom.hdmiProperty().get());
            getClassroomById(classroom.nameProperty().get()).setVga(classroom.vgaProperty().get());
            return true;
        }
        return false;
    }

    /**
     * Returns all classrooms which can fit a specific course and meet search condition
     * @param search search information
     * @param course selected course
     * @return classroom which meet conditions
     */
    public ArrayList<Classroom> getValidClassrooms(String search, Course course) {
        ArrayList<Classroom> validClassrooms = new ArrayList<Classroom>();
        for (Classroom classroom : getClassroomsBySearch(search))
            if (classroom.capacityProperty().get() > (course != null ? course.numberOfStudentsProperty().get() : 0))
                validClassrooms.add(classroom);
        return validClassrooms;
    }

    /**
     * Returns the available classrooms based on list of ids of unavailable classrooms.
     * @param unavailableClasses ArrayList of String of unavailable classrooms
     * @return ArrayList of classroom of available classrooms
     */
    public ArrayList<Classroom> getClassroomsById(ArrayList<String> unavailableClasses) {
        ArrayList<Classroom> items = new ArrayList<Classroom>();
        for(Classroom classroom : classrooms) {
            if(!unavailableClasses.contains(classroom.nameProperty().get()))
                items.add(classroom);
        }
        return items;
    }
}