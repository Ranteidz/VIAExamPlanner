package model.beans;

import planner.ExamController;

public class Counter extends Thread {

    private long keyTime;
    private ExamController parentController;
    private volatile boolean allowRunning;

    public Counter(long keyTime, ExamController parentController) {
        this.keyTime = keyTime;
        this.parentController = parentController;
        this.allowRunning = true;
    }

    public void run() {
        System.out.println("start");
        while (System.currentTimeMillis() - keyTime < 1000 && allowRunning) ;
        if (allowRunning)
            System.out.println("search"); //TODO get students from course-students where courseid = coursefield.getText();
    }

    public void keyPressed() {
        allowRunning = false;
        System.out.println("stop");
    }
}
