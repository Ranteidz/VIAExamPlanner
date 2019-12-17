package planner;

import javafx.scene.control.Alert;
import planner.PrimaryController;

/**
 * Thread to load all data when the program is starting.
 */
public class LoadData extends Thread {

    private PrimaryController parentController;
    private long timeDelta = 1000;

    /**
     * Constructor
     * @param parentController reference to controller in which the class is created
     */
    public LoadData(PrimaryController parentController) {
        this.parentController = parentController;
    }

    /**
     * updateData() throws NullPointerExceptions until all data is loaded into the program. This thread keeps trying to load the data until it manages to do so.
     */
    public void run() {
        long startTime = System.currentTimeMillis();
        boolean stop = false;
        while (!stop) {
            while (System.currentTimeMillis() - startTime < timeDelta) ;
            try {
                parentController.updateData();
                stop = true;
            } catch (NullPointerException e) {
                startTime = System.currentTimeMillis();
            }
        }
            System.out.println("Data retrieved from database");
    }
}

