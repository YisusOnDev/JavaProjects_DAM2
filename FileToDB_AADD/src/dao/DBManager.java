package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Subject;
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
				boolean firstTime = true;
				int autoId = -1;

				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getClass() == Teacher.class) {
						if (!firstTime)
							conn.commit();

						firstTime = false;
						Teacher teacher = (Teacher) list.get(i);
						stmt = conn.prepareStatement(
								"INSERT INTO profesor (nif_p, nombre, especialidad, telefono) VALUES (?, ?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS);
						stmt.setString(1, teacher.getNif());
						stmt.setString(2, teacher.getName());
						stmt.setString(3, teacher.getSpecialty());
						stmt.setString(4, teacher.getPhoneNumber());
						stmt.execute();

					} else if (list.get(i).getClass() == Subject.class) {
						Subject subject = (Subject) list.get(i);
						rs = stmt.getGeneratedKeys();
						rs.next();
						autoId = rs.getInt(1);
						PreparedStatement subjectInsert = conn.prepareStatement(
								"INSERT INTO asignatura (codasignatura, nombre, idprofesor) VALUES (?, ?, ?)",
								PreparedStatement.RETURN_GENERATED_KEYS);
						subjectInsert.setString(1, subject.getCode());
						subjectInsert.setString(2, subject.getName());
						subjectInsert.setInt(3, autoId);
						subjectInsert.execute();
					}
				}
				conn.commit();

			} catch (SQLException se) {
				// If there is any error.
				System.out.println(se);
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
