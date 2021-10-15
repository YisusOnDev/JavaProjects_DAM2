package dao;

import java.sql.*;
import java.util.ArrayList;

public class UtilsDAO {
	private static final String URL = "jdbc:mysql://localhost:3306/";
	private static final String BBDD = "school";
	private static final String PARAMS = "?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PWD = "";

	public ArrayList<ArrayList<String>> fetch(String tableName) {

		try (Connection conn = DriverManager.getConnection(URL + BBDD + PARAMS, USER, PWD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)) {
			ArrayList<ArrayList<String>> data = new ArrayList<>();
			int columnCount = rs.getMetaData().getColumnCount();

			ArrayList<String> columnNames = new ArrayList<>();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(rs.getMetaData().getColumnName(i));
			}
			data.add(columnNames);

			while (rs.next()) {
				ArrayList<String> tempResult = new ArrayList<>();
				for (int i = 1; i <= columnCount; i++) {
					tempResult.add(rs.getString(i));
				}
				data.add(tempResult);
			}

			return data;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		// If SQL check failed
		return null;
	}
}
