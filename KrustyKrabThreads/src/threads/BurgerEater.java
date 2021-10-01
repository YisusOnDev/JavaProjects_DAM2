package threads;

import java.util.Random;

import criticsection.Tray;

public class BurgerEater extends Thread {
    private boolean _RunThread;
    private Tray burgerTray;
    private int eaterId;

    public BurgerEater(Tray burgerTray, int eaterId) {
        this.burgerTray = burgerTray;
        this.eaterId = eaterId;
        this._RunThread = true;
    }

    @Override
    public void run() {
        while (_RunThread) {

            burgerTray.eatBurger(eaterId);
            int timeToEat = new Random().nextInt(3000 - 1000) + 1000;

            try {
                Thread.sleep(timeToEat);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}