package threads;

import java.util.Random;

import criticsection.Tray;

/**
 * BurgerEater `AKA` Client Class
 */
public class BurgerEater extends Thread {
    private boolean _RunThread;
    private Tray burgerTray;
    private int eaterId;

    /**
     * Constructor of BurgerEater (AKA Client)
     * 
     * @param burgerTray Current tray thar is being used
     * @param eaterId    Thread id of current client (just for identification)
     */
    public BurgerEater(Tray burgerTray, int eaterId) {
        this.burgerTray = burgerTray;
        this.eaterId = eaterId;
        this._RunThread = true; // Flag to start a while loop when start() is called
    }

    @Override
    public void run() {
        // Infinite loop
        while (_RunThread) {
            // Simulate eating time
            int timeToEat = new Random().nextInt(3000 - 1000) + 1000;
            // int timeToEat = new Random().nextInt(2000 - 1) + 1;

            // We try to wait to take another burger and eat it
            try {
                Thread.sleep(timeToEat);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            burgerTray.eatBurger(eaterId);
        }
    }
}