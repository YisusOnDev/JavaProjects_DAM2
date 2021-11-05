package main;

import java.sql.*;
import java.util.Random;

public class MainApp {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/bd_neptuno2";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "";

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String SQL = "INSERT INTO categorias(categoria, descripcion) " + "VALUES(?, ?)";
			stmt = conn.prepareStatement(SQL);

			// Set auto-commit to false
			conn.setAutoCommit(false);

			// Make 20 inserts
			for (int i = 1; i < 5; i++) {
				stmt = conn.prepareStatement(SQL);
				
				// Generate 2 random words
				String[] words = generateRandomWords(2);
				// Set the variables
				stmt.setString(1, words[0]);
				stmt.setString(2, words[1]);
				String toprint = "Category: " + words[0] + ", Description " + words[1] + " added to batch";
				System.out.println(toprint);
				// Add it to the batch
				stmt.addBatch();
				
				words = generateRandomWords(2);
				stmt.setString(1, words[0]);
				stmt.setString(2, words[1]);
				toprint = "Category: " + words[0] + ", Description " + words[1] + " added to batch";
				System.out.println(toprint);
				stmt.addBatch();
				
				words = generateRandomWords(2);
				stmt.setString(1, words[0]);
				stmt.setString(2, words[1]);
				toprint = "Category: " + words[0] + ", Description " + words[1] + " added to batch";
				System.out.println(toprint);
				stmt.addBatch();

				words = generateRandomWords(2);
				stmt.setString(1, words[0]);
				stmt.setString(2, words[1]);
				toprint = "Category: " + words[0] + ", Description " + words[1] + " added to batch";
				System.out.println(toprint);
				stmt.addBatch();
				
				words = generateRandomWords(2);
				stmt.setString(1, words[0]);
				stmt.setString(2, words[1]);
				toprint = "Category: " + words[0] + ", Description " + words[1] + " added to batch";
				System.out.println(toprint);
				stmt.addBatch();
				
				System.out.println("Batch " + i + " executed \n");
				stmt.executeBatch();
			}
			
			conn.commit();
			System.out.println("Commited! \n");

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Finished!");
	}

	public static String[] generateRandomWords(int numberOfWords) {
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for (int i = 0; i < numberOfWords; i++) {
			char[] word = new char[random.nextInt(8) + 3];
			for (int j = 0; j < word.length; j++) {
				word[j] = (char) ('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}
		return randomStrings;
	}

}
