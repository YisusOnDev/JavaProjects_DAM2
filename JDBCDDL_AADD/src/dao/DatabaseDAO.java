package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String PARAMS = "?serverTimezone=UTC";
    private static String BBDD = "";
    private static final String USER = "root";
    private static final String PWD = "";

    /**
     * Method that connect to the server and create a database with the given name
     * 
     * @param databaseName the name of the database to create
     * @return true if ok false if fails
     */
    public boolean createDatabase(String databaseName) {
        try (Connection conn = DriverManager.getConnection(URL + PARAMS, USER, PWD);
                Statement stmt = conn.createStatement();) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;

            stmt.executeUpdate(sql);

            BBDD = databaseName; // We set current db to the latest we create
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method that create tables into database from an ArrayList of queries (string)
     * 
     * @param createList an ArrayList<String> with full create queries inside.
     * @return true if ok false if fails
     */
    public boolean createTable(ArrayList<String> createList) {
        try (Connection conn = DriverManager.getConnection(URL + BBDD + PARAMS, USER, PWD);
                Statement stmt = conn.createStatement();) {

            for (String query : createList) {
                stmt.executeUpdate(query);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
