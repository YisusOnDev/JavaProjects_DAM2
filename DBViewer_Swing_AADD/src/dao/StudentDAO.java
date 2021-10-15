package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Student;

public class StudentDAO {
	private static final String CONNECTION = "jdbc:mysql://localhost/school";
	private static final String USER = "root";
	private static final String PWD = "";

	/**
	 * Method that gets all data from db and return an arraylist with all students.
	 * 
	 * @return an ArrayList<Student> with all students in db or null if query fails
	 */
	public ArrayList<Student> getAllStudents() {
		try (Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM alumno;")) {
			ArrayList<Student> studentList = new ArrayList<Student>();
			while (rs.next()) {
				Student student = new Student(rs.getInt("nummatricula"), rs.getString("nombre"),
						rs.getString("fechanacimiento"), rs.getString("telefono"));
				studentList.add(student);
			}

			return studentList;

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		// If SQL check failed
		return null;
	}

	/**
	 * Check if registration number already exists
	 * 
	 * @param regNum number to check
	 * @return true if is valid (does not exist) false if not valid (does exists)
	 */
	public boolean isValidRegistrationNumber(int regNum) {
		try (Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT 1 FROM alumno WHERE nummatricula = " + regNum)) {

			if (rs.next()) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}

		// If SQL check failed
		return false;
	}

	/**
	 * Method that based on an Student object does a query to insert it into
	 * database
	 * 
	 * @param student object
	 */
	public void insertStudent(Student student) {
		try {
			Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
			String query = " INSERT INTO alumno (nummatricula, nombre, fechanacimiento, telefono)"
					+ " VALUES ( ?, ?, ?, ?)";
			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);

			preparedStmt.setInt(1, student.getRegistrationNumber());
			preparedStmt.setString(2, student.getName());
			preparedStmt.setString(3, student.getBirthdate());
			preparedStmt.setString(4, student.getPhoneNumber());

			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Method to update an existing student parting from a student object
	 * 
	 * @param student    object
	 * @param identifier the unique identifier that the current student has (if
	 *                   changed somehow)
	 */
	public void updateStudent(Student student, int identifier) {
		try {
			Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
			String query = " UPDATE alumno SET nummatricula = ?, nombre = ?, fechanacimiento = ?, telefono = ?"
					+ " WHERE nummatricula = '" + identifier + "'";

			// create the mysql insert prepared statement
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, student.getRegistrationNumber());
			preparedStmt.setString(2, student.getName());
			preparedStmt.setString(3, student.getBirthdate());
			preparedStmt.setString(4, student.getPhoneNumber());

			preparedStmt.execute();

			conn.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
