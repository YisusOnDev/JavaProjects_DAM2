package io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Write {

    /**
     * Print fechedList to a file as text (line per line)
     * 
     * @param fetchedList ArrayList<ArrayList<String>> lines of data
     * @param filePath    filepath where to write file
     * @return true if done false if fails
     */
    public static boolean fetchedListToFile(ArrayList<ArrayList<String>> fetchedList, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (ArrayList<String> s : fetchedList) {
                fileWriter.write(s.toString());
                fileWriter.write(System.getProperty("line.separator"));

            }
            fileWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
