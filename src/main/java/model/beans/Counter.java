package model.beans;

import planner.ExamController;

public class Counter extends Thread {

    private long keyTime;
    private ExamController parentController;

    public Counter(long keyTime, ExamController parentController) {
        this.keyTime = keyTime;
        this.parentController = parentController;
    }

    public void run() {

        while(System.currentTimeMillis() - keyTime < 5000);
        System.out.println("search");
    }

    public void updateKeyTime(long keyTime) {
        this.keyTime = keyTime;
        System.out.println("search override");
        if(System.currentTimeMillis() - keyTime > 1000)
            start();
    }
}
