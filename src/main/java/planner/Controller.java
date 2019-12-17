package planner;

//do not touch, it is ok for this to be empty

/**
 * Enables referencing to controllers from SearchThread
 */
public abstract class Controller {
    public abstract void getExaminers();

    public abstract void getCourses();

    public abstract void getClassrooms();

    public void getStudents() {

    }
}
