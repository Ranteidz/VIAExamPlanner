package model;

import planner.PrimaryController;

public class LoadData extends Thread {

    private PrimaryController parentController;
    private long timeDelta = 1000;

    public LoadData(PrimaryController parentController) {
        this.parentController = parentController;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        boolean stop = false;
        while(!stop) {
            while (System.currentTimeMillis() - startTime < timeDelta) ;
            try {
                parentController.updateData();
                stop = true;
            } catch (NullPointerException e) {
                startTime = System.currentTimeMillis();
            }
        }
    }
}

