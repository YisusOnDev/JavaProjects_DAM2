package criticsection;

public class Tray {

    private int burgersInTray;

    /**
     * Constructor for Tray Just initialize burgersInTray setting it to 0.
     */
    public Tray() {
        burgersInTray = 0;
    }

    /**
     * Synchronized function that just simulate a client finishing his burger.
     * 
     * @param clientId just an unique int that indicates the id of the client that
     *                 is eating the burguer (also means clientThreadId)
     */
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

    /**
     * Synchronized function that just simulate a chef finishing a burguer and
     * adding it to tray.
     * 
     * @param chefId just an unique int that indicates the id of the chef that is
     *               cooking the burger and adding it to the tray (also means
     *               chefThreadId)
     */
    public synchronized void addBurger(int chefId) {
        burgersInTray++;
        System.out.println(
                "Chef with id " + chefId + " finished one more buger.\nTray burger count: " + burgersInTray + "\n");
        notify();
    }
}
