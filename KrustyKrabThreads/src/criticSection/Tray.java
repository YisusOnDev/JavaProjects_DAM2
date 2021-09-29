package criticSection;

public class Tray {

    private int burgersInTray;

    /**
     * Constructor for Tray Just initialize burgersInTray setting it to 0.
     */
    public Tray() {
        burgersInTray = 0;
    }

    public synchronized void eatBurger(int clientId) {
        while (burgersInTray == 0) {
            try {
                System.out.println("Client with id " + clientId + " is hungry! \nTray burger count: " + burgersInTray);
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        burgersInTray = burgersInTray - 1;
        System.out.println("Client with id " + clientId + " has eaten a burger.\nTray burger count: " + burgersInTray);
        notify();
    }
}
