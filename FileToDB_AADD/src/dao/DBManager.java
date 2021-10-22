package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.Subject;

import models.Teacher;

public class DBManager {

	private static final String CONNECTION = "jdbc:mysql://localhost/school";
	private static final String USER = "root";
	private static final String PWD = "";

	public void insertTeacherWithData(ArrayList<Object> list) {
		try {
			Connection conn = DriverManager.getConnection(CONNECTION, USER, PWD);
			try {
				// Assume a valid connection object conn
				conn.setAutoCommit(false);
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				boolean insertingTeacher = false;
		
				for (Object object : list) {
					if (object.getClass() == Teacher.class) {
						if (!insertingTeacher) {
							insertingTeacher = true;
							Teacher teacher = (Teacher) object;
							stmt = conn.prepareStatement("INSERT INTO profesor VALUES (" + teacher.getNif() + "," + teacher.getName() + "," + teacher.getSpecialty() + "," + teacher.getPhoneNumber() + ")", PreparedStatement.RETURN_GENERATED_KEYS);
							stmt.execute();
						}
					} else if (insertingTeacher && object.getClass() == Subject.class) {
						rs = stmt.getGeneratedKeys();
						System.out.println("Rs:");
						System.out.println(rs);
						
					}
				}
				
				
				String SQL = "INSERT INTO profesor " + "VALUES (106, 20, 'Rita', 'Tez')";
				stmt.executeUpdate(SQL);
				// Submit a malformed SQL statement that breaks
				String SQL2 = "INSERTED IN Employees  " + "VALUES (107, 22, 'Sita', 'Singh')";
				stmt.executeUpdate(SQL2);
				// If there is no error.
				conn.commit();
			} catch (SQLException se) {
				// If there is any error.
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
