package planner;

import javafx.scene.control.Alert;
import planner.PrimaryController;

public class LoadData extends Thread {

    private PrimaryController parentController;
    private long timeDelta = 1000;

    public LoadData(PrimaryController parentController) {
        this.parentController = parentController;
    }

    public void run() {
        int numberOfTries = 0;
        long startTime = System.currentTimeMillis();
        boolean stop = false;
        while (!stop && numberOfTries < 10) {
            while (System.currentTimeMillis() - startTime < timeDelta) ;
            try {
                parentController.updateData();
                numberOfTries++;
                stop = true;
            } catch (NullPointerException e) {
                startTime = System.currentTimeMillis();
            }
        }
        if (numberOfTries == 10) {
            System.out.println("Could not retrieve data from database, please check if the computer is connected to the internet, otherwise, contact system administrator.");
        } else
            System.out.println("Data retrieved from database");
    }
}

