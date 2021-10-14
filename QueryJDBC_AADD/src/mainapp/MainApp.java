package mainapp;

import java.util.ArrayList;

import dao.QueryDAO;
import io.Write;

public class MainApp {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<String>> dataFetched = new QueryDAO().fetch("address");
        if (Write.fetchedListToFile(dataFetched, "kekw.txt")) {
            System.out.println("Written succesfully");
        }

    }
}
