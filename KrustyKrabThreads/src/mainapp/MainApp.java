package mainapp;

import criticsection.Tray;
import threads.BurgerEater;
import threads.Chef;

public class MainApp {
    public static void main(String[] args) throws Exception {
        final int CHEF_COUNT = 10;
        final int CLIENT_COUNT = 20;
        
        Tray KrustyKrabTray = new Tray();
        
        Chef[] KrustyChefs = new Chef[CHEF_COUNT];
        BurgerEater[] KrustyClients = new BurgerEater[CLIENT_COUNT];
        
        for (int i = 0; i < KrustyChefs.length; i++) {
            KrustyChefs[i] = new Chef(KrustyKrabTray, i);
        }

        for (int i = 0; i < KrustyClients.length; i++) {
            KrustyClients[i] = new BurgerEater(KrustyKrabTray, i);
        }

        for (int i = 0; i < KrustyChefs.length; i++) {
            KrustyChefs[i].start();
        }
        
        for (int i = 0; i < KrustyClients.length; i++) {
            KrustyClients[i].start();
        }
            
    }
}
