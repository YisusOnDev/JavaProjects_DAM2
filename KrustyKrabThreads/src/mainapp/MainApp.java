package mainapp;

import criticsection.Tray;
import threads.BurgerEater;
import threads.Chef;

public class MainApp {
    public static void main(String[] args) throws Exception {
        // CHEF and CLIENTS Threads count.
        final int CHEF_COUNT = 100;
        final int CLIENT_COUNT = 100;

        // Generate an unique tray where burgers are taken from and added by the chefs.
        Tray KrustyKrabTray = new Tray();

        // Generate an array of chefs and clients threads.
        Chef[] KrustyChefs = new Chef[CHEF_COUNT];
        BurgerEater[] KrustyClients = new BurgerEater[CLIENT_COUNT];

        // Adding chef threads to the array
        for (int i = 0; i < KrustyChefs.length; i++) {
            KrustyChefs[i] = new Chef(KrustyKrabTray, i);
        }

        // Adding client threads to the array
        for (int i = 0; i < KrustyClients.length; i++) {
            KrustyClients[i] = new BurgerEater(KrustyKrabTray, i);
        }

        // Initialize chef and client threads.
        for (Chef chef : KrustyChefs) {
            chef.start();
        }

        for (BurgerEater burgerEater : KrustyClients) {
            burgerEater.start();
        }

    }
}
