package threads;

import java.util.Random;

import criticsection.Tray;

public class Chef extends Thread {
    private boolean _RunThread;
    private Tray burgerTray;
    private int chefId;

    public Chef(Tray burgerTray, int chefId) {
        this.burgerTray = burgerTray;
        this.chefId = chefId;
        this._RunThread = true;
    }

    @Override
    public void run() {
        while (_RunThread) {
            int timeToCook = new Random().nextInt(3000 - 1000) + 1000;

            try {
                Thread.sleep(timeToCook);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            burgerTray.addBurger(chefId);
        }
    }

}