package planner;

import planner.Controller;

/**
 * Thread that makes sure search methods are not launched if a key is kept down
 */
public class SearchThread extends Thread {

    private volatile String search;
    private long keyTime;
    private Controller parentController;
    private volatile boolean allowRunning;
    private final int keyDelta = 50; //ms between keypresses so system does not search

    /**
     * Creates a new SearchThread
     * @param keyTime time at which a key has been pressed
     * @param parentController reference to controller in which thread is created
     * @param search search query
     */
    public SearchThread(long keyTime, Controller parentController, String search) {
        this.keyTime = keyTime;
        this.parentController = parentController;
        this.search = search;
        this.allowRunning = true;
    }

    /**
     * Waits for enough time to pass or for a key to be pressed before it calls a search method.
     */
    public void run() {
        while (System.currentTimeMillis() - keyTime < keyDelta && allowRunning) ;
        if (allowRunning)
            switch (search) {
                case "Students":
                    parentController.getStudents();
                    break;
                case "Examiners":
                    parentController.getExaminers();
                    break;
                case "Classrooms":
                    parentController.getClassrooms();
                    break;
                case "Courses":
                    parentController.getCourses();
                    break;
                default:
                    break;
            }
    }

    /**
     * stops the thread if a key is pressed so a search method is not started
     */
    public void keyPressed() {
        allowRunning = false;
    }
}
