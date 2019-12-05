package model.beans;

import planner.ExamController;

public class Counter extends Thread {

    private volatile String search;
    private long keyTime;
    private ExamController parentController;
    private volatile boolean allowRunning;

    public Counter(long keyTime, ExamController parentController, String search) {
        this.keyTime = keyTime;
        this.parentController = parentController;
        this.search = search;
        this.allowRunning = true;
    }

    public void run() {
        System.out.println("start");
        while (System.currentTimeMillis() - keyTime < 1000 && allowRunning) ;
        if (allowRunning)
            switch (search) {
                case "Students":
                    System.out.println("Search students");
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
                case "Exams":
                    System.out.println("Search exams");
                    break;
                default:
                    System.out.println("do nothing");
                    break;
            }
    }

    public void keyPressed() {
        allowRunning = false;
        System.out.println("stop");
    }
}
