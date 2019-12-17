package planner;

import planner.Controller;

public class SearchThread extends Thread {

    private volatile String search;
    private long keyTime;
    private Controller parentController;
    private volatile boolean allowRunning;
    private final int keyDelta = 50; //ms between keypresses so system does not search

    public SearchThread(long keyTime, Controller parentController, String search) {
        this.keyTime = keyTime;
        this.parentController = parentController;
        this.search = search;
        this.allowRunning = true;
    }

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

    public void keyPressed() {
        allowRunning = false;
    }
}
