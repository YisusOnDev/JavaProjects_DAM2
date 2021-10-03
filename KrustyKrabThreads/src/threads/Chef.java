package threads;

import java.util.Random;

import criticsection.Tray;

/**
 * Chef Class
 */
public class Chef extends Thread {
    private boolean _RunThread;
    private Tray burgerTray;
    private int chefId;

    /**
     * Constructor for our famous Chefs
     * 
     * @param burgerTray Current tray that is being used
     * @param chefId     Thread id of the chef (for identification only)
     */
    public Chef(Tray burgerTray, int chefId) {
        this.burgerTray = burgerTray;
        this.chefId = chefId;
        this._RunThread = true; // Flag to start a while loop when start() is called
    }

    @Override
    public void run() {
        // Infinite loop
        while (_RunThread) {
            // Simulate cook time
            int timeToCook = new Random().nextInt(3000 - 1000) + 1000;
            // int timeToCook = new Random().nextInt(2000 - 1) + 1;

            // We try to wait to take our time to cook burger and add it to the tray
            try {
                Thread.sleep(timeToCook);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            burgerTray.addBurger(chefId);
        }
    }

}