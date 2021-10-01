package criticsection;

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
                System.out.println(
                        "Client with id " + clientId + " is hungry! \nTray burger count: " + burgersInTray + "\n");
                wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        burgersInTray = burgersInTray - 1;
        System.out.println("Client with id " + clientId + " has eaten a burger from tray.\nTray burger count: "
                + burgersInTray + "\n");
        notify();
    }

    public synchronized void addBurger(int chefId) {
        burgersInTray++;
        System.out.println(
                "Chef with id " + chefId + " finished one more buger.\nTray burger count: " + burgersInTray + "\n");
        notify();
    }
}
